package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.BookService;

public class SearchAJAXServlet extends HttpServlet {
	public SearchAJAXServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String name = request.getParameter("name");
		
		BookService bs=new BookService();
		List<Object> list=bs.searchBookByName(name);
		
		String str = "";
		for(int i = 0 ; i<list.size();i++){
			if(i>0){
				str+=",";
			}
			str+=list.get(i).toString();
		}
		response.getWriter().write(str);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

	public void init() throws ServletException {
	}

}
