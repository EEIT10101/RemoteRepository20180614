package model.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import model.ProductBean;
import model.ProductDAO;

public class ProductDAOHibernate implements ProductDAO {
	private SessionFactory sessionFactory;
	public ProductDAOHibernate(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public ProductBean select(int id) {
		return this.getSession().get(ProductBean.class, id);
	}
	
	@Override
	public List<ProductBean> select() {
		return this.getSession().createQuery(
				"FROM ProductBean", ProductBean.class).list();
	}
	
	@Override
	public ProductBean insert(ProductBean bean) {
		if(bean!=null) {
			ProductBean temp = 
					this.getSession().get(ProductBean.class, bean.getId());
			if(temp==null) {
				this.getSession().save(bean);
				return bean;
			}
		}
		return null;
	}

	@Override
	public ProductBean update(String name, double price, Date make, int expire, int id) {
		ProductBean temp = 
				this.getSession().get(ProductBean.class, id);
		if(temp!=null) {
			temp.setName(name);
			temp.setPrice(price);
			temp.setMake(make);
			temp.setExpire(expire);
			return temp;
		}
		return null;
	}
	
	@Override
	public boolean delete(int id) {
		ProductBean temp = 
				this.getSession().get(ProductBean.class, id);
		if(temp!=null) {
			this.getSession().delete(temp);
			return true;
		}
		return false;
	}
}
