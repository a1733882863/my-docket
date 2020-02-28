package com.admin.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.domain.Product;
import com.service.BookService;
//����Ա����id����ͼ��
public class FindBookByIdServlet extends HttpServlet {
	public FindBookByIdServlet() {
		super();
	}
	public void destroy() {
		super.destroy(); 
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡͼ��id
		String id=request.getParameter("id");
		//����id�ҵ�ͼ��
		BookService bs =new BookService();
		Product book=bs.findBookById(id);
		//ת���ض���
		request.setAttribute("book", book);
		request.getRequestDispatcher("/admin/edit.jsp").forward(request, response);

	
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}
	public void init() throws ServletException {
		// Put your code here
	}

}
