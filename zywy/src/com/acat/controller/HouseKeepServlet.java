package com.acat.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.acat.pojo.ComCul;
import com.acat.pojo.HouseKeep;
import com.acat.pojo.Page;
import com.acat.service.HouseKeepService;
import com.acat.service.impl.HouseKeepServiceImpl;
import com.acat.util.FormBeanUtil;

/**
 * Servlet implementation class HouseKeepServlet
 */
@WebServlet("/HouseKeepServlet")
public class HouseKeepServlet extends HttpServlet {
	private HouseKeepService hks = new HouseKeepServiceImpl(); 
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operation = request.getParameter("operation");
		if("addHouseKeep".equals(operation)){
			addHouseKeep(request,response);
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
		if("updateHouseKeepUI".equals(operation)){
			updateHouseKeepUI(request,response);
		}
		if("updateHouseKeep".equals(operation)){
			updateHouseKeep(request,response);
		}
		if("query".equals(operation)){
			query(request,response);
		}
		if("showMaxId".equals(operation)){
			showMaxId(request,response);
		}
		if("DelAllServlet".equals(operation)){
			DelAllServlet(request,response);
		}
	}

	private void DelAllServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ids[] = request.getParameterValues("ids");
		if(ids!=null && ids.length>0){
			for(String id:ids){
				//s.delUserById(id);
				hks.deleteHouseKeep(Integer.parseInt(id));
			}
		}
		request.getRequestDispatcher("/servlet/HouseKeepServlet?operation=showAll").forward(request, response);
	}

	private void showMaxId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HouseKeep hk = hks.findMaxHouseKeep();
		request.setAttribute("hk", hk);
		request.getRequestDispatcher("/manager/HouseKeep/Qianduanshow.jsp").forward(request, response);
	}

	private void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String str = request.getParameter("text");
		StringBuilder sb = new StringBuilder(str);
		sb.insert(0, "%").append("%");
		String string = sb.toString();
		List<HouseKeep> hk = hks.query(string);
		request.setAttribute("hk", hk);
		request.getRequestDispatcher("/manager/HouseKeep/showquery.jsp").forward(request, response);
	}

	private void updateHouseKeep(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HouseKeep hk = FormBeanUtil.fillFormBean(HouseKeep.class, request);
		hks.updateHouseKeep(hk);
		request.getRequestDispatcher("/servlet/HouseKeepServlet?operation=showAll").forward(request, response);
	}

	private void updateHouseKeepUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = (Integer.parseInt(request.getParameter("houseid")));	
		HouseKeep hk = hks.findHouseKeep(id);
		request.setAttribute("hk", hk);
		request.getRequestDispatcher("/manager/HouseKeep/updateHouseKeepUI.jsp").forward(request, response);
	}

	private void deleteOne(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = (Integer.parseInt(request.getParameter("houseid")));	
		hks.deleteHouseKeep(id);
		response.sendRedirect("/zywy/servlet/HouseKeepServlet?operation=showAll");	
	}

	private void showDetailOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = (Integer.parseInt(request.getParameter("houseid")));
		HouseKeep hk = hks.findHouseKeep(id);
		request.setAttribute("hk", hk);
		request.getRequestDispatcher("/manager/HouseKeep/HouseKeepShowOne.jsp").forward(request, response);	
	}

	private void showAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pagenum = request.getParameter("pagenum");
		Page page = hks.findPageRecords(pagenum);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/manager/HouseKeep/HouseKeepShowAll.jsp").forward(request, response);
	}

	private void addHouseKeep(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HouseKeep hk = new HouseKeep();
		String title = request.getParameter("title");
		String control = request.getParameter("control");
		hk.setTitle(title);
		hk.setControl(control);
		hks.addHouseKeep(hk);
		response.sendRedirect("/zywy/manager/HouseKeep/HouseKeepAdd.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
