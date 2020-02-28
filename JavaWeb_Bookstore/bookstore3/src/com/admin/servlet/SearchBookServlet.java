package com.admin.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.domain.Pagebean;
import com.domain.Product;
import com.service.BookService;
//����ͼ��,admin���
public class SearchBookServlet extends HttpServlet {
	public SearchBookServlet() {
		super();
	}
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ����ҳ�ı�����
		String id = request.getParameter("id");
		String category = request.getParameter("category");
		String name = request.getParameter("name");
		String minprice = request.getParameter("minprice");
		String maxprice = request.getParameter("maxprice");
		//����ҵ���߼�
		BookService bs= new BookService();
		List<Product> list=bs.searchBook(id,category,name,minprice,maxprice);
		//ת���ض���,��ʾͼ��
		request.setAttribute("booklist", list);
		request.getRequestDispatcher("/admin/listsearchbook.jsp").forward(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

	public void init() throws ServletException {
	}

}
