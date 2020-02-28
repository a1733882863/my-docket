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
//�û����Ե�servlet
public class SaveMessageServlet extends HttpServlet {
	public SaveMessageServlet() {
		super();
	}
	public void destroy() {
		super.destroy(); 
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�ж��û��Ƿ��½
		this.isLogin(request,response);
		//��ȡ����Ϣ
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		//�ѿո��л��ɻس���ʾ����ҳ
		if(content.indexOf("\n")!= -1){
			content=content.replaceAll("/n", "<br>");
		}
		User user =(User)request.getSession().getAttribute("user");
		//��װ����Ϣ��message
		Message message = new Message();
		message.setTitle(title);
		message.setContent(content);
		message.setCreateTime(new Date(0));
		message.setUser(user);
		MessageService ms = new MessageService();
		//ִ�б���ҵ��
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
