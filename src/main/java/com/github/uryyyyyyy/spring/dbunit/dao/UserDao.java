package com.github.uryyyyyyy.spring.dbunit.dao;

import java.util.List;

public interface UserDao {

	User findByName(String name);

	List<User> findAll();

	User insert(User name);

	void insertAll(List<User> users);
}
