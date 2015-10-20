package com.github.uryyyyyyy.spring.dbunit.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = BeanConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDaoTest {

    @Autowired
	UserDao userDao;

//	@Before
//	public void setUp() {
//		//db = new EmbeddedDatabaseBuilder().addDefaultScripts().build();
//		db = new EmbeddedDatabaseBuilder()
//				.setType(EmbeddedDatabaseType.H2)
//				.addScript("create-db.sql")
//				.addScript("insert-data.sql")
//				.build();
//	}

	@Test
	public void testFindByname() {
		User user = userDao.findByName("mkyong");
		Assert.assertNotNull(user);
        System.out.println(user);
		Assert.assertEquals(1, user.getId());
		Assert.assertEquals("mkyong", user.getName());
	}

}