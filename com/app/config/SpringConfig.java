package com.app.config;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;

@Configuration
public class SpringConfig {
	//bean for Driver Manager Data Source
	@Bean
	public DataSource dsObj()
	{
		DriverManagerDataSource ds=new DriverManagerDataSource();
			ds.setDriverClassName("oracle.jdbc.OracleDriver");
			ds.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
			ds.setUsername("system");
			ds.setPassword("oracle");
		return ds;
	}

	//bean for AnnotationSessionFactoryBean
	@Bean
	public SessionFactory sfObj()
	{
		AnnotationSessionFactoryBean sf=new AnnotationSessionFactoryBean();
			sf.setDataSource(dsObj());
			
			//hibernate Properties
			Properties prop=new Properties();
			prop.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
			prop.put("hibernate.show_sql", "true");
			prop.put("hibernate.format_sql", "true");
			prop.put("hibernate.hbm2ddl.auto", "update");
			
			sf.setHibernateProperties(prop);
			
			sf.setAnnotatedClasses(new Class[] {com.app.entity.Employee.class});
		return (SessionFactory) sf;
	}
	
	
	//bean for Hibernate Template
	
		@Bean
		public HibernateTemplate htObj() 
		{
			HibernateTemplate ht= new HibernateTemplate();
				ht.setSessionFactory(sfObj());
			return ht;
		}
}
