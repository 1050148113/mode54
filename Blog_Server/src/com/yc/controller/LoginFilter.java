package com.yc.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet implementation class LoginFilter
 */
@WebFilter(urlPatterns= {"*.jsp","*.s"})
public class LoginFilter implements Filter {


	@Override
	public void destroy() {
	
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpRequest=(HttpServletRequest) request;
		
		String path=httpRequest.getServletPath();
		if(path.endsWith("user.s")||path.endsWith("login.jsp")) {
			chain.doFilter(request, response);
			return;
		}
		
		if(httpRequest.getSession().getAttribute("loginedUser")!=null) {
			chain.doFilter(request,response);
		}else {
			request.setAttribute("msg","请先登录系统！");
			request.getRequestDispatcher("login.jsp").forward(request,response);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
       
}