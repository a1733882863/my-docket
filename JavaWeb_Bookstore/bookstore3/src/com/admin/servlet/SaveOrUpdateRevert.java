package com.admin.servlet;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.domain.Message;
import com.domain.Revert;
import com.service.MessageService;
//admin修改或回复留言
public class SaveOrUpdateRevert extends HttpServlet {

	public SaveOrUpdateRevert() {
		super();
	}

	public void destroy() {
		super.destroy(); 
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//判断是否有管理员权限
		if(request.getSession().getAttribute("admin")==null){
			request.setAttribute("msg", "权限不足");
			request.getRequestDispatcher("/fail.jsp").forward(request, response);
		}
		//获取用户id 和admin的回复内容
		String content = request.getParameter("content");
		String id=request.getParameter("id");
		if(content.indexOf("\n")!=-1){
			content=content.replaceAll("\n", "<br>");
		}
		//获取用户具体留言内容
		MessageService ms = new MessageService();
		Message message=ms.getMessage(Integer.valueOf(id));
		//不为空才能进行回复
		if(message!=null){
			Revert revert = message.getRevert();
			if(revert==null){
				revert = new Revert();
			}
			revert.setContent(content);
			revert.setRevertTime(new Date(0));
			message.setRevert(revert);
			//保存admin回复信息
			ms.saveMessage(message);
		}
		request.getRequestDispatcher("/servlet/ViewMessageServlet").forward(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	public void init() throws ServletException {
		// Put your code here
	}

}
