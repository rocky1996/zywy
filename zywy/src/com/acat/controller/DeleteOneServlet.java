package com.acat.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.acat.service.ComIntroService;
import com.acat.service.impl.ComIntroServiceImpl;

/**
 * Servlet implementation class DeleteOneServlet
 */
@WebServlet("/DeleteOneServlet")
public class DeleteOneServlet extends HttpServlet {
	
	private ComIntroService cis = new ComIntroServiceImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = (Integer.parseInt(request.getParameter("comid")));	
		cis.deleteComIntro(id);
		response.sendRedirect("/zyw/servlet/ComIntroServlet?operation=showAll");
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
