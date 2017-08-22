package com.wenniuwuren.redis;

import com.wenniuwuren.Constants;
import org.apache.commons.lang.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ZParams;

import java.util.*;

public class JedisUtil {

    private static int ONE_WEEK_IN_SECONDS = 7 * 86400;
    private static int VOTE_SCORE = 432;
    private static int ARTICLES_PER_PAGE = 25;

    public static void main(String[] args) {
        Jedis jedis = new Jedis(Constants.REDIS_HOST_COMPANY);

        jedis.zadd("zset1", 1, "a");
        jedis.zadd("zset1", 2, "b");

        jedis.zadd("zset2", 2, "b");
        jedis.zadd("zset2", 3, "d");

        String[] zsets = new String[]{"zset1", "zset2"};
        // 交集
        System.out.println(jedis.zinterstore("zset3", zsets));
        System.out.println(jedis.zrange("zset3", 0, -1));
        // 并集
        System.out.println(jedis.zunionstore("zset4", zsets));
        System.out.println(jedis.zrange("zset4", 0, -1));


    }

    // 对文章进行投票
    private void articleVote(Jedis conn, String user, String article) {
        // 计算文章的投票截止时间
        long cutoff = new Date().getTime() / 1000 - ONE_WEEK_IN_SECONDS;

         /*检查是否还可以对文章进行投票
        （虽然使用散列也可以获取文章的发布时间，
         但有序集合返回的文章发布时间为浮点数，
         可以不进行转换直接使用）。*/
        if (conn.zscore("time:", article) < cutoff){
            return;
        }

        // 从article:id标识符（identifier）里面取出文章的ID
        String articleId = article.substring(article.indexOf(":") + 1);

        // 如果用户是第一次为这篇文章投票，那么增加这篇文章的投票数量和评分。
        if (conn.sadd("voted:" + articleId, user) == 1) { // 标记用户已投票
            conn.zincrby("score:", VOTE_SCORE, article);
            conn.hincrBy(article, "votes", 1);
        }

    }

    // 发布文章
    public String postArticle(Jedis conn, String user, String title, String link) {
        // 生成一个新的文章ID，使用 redis 的计数器
        String articleId = String.valueOf(conn.incr("article:"));

        String voted = "voted:" + articleId;

        // 将发布文章的用户添加到文章的已投票用户名单里面，
        // 然后将这个名单的过期时间设置为一周
        conn.sadd(voted, user);
        conn.expire(voted, ONE_WEEK_IN_SECONDS);

        // 将文章信息存储到一个散列里面。
        long currentTime = System.currentTimeMillis() / 1000;
        String article = "article:" + articleId;
        HashMap<String,String> articleData = new HashMap<String,String>();
        articleData.put("title", title);
        articleData.put("link", link);
        articleData.put("user", user);
        articleData.put("now", String.valueOf(currentTime));
        articleData.put("votes", "1");
        conn.hmset(article, articleData);

        // 将文章添加到根据发布时间排序的有序集合和根据评分排序的有序集合里面。
        conn.zadd("time:", currentTime, article);
        conn.zadd("score:", currentTime + VOTE_SCORE, article); // 相当于自己给自己点一次赞

        return articleId;
    }

    // 获取评分最高文章或者最新发布的文章
    public List<Map<String,String>> getArticles(Jedis conn, int page, String order) {
        // 设置获取文章的起始索引和结束索引。
        int start = (page - 1) * ARTICLES_PER_PAGE;
        int end = start + ARTICLES_PER_PAGE - 1;

        // 获取多个文章ID。
        Set<String> ids = conn.zrevrange(order, start, end);
        List<Map<String,String>> articles = new ArrayList<Map<String,String>>();
        // 根据文章ID获取文章的详细信息。
        for (String id : ids){
            Map<String,String> articleData = conn.hgetAll(id);
            articleData.put("id", id);
            articles.add(articleData);
        }

        return articles;
    }

    // 将文章加入组
    public void addGroups(Jedis conn, String articleId, String[] toAdd) {
        String article = "article:" + articleId;
        // 将文章添加到它所属的群组里面
        for (String group : toAdd) {
            conn.sadd("group:" + group, article);
        }
    }

    // 获取组中的文章
    public List<Map<String,String>> getGroupArticles(Jedis conn, String group, int page, String order) {
        // 为每个群组的每种排列顺序都创建一个键
        String key = order + group;
        // 检查是否有已缓存的排序结果，如果没有的话就现在进行排序。
        if (!conn.exists(key)) {
            ZParams params = new ZParams().aggregate(ZParams.Aggregate.MAX);
            // 根据评分或者发布时间，对群组文章进行排序
            conn.zinterstore(key, params, "group:" + group, order);
            // 让Redis在60秒钟之后自动删除这个有序集合
            conn.expire(key, 60);
        }
        // 调用之前定义的get_articles()函数来进行分页并获取文章数据。
        return getArticles(conn, page, key);
    }

}