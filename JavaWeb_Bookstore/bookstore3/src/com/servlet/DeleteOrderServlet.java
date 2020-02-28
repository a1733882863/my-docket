package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.OrderService;
//É¾³ý¶©µ¥
public class DeleteOrderServlet extends HttpServlet {
	public DeleteOrderServlet() {
		super();
	}
	public void destroy() {
		super.destroy(); 
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id = request.getParameter("orderid");
		int orderid = Integer.parseInt(id);
		OrderService os = new OrderService();
		os.deleteOrder(orderid);
		request.setAttribute("msg", "É¾³ý¶©µ¥³É¹¦");
		request.getRequestDispatcher("/Rsuccess.jsp").forward(request, response);
		
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	public void init() throws ServletException {
	}

}
