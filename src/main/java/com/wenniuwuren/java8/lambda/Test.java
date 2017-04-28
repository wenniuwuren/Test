package com.wenniuwuren.java8.lambda;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *  Created by hzzhuyibin on 2017/4/24.
 */
public class Test {

    public static void main(String[] args) {
        // java8 之前不能传递方法,只能借助一个接口或者匿名内部类，但是核心只有方法里的代码
        // 查找一个目录中所有隐藏的文件
        File[] hiddenFiles = new File("D:\\logistics-dsc").listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isHidden();
            }
        });
        for (int i = 0; i < hiddenFiles.length; i++) {
            System.out.println(hiddenFiles[i].getName());
        }
        // 使用 Java8 的引用方法「::」语法，即把这个方法作为值
        File[] hiddenFiles8 = new File("D:\\logistics-dsc").listFiles(File::isHidden);
//        File[] hiddenFiles8 = new File("D:\\logistics-dsc").listFiles((path) -> path.isHidden());
        for (int i = 0; i < hiddenFiles8.length; i++) {
            System.out.println(hiddenFiles8[i].getName());
        }



        // Java8 Lambda 表达式, Lambda表达式在Java中又称为闭包或匿名函数。用来简化代码，而不是改变功能。
        // 在哪里使用 Lambda ? 在函数式接口(@FunctionalInterface)上使用：只定义一个抽象方法的接口(除了 default默认实现，equal，toString 外)，
        // 如 Comparator,Runnable,Callable等，Lambda 提供了函数式接口中抽象方法的匿名实现，
        // 例如实现 Runnable，就要对应 run 没有参数，没有返回值 () -> System.out.println("");
        // 1. 替代匿名内部类
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("before java 8 too much useless code");
            }
        }).start();
        /**
         * lambda 表达式格式
         * (params) -> expression (params)
         *          -> statement (params)
         *          -> { statements; } 注意这里的花括号，如 {return true;} 一个完整语句显式返回
         */
        new Thread(() -> System.out.println("in java 8 lambda expression is clean")).start();
        // 2.对列表进行迭代
        List<String> features = Arrays.asList("a", "b", "c");
        for (String feature: features) {
            System.out.println(feature);
        }
        // 可以省略这里的lambda参数的类型声明，因为编译器可以从列表的类属性推测出来
        features.forEach((n) -> System.out.println(n));
        // lambda表达式内可以使用方法引用，仅当该方法不修改lambda表达式提供的参数
        // 本例中的lambda表达式可以换为方法引用，因为这仅是一个参数相同的简单方法调用
        features.forEach(System.out::println);
        // 3. 函数式接口Predicate，数学上叫谓词
        List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");

        System.out.println("Languages which starts with J :");
        filter(languages, (str) -> str.startsWith("J"));

        System.out.println("Print all languages :");
        filter(languages, (str) -> true);

        System.out.println("startWithJ and endWithA");
        Predicate<String> startWithJ = (str) -> str.startsWith("J");
        Predicate<String> endWithA = (str) -> str.endsWith("a");
        filter(languages, startWithJ.and(endWithA));
        // 3. map:应用到流中的每一个元素; reduce:聚合操作，类似SQL的 sum，average
        // 将每个订单+12%的税，并计算总和
        List<Integer> costBeforeTest = Arrays.asList(10, 20, 30, 40);
        double total = 0;
        for (int i = 0; i < costBeforeTest.size(); i++) {
            double newCost = costBeforeTest.get(i) + 0.12 * costBeforeTest.get(i);
            total += newCost;
        }
        System.out.println("total="+ total);
        double java8Total = costBeforeTest.stream().map((cost) -> cost + 0.12*cost).reduce((sum, cost) -> sum + cost).get();
        System.out.println("java8Total=" + java8Total);
        // 4. 使用 Collection collect()
        // 将字符串换成大写并用逗号链接起来
        List<String> G7 = Arrays.asList("USA", "Japan", "France", "Germany", "Italy", "U.K.","Canada");
        String G7Countries = G7.stream().map(x -> x.toUpperCase()).collect(Collectors.joining(", "));
        System.out.println(G7Countries);
        // 计算集合元素的最大值、最小值、总和以及平均值
        List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
        IntSummaryStatistics stats = primes.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println("Highest prime number in List : " + stats.getMax());
        System.out.println("Lowest prime number in List : " + stats.getMin());
        System.out.println("Sum of all prime numbers : " + stats.getSum());
        System.out.println("Average of all prime numbers : " + stats.getAverage());


        // Java8 默认方法。在接口里有 default 关键字修饰的默认方法，这样其他实现类不需要都实现这个新加的接口方法

        // Java8 Option<T> 类，可以帮助避免出现 NullPointer

    }

    // Predicate 返回 true or false
    public static void filter(List<String> names, Predicate<String> condition) {
        for (String name: names) {
            if (condition.test(name)) {
                System.out.println(name + " ");
            }
        }
    }

}
