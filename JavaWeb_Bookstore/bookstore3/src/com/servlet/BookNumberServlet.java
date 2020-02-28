package com.servlet;

import java.io.IOException;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.domain.Product;
//修改购物车里的商品数量,与dao无关
@WebServlet("/servlet/BookNumberServlet")
public class BookNumberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public BookNumberServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String str = request.getParameter("id");
		int id = Integer.parseInt(str);
		String num = request.getParameter("num");
		//注：只能重写id的hashcode
		Product b = new Product();
		b.setId(id);
		HttpSession session = request.getSession();
		Map<Product, String> cart = (Map<Product, String>) session.getAttribute("cart");
		//如果商品数据为0，就删除对象
		if("0".equals(num)){
			cart.remove(b);
		}
		//判断如果找到与id相同的书，
		if(cart.containsKey(b)){
			cart.put(b, num);
		}
		response.sendRedirect(request.getContextPath()+"/cart.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
