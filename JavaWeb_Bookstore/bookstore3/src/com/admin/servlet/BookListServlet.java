package com.admin.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.domain.Pagebean;
import com.service.BookService;
//���ȫ��ͼ��,ͼ������������servlet,���ҳ��ʾͼ����Ϣ(ͼ�����ϵͳ��)
public class BookListServlet extends HttpServlet {
	public BookListServlet() {
		super();
	}
	public void destroy() {
		super.destroy(); 
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//������ť�Ĳ�ѯ����
		String category = request.getParameter("category");
		if(category==null){
			category="";
		}
		//��ʼ��ÿҳ��ʾ�ļ�¼��
		int pageSize = 10;
		//��ǰҳ,Ĭ�����1
		int currentPage = 1;
		//����һҳ����һҳ�õ�������
		String currPage = request.getParameter("currentPage");
		//��һ�η�����Դʱ��currPage������null
		if(currPage!=null&&!"".equals(currPage)){
			currentPage = Integer.parseInt(currPage);
		}	
		BookService bs = new BookService();
		//��ҳ��ѯ��������PageBean����
		//�ѵ�ǰҳ��book����pageBean
		Pagebean pb = bs.findAllBooks(currentPage,pageSize,category);
			//�ѷ��صĴ���ͼ�鼯��list�Ż�request������
			request.setAttribute("pb", pb);
			//ת����ͼ�����ϵͳ�ķ�ҳ��ʾ����
			request.getRequestDispatcher("/admin/list.jsp").forward(request, response);
		
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	public void init() throws ServletException {
	}

}
