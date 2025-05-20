package com.hexaware.quitqecom;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
	
	private static final SessionFactory sessionFactory;
	
	static {
		StandardServiceRegistry standardRegistery =  new StandardServiceRegistryBuilder()
													.configure("hibernate.cfg.xml")
													.build();
		
		Metadata metaData = new MetadataSources(standardRegistery).getMetadataBuilder().build();
		
		sessionFactory = metaData.getSessionFactoryBuilder().build();
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
