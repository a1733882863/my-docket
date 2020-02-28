package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.UserService;

public class UnFavoriteServlet extends HttpServlet {

	public UnFavoriteServlet() {
		super();
	}
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//ȡ�����id
		int id = Integer.parseInt(request.getParameter("id"));
		//ɾ������id�ļ�¼,��������
		UserService us =new UserService();
		us.deleteFavoriteItem(id);
		//ת��
		request.setAttribute("msg", "��ȡ���ղ�");
		request.getRequestDispatcher("/Rsuccess.jsp").forward(request, response);
		
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);

	}

	public void init() throws ServletException {
		// Put your code here
	}

}
