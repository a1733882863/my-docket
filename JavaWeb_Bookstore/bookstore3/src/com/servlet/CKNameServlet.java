package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.UserService;

//”ÎckemailÕ¨¿Ì
@WebServlet("/servlet/ckNameServlet")
public class CKNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public CKNameServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		PrintWriter out=response.getWriter();
		String getname=request.getParameter("name");
		UserService us= new UserService();
		
		boolean bool=us.ckGetName(getname);
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
