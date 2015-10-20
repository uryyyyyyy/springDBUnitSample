package com.github.uryyyyyyy.spring.dbunit.dao;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@ComponentScan
@Configuration
public class UserDaoTest {

	private EmbeddedDatabase db;

    @Autowired
	UserDao userDao;

	@Before
	public void setUp() {
		//db = new EmbeddedDatabaseBuilder().addDefaultScripts().build();
		db = new EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.H2)
				.addScript("create-db.sql")
				.addScript("insert-data.sql")
				.build();
	}

	@Test
	public void testFindByname() {
		UserDaoImpl userDao = new UserDaoImpl();
		userDao.setNamedParameterJdbcTemplate(db);

		User user = userDao.findByName("mkyong");

		Assert.assertNotNull(user);
		Assert.assertEquals(1, user.getId());
		Assert.assertEquals("mkyong", user.getName());
		Assert.assertEquals("mkyong@gmail.com", user.getEmail());

	}

	@After
	public void tearDown() {
		db.shutdown();
	}

}