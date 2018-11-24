package com.yc.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.yc.bean.Columns;
import com.yc.bean.User;
import com.yc.biz.BizException;
import com.yc.biz.UserBiz;
import com.yc.dao.BeanUtils;

/**
 *   登录、注册、查询、退出、忘记密码  使用op  字段标识业务操作类型
 */
@WebServlet("/user.s")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public UserBiz ubiz=new UserBiz();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");	
		String op=request.getParameter("op");
		if("login".equals(op)) {
			login(request,response);
		}else if("query".equals(op)) {
			query(request,response);
		}else if("add".equals(op)) {
			add(request,response);
		}else if("cate".equals(op)) {
			cate(request,response);
		}else if("adda".equals(op)) {
			adda(request,response);
		}else if("find".equals(op)) {
			find(request,response);
		}else if("save".equals(op)) {
			save(request,response);
		}else if("finda".equals(op)) {
			finda(request,response);
		}else if("savea".equals(op)) {
			savea(request,response);
		}
	} 
	
	private void savea(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		response.setCharacterEncoding("utf-8");	
		Columns columns=BeanUtils.asBean(request, Columns.class);
		String msg;
		try {
			ubiz.savea(columns);
			msg="保存成功";
			
			
		} catch (BizException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			msg=e.getMessage();
		
		}
			
		

		response.getWriter().append(msg);
		
	}
	private void finda(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		response.setCharacterEncoding("utf-8");	
		
		String id=request.getParameter("id");
		Columns columns=ubiz.findaById(id);
		String coumString=JSON.toJSONString(columns);
		response.getWriter().append(coumString);
		
		
	}
	private void save(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		response.setCharacterEncoding("utf-8");	
		User user=BeanUtils.asBean(request, User.class);
		String msg;
		try {
			ubiz.save(user);
			msg="用户信息保存成功";
			
			
		} catch (BizException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			msg=e.getMessage();
		
		}
			
		

		response.getWriter().append(msg);
		
	}
	private void find(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		response.setCharacterEncoding("utf-8");	
		
		String id=request.getParameter("id");
		User user=ubiz.findById(id);
		String userString=JSON.toJSONString(user);
		response.getWriter().append(userString);
		
		
	}
	private void adda(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		response.setCharacterEncoding("utf-8");	
		Columns columns=BeanUtils.asBean(request, Columns.class);
		try {
			  
			ubiz.adda(columns);
			//1
			//request.getRequestDispatcher("user.s?op=query").forward(request, response);
			//2
			//query(request, response);
		} catch (BizException e) {
			
			e.printStackTrace();
			
			request.setAttribute("msg", e.getMessage());
			
		}finally {
			cate(request, response);
		}
		
	}
	
	private void cate(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		response.setCharacterEncoding("utf-8");	
		  
		   Columns columns=BeanUtils.asBean(request, Columns.class);
		   request.setAttribute("userlm",ubiz.findl(columns));
		   request.getRequestDispatcher("category.jsp").forward(request, response);
	}
	


	private void add(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		response.setCharacterEncoding("utf-8");	
		User user=BeanUtils.asBean(request, User.class);
		try {
			  
			ubiz.add(user);
			//1
			//request.getRequestDispatcher("user.s?op=query").forward(request, response);
			//2
			//query(request, response);
		} catch (BizException e) {
			
			e.printStackTrace();
			
			request.setAttribute("msg", e.getMessage());
			
		}finally {
			query(request, response);
		}
		
	}


	private void query(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		
		response.setCharacterEncoding("utf-8");	
		   User user=BeanUtils.asBean(request, User.class);
		   request.setAttribute("userList",ubiz.find(user)); 
		   request.getRequestDispatcher("manage-user.jsp").forward(request, response);
	}


	private void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	     String username=request.getParameter("username");
	     
	     String userpwd=request.getParameter("userpwd");
	     
	   
	  // Map<String, String> user = null;
	   User user = null;
	try {
		user = ubiz.login(username, userpwd);
	} catch (BizException e) {
		
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


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

}
