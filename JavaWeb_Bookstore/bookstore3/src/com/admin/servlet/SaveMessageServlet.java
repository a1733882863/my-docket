package com.admin.servlet;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.domain.Message;
import com.domain.User;
import com.service.MessageService;
//用户留言的servlet
public class SaveMessageServlet extends HttpServlet {
	public SaveMessageServlet() {
		super();
	}
	public void destroy() {
		super.destroy(); 
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//判断用户是否登陆
		this.isLogin(request,response);
		//获取表单信息
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		//把空格切换成回车显示到网页
		if(content.indexOf("\n")!= -1){
			content=content.replaceAll("/n", "<br>");
		}
		User user =(User)request.getSession().getAttribute("user");
		//封装表单信息到message
		Message message = new Message();
		message.setTitle(title);
		message.setContent(content);
		message.setCreateTime(new Date(0));
		message.setUser(user);
		MessageService ms = new MessageService();
		//执行保存业务
		ms.saveMessage(message);
		request.getRequestDispatcher("/servlet/ViewMessageServlet").forward(request, response);
		
	}

	public void isLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("user")==null){
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

	public void init() throws ServletException {
	}

}
