package com.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.classic.Session;

import com.Utils.HibernateUtils;
import com.domain.Order;
import com.domain.Orderitem;
import com.domain.Product;

public class ItemDao {

	public List<Product> FindProduct(String sql) {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//...................................................................
		List<Product> list = (List<Product>) session.createSQLQuery(sql).addEntity(Product.class).list();
		//...................................................................
		session.getTransaction().commit();
		return list;
	}

}
