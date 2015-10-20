package com.github.uryyyyyyy.spring.dbunit.dao;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@ContextConfiguration(classes = UserDaoImplTest.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Configuration
public class UserDaoImplTest {

	@Bean
	public DataSource dataSource() {
		//db = new EmbeddedDatabaseBuilder().addDefaultScripts().build();
		return new EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.H2)
				.addScript("create-db.sql")
				.addScript("insert-data.sql")
				.build();
	}

	@Bean
	public UserDao userDao(){
		return new UserDaoImpl();
	}

	@Autowired
	UserDao userDao;

	@Test
	public void testFindByname() {
		User user = userDao.findByName("mkyong");
		assertThat(user.getId(), is(1));
		assertThat(user.getName(), is("mkyong"));
	}

	@Test
	public void testInsert() throws Exception {
		User temp = new User(null, "uryyyyyyy");
		User user = userDao.insert(temp);
		assertThat(user.getId(), is(4));
		assertThat(user.getName(), is("uryyyyyyy"));
	}

	@Test
	public void testFindAll() throws Exception {
		List<User> users = userDao.findAll();
		assertThat(users.size(), is(3));
	}

	@Test
	public void testInsertAll() throws Exception {
		List<User> users = userDao.findAll();
		assertThat(users.size(), is(3));

		User temp1 = new User(null, "uryyyyyyy");
		User temp2 = new User(null, "wawawa");
		List<User> list = new ArrayList<>();
		list.add(temp1);
		list.add(temp2);
		userDao.insertAll(list);
		User user = userDao.findByName("uryyyyyyy");
		assertThat(user.getId(), is(4));
		assertThat(user.getName(), is("uryyyyyyy"));
		User user2 = userDao.findByName("wawawa");
		assertThat(user2.getId(), is(5));
		assertThat(user2.getName(), is("wawawa"));

		List<User> users2 = userDao.findAll();
		assertThat(users2.size(), is(5));
	}

}