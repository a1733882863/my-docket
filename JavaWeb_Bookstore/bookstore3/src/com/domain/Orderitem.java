package com.domain;
public class Orderitem {
	private int id;
	private int buynum; // ��������
	private int pid;
	private Order order;// ����
	private Product product;
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}

	public int getId() {
		return id;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBuynum() {
		return buynum;
	}
	public void setBuynum(int buynum) {
		this.buynum = buynum;
	}
	@Override
	public String toString() {
		return "Orderitem [id=" + id + ", buynum=" + buynum + ", pid=" + pid + "]";
	}
	
}
