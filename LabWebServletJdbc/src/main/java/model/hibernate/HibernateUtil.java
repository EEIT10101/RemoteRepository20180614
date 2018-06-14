package model.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
	private static final SessionFactory sessionFactory = initializeSessionFactory();
	private static SessionFactory initializeSessionFactory() {
		try {
			final StandardServiceRegistry serviceRegistry =
					new StandardServiceRegistryBuilder().configure().build();
			return new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
		} catch(Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
	}
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public static void closeSessionFactory() {
		if(sessionFactory!=null) {
			sessionFactory.close();
		}
	}
}
