package com.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.domain.Order;
import com.domain.Orderitem;
import com.domain.Product;
import com.domain.User;
import com.service.OrderService;
@WebServlet("/servlet/AddOrderServlet")
//添加订单
public class AddOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public AddOrderServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String time = request.getParameter("ordertime1");
		java.sql.Date date=java.sql.Date.valueOf(time);
		Order order ;
		User user = (User) request.getSession().getAttribute("user");
		order = new Order();
		//获取购物车的Map,并封装
		Map<String, String[]> map = request.getParameterMap();
		try {
			
			BeanUtils.populate(order, map);
			order.setOrdertime(date);
		} catch (IllegalAccessException e) {
			System.out.println("1这里");
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			System.out.println("2这里");
			e.printStackTrace();
		} 
		Map<Product,String> cart= (Map<Product, String>) request.getSession().getAttribute("cart");
		OrderService os = new OrderService();
		Set<Orderitem> set = new HashSet<Orderitem>();
		//把book装到orderitem里,
		for(Product p : cart.keySet()){
			Orderitem orderitem = new Orderitem();
			orderitem.setPid(p.getId());
			orderitem.setBuynum(Integer.parseInt(cart.get(p)));
			set.add(orderitem);
		}
			//把orderitem装到order里
			order.setOrderitem(set);
			//创建订单
			os.createOrder(order,user);
			request.setAttribute("msg", "成功提交订单");
			request.getRequestDispatcher("/Rsuccess.jsp").forward(request, response);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
