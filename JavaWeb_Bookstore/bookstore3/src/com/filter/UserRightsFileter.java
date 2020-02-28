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
			//强转
			HttpServletRequest request = (HttpServletRequest) req;
			HttpServletResponse response = (HttpServletResponse) resp;
			
			//处理业务
				//从session中把用户对象得到
				User user = (User) request.getSession().getAttribute("user");
				//判断当前用户权限
				if(user!=null){
					User admin = (User) request.getSession().getAttribute("admin");
					if(!(admin instanceof Admin)) {
						response.getWriter().write("权限不足，请把权限设置成Admin！");
						response.setHeader("refresh", "3;url="+request.getContextPath()+"/index.jsp");
						return;
					}
					//放行
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
