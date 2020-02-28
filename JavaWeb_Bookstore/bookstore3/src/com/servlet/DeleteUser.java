package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.domain.User;
import com.service.UserService;

@WebServlet("/servlet/DeleteUser")
public class DeleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public DeleteUser() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		UserService us = new UserService();
		if(user!=null){
			System.out.println("�������   :");
			us.deleteUser(user.getId());
			request.getSession().invalidate();//ʹ��sessions����
			request.setAttribute("msg", "�û��Ѿ�ɾ��,�Ҳ�����");
			request.getRequestDispatcher("/Rsuccess.jsp").forward(request, response);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
