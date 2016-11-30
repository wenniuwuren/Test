package com.wenniuwuren.spring.transaction;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by hzzhuyibin on 2016/11/30.
 */
public class TransactionTest {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("transactionTest.xml");

        UserService userService = (UserService) applicationContext.getBean("userService");
        User user = new User();
        user.setAge(22);
        user.setName("wenniuwuren");
        user.setSex("男");

        userService.save(user);

        List<User> userList = userService.getUsers();
        for (User userTemp : userList) {
            System.out.println(userTemp.getId() + ", " + userTemp.getName() + ", " + userTemp.getAge() + ", " + user.getSex());
        }
    }
}

