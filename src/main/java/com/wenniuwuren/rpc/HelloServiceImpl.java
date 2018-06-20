package com.wenniuwuren.rpc;

/**
 * 实现服务
 *
 */  
public class HelloServiceImpl implements HelloService {  
  
    public String hello(String name) {  
        return "Hello " + name;  
    }  
  
}  