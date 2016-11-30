package com.wenniuwuren.spring.jdbc;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 表与实体的映射
 * Created by hzzhuyibin on 2016/11/30.
 */
public class UserRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("age"), resultSet.getString("sex"));
        return user;
    }
}

