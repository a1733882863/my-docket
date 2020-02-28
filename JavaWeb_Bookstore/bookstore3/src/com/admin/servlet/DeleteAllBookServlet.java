package com.admin.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.service.BookService;
//����ɾ��ͼ���servlet
public class DeleteAllBookServlet extends HttpServlet {
	public DeleteAllBookServlet() {
		super();
	}
	public void destroy() {
		super.destroy(); 
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//��ȡ�౾ͼ���id
		String [] ids = request.getParameterValues("ids");
		//���Ѳ�ͬͼ���idƴ������
		String hql="";
		for(int i=0;i<ids.length;i++ ){
			if(i==0) {
				hql= "id="+ids[i];
			}else{
				hql = hql +" or id="+ids[i];
			}
		}
		hql="delete from Product where "+hql;
		//����ɾ��ҵ��
		BookService bs=new BookService();
		if(!"".equals(hql)){
			bs.delAllBook(hql);
		}
		request.getRequestDispatcher("BookListServlet").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}
	public void init() throws ServletException {
	}

}
