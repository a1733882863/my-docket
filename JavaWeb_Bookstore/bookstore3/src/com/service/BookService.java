package com.service;

import java.sql.SQLException;
import java.util.List;

import com.dao.BookDao;
import com.domain.Pagebean;
import com.domain.Product;

public class BookService {
	BookDao bd = new BookDao();
	public Pagebean pageBean(int currentPage, int pageSize, String category) {
		//�õ��ܼ�¼��
		int count  = bd.count(category);
		int totalPage = (int)Math.ceil(count*1.0/pageSize); //�����ҳ��
		List<Product> products= bd.findBooks(currentPage,pageSize,category);
		
		//��5��������װ��PageBean�У���Ϊ����ֵ
		Pagebean pb = new Pagebean();
		pb.setProducts(products);
		pb.setCount(count);
		pb.setCurrentPage(currentPage);
		pb.setPageSize(pageSize);
		pb.setTotalPage(totalPage);
		//��pageBean��������ԣ����ڵ����һҳ����һҳʱʹ��
		pb.setCategory(category);
		
		return pb;
	}
	//admin����id����ͼ��
	public Product findBookById(String id) {
		return bd.finBookById(id);
	}
	//admin��ķ���
	public Pagebean findAllBooks(int currentPage, int pageSize, String category) {
			int count  = bd.count(category);//�õ��ܼ�¼��
			int totalPage = (int)Math.ceil(count*1.0/pageSize); //�����ҳ��
			List<Product> products= bd.findAllbooks(currentPage, pageSize,category);
			//��5��������װ��PageBean�У���Ϊ����ֵ
			Pagebean pb = new Pagebean();
			pb.setProducts(products);
			pb.setCount(count);
			pb.setCurrentPage(currentPage);
			pb.setPageSize(pageSize);
			pb.setTotalPage(totalPage);
			//��pageBean��������ԣ����ڵ����һҳ����һҳʱʹ��
			pb.setCategory(category);
			
			return pb;
	}
	public void deleteProduct(int productid) {
		bd.delBook(productid);
		
	}
	//����ɾ��ͼ��
	public void delAllBook(String sql) {
		bd.delAllBook(sql);
		
	}
	//���ͼ��
	public void addBook(Product book) {
		bd.addBook(book);
		
	}
	public void updateBook(Product book) {
		bd.updateBook(book);
		
	}
	//admin ����ͼ��
	public List<Product> searchBook(String id, String category, String name, String minprice, String maxprice) {
		return bd.searchBook(id,category,name,minprice,maxprice);
	}
	//�������õ�
	public List<Object> searchBookByName(String name) {
		return bd.searchBookByName(name);
	}
	public List<Product> findBookByName(String name) {
		return bd.findBookByName(name);
	}

}
