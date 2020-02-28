package com.admin.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.domain.Message;
import com.service.MessageService;
//���Ǹ�����ת������Ա�ظ�����servlet(��message��ֵ��ʾ�����������,���ڻظ�)
public class AdminRevertServlet extends HttpServlet {
	public AdminRevertServlet() {
		super();
	}
	public void destroy() {
		super.destroy(); 
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡsession����Ĺ���Ա,�ж��Ƿ���Ȩ��,��ֱֹ�ӷ���
		if(request.getSession().getAttribute("admin")==null){
			request.setAttribute("msg", "Ȩ�޲���");
			request.getRequestDispatcher("/fail.jsp").forward(request, response);
		}
		//��ȡ���Ա�message��id
		String id=request.getParameter("id");
		//id�����Ϊ��,admin������ת�����Խ���
		if(id!=null){
			MessageService ms = new MessageService();
			//��ȡ������Ϣ,�Ա��ڻظ�
			Message message = ms.getMessage(Integer.valueOf(id));
			//��ת�����Եı�����
			request.setAttribute("message", message);
			request.getRequestDispatcher("/adminrevert.jsp").forward(request, response);
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	public void init() throws ServletException {
	}

}
