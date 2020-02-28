package com.service;

import java.sql.SQLException;
import java.util.List;

import com.dao.BookDao;
import com.domain.Pagebean;
import com.domain.Product;

public class BookService {
	BookDao bd = new BookDao();
	public Pagebean pageBean(int currentPage, int pageSize, String category) {
		//得到总记录数
		int count  = bd.count(category);
		int totalPage = (int)Math.ceil(count*1.0/pageSize); //求出总页数
		List<Product> products= bd.findBooks(currentPage,pageSize,category);
		
		//把5个变量封装到PageBean中，做为返回值
		Pagebean pb = new Pagebean();
		pb.setProducts(products);
		pb.setCount(count);
		pb.setCurrentPage(currentPage);
		pb.setPageSize(pageSize);
		pb.setTotalPage(totalPage);
		//在pageBean中添加属性，用于点击上一页或下一页时使用
		pb.setCategory(category);
		
		return pb;
	}
	//admin根据id查找图书
	public Product findBookById(String id) {
		return bd.finBookById(id);
	}
	//admin里的方法
	public Pagebean findAllBooks(int currentPage, int pageSize, String category) {
			int count  = bd.count(category);//得到总记录数
			int totalPage = (int)Math.ceil(count*1.0/pageSize); //求出总页数
			List<Product> products= bd.findAllbooks(currentPage, pageSize,category);
			//把5个变量封装到PageBean中，做为返回值
			Pagebean pb = new Pagebean();
			pb.setProducts(products);
			pb.setCount(count);
			pb.setCurrentPage(currentPage);
			pb.setPageSize(pageSize);
			pb.setTotalPage(totalPage);
			//在pageBean中添加属性，用于点击上一页或下一页时使用
			pb.setCategory(category);
			
			return pb;
	}
	public void deleteProduct(int productid) {
		bd.delBook(productid);
		
	}
	//批量删除图书
	public void delAllBook(String sql) {
		bd.delAllBook(sql);
		
	}
	//添加图书
	public void addBook(Product book) {
		bd.addBook(book);
		
	}
	public void updateBook(Product book) {
		bd.updateBook(book);
		
	}
	//admin 搜索图书
	public List<Product> searchBook(String id, String category, String name, String minprice, String maxprice) {
		return bd.searchBook(id,category,name,minprice,maxprice);
	}
	//搜索框用到
	public List<Object> searchBookByName(String name) {
		return bd.searchBookByName(name);
	}
	public List<Product> findBookByName(String name) {
		return bd.findBookByName(name);
	}

}
