package com.wenniuwuren.spring.transaction;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by hzzhuyibin on 2016/11/30.
 */

public interface UserService {

    @Transactional(propagation = Propagation.REQUIRED)
    public void save(User user);

    public List<User> getUsers();
}

