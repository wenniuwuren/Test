package com.wenniuwuren.java8.lambda;

import org.apache.commons.collections.map.HashedMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * 接受一个泛型T，返回一个泛型R。类似做一种映射
 * Created by hzzhuyibin on 2017/4/26.
 */
public class FunctionTest {

    // 输入字符串，输出字符串长度
    public static void main(String[] args) {
        List<String> stringList = Arrays.asList("a", "aa", "aaa");

        // List<Integer> result = strLengthFuction(stringList, (s) -> s.length());
        // 方法引用方式，更简洁
        List<Integer> result = strLengthFuction(stringList, String::length);
        for (Integer i : result) {
            System.out.println(i);
        }
    }

    public static List<Integer> strLengthFuction(List<String> stringList, Function<String, Integer> f) {
        List<Integer> result = new ArrayList<>();
        for (String s : stringList) {
            result.add(f.apply(s));
        }
        return result;
    }
}

