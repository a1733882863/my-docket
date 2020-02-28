package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.domain.Admin;
import com.domain.User;
import com.exception.UserException;
import com.service.UserService;

/**
 * Servlet implementation class LogServlet
 */
@WebServlet("/servlet/LogServlet")
public class LogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		User user = new User();
		UserService us = new UserService();
		try {
			// BeanUtils.populate(user, request.getParameterMap());
			user.setName(request.getParameter("name"));
			user.setPassword(request.getParameter("password"));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		/*
		 * Cookie cookie1=null; Cookie [] cookies =request.getCookies(); for(int
		 * i =0;cookies!=null && i<cookies.length;i++){
		 * if("user".equals(cookies[i].getName())){ cookie1=cookies[i]; } }
		 * if(cookie1!=null){ String value =cookie1.getValue(); String[]
		 * values=value.split("&"); String name=values[0]; String
		 * word=values[1];
		 * 
		 * }
		 */

		if ("".equals(user.getName())) {
			request.setAttribute("error1", "�û�������Ϊ��");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		if ("".equals(user.getPassword())) {
			request.setAttribute("error2", "���벻��Ϊ��");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		String autolog = request.getParameter("autolog");
		Cookie cookie = new Cookie("user", user.getName() + "&" + user.getPassword());
		cookie.setPath("/");
		try {
			user = us.login(user);// ��User�ٸ�ֵ
			if (user != null) { // ��ʱ�鵽�û�

				if (autolog != null) {

					cookie.setMaxAge(60);// 60���ʧЧ
				} else {// ���cookie��������

					cookie.setMaxAge(0);
				}

				response.addCookie(cookie);// ��cookie���浽�ͻ���
				request.getSession().setAttribute("user", user);// �����¼�ɹ����Ͱ�user����ŵ�session����
				if(user instanceof Admin ){
					request.getSession().setAttribute("admin", user);//���Դ����	
				}
												// ��
				// request.getRequestDispatcher("/index.jsp").forward(request,
				// response);
				// response.sendRedirect(request.getContextPath()+"/index.jsp");
				request.getRequestDispatcher("/index.jsp").forward(request, response);
				return;

			}
			/*
			 * else{ request.setAttribute("error1", "�û������ڻ����������");
			 * request.getRequestDispatcher("/login.jsp").forward(request,
			 * response); }
			 */
		} catch (UserException e) {
			if (autolog == null) {
				cookie.setMaxAge(60);// 60���ʧЧ
			} else {// ���cookie��������
				cookie.setMaxAge(0);
			}
			e.printStackTrace();
			request.setAttribute("error1", e.getMessage());
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		} catch (RuntimeException e) {
			request.setAttribute("error1", "������æ��logservlet");
			request.getRequestDispatcher("/servletBuzy.jsp").forward(request, response);
		} catch (Exception e) {

		} finally {
			if (autolog != null) {

				cookie.setMaxAge(60);// 60���ʧЧ
			} else {// ���cookie��������

				cookie.setMaxAge(0);
			}
		}

		// �ַ�ת��
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
