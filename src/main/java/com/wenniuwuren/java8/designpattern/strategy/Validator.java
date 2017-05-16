package com.wenniuwuren.java8.designpattern.strategy;

/**
 * Created by hzzhuyibin on 2017/5/16.
 */
public class Validator {

    private final ValidateStrategy validateStrategy;

    public Validator(ValidateStrategy v) {
        this.validateStrategy = v;
    }

    public boolean validate(String s) {
        return validateStrategy.execute(s);
    }

    public static void main(String[] args) {
        Validator validator = new Validator(new IsAllowCase());
        System.out.println(validator.validate("abc"));

        Validator v = new Validator(new IsNumberic());
        System.out.println(v.validate("11"));

        // 可以看出 ValidateStrategy 是一种函数式接口，那么就能转换为 Lambda 表达式
        Validator v1 = new Validator((s) -> s.matches("[a-z]+"));
        System.out.println(v1.validate("abc"));
    }
}

