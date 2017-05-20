package com.wenniuwuren.java8.stream;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 *
 * Created by hzzhuyibin on 2017/5/3.
 */
public class OtherStream {

    static List<Integer> integerList = Arrays.asList(1, 2, 3);

    public static void main(String[] args) {
        /*******
         *  数值流(从数值生成流)
         */

        // mapToInt()[mapToDouble, mapToLong] 不会装箱成 Stream<Integer>，而是 IntStream
        IntStream intStream = integerList.stream().mapToInt(n -> n);
        intStream.max();

        // 装箱
//        Stream<Integer> integerStream = intStream.boxed();

        // 1-100 中的偶数个数， 而 range 不包含结束值是1-99
        IntStream intStream1 = IntStream.rangeClosed(1, 100).filter(n -> n % 2 == 0);
        System.out.println("1-100 中的偶数个数" + intStream1.count());

        // 勾股数 box()是为了最后返回int[]  流中有流使用flapMap可以合并为一个流
        Stream<int[]> pythagoreanTriples = IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100).filter(b -> Math.sqrt(a*a + b*b) % 1 == 0).boxed()
                        .map(b -> new int[]{a, b, (int)Math.sqrt(a*a + b*b)}));
        pythagoreanTriples.limit(5).forEach(t -> System.out.println(t[0] + ", " + t[1] + ", " + t[2]));


        /*******
         *  从值创建流
         */
        // 使用静态方法 Stream.of显式创建一个流，可接受任意数量的参数
        Stream<String> stream = Stream.of("JDK7", "JDK8", "JDK9");
        stream.map(String::toUpperCase).forEach(System.out::println);

        /*******
         *  从数组创建流
         */
        int[] nums = {7, 8, 9};
        int sum = Arrays.stream(nums).sum();

        /*******
         *  由文件生成流
         */
        long uniqueWords = 0;
        try (Stream<String> lines = Files.lines(Paths.get("C:\\Windows\\System32\\drivers\\etc\\hosts"))) {
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" "))).distinct().count();
            System.out.println("不同的单词有多少个？" + uniqueWords);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*******
         *  创建无限流
         */
        // 迭代：斐波那契数列 iterate不适合并行化，因为其他值不确定，无法fork
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]}).limit(5)
                .forEach(t -> System.out.println("[" + t[0] + "," + t[1] + "]"));

        // 生成: parallel()并行，内部默认使用了ForkJoinPool; sequential()顺序
        Stream.generate(Math::random).parallel().limit(5).forEach(System.out::println);
        // 通过下面这个来改变线程池大小，默认线程数量就是处理器数量，当前只能全局改变
//        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "12");
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}

