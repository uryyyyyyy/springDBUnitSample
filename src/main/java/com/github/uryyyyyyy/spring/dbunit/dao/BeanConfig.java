//package com.github.uryyyyyyy.spring.dbunit.dao;
//
//import javax.sql.DataSource;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
//import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
//
//public class BeanConfig {
//
//	@Bean
//	public DataSource dataSource() {
//
//		// no need shutdown, EmbeddedDatabaseFactoryBean will take care of this
//		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
//		return builder
//				.setType(EmbeddedDatabaseType.H2) //.HSQL or .DERBY
//				.addScript("db/sql/create-db.sql")
//				.addScript("db/sql/insert-data.sql")
//				.build();
//	}
//
//}