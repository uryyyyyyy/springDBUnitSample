package com.github.uryyyyyyy.spring.dbunit.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserDaoImpl implements UserDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User findByName(String name) {
        return jdbcTemplate.queryForObject("SELECT * FROM users where name = ?",
                BeanPropertyRowMapper
                        .newInstance(User.class), name);
    }
}
