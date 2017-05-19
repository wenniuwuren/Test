
package com.wenniuwuren.java8.optional;

import org.apache.commons.collections.map.HashedMap;

import java.util.Map;
import java.util.Optional;

/**
 * Created by hzzhuyibin on 2017/5/18.
 */
public class Test {

    public static void main(String[] args) {

        // 1. 声明了一个空的 Optional
        Optional<Car> carOptional = Optional.empty();

        // 2. 依据一个非空值创建 Optional
        Car car = new Car();
        Optional<Car> carOptional1 = Optional.of(car);

        // 3. 可接受 null 的 optional
        Optional<Car> carOptional2 = Optional.ofNullable(car);

        Person person = new Person();
        Optional<Person> optPerson = Optional.of(person);
//        以下语句编译不通过，optPerson返回Optional<Person>是可以的，但是getCar返回Optional<Car>，那么这个map就是返回Optional<Optional<Car>>
//        Optional<String> name = optPerson.map(Person::getCar).map(Car::getInsurance).map(Insurance::getName);
        // 使用flapMap来将 Optinal<Optional>扁平化为Optional
//        String name = optPerson.flatMap(Person::getCar).flatMap(Car::getInsurance).map(Insurance::getName).orElse("noName");


        // 使用 Optional 封装可能的 null 值
        Map<String, String> map = new HashedMap();
        Optional<String> stringOptional = Optional.ofNullable(map.get("a"));


    }


    public static Optional<Integer> stringToInt(String s) {
        try {
            return Optional.of(Integer.parseInt(s));
        } catch (Exception e) {
            return Optional.empty();
        }

    }


}

