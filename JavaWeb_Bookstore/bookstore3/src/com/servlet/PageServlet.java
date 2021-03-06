package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.domain.Pagebean;
import com.service.BookService;

@WebServlet("/servlet/PageServlet")
public class PageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public PageServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//导航按钮的查询条件
				String category = request.getParameter("category");
				if(category==null){
					category="";
				}
				//初始化每页显示的记录数
				int pageSize = 5;
				int currentPage = 1;//当前页
				String currPage = request.getParameter("currentPage");//从上一页或下一页得到的数据
				if(currPage!=null&&!"".equals(currPage)){//第一次访问资源时，currPage可能是null
					currentPage = Integer.parseInt(currPage);
				}	
				BookService bs = new BookService();
				//分页查询，并返回PageBean对象
				Pagebean pb = bs.pageBean(currentPage,pageSize,category);
				request.setAttribute("pb", pb);
				request.getRequestDispatcher("/index.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
