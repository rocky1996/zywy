package com.acat.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.acat.pojo.ConRep;
import com.acat.pojo.OwnHome;
import com.acat.pojo.Page;
import com.acat.service.OwnHomeService;
import com.acat.service.impl.OwnHomeServiceImpl;
import com.acat.util.FormBeanUtil;

/**
 * Servlet implementation class OwnHomeServlet
 */
@WebServlet("/OwnHomeServlet")
public class OwnHomeServlet extends HttpServlet {
	private OwnHomeService ohs = new OwnHomeServiceImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operation = request.getParameter("operation");
		if("addOwnHome".equals(operation)){
			addOwnHome(request,response);
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
		if("updateOwnHomeUI".equals(operation)){
			updateOwnHomeUI(request,response);
		}
		if("updateOwnHome".equals(operation)){
			updateOwnHome(request,response);
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
		OwnHome oh = ohs.findMaxOwnHome();
		request.setAttribute("oh", oh);
		request.getRequestDispatcher("/manager/OwnHome/Qianduanshow.jsp").forward(request, response);
	}

	private void DelAllServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ids[] = request.getParameterValues("ids");
		if(ids!=null && ids.length>0){
			for(String id:ids){
				//s.delUserById(id);
				ohs.deleteOwnHome(Integer.parseInt(id));
			}
		}
		request.getRequestDispatcher("/servlet/OwnHomeServlet?operation=showAll").forward(request, response);
	}

	private void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String str = request.getParameter("text");
		StringBuilder sb = new StringBuilder(str);
		sb.insert(0, "%").append("%");
		String string = sb.toString();
		List<OwnHome> oh = ohs.query(string);
		request.setAttribute("oh", oh);
		request.getRequestDispatcher("/manager/OwnHome/showquery.jsp").forward(request, response);
	}

	private void updateOwnHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OwnHome oh = FormBeanUtil.fillFormBean(OwnHome.class, request);
		ohs.updateOwnHome(oh);
		request.getRequestDispatcher("/servlet/OwnHomeServlet?operation=showAll").forward(request, response);
	}

	private void updateOwnHomeUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = (Integer.parseInt(request.getParameter("homeid")));	
		OwnHome oh = ohs.findOwnHome(id);
		request.setAttribute("oh", oh);
		request.getRequestDispatcher("/manager/OwnHome/updateOwnHomeUI.jsp").forward(request, response);
	}

	private void deleteOne(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = (Integer.parseInt(request.getParameter("homeid")));	
		ohs.deleteOwnHome(id);
		response.sendRedirect("/zywy/servlet/OwnHomeServlet?operation=showAll");	
	}

	private void showDetailOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = (Integer.parseInt(request.getParameter("homeid")));
		OwnHome oh = ohs.findOwnHome(id);
		request.setAttribute("oh", oh);
		request.getRequestDispatcher("/manager/OwnHome/OwnHomeShowOne.jsp").forward(request, response);
	}

	private void showAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pagenum = request.getParameter("pagenum");
		Page page = ohs.findPageRecords(pagenum);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/manager/OwnHome/OwnHomeShowAll.jsp").forward(request, response);
	}

	private void addOwnHome(HttpServletRequest request, HttpServletResponse response) throws IOException {
		OwnHome oh = new OwnHome();
		String title = request.getParameter("title");
		String control = request.getParameter("control");
		oh.setTitle(title);
		oh.setControl(control);
		ohs.addOwnHome(oh);
		response.sendRedirect("/zywy/manager/OwnHome/OwnHomeAdd.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
