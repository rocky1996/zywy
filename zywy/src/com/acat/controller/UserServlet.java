package com.acat.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.acat.pojo.User;
import com.acat.service.UserService;
import com.acat.service.impl.UserServiceImpl;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends HttpServlet {
	private UserService us = new UserServiceImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operation = request.getParameter("operation");
		if("login".equals(operation)){
			login(request,response);
		}
		
		if("logout".equals(operation)){
			logout(request,response);
		}
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		response.sendRedirect("/zywy/index.jsp");		
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		//response.getWriter().write(username);
		
		System.out.println("---------------");
		System.out.println(username);
		System.out.println(password);
		System.out.println("+++++++++++++++");
		
		User u = us.login(username, password);
		List<User> cs = us.findAll();
		if(u==null){
			request.setAttribute("u", "<script type='text/javascript'>alert('账号或者密码错误!!!')</script>");
			request.getRequestDispatcher("/manager/login.jsp").forward(request, response);
		}else{
			request.getSession().setAttribute("u", u);
			//request.getSession().setMaxInactiveInterval(6*60);
			//request.getRequestDispatcher("/manager/houtai.jsp").forward(request, response);
			response.sendRedirect("/zywy/manager/houtai.jsp");
		}		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
