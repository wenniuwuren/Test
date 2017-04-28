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
    }

}

