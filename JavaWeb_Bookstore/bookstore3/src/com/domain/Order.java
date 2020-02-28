package com.domain;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Order {
	private int id;
	private double money; // �����ܼ�
	private String receiverAddress; // �ͻ���ַ
	private String receiverName; // �ջ�������
	private String receiverPhone; // �ջ��˵绰
	private int paystate; // ����״̬
	private Date ordertime; // �µ�ʱ��
	
	private User user;
	private Set<Orderitem> orderitem = new HashSet<Orderitem>();
	
	
	public Set<Orderitem> getOrderitem() {
		return orderitem;
	}
	public void setOrderitem(Set<Orderitem> orderitem) {
		this.orderitem = orderitem;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public String getReceiverAddress() {
		return receiverAddress;
	}
	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getReceiverPhone() {
		return receiverPhone;
	}
	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}
	public int getPaystate() {
		return paystate;
	}
	public void setPaystate(int paystate) {
		this.paystate = paystate;
	}
	public Date getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", money=" + money + ", orderitem=" + orderitem + "]";
	}
	
	

	
}
