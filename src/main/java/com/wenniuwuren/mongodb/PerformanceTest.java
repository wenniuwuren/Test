package com.wenniuwuren.mongodb;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.*;
import com.wenniuwuren.mongodb.utils.MongoUtil;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * mongodb.properties
 *
 * 插入（3个分片）
 *           1000 次      10000 次
 * 单线程：   30个/s       30个/s
 * 10线程：    85           75
 * 100 ：     333          476
 * 1000：     500          454
 *
 *
 * 查询（3个分片，带着分片键查询）
 *           1000次    10000次
 *10线程：   1189个/s      3600个/s
 *100线程：  957           3000      (感觉速度下降是因为线程切换频繁)
 *1000线程： 超过MongoDB线程池数量，开始报错
 */
public class PerformanceTest {

    private static final int THREAD_COUNT = 100;
    private static final int EXECUTE_TIMES = 1000;

    private static ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);

    private static CountDownLatch countDownLatch = new CountDownLatch(EXECUTE_TIMES);

    public static void main(String[] args) {

        final String dbName = "test";
        final String primaryMessageCollectionName = "primary_message";
        final String secondaryMessageCollectionName = "sencondary_message";
        final String messageCollectionName = "all_in_one_message";
        final int primaryType = 1;
        int subType = 2;
        final DB db = MongoUtil.getDB(dbName);
        initPrimaryMessageCollection(db, messageCollectionName);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < EXECUTE_TIMES; i++) {
            final int index = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + ", 正在执行" + index);
                    insertPrimaryMessage(db, primaryMessageCollectionName, index + "", "活动", "买袜子送汽车", 1,
                            primaryType, countDownLatch);

//                    DBObject dbObject = findPrimaryMessage(db, primaryMessageCollectionName, index + "");
//                    System.out.println("uid:" + dbObject.get("uid"));

//            insertSecondaryMessage(db, secondaryMessageCollectionName, i + "", primaryType, "messageId" + i, subType, "打折啦啊");

//            insertMessage(db, messageCollectionName, i + "", "活动", "买袜子送汽车", 1, primaryType, "messageId" + i, subType, "打折啦啊");
                }
            });

        }

        try {
            countDownLatch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }

        /**
         *
         * 不知道要执行任务有多少时，用如下方法等待所有任务执行完成。
         * 这个方法有个「不好」的地方就是会把线程池关闭掉，那么线程池在线上就不能复用了。
         *
        executorService.shutdown();
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
         */

        System.out.println("cost : " + (System.currentTimeMillis() - startTime));

    }

    private static DBObject findPrimaryMessage(DB db, String messageCollectionName, String uid) {

        BasicDBObject dbObject = new BasicDBObject();
        dbObject.put("uid", uid);
        DBObject result = MongoUtil.getCollection(db, messageCollectionName).findOne(dbObject);
        countDownLatch.countDown();
        return result;
    }

    private static void insertMessage(DB db, String messageCollectionName, String uid, String title, String content,
                                      int unreadCount, int primaryType, String messageId, int subType, String messageData) {
        List<Object> list = new LinkedList<Object>();

        JSONObject primaryObject = new JSONObject();
        primaryObject.put("uid", uid);
        primaryObject.put("title", title);
        primaryObject.put("content", content);
        primaryObject.put("unreadCount", unreadCount);
        primaryObject.put("primaryType", primaryType);

        List<Object> sublist = new LinkedList<Object>();
        JSONObject subObject = new JSONObject();
        subObject.put("messageId", messageId);
        subObject.put("subType", subType);
        subObject.put("messageData", messageData);
        JSONObject subObject1 = new JSONObject();
        subObject1.put("messageId", messageId+"2");
        subObject1.put("subType", 2);
        subObject1.put("messageData", messageData);
        sublist.add(1, subObject);
        sublist.add(2, subObject1);
        primaryObject.put("subContent", JSON.toJSONString(sublist));

        list.add(1, primaryObject);

        DBObject dbObject = new BasicDBObject();
        dbObject.put("data", JSON.toJSONString(list));
        MongoUtil.getCollection(db, messageCollectionName).insert(dbObject);

    }

    private static void insertPrimaryMessage(DB db, String primaryMessageCollectionName, String uid,
                                             String title, String content, int unreadCount, int primaryType, CountDownLatch countDownLatch) {
        initPrimaryMessageCollection(db, primaryMessageCollectionName);
        List<Object> list = new LinkedList<Object>();
        DBObject dbObject = new BasicDBObject();
        dbObject.put("uid", uid);
        dbObject.put("title", title);
        dbObject.put("content", content);
        dbObject.put("unreadCount", unreadCount);

        list.add("primaryType1");
        list.add("primaryType2");
        list.add("primaryType3");
        dbObject.put("primaryTypeList", JSON.toJSONString(list));

        MongoUtil.getCollection(db, primaryMessageCollectionName).insert(dbObject);
        countDownLatch.countDown();
    }

    private static void insertSecondaryMessage(DB db, String secondaryMessageCollectionName, String uid,
                                             int primaryType, String messageId, int subType, String messageData) {
        initPrimaryMessageCollection(db, secondaryMessageCollectionName);
        DBObject dbObject = new BasicDBObject();
        dbObject.put("uid", uid);
        dbObject.put("primaryType", primaryType);
        dbObject.put("messageId", messageId);
        dbObject.put("subType", subType);
        dbObject.put("messageData", messageData);
        MongoUtil.getCollection(db, secondaryMessageCollectionName).insert(dbObject);

    }

    private static synchronized void initPrimaryMessageCollection(DB db, String collection) {

        if (!db.collectionExists(collection)) {
            DBCollection dbCollection = db.getCollection(collection);

            // 业务索引
            DBObject dbObj = new BasicDBObject();
            dbObj.put("uid", 1);
            dbObj.put("unique", true);
            dbCollection.createIndex(dbObj);

            CommandResult result = dbCollection.getStats();
            if (!result.getBoolean("sharded")) {
                DB adminDb = MongoUtil.getDB("admin");
                result = adminDb.command(new BasicDBObject("enableSharding", db
                        .getName()));
                if (!result.ok()) {
                    // 判断DB是否已经开启分片机制, 注意这里的判断很弱
                    if (!result.getErrorMessage().contains("already")) {
                        throw new IllegalStateException(
                                "enable db sharding failed. database=" + db.getName());
                    }
                }

                DBObject cmd = new BasicDBObject();
                cmd.put("shardcollection", dbCollection.getFullName());
                cmd.put("key", new BasicDBObject("uid", "hashed"));
                result = adminDb.command(cmd);
                if (!result.ok()) {
                    throw new IllegalStateException(
                            "shard collection failed. collection="
                                    + dbCollection.getFullName());
                }
            }
        }
    }
}

