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
//�޸�ͼ����Ϣ
public class UpdateBookServlet extends HttpServlet {
	public UpdateBookServlet() {
		super();
	}
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//��װ��ͼƬ�ϴ�����
		UploadImg up = new UploadImg();
		Map<String,String[]> map= up.getMap(request, this);	
		Product book = new Product();
		try {
			BeanUtils.populate(book, map);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		//����ҵ���߼�,����ͼ��
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
