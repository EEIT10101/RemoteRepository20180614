package model;

import java.util.Arrays;

import model.dao.CustomerDAOHibernate;
import model.hibernate.HibernateUtil;

public class CustomerService {
	private CustomerDAO customerDao;
	public CustomerService() {
		this.customerDao =
				new CustomerDAOHibernate(HibernateUtil.getSessionFactory());
	}
	public CustomerBean login(String username, String password) {
		CustomerBean bean = customerDao.select(username);
		if(bean!=null) {
			if(password!=null && password.length()!=0) {
				byte[] temp = password.getBytes();	//使用者輸入
				byte[] pass = bean.getPassword();	//資料庫抓出
				if(Arrays.equals(temp, pass)) {
					return bean;
				}
			}
		}
		return null;
	}
	public boolean changePassword(String username, String oldPassword, String newPassword) {
		CustomerBean bean = this.login(username, oldPassword);
		if(bean!=null) {
			if(newPassword!=null && newPassword.length()!=0) {
				byte[] temp = newPassword.getBytes();	//使用者輸入
				return customerDao.update(
						temp, bean.getEmail(), bean.getBirth(), username);
			}
		}
		return false;
	}
}
