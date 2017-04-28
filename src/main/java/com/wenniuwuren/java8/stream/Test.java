package com.wenniuwuren.java8.stream;

import static java.util.stream.Collectors.toList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by hzzhuyibin on 2017/4/28.
 */
public class Test {

    static List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH)
            );

    /**
     * 结果：
     * filter pork
     * map pork
     * filter beef
     * map beef
     * filter chicken
     * map chicken
     * [pork, beef, chicken]
     * 从结果可以看出几种优化手段：
     * 1.只选出了前 3 个，这是因为 limit和短路
     * 2.filter 和 map 是两个独立操作，但它们合并到同一次遍历（循环合并）
     * @param args
     */
    public static void main(String[] args) {
        // 打印出每步操作了什么
        List<String> threeHighCaloricDishNames = menu.stream().
                filter(d ->
                {
                    System.out.println("filter " + d.getName());
                    return d.getCalories() > 300;
                }).map(d ->
                {
                    System.out.println("map " + d.getName());
                    return d.getName();
                })
                .limit(3).collect(toList()); // collect 是终端操作，汇总结果，其他的 map 之类的都是中间操作,形成流水线，类似构建器模式

        System.out.println(threeHighCaloricDishNames);


        // 1. 谓词(返回boolean的函数)筛选器
        List<Dish> vagetarianMenu = menu.stream().filter(Dish::isVegetarian).collect(toList());

        // 2. 筛选各异的元素 distinct()

        // 3. 截短留 limit()

        // 4. 跳过元素 skip()

        // 5. 对流中每一个元素应用函数 map()。 map是生成单个流，而flapMap()将流合并起来 Java8实战P88

        // 6. 是否至少匹配一个元素
        if (menu.stream().anyMatch(Dish::isVegetarian))
            System.out.println("菜单中有素食");

        // 7. 是否匹配所有元素
        if (menu.stream().allMatch((d) -> d.getCalories() < 1000))
            System.out.println("所有菜品卡路里都小于 1000");

        // 8. nonMatch() 没有一个匹配

        // 9. 查找任一元素 findAny() 找到任一个便返回
        Optional<Dish> dish = menu.stream().filter(Dish::isVegetarian).findAny();
        System.out.println(dish.get().getName());

        // 10. 查找第一个元素 findFirst() 这个并行上限制更多

        // 11. 规约 reduce()
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        // 求和， 0是默认初始值，也可以无初始值
        Integer sum = numbers.stream().reduce(0, (a, b) -> a + b);
        System.out.println(sum);
        // 最大值 max  最小值 min
        Optional<Integer> max = numbers.stream().reduce(Integer::max);
//        max = numbers.stream().reduce((x, y) -> x > y ? x : y);
        System.out.println(max.get());
        // 菜单中有多少菜 d->1 每道菜算1份
        System.out.println("菜单中有多少菜="+ menu.stream().map(d -> 1).reduce((x, y) -> x+y));
        System.out.println("菜单中有多少菜="+ menu.stream().count());



    }


}

