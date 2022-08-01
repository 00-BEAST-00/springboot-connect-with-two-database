package com.samco.springbootH2.primaryAndSecondaryConfiguration;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class PrimaryAndSecondaryConfiguration {
	@EnableJpaRepositories(entityManagerFactoryRef = "primaryEntityManagerFactory", transactionManagerRef = "primarytranscationManager", basePackages = {
	"com.samco.springbootH2.EmployeeRepository" })

public class PrimaryDBconfig {
@Bean(name = "dataSource")
@Primary
@ConfigurationProperties(prefix = "spring.datasource")
public DataSource dataSource() {
	return DataSourceBuilder.create().build();

}

@Primary
@Bean(name = "primaryEntityManagerFactory")
public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder,
		@Qualifier("dataSource") DataSource dataSource) {
	Map<String, Object> map = new HashMap<>();
	map.put("hibernate.hbm2ddl.auto", "update");
	map.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
	return builder.dataSource(dataSource).properties(map).packages("com.samco.springbootH2.primary").build();

}

@Primary
@Bean(name = "primarytranscationManager")
public PlatformTransactionManager primarytransactionManager(
		@Qualifier("primaryEntityManagerFactory") EntityManagerFactory primaryEntityManagerFactory) {
	return new JpaTransactionManager(primaryEntityManagerFactory);

}
	}
	
	@EnableJpaRepositories(
			entityManagerFactoryRef = "secondaryEntityManagerFactory",
			transactionManagerRef = "secondarytranscationManager",
			basePackages = {"com.samco.springbootH2.ManagerRepository"} )		

	public class SecondaryDBconfig {

		@Bean(name="managerDataSource")
		
		@ConfigurationProperties(prefix="spring.datasource2")
		public DataSource dataSource() {
			return DataSourceBuilder.create().build();
			
		}
		
		@Bean(name="secondaryEntityManagerFactory") 
		public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder,
				@Qualifier("managerDataSource") DataSource dataSource) {
			Map<String,Object> map = new HashMap<>();
			map.put("hibernate.hbm2ddl.auto","update");
			map.put("hibernate.dialect","org.hibernate.dialect.H2Dialect");
			return builder.dataSource(dataSource).properties(map).packages("com.samco.springbootH2.secondary").build();
		
	}
		@Bean(name = "secondarytranscationManager")
		public PlatformTransactionManager primarytransactionManager(
				@Qualifier("secondaryEntityManagerFactory")
				EntityManagerFactory primaryEntityManagerFactory) {
			return new JpaTransactionManager(primaryEntityManagerFactory);
					
		}
	}
	
}
