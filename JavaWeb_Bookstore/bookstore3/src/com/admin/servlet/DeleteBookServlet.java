package com.admin.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.BookService;
//ɾ������ͼ���servlet
public class DeleteBookServlet extends HttpServlet {
	public DeleteBookServlet() {
		super();
	}
	public void destroy() {
		super.destroy(); 
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡͼ���id,����ת������
		String id =  request.getParameter("productid");
		int productid = Integer.parseInt(id);
		//����ҵ���߼�,�ж�id�Ƿ�Ϊ��,��Ϊ��ɾ��
		BookService bs = new BookService();
		if(!"".equals(productid)){
			bs.deleteProduct(productid);
		}
		//��ʾɾ������ͼ���б�
		request.getRequestDispatcher("/servlet/BookListServlet").forward(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}
	public void init() throws ServletException {
	}

}
