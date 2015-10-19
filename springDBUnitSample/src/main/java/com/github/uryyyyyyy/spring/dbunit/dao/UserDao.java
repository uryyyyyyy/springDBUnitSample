package com.github.uryyyyyyy.spring.dbunit.dao;

import javax.sql.DataSource;

public interface UserDao {

	void setNamedParameterJdbcTemplate(DataSource dataSource);

	User findByName(String name);
}
