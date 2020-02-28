package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exception.UserException;
import com.service.UserService;
//�����û�
public class ActiveServlet extends HttpServlet {
	public ActiveServlet() {
		super();
	}
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ�û����ͼ�����
		UserService us = new UserService();
		String name = request.getParameter("name");
		String code = request.getParameter("code");
		try {
			us.active(name,code);
			//ת���ض���,ע��ɹ�
			request.setAttribute("msg", "����ɹ������Ե�¼��");
			request.getRequestDispatcher("/Rsuccess.jsp").forward(request, response);
		} catch (UserException e) {
			e.printStackTrace();
			//ת���ض���,ע��ʧ��
			request.setAttribute("msg", "����ʧ�ܣ������¼���");
			request.getRequestDispatcher("/fail.jsp").forward(request, response);
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
	public void init() throws ServletException {
	}

}
