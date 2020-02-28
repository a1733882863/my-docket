package com.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.domain.Order;
import com.domain.User;
import com.service.OrderService;

@WebServlet("/servlet/FindOrderServlet")
public class FindOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		
		OrderService os = new OrderService();
		//需要修改成list的order
		System.out.println("FindOrderServlet 1:"+user.getId());
		List<Order> orderlist = os.findOrderList(user.getId());
		System.out.println("FindOrderServlet 2:"+orderlist);
		//List<Order> list = new ArrayList<Order>();
		//list.add(order);
		request.setAttribute("orders", orderlist);
		//System.out.println("FindOrderServlet 1:");
		//System.out.println(order.toString());
		request.setAttribute("count", orderlist.size());
		//System.out.println(order.getOrderitem().size());
		request.getRequestDispatcher("/listorder.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
