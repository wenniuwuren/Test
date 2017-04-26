package com.wenniuwuren.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * 应用于 传入一个泛型，返回 void
 * Created by hzzhuyibin on 2017/4/26.
 */
public class ConsumerTest {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3);
        for (Integer i : list) {
                System.out.println(i);
        }
        System.out.println("-------------------");

        consumerProcess(list, (i) -> System.out.println(i));

    }

    public static void consumerProcess(List<Integer> list, Consumer<Integer> consumer) {
        for (Integer i : list) {
            consumer.accept(i);
        }
    }
}

