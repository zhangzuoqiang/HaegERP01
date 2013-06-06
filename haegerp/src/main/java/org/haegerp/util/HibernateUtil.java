package org.haegerp.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * 
 * 
 * 
 * Deprecated: import org.hibernate.service.ServiceRegistryBuilder;
 * Neu: import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
 * 
 * Deprecated: ServiceRegistryBuilder()
 * Neu: StandardServiceRegistryBuilder()
 * 
 * @author Wolf
 *
 */
public class HibernateUtil {
	
	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;
	
	static
	{
		try {
			Configuration configuration = new Configuration().configure();
			serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			ex.printStackTrace();
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
 
	public static void shutdown(){
		getSessionFactory().close();
	}
}
