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
import com.domain.User;
import com.service.UserService;

public class ListMyFavoriteServlet extends HttpServlet {

	public ListMyFavoriteServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}


	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//��ȡuser����	
		User user = (User) request.getSession().getAttribute("user");
		//��ѯFavorite��,����Favorite����
		UserService us = new UserService();
		List<FavoriteItem> list = us.getFavoriteItems(user.getId());
		//Favorite.getFavoriteItem()����attribute
		request.setAttribute("favoriteitems", list);
		//ת���ض���
		request.getRequestDispatcher("/favorite.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	public void init() throws ServletException {
	}

}
