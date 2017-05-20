package com.wenniuwuren.java8.designpattern.strategy;

/**
 * Created by hzzhuyibin on 2017/5/16.
 */
public class IsNumberic implements ValidateStrategy{
    @Override
    public boolean execute(String s) {
        return s.matches("\\d+");
    }
}

