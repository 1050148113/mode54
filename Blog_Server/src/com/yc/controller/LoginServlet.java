package com.yc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import com.yc.bean.User;
import com.yc.biz.BizException;
import com.yc.biz.UserBiz;
import com.yc.dao.DBHelper;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login.s")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		     String username=request.getParameter("username");
		     
		     String userpwd=request.getParameter("userpwd");
		     
		   UserBiz ubiz=new UserBiz();
		  // Map<String, String> user = null;
		   User user = null;
		try {
			user = ubiz.login(username, userpwd);
		} catch (BizException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
			 request.setAttribute("msg", e.getMessage());
			 request.getRequestDispatcher("login.jsp").forward(request, response);
			 return;
		}
		
		     
		     if(user==null) {
		    	 
		    	request.setAttribute("msg", "用户名或密码错误");
		    
		    	//JOptionPane.showMessageDialog(null, "用户名或密码错误");
		    	 request.getRequestDispatcher("login.jsp").forward(request, response);
		     }else {
		    	 
		    	 request.getSession().setAttribute("loginedUser", user);
		    	 response.sendRedirect("index.jsp");
		     }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}	
}
