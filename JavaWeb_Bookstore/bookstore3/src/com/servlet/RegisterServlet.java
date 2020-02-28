package com.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.HibernateException;
import org.junit.Test;

import com.Utils.SendEmail;
import com.domain.Guest;
import com.domain.User;
import com.exception.UserException;
import com.service.UserService;
import com.domain.UserForm;

/**
 * Servlet implementation class Register
 */
@WebServlet("/servletRegister")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegisterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 检验阶段
		UserForm uf = new UserForm();
		try {
			BeanUtils.populate(uf, request.getParameterMap());
			uf.setCkcode2((String) request.getSession().getAttribute("checkcode_session"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (!uf.validate()) { // 说明有错误信息
			request.setAttribute("uf", uf);
			request.getRequestDispatcher("/register.jsp").forward(request, response);
			return;
		}
		// 检验成功，进入注册阶段
		User user = new Guest();
		UserService us = new UserService();
		try {
			BeanUtils.populate(user, request.getParameterMap());
			user.setActiveCode(UUID.randomUUID().toString());
			us.register(user);
			System.out.println(user);
			//request.getSession().setAttribute("user", user);
			request.setAttribute("msg", "注册成功，请前往邮箱激活");
			request.getRequestDispatcher("/Rsuccess.jsp").forward(request, response);
			String emailMsg = "尊敬的" + user.getName()
					+ "用户，请前往邮箱<a href='http://localhost:8080/bookstore3/servlet/ActiveServlet?name=" + user.getName()
					+ "&code=" + user.getActiveCode() + "'>激活</a>";
			SendEmail.sendMail(user.getEmail(), emailMsg);
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
