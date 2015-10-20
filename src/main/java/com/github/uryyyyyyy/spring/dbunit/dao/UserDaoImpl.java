package com.github.uryyyyyyy.spring.dbunit.dao;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDaoImpl implements UserDao{

	@Autowired
	private DataSource dataSource;

	@Override
	public User findByName(String name) {
		return new JdbcTemplate(dataSource).queryForObject("SELECT * FROM users where name = ?",
				BeanPropertyRowMapper.newInstance(User.class), name);
	}

	@Override
	public List<User> findAll() {
		return new JdbcTemplate(dataSource).query("SELECT * FROM users",
				BeanPropertyRowMapper.newInstance(User.class));
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

	@Override
	public void insertAll(List<User> users) {
		SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource)
				.withTableName("users");

		SqlParameterSource[] params = users.stream()
				.map(v -> new BeanPropertySqlParameterSource(v))
				.toArray(size -> new SqlParameterSource[size]);
		insert.executeBatch(params);
	}
}
