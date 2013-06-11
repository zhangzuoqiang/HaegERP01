package org.haegerp.util;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
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
	
	public static void insert(Object obj, Session session){
		session.beginTransaction();
        session.save(obj);
        session.getTransaction().commit();
	}
	
	public static void update(Object obj, Session session){
		session.beginTransaction();
        session.merge(obj);
        session.getTransaction().commit();
	}
	
	public static void delete(Object obj, Session session){
		session.beginTransaction();
        session.delete(obj);
        session.getTransaction().commit();
	}
	
	public static Object selectObject(String strQuery, Session session){
		session.beginTransaction();
        Query query = session.createQuery(strQuery);
        session.getTransaction().commit();
        return query.uniqueResult();
	}
	
	public static List<?> selectList(String strQuery, Session session){
		session.beginTransaction();
        Query query = session.createQuery(strQuery);
        session.getTransaction().commit();
        return query.list();
	}
 
	public static void shutdown(){
		getSessionFactory().close();
	}
}
