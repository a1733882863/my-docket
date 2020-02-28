package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exception.UserException;
import com.service.UserService;
//激活用户
public class ActiveServlet extends HttpServlet {
	public ActiveServlet() {
		super();
	}
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取用户名和激活码
		UserService us = new UserService();
		String name = request.getParameter("name");
		String code = request.getParameter("code");
		try {
			us.active(name,code);
			//转发重定向,注册成功
			request.setAttribute("msg", "激活成功，可以登录了");
			request.getRequestDispatcher("/Rsuccess.jsp").forward(request, response);
		} catch (UserException e) {
			e.printStackTrace();
			//转发重定向,注册失败
			request.setAttribute("msg", "激活失败，请重新激活");
			request.getRequestDispatcher("/fail.jsp").forward(request, response);
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
	public void init() throws ServletException {
	}

}
