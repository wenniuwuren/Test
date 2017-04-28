package com.wenniuwuren.java8.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * 处理 传入一个泛型，返回 boolean 的情况
 * Created by hzzhuyibin on 2017/4/26.
 */
public class PredicateTest {


    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3);
        List<Integer> result1 = new ArrayList<>();
        for (Integer i : list) {
            if (i > 2)
                result1.add(i);
        }
        System.out.println(result1.size());

        List<Integer> result2 = new ArrayList<>();
        result2 = processIntegerList(list, (i) -> i > 2);
        System.out.println(result2.size());
    }

    public static List<Integer> processIntegerList(List<Integer> list, Predicate<Integer> p) {
        List<Integer> result = new ArrayList<>();
        for (Integer i : list) {
            if (p.test(i))
                result.add(i);
        }
        return result;
    }
}

