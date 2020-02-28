package com.Utils;

import org.apache.commons.collections.Factory;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

public class HibernateUtils {
	 private static SessionFactory factory =null;
	static{
		Configuration configuration = new Configuration();
		configuration.configure();
		factory = configuration.buildSessionFactory();
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
		public void run() {
				System.out.println("");
				factory.close();
			}
		}));
	}
	

	public static Session openSession() {
		return factory.getCurrentSession();
	}
	/*public static org.hibernate.Session  getCurrentSession(){
		Session session =factory.getCurrentSession();
		return session;
	}*/
}
