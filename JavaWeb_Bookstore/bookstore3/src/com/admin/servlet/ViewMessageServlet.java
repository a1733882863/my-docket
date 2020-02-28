package com.admin.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.MessageDao;
import com.domain.PageMessageBean;
import com.service.MessageService;
//�鿴���Ե�serlet
public class ViewMessageServlet extends HttpServlet {
	public ViewMessageServlet() {
		super();
	}
	public void destroy() {
		super.destroy(); 
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ��ǰҳ
		String page = request.getParameter("currPage");
		int currPage = 1;
		int pageSize = 5;
		if(page!=null){
			currPage = Integer.parseInt(page);
		}
		//����ҵ���߼�
		MessageService ms = new MessageService();
		PageMessageBean mb =ms.findPage(currPage, pageSize);
		//ת���ض���
		request.setAttribute("pagebean", mb);
		request.getRequestDispatcher("/listmessage.jsp").forward(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}
	public void init() throws ServletException {
	}

}
