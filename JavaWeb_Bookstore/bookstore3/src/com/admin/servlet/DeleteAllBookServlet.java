package com.admin.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.service.BookService;
//批量删除图书的servlet
public class DeleteAllBookServlet extends HttpServlet {
	public DeleteAllBookServlet() {
		super();
	}
	public void destroy() {
		super.destroy(); 
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//获取多本图书的id
		String [] ids = request.getParameterValues("ids");
		//并把不同图书的id拼接起来
		String hql="";
		for(int i=0;i<ids.length;i++ ){
			if(i==0) {
				hql= "id="+ids[i];
			}else{
				hql = hql +" or id="+ids[i];
			}
		}
		hql="delete from Product where "+hql;
		//调用删除业务
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
