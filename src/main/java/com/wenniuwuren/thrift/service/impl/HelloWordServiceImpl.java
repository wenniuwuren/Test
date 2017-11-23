package com.wenniuwuren.thrift.service.impl;

import org.apache.commons.lang.StringUtils;
import org.apache.thrift.TException;

import java.util.Date;

import com.wenniuwuren.thrift.service.HelloWordService;
import com.wenniuwuren.thrift.service.Request;
import com.wenniuwuren.thrift.service.RequestException;
import com.wenniuwuren.thrift.service.RequestType;

/**
 * 服务实现
 */
public class HelloWordServiceImpl implements HelloWordService.Iface {
    // 实现这个方法完成具体的逻辑。
    public String doAction(Request request) throws RequestException, TException {
        System.out.println("Get request: " + request);
        if (StringUtils.isBlank(request.getName()) || request.getType() == null) {
            throw new RequestException();
        }
        String result = "Hello, " + request.getName();
        if (request.getType() == RequestType.SAY_HELLO) {
            result += ", Welcome!";
        } else {
            result += ", Now is " + new Date().toLocaleString();
        }
        return result;
    }
}

