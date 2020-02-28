package com.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

import com.Utils.HibernateUtils;
import com.domain.Order;
import com.domain.Orderitem;
import com.domain.Product;
import com.domain.Purse;
import com.domain.User;

public class OrderDao {
	//创建订单
	public void createOrder(Order order,User user) {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//user.setOrder(order);
		//user.getOrders().add(order);单方维护关系就行
		order.setUser(user);
		session.save(order);
		session.getTransaction().commit();		
	}
	//貌似没用
	public void updateOrder(Order order) {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		session.update(order);
		session.getTransaction().commit();	
	}
	//查看所有订单
	public List<Order> finOrderListById(int uid) {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		String sql = "from Order where uid=?";
		Query query=session.createQuery (sql);
		query.setParameter(0, uid).setCacheable(true);
		List<Order> orderlist = query.list();
		session.getTransaction().commit();
		return orderlist;	
		
	}
	//订单历史里的查看
	public List<Orderitem> FindItems(int orderid) {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();	
		//用criteria会报错，报映射错误，没有oid这一列
	/*	System.out.println("OrderDao   1:");
		Criteria criteria= session.createCriteria(Orderitem.class);
		System.out.println("OrderDao   2:");
		criteria.add(Restrictions.eq("oid", new Integer(orderid)));
		System.out.println("OrderDao   3:");
		//update t_orderitem set oid=1;
	
		List list = criteria.list();
		System.out.println("OrderDao   4:");*/
		String sql ="select * from t_orderitem where oid = "+orderid;
		List<Orderitem> list=(List<Orderitem>) session.createSQLQuery(sql).addEntity(Orderitem.class).list();
		System.out.println(list.toString());
		session.getTransaction().commit();
		return list;
		
		
	}
	//更改订单支付状态
	public void modifyOrderState(String oid) {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		int orderid = Integer.parseInt(oid);
		//String queryString = "delete from UserFolder as model where model.userId = ?";
		String sql = "update Order set paystate=1 where id=?";
		session.createQuery (sql).setParameter(0, orderid).executeUpdate() ;
		 
		session.getTransaction().commit();
		
		
	}

	public Order findOrder(int orderid) {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		Order order = (Order) session.get(Order.class, orderid);
		session.getTransaction().commit();	
		return order;
	}
//必须这样删除 ，否者会报错 (先解除订单与user的关系)               订单删除  
	public void deleteOrder(int orderid) {
		//这样删除必须把Order的inverse设为false,才能自动删除Orderitem
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		System.out.println(orderid);
		Order order = (Order) session.get(Order.class, orderid);
		System.out.println(order.toString());
		order.getUser().getOrders().remove(order);
		order.setUser(null);
		session.delete(order);
		session.getTransaction().commit();
	}
	//对用户钱包进行扣钱,加了乐观锁
	public void payOrder(int uid, double money) {
		
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		Purse purse1 = (Purse)session.createQuery("from Purse where uid=?").setParameter(0, uid).uniqueResult();
		purse1.setMoney(purse1.getMoney()-money);
		session.getTransaction().commit();
	}

	//根据用户id,找到钱包
	public Purse finPurseById(int uid) {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		Purse purse1 = (Purse)session.createQuery("from Purse where uid=?").setParameter(0, uid).uniqueResult();
		session.getTransaction().commit();
		return purse1;
	}
	//创建钱包
	public void createPurse(User user, double money) {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		Purse purse = new Purse();
		purse.setMoney(money);
		purse.setUser(user);
		session.save(purse);
		session.getTransaction().commit();
		
	}
	//为钱包充值
	public void rechargePurse(User user, double money) {
		
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		Purse purse = (Purse)session.createQuery("from Purse where uid=?").setParameter(0, user.getId()).uniqueResult();
		purse.setMoney(money+purse.getMoney());
		session.update(purse);
		session.getTransaction().commit();
	}

	/*
	 * Order o = new Order();
			User u =  new User();;
			u.setId(11);
			o.setId(11);
			o.setUser(u);
			u.setOrder(o);
			session.saveOrUpdate(o);
	 * public void createOrder(User user) {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		session.update(user);
		session.getTransaction().commit();	
	}*/
}
