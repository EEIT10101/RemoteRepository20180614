package model.hibernate;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class SessionFactoryListener implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent event) {
		System.out.println("contextInitialized");
		HibernateUtil.getSessionFactory();
	}
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		System.out.println("contextDestroyed");
		HibernateUtil.closeSessionFactory();
	}
}
