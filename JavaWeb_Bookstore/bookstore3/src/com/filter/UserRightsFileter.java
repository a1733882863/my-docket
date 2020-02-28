package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.domain.Admin;
import com.domain.User;

public class UserRightsFileter  implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
			//ǿת
			HttpServletRequest request = (HttpServletRequest) req;
			HttpServletResponse response = (HttpServletResponse) resp;
			
			//����ҵ��
				//��session�а��û�����õ�
				User user = (User) request.getSession().getAttribute("user");
				//�жϵ�ǰ�û�Ȩ��
				if(user!=null){
					User admin = (User) request.getSession().getAttribute("admin");
					if(!(admin instanceof Admin)) {
						response.getWriter().write("Ȩ�޲��㣬���Ȩ�����ó�Admin��");
						response.setHeader("refresh", "3;url="+request.getContextPath()+"/index.jsp");
						return;
					}
					//����
					chain.doFilter(request, response);
					return ;
				}
				
				response.sendRedirect(request.getContextPath()+"/login.jsp");
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
