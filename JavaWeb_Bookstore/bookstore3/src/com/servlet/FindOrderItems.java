package com.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import com.domain.Order;
import com.domain.Orderitem;
import com.domain.Product;
import com.service.OrderService;
//收藏夹里查看收藏几样什么东西
@WebServlet("/servlet/FindOrderItems")
public class FindOrderItems extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FindOrderItems() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("orderid");
		int orderid = Integer.parseInt(id);
		OrderService os = new OrderService();
		List<Product>  list= os.getAllProduct(orderid);
		Order order = os.findOrder(orderid);
		for(Orderitem oi : order.getOrderitem()){
			int i = 0;
			oi.setProduct(list.get(i));
			++i;
		}
		request.setAttribute("order1", order);
		request.getRequestDispatcher("/orderInfo.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
