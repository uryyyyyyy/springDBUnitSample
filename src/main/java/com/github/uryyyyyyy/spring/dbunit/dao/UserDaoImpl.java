package com.github.uryyyyyyy.spring.dbunit.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoImpl implements UserDao{

    private JdbcTemplate jdbcTemplate;

    @Override
    public void setNamedParameterJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public User findByName(String name) {
        return jdbcTemplate.queryForObject("SELECT name FROM users where name = ?",
                BeanPropertyRowMapper
                        .newInstance(User.class), name);
    }
}
