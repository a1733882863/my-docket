package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.domain.Pagebean;
import com.service.BookService;

@WebServlet("/servlet/PageServlet")
public class PageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public PageServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//������ť�Ĳ�ѯ����
				String category = request.getParameter("category");
				if(category==null){
					category="";
				}
				//��ʼ��ÿҳ��ʾ�ļ�¼��
				int pageSize = 5;
				int currentPage = 1;//��ǰҳ
				String currPage = request.getParameter("currentPage");//����һҳ����һҳ�õ�������
				if(currPage!=null&&!"".equals(currPage)){//��һ�η�����Դʱ��currPage������null
					currentPage = Integer.parseInt(currPage);
				}	
				BookService bs = new BookService();
				//��ҳ��ѯ��������PageBean����
				Pagebean pb = bs.pageBean(currentPage,pageSize,category);
				request.setAttribute("pb", pb);
				request.getRequestDispatcher("/index.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
