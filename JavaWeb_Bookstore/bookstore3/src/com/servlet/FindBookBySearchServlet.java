package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.domain.Pagebean;
import com.domain.Product;
import com.service.BookService;
//ËÑË÷¿ò,ÅäºÏajaxÊ¹ÓÃ
public class FindBookBySearchServlet extends HttpServlet {
	public FindBookBySearchServlet() {
		super();
	}
	public void destroy() {
		super.destroy(); 
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name=request.getParameter("name");
		BookService bs =new BookService();
		List<Product> listbook= bs.findBookByName(name);
		Pagebean pb = new Pagebean();
		pb.setProducts(listbook);
		request.setAttribute("pb", pb);
		request.getRequestDispatcher("/index.jsp").forward(request, response); 
	
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

	public void init() throws ServletException {
	}

}
