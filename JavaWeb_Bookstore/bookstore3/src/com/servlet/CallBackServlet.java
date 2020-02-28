package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.StaleObjectStateException;

import com.domain.Purse;
import com.domain.User;
import com.exception.UserException;
import com.service.OrderService;
//模拟支付功能
public class CallBackServlet extends HttpServlet {

	public CallBackServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取订单id和总价
		String orderid = request.getParameter("orderid");
		String m = request.getParameter("money");
		Double money = Double.valueOf(m);
		
		//获取当前用户的钱包余额
		User user = (User) request.getSession().getAttribute("user");
		OrderService os = new OrderService();
		Purse purse = os.finPurseById(user.getId());
		
		//转账操作,有钱包功能执行if里的语句,否者else
		if (purse != null) {
			if(purse.getMoney()<money){
				request.setAttribute("msg", "<h1 style='color:#FF0000'>你的账户余额不足!</h1>");
				request.getRequestDispatcher("/fail.jsp").forward(request, response);
				return;
			}
			try {
				//对用户钱包进行扣钱,加了乐观锁
				os.payOrder(user.getId(), money);
			} catch (UserException e) {
				request.setAttribute("msg", e.getMessage());
				e.printStackTrace();
			}
			os.modifyOrderState(orderid);

			request.setAttribute("msg", "<h1 style='color:#FF0000'>支付成功!</h1>");
			request.getRequestDispatcher("/Rsuccess.jsp").forward(request, response);
			return;
		} else {
			request.setAttribute("msg", "<h1 style='color:#FF0000'>你还没开通钱包支付功能,你开通后再来!</h1>");
			request.getRequestDispatcher("/fail.jsp").forward(request, response);
			return;
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);

	}

	public void init() throws ServletException {
		// Put your code here
	}

}
