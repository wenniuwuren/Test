package com.wenniuwuren.spring.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.List;

/**
 * 数据操作接口实现类
 * Created by hzzhuyibin on 2016/11/30.
 */
public class UserServiceImpl implements UserService {

    private JdbcTemplate jdbcTemplate;

    // set data source
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(User user) {
        jdbcTemplate.update("INSERT INTO t_user(name,age,sex) VALUES (?,?,?)", new Object[] {user.getName(),
                user.getAge(), user.getSex()}, new int[] {Types.VARCHAR, Types.INTEGER, Types.VARCHAR});


    }

    @Override
    public List<User> getUsers() {
        List<User> list = jdbcTemplate.query("SELECT * FROM t_user", new UserRowMapper());
        return list;
    }
}

