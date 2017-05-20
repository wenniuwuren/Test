package com.wenniuwuren.java8.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by hzzhuyibin on 2017/4/28.
 */
public class TransactionTest {

    static final Trader zhangsan = new Trader("zhangsan", "DongBei");
    static final Trader lisi = new Trader("lisi", "BeiJing");
    static final Trader wangwu = new Trader("wangwu", "BeiJing");

    static final List<Transaction> transactions = Arrays.asList(
            new Transaction(zhangsan, 2011, 300),
            new Transaction(lisi, 2011, 400),
            new Transaction(wangwu, 2013, 1000),
            new Transaction(wangwu, 2013, 2000)
    );

    public static void main(String[] args) {
        // 1. 找出 2011 年发生的所有交易，并按交易额排序（从低到高）
//        List<Transaction> transactionList = transactions.stream().filter(transaction -> transaction.getYear() == 2011)
//                .sorted(Comparator.comparing(transaction -> transaction.getValue())).collect(Collectors.toList());
        List<Transaction> transactionList = transactions.stream().filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue)).collect(Collectors.toList());
        System.out.println("找出 2011 年发生的所有交易，并按交易额排序（从低到高）" + transactionList);

        // 2. 交易员都在哪些不同城市工作过?
        List<String> distinctCity = transactions.stream().map(transactions -> transactions.getTrader().getCity())
                .distinct().collect(Collectors.toList());
        System.out.println("交易员都在哪些不同城市工作过?" + distinctCity);

        // 3. 查找所有来自于 BeiJing 的交易员，并按姓名排序
        List<Trader> beiJingTrader = transactions.stream().map(transactions -> transactions.getTrader()).distinct()
                .filter(trader -> "BeiJing".equals(trader.getCity()))
                .sorted(Comparator.comparing(trader -> trader.getName())).collect(Collectors.toList());
        System.out.println("查找所有来自于 BeiJing 的交易员，并按姓名排序:" + beiJingTrader);


        // 4. 返回所有交易员的姓名字符串，并按字母顺序排序
        // 最后reduce 反复拼接字符串较低效
        String traderNameStr = transactions.stream().map(transactions -> transactions.getTrader().getName())
                .peek(x -> System.out.println("after map " + x))
                .distinct()
                .peek(x -> System.out.println("after distinct " + x))
                .sorted()
                .peek(x -> System.out.println("after sort " + x))
                .reduce("", (n1, n2) -> n1 + n2);
//        String traderNameStr = transactions.stream().map(transactions -> transactions.getTrader().getName())
//                .distinct().sorted().collect(joinning());
        System.out.println("返回所有交易员的姓名字符串，并按字母顺序排序:" + traderNameStr);

        // 5. 有没有交易员是来自 DongBei 工作的
        boolean isTraderComeFromDongBei = transactions.stream()
                .anyMatch(transaction -> transaction.getTrader().getCity().equals("DongBei"));
        System.out.println("有没有交易员是来自 DongBei 工作的:" + isTraderComeFromDongBei);

        // 6. 打印生活在 BeiJing 的交易员的所有交易额
        List<Integer> tradeAmount = transactions.stream()
                .filter(transactions -> "BeiJing".equals(transactions.getTrader().getCity()))
                .map(transaction -> transaction.getValue()).collect(Collectors.toList());
        System.out.println("打印生活在 BeiJing 的交易员的所有交易额:" + tradeAmount);

        // 7. 在所有交易中，最高的交易额是多少
        Integer maxTradeAmount = transactions.stream().map(t -> t.getValue()).reduce((a, b) -> a > b ? a : b).get();
        System.out.println("在所有交易中，最高的交易额是多少?" + maxTradeAmount);

        // 8. 交易额最小的交易
        Transaction minTransaction = transactions.stream().reduce((a, b) -> a.getValue() < b.getValue() ? a : b).get();
        System.out.println("交易额最小的交易:" + minTransaction);
    }

}

