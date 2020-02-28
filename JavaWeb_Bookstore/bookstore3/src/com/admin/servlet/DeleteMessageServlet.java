package com.admin.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.MessageService;
//����Աɾ�����Ե�servlet
public class DeleteMessageServlet extends HttpServlet {

	public DeleteMessageServlet() {
		super();
	}
	public void destroy() {
		super.destroy(); 
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�ж��Ƿ���Ȩ��ɾ������
		if(request.getSession().getAttribute("admin")==null){
			request.setAttribute("msg", "Ȩ�޲���");
			request.getRequestDispatcher("/fail.jsp").forward(request, response);
		}
		//��ȡ���Ա�message��id
		String id=request.getParameter("id");
		//id��Ϊ�յĻ�,ɾ������
		if(id!=null){
			MessageService ms = new MessageService();
			ms.deleteMessage(Integer.valueOf(id));
			request.getRequestDispatcher("/servlet/ViewMessageServlet").forward(request, response);
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	public void init() throws ServletException {
	}

}
