package com.admin.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.domain.Pagebean;
import com.service.BookService;
//点击全部图书,图书分类跳到这个servlet,会分页显示图书信息(图书管理系统里)
public class BookListServlet extends HttpServlet {
	public BookListServlet() {
		super();
	}
	public void destroy() {
		super.destroy(); 
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//导航按钮的查询条件
		String category = request.getParameter("category");
		if(category==null){
			category="";
		}
		//初始化每页显示的记录数
		int pageSize = 10;
		//当前页,默认设成1
		int currentPage = 1;
		//从上一页或下一页得到的数据
		String currPage = request.getParameter("currentPage");
		//第一次访问资源时，currPage可能是null
		if(currPage!=null&&!"".equals(currPage)){
			currentPage = Integer.parseInt(currPage);
		}	
		BookService bs = new BookService();
		//分页查询，并返回PageBean对象
		//把当前页及book放入pageBean
		Pagebean pb = bs.findAllBooks(currentPage,pageSize,category);
			//把返回的带有图书集的list放回request对象中
			request.setAttribute("pb", pb);
			//转发到图书管理系统的分页显示面上
			request.getRequestDispatcher("/admin/list.jsp").forward(request, response);
		
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	public void init() throws ServletException {
	}

}
