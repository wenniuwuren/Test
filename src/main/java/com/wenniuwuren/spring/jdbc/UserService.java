package com.wenniuwuren.spring.jdbc;

import java.util.List;

/**
 * Created by hzzhuyibin on 2016/11/30.
 */
public interface UserService {

    public void save(User user);

    public List<User> getUsers();
}

