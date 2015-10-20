package com.github.uryyyyyyy.spring.dbunit.dao;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

@Component
public class UserDaoImpl implements UserDao{

    @Autowired
    private DataSource dataSource;

    @Override
    public User findByName(String name) {
        return new JdbcTemplate(dataSource).queryForObject("SELECT * FROM users where name = ?",
                BeanPropertyRowMapper
                        .newInstance(User.class), name);
    }

    @Override
    public User insert(User user) {
        SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource)
                .withTableName("users")
                .usingGeneratedKeyColumns("id");

        SqlParameterSource param = new BeanPropertySqlParameterSource(user);
        if(user.getId() == null) {
            Number key = insert.executeAndReturnKey(param);
            user.setId(key.intValue());
            return user;
        }
        throw new RuntimeException();
    }
}
