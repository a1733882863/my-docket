package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.domain.User;
import com.service.OrderService;
//��ͨǮ��֧������
public class CreatePurseServlet extends HttpServlet {
	public CreatePurseServlet() {
		super();
	}
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��session���ȡ�û�
		User user = (User) request.getSession().getAttribute("user");
		//��ʵû��
		String mm=request.getParameter("money");
		double money = Double.parseDouble(mm);
		OrderService os = new OrderService();

		os.createPurse(user,money);
		
		request.setAttribute("msg", "Ǯ����ͨ�ɹ�");
		request.getRequestDispatcher("/Rsuccess.jsp").forward(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

	public void init() throws ServletException {
	}

}
