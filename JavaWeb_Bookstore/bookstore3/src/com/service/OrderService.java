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
	//创建订单
	public void createOrder(Order order,User user){
		od.createOrder(order,user);
	}
	//貌似没用
	public void updateOrder(Order order) {
		od.updateOrder(order);
	}
	//查看所有订单
	public List<Order> findOrderList(int uid) {
		return od.finOrderListById(uid);
	}
	public List<Product> getAllProduct(int orderid) {
		//先查出orderid订单下所有orderitem
		System.out.println("OrderService  1:");
		List<Orderitem> orderitems=od.FindItems(orderid);
		System.out.println("OrderService  2:");
		//遍历orderitem取出所有pid,并进行组合
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
		//sql查询，并返回list集合
		//delete from tabneName where id in(2,3,4,5,6)
		return id.FindProduct(sql);
	}
	//更改订单支付状态
	public void modifyOrderState(String orderid) {
		od.modifyOrderState(orderid);
		
	}
	//根据订单号找打订单
	public Order findOrder(int orderid) {
		
		return od.findOrder(orderid);
	}
	//删除订单
	public void deleteOrder(int orderid) {
		od.deleteOrder(orderid);
	}
	//对用户钱包进行扣钱,加了乐观锁
	public void payOrder(int uid, double money) throws  UserException{
		try {
			od.payOrder(uid,money);
		} catch (org.hibernate.StaleObjectStateException e) {
			e.printStackTrace();
			throw new  UserException("支付出现问题了");
		}
		
	}
	//根据用户id,找到钱包
	public Purse finPurseById(int uid) {
		return od.finPurseById(uid);
	}
	//创建钱包
	public void createPurse(User user, double money) {
		od.createPurse(user,money);
		
	}
	public void rechargePurse(User user, double money) {
		od.rechargePurse(user,money);
		
	}
	

}
