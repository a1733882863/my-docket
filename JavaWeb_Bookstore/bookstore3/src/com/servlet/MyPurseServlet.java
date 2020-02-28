package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.domain.Purse;
import com.domain.User;
import com.service.OrderService;

public class MyPurseServlet extends HttpServlet {

	/**
		 * Constructor of the object.
		 */
	public MyPurseServlet() {
		super();
	}
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		User user = (User) request.getSession().getAttribute("user");
		OrderService os = new OrderService();
		Purse purse = os.finPurseById(user.getId());
		request.setAttribute("purse", purse);
		request.getRequestDispatcher("/mypurse.jsp").forward(request, response);
		return ;
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

	public void init() throws ServletException {
		
	}

}
