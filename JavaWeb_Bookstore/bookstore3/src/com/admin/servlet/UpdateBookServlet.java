package com.admin.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.Utils.UploadImg;
import com.domain.Product;
import com.service.BookService;
//修改图书信息
public class UpdateBookServlet extends HttpServlet {
	public UpdateBookServlet() {
		super();
	}
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//封装了图片上传功能
		UploadImg up = new UploadImg();
		Map<String,String[]> map= up.getMap(request, this);	
		Product book = new Product();
		try {
			BeanUtils.populate(book, map);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		//调用业务逻辑,更新图书
		BookService bs = new BookService();
		bs.updateBook(book);
		request.getRequestDispatcher("BookListServlet").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}
	public void init() throws ServletException {
	}

}
