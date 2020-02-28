package com.admin.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.domain.Message;
import com.service.MessageService;
//这是负责跳转到管理员回复表单的servlet(把message的值显示到表单里的内容,便于回复)
public class AdminRevertServlet extends HttpServlet {
	public AdminRevertServlet() {
		super();
	}
	public void destroy() {
		super.destroy(); 
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取session里面的管理员,判断是否有权限,防止直接访问
		if(request.getSession().getAttribute("admin")==null){
			request.setAttribute("msg", "权限不足");
			request.getRequestDispatcher("/fail.jsp").forward(request, response);
		}
		//获取留言表message的id
		String id=request.getParameter("id");
		//id如果不为空,admin才能跳转到留言界面
		if(id!=null){
			MessageService ms = new MessageService();
			//获取留言信息,以便于回复
			Message message = ms.getMessage(Integer.valueOf(id));
			//跳转到留言的表单界面
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
