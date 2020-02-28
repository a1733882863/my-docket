package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.domain.Product;
import com.service.BookService;

@WebServlet("/servlet/BookInfoServlet")
public class BookInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public BookInfoServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");

		BookService bs = new BookService();
		Product book = bs.findBookById(id);

		request.setAttribute("b", book);
		request.getRequestDispatcher("/bookinfo.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doGet(request, response);
	}

}
