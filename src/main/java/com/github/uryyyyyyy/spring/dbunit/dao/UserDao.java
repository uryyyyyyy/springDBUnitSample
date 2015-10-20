package com.github.uryyyyyyy.spring.dbunit.dao;

public interface UserDao {

	User findByName(String name);

	User insert(User name);
}
