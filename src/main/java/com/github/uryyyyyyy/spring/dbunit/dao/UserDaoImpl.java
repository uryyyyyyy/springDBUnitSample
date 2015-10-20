package com.github.uryyyyyyy.spring.dbunit.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

public class UserDaoImpl implements UserDao{

	private NamedParameterJdbcTemplate template;

	@Override
	public void setNamedParameterJdbcTemplate(DataSource dataSource) {
		this.template = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public User findByName(String name) {
		return template.queryForObject("select * from table_name where name = ?", name, User.class);
	}
}
