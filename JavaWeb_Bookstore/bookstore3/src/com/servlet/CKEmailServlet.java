package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.UserService;

//用户注册时,检查邮箱是否已经被注册
@WebServlet("/servlet/ckEmailServlet")
public class CKEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public CKEmailServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		String getemail=request.getParameter("email");
		System.out.println(getemail);
		UserService us= new UserService();
		
		boolean bool=us.ckGetEmail(getemail);
		//已被注册,返回true
		if(bool){
			out.print(true);
		}else{
			out.print(false);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
