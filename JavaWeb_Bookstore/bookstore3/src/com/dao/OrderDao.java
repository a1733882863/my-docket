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
	//��������
	public void createOrder(Order order,User user) {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//user.setOrder(order);
		//user.getOrders().add(order);����ά����ϵ����
		order.setUser(user);
		session.save(order);
		session.getTransaction().commit();		
	}
	//ò��û��
	public void updateOrder(Order order) {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		session.update(order);
		session.getTransaction().commit();	
	}
	//�鿴���ж���
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
	//������ʷ��Ĳ鿴
	public List<Orderitem> FindItems(int orderid) {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();	
		//��criteria�ᱨ����ӳ�����û��oid��һ��
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
	//���Ķ���֧��״̬
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
//��������ɾ�� �����߻ᱨ�� (�Ƚ��������user�Ĺ�ϵ)               ����ɾ��  
	public void deleteOrder(int orderid) {
		//����ɾ�������Order��inverse��Ϊfalse,�����Զ�ɾ��Orderitem
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
	//���û�Ǯ�����п�Ǯ,�����ֹ���
	public void payOrder(int uid, double money) {
		
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		Purse purse1 = (Purse)session.createQuery("from Purse where uid=?").setParameter(0, uid).uniqueResult();
		purse1.setMoney(purse1.getMoney()-money);
		session.getTransaction().commit();
	}

	//�����û�id,�ҵ�Ǯ��
	public Purse finPurseById(int uid) {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		Purse purse1 = (Purse)session.createQuery("from Purse where uid=?").setParameter(0, uid).uniqueResult();
		session.getTransaction().commit();
		return purse1;
	}
	//����Ǯ��
	public void createPurse(User user, double money) {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		Purse purse = new Purse();
		purse.setMoney(money);
		purse.setUser(user);
		session.save(purse);
		session.getTransaction().commit();
		
	}
	//ΪǮ����ֵ
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
