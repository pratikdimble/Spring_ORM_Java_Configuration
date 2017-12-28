package com.app.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.app.config.SpringConfig;
import com.app.entity.Employee;

public class Test_Delete {

	public static void main(String[] args) {
		//get the spring configuration
		AnnotationConfigApplicationContext ctx=new AnnotationConfigApplicationContext(SpringConfig.class);
		//get the hibernate Template bean
		HibernateTemplate ht=(HibernateTemplate)ctx.getBean("htObj");
				//create the entity object and delete the data based on primary key
				Employee e=ht.get(Employee.class, 12);
				if(e!=null){
					//delete the object
					ht.delete(e);
						System.out.println("\n\t\tEmployee Record Found and Deleted ");
				}
				else System.out.println("\n\t\tEmployee Record Not Found ");	
	}

}
