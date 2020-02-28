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
//admin�޸Ļ�ظ�����
public class SaveOrUpdateRevert extends HttpServlet {

	public SaveOrUpdateRevert() {
		super();
	}

	public void destroy() {
		super.destroy(); 
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�ж��Ƿ��й���ԱȨ��
		if(request.getSession().getAttribute("admin")==null){
			request.setAttribute("msg", "Ȩ�޲���");
			request.getRequestDispatcher("/fail.jsp").forward(request, response);
		}
		//��ȡ�û�id ��admin�Ļظ�����
		String content = request.getParameter("content");
		String id=request.getParameter("id");
		if(content.indexOf("\n")!=-1){
			content=content.replaceAll("\n", "<br>");
		}
		//��ȡ�û�������������
		MessageService ms = new MessageService();
		Message message=ms.getMessage(Integer.valueOf(id));
		//��Ϊ�ղ��ܽ��лظ�
		if(message!=null){
			Revert revert = message.getRevert();
			if(revert==null){
				revert = new Revert();
			}
			revert.setContent(content);
			revert.setRevertTime(new Date(0));
			message.setRevert(revert);
			//����admin�ظ���Ϣ
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
