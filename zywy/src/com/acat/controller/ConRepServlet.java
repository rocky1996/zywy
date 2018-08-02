package com.acat.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.acat.pojo.ComNew;
import com.acat.pojo.ConRep;
import com.acat.pojo.Page;
import com.acat.service.ConRepService;
import com.acat.service.impl.ConRepServiceImpl;
import com.acat.util.FormBeanUtil;

/**
 * Servlet implementation class ConRepServlet
 */
@WebServlet("/ConRepServlet")
public class ConRepServlet extends HttpServlet {
	private ConRepService crs = new ConRepServiceImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operation = request.getParameter("operation");
		if("addConRep".equals(operation)){
			addConRep(request,response);
		}
		if("showAll".equals(operation)){
			showAll(request,response);
		}
		if("showDetailOne".equals(operation)){
			showDetailOne(request,response);
		}
		if("deleteOne".equals(operation)){
			deleteOne(request,response);
		}
		if("updateConRepUI".equals(operation)){
			updateConRepUI(request,response);
		}
		if("updateComCul".equals(operation)){
			updateComCul(request,response);
		}
		if("query".equals(operation)){
			query(request,response);
		}
		if("DelAllServlet".equals(operation)){
			DelAllServlet(request,response);
		}
		if("showMaxId".equals(operation)){
			showMaxId(request,response);
		}
	}

	private void showMaxId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ConRep cr = crs.findMaxConRep();
		request.setAttribute("cr", cr);
		request.getRequestDispatcher("/manager/ConRep/Qianduanshow.jsp").forward(request, response);
	}

	private void DelAllServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ids[] = request.getParameterValues("ids");
		if(ids!=null && ids.length>0){
			for(String id:ids){
				//s.delUserById(id);
				crs.deleteConRep(Integer.parseInt(id));
			}
		}
		request.getRequestDispatcher("/servlet/ConRepServlet?operation=showAll").forward(request, response);
	}

	private void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String str = request.getParameter("text");
		StringBuilder sb = new StringBuilder(str);
		sb.insert(0, "%").append("%");
		String string = sb.toString();
		List<ConRep> cr = crs.query(string);
		request.setAttribute("cr", cr);
		request.getRequestDispatcher("/manager/ConRep/showquery.jsp").forward(request, response);
	}

	private void updateComCul(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ConRep cr = FormBeanUtil.fillFormBean(ConRep.class, request);
		crs.updateConRep(cr);
		request.getRequestDispatcher("/servlet/ConRepServlet?operation=showAll").forward(request, response);
	}

	private void updateConRepUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = (Integer.parseInt(request.getParameter("conid")));	
		ConRep cr = crs.findConRep(id);
		request.setAttribute("cr", cr);
		request.getRequestDispatcher("/manager/ConRep/updateComNewUI.jsp").forward(request, response);
	}

	private void deleteOne(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = (Integer.parseInt(request.getParameter("conid")));	
		crs.deleteConRep(id);
		response.sendRedirect("/zywy/servlet/ConRepServlet?operation=showAll");	
	}

	private void showDetailOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = (Integer.parseInt(request.getParameter("conid")));
		ConRep cr = crs.findConRep(id);
		request.setAttribute("cr", cr);
		request.getRequestDispatcher("/manager/ConRep/ConRepShowOne.jsp").forward(request, response);
	}

	private void showAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pagenum = request.getParameter("pagenum");
		Page page = crs.findPageRecords(pagenum);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/manager/ConRep/ConRepShowAll.jsp").forward(request, response);
	}

	private void addConRep(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ConRep cr = new ConRep(); 
		String title = request.getParameter("title");
		String control = request.getParameter("control");
		cr.setTitle(title);
		cr.setControl(control);
		crs.addConRep(cr);
		response.sendRedirect("/zywy/manager/ConRep/ConRepAdd.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
