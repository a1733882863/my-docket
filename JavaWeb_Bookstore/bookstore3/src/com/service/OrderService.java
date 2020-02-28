package com.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.StaleObjectStateException;

import com.dao.BookDao;
import com.dao.ItemDao;
import com.dao.OrderDao;
import com.domain.Order;
import com.domain.Orderitem;
import com.domain.Product;
import com.domain.Purse;
import com.domain.User;
import com.exception.UserException;

public class OrderService {
	OrderDao od = new OrderDao();
	BookDao bd = new BookDao();
	ItemDao id = new ItemDao();
	//��������
	public void createOrder(Order order,User user){
		od.createOrder(order,user);
	}
	//ò��û��
	public void updateOrder(Order order) {
		od.updateOrder(order);
	}
	//�鿴���ж���
	public List<Order> findOrderList(int uid) {
		return od.finOrderListById(uid);
	}
	public List<Product> getAllProduct(int orderid) {
		//�Ȳ��orderid����������orderitem
		System.out.println("OrderService  1:");
		List<Orderitem> orderitems=od.FindItems(orderid);
		System.out.println("OrderService  2:");
		//����orderitemȡ������pid,���������
		String str ="(";
		for(Orderitem oi : orderitems){
			str+=oi.getPid();
			str+=",";
		}
		str=str.substring(0,str.length()-1);
		System.out.println("OrderService  3:"+str);
		str+=")";
		String sql="select * from t_product where id in"+str;
		System.out.println("OrderService  4:"+sql);
		//select * from t_product where id in(2,1);
		//sql��ѯ��������list����
		//delete from tabneName where id in(2,3,4,5,6)
		return id.FindProduct(sql);
	}
	//���Ķ���֧��״̬
	public void modifyOrderState(String orderid) {
		od.modifyOrderState(orderid);
		
	}
	//���ݶ������Ҵ򶩵�
	public Order findOrder(int orderid) {
		
		return od.findOrder(orderid);
	}
	//ɾ������
	public void deleteOrder(int orderid) {
		od.deleteOrder(orderid);
	}
	//���û�Ǯ�����п�Ǯ,�����ֹ���
	public void payOrder(int uid, double money) throws  UserException{
		try {
			od.payOrder(uid,money);
		} catch (org.hibernate.StaleObjectStateException e) {
			e.printStackTrace();
			throw new  UserException("֧������������");
		}
		
	}
	//�����û�id,�ҵ�Ǯ��
	public Purse finPurseById(int uid) {
		return od.finPurseById(uid);
	}
	//����Ǯ��
	public void createPurse(User user, double money) {
		od.createPurse(user,money);
		
	}
	public void rechargePurse(User user, double money) {
		od.rechargePurse(user,money);
		
	}
	

}
