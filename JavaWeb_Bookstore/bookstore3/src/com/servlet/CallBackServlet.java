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
//ģ��֧������
public class CallBackServlet extends HttpServlet {

	public CallBackServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ����id���ܼ�
		String orderid = request.getParameter("orderid");
		String m = request.getParameter("money");
		Double money = Double.valueOf(m);
		
		//��ȡ��ǰ�û���Ǯ�����
		User user = (User) request.getSession().getAttribute("user");
		OrderService os = new OrderService();
		Purse purse = os.finPurseById(user.getId());
		
		//ת�˲���,��Ǯ������ִ��if������,����else
		if (purse != null) {
			if(purse.getMoney()<money){
				request.setAttribute("msg", "<h1 style='color:#FF0000'>����˻�����!</h1>");
				request.getRequestDispatcher("/fail.jsp").forward(request, response);
				return;
			}
			try {
				//���û�Ǯ�����п�Ǯ,�����ֹ���
				os.payOrder(user.getId(), money);
			} catch (UserException e) {
				request.setAttribute("msg", e.getMessage());
				e.printStackTrace();
			}
			os.modifyOrderState(orderid);

			request.setAttribute("msg", "<h1 style='color:#FF0000'>֧���ɹ�!</h1>");
			request.getRequestDispatcher("/Rsuccess.jsp").forward(request, response);
			return;
		} else {
			request.setAttribute("msg", "<h1 style='color:#FF0000'>�㻹û��ͨǮ��֧������,�㿪ͨ������!</h1>");
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
