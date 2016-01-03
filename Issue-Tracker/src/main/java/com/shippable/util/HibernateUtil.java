package com.shippable.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;


/**
 * @author Soori Babu Meesala
 *  This class is used to get the hibernate session to access the database
 */
public class HibernateUtil {

	private static final SessionFactory sessionFactory;  
	 private static final ServiceRegistry serviceRegistry;  
	      
	    static {  
	        Configuration conf = new Configuration();  
	        conf.configure();
	        serviceRegistry = new ServiceRegistryBuilder().applySettings(conf.getProperties()).buildServiceRegistry();  
	        try {  
	            sessionFactory = conf.buildSessionFactory(serviceRegistry);  
	        } catch (Exception e) {  
	            System.err.println("SessionFactory creation failed." + e);  
	            throw new ExceptionInInitializerError(e);  
	        }         
	    }  
	      
	    public static SessionFactory getSessionFactory() {  
	        return sessionFactory;  
	    }  
}
