package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.domain.Favorite;
import com.domain.FavoriteItem;
import com.domain.Product;
import com.domain.User;
import com.service.BookService;
import com.service.UserService;
//�ղؼй���
public class FavoriteServlet extends HttpServlet {

	public FavoriteServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String str;
		int pid = Integer.parseInt(request.getParameter("id"));
		User user = (User) request.getSession().getAttribute("user");
		UserService us = new UserService();
		BookService bs = new BookService();
		//����û���һ���ղ���Ʒ,�����ղؼ�
		if(user.getFavorite()==null){
			user=us.createFavorite(user.getId());
			request.getSession().setAttribute("user", user);
		}
		//�����û����ղص�ͼ��id�ҵ��ղ���
		FavoriteItem favoriteitem = us.findFavoriteItem(user,pid);
		if(favoriteitem==null){
			 str="no";
			 favoriteitem =new FavoriteItem();
			 //����id�ҵ�ͼ��
			 Product product = bs.findBookById(pid+""); 
			 //��װfavoriteitem
			 favoriteitem.setPid(pid);
			 favoriteitem.setImg_url(product.getImg_url());
			 favoriteitem.setName(product.getName());
			 favoriteitem.setUrl("http://localhost:8080/"+request.getContextPath()+"/servlet/BookInfoServlet?id="+pid);
			 favoriteitem.setFavorite(user.getFavorite());
			User user1= us.finUserById(user.getId());
			int i = user1.getFavorite().getFavoriteItems().size();
			user1.getFavorite().setNumber(i+1);
			 user1.getFavorite().getFavoriteItems().add(favoriteitem);
			//�����ղ���
			 user1=us.createFavoriteItem (user1);
			 request.getSession().setAttribute("user", user1);
		}else{
			 str="yes";
		}
		System.out.println(pid);
		response.getWriter().write(str);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}
	public void init() throws ServletException {
	}

}
