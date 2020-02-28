package com.admin.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.BookService;
//删除单本图书的servlet
public class DeleteBookServlet extends HttpServlet {
	public DeleteBookServlet() {
		super();
	}
	public void destroy() {
		super.destroy(); 
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取图书的id,并把转换类型
		String id =  request.getParameter("productid");
		int productid = Integer.parseInt(id);
		//调用业务逻辑,判断id是否为空,不为空删除
		BookService bs = new BookService();
		if(!"".equals(productid)){
			bs.deleteProduct(productid);
		}
		//显示删除完后的图书列表
		request.getRequestDispatcher("/servlet/BookListServlet").forward(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}
	public void init() throws ServletException {
	}

}
