package com.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.domain.User;
import com.exception.UserException;
import com.service.UserService;

/**
 * Servlet implementation class Modified
 */
@WebServlet("/servlet/ModifiedUser")
public class ModifiedUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifiedUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//封装数据表单
		User user = new User();
		UserService us = new UserService();
		if ("".equals(request.getParameter("password"))) {
			request.setAttribute("msg", "密码不能为空");
			request.getRequestDispatcher("/fail.jsp").forward(request, response);
			return;
		}
		try {
			BeanUtils .populate(user, request.getParameterMap());
			us.modifiedUser(user);
			request.getSession().invalidate();
			request.setAttribute("msg", "修改成功");
			request.getRequestDispatcher("/Rsuccess.jsp").forward(request, response);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("msg", "修改失败");
			request.getRequestDispatcher("/fail.jsp").forward(request, response);
		}
		catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
