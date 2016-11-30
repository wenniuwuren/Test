package com.wenniuwuren.spring.aop.jdk;

/**
 * 创建业务接口的实现类
 * Created by hzzhuyibin on 2016/11/24.
 */
public class UserServiceImpl implements UserService{

    @Override
    public void add() {
        System.out.println("--------------add-----------------");
    }
}

