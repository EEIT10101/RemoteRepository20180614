package model.dao;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import model.CustomerBean;
import model.CustomerDAO;

public class CustomerDAOHibernate implements CustomerDAO {
	private SessionFactory sessionFactory;
	public CustomerDAOHibernate(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	@Override
	public CustomerBean select(String custid) {
		return this.getSession().get(CustomerBean.class, custid);
	}

	@Override
	public boolean update(byte[] password, String email, Date birth, String custid) {
		CustomerBean temp = 
				this.getSession().get(CustomerBean.class, custid);
		if(temp!=null) {
			temp.setPassword(password);
			temp.setEmail(email);
			temp.setBirth(birth);
			return true;
		}
		return false;
	}
}
