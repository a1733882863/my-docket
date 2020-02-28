package com.admin.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.MessageService;
//管理员删除留言的servlet
public class DeleteMessageServlet extends HttpServlet {

	public DeleteMessageServlet() {
		super();
	}
	public void destroy() {
		super.destroy(); 
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//判断是否有权限删除留言
		if(request.getSession().getAttribute("admin")==null){
			request.setAttribute("msg", "权限不足");
			request.getRequestDispatcher("/fail.jsp").forward(request, response);
		}
		//获取留言表message的id
		String id=request.getParameter("id");
		//id不为空的话,删除留言
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
