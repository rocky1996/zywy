package com.acat.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.acat.pojo.ComNew;
import com.acat.pojo.LocalDyn;
import com.acat.pojo.Page;
import com.acat.service.LocalDynService;
import com.acat.service.impl.LocalDynServiceImpl;
import com.acat.util.FormBeanUtil;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class LocalDynServlet
 */
@WebServlet("/LocalDynServlet")
public class LocalDynServlet extends HttpServlet {
	private LocalDynService lds = new LocalDynServiceImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operation = request.getParameter("operation");
		if("addComIntro".equals(operation)){
			addComIntro(request,response);
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
		if("updateLocalDynUI".equals(operation)){
			updateLocalDynUI(request,response);
		}
		if("updateLocalDyn".equals(operation)){
			updateLocalDyn(request,response);
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
		if("showDetailAll".equals(operation)){
			showDetailAll(request,response);
		}
	}

	private void showDetailAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		List<LocalDyn> cs = lds.findAll();
		JSONArray jsonArray = JSONArray.fromObject(cs);
		out.print(jsonArray.toString());
	}

	private void showMaxId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LocalDyn ld = lds.findMaxLocalDyn();
		request.setAttribute("ld", ld);
		request.getRequestDispatcher("/manager/LocalDyn/Qianduanshow.jsp").forward(request, response);
	}

	private void DelAllServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ids[] = request.getParameterValues("ids");
		if(ids!=null && ids.length>0){
			for(String id:ids){
				//s.delUserById(id);
				lds.deleteLocalDyn(Integer.parseInt(id));
			}
		}
		request.getRequestDispatcher("/servlet/LocalDynServlet?operation=showAll").forward(request, response);
	}

	private void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String str = request.getParameter("text");
		StringBuilder sb = new StringBuilder(str);
		sb.insert(0, "%").append("%");
		String string = sb.toString();
		List<LocalDyn> ld = lds.query(string);
		request.setAttribute("ld", ld);
		request.getRequestDispatcher("/manager/LocalDyn/showquery.jsp").forward(request, response);
	}

	private void updateLocalDyn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LocalDyn ld = FormBeanUtil.fillFormBean(LocalDyn.class, request);
		lds.updateLocalDyn(ld);
		request.getRequestDispatcher("/servlet/LocalDynServlet?operation=showAll").forward(request, response);
	}

	private void updateLocalDynUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = (Integer.parseInt(request.getParameter("localid")));	
		LocalDyn ld = lds.findLocalDyn(id);
		request.setAttribute("ld", ld);
		request.getRequestDispatcher("/manager/LocalDyn/updateLocalDynUI.jsp").forward(request, response);
	}

	private void deleteOne(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = (Integer.parseInt(request.getParameter("localid")));	
		lds.deleteLocalDyn(id);
		response.sendRedirect("/zywy/servlet/LocalDynServlet?operation=showAll");	
	}

	private void showDetailOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = (Integer.parseInt(request.getParameter("localid")));
		LocalDyn ld = lds.findLocalDyn(id);
		request.setAttribute("ld", ld);
		request.getRequestDispatcher("/manager/LocalDyn/LocalDynShowOne.jsp").forward(request, response);
	}

	private void showAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pagenum = request.getParameter("pagenum");
		Page page = lds.findPageRecords(pagenum);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/manager/LocalDyn/LocalDynShowAll.jsp").forward(request, response);
	}

	private void addComIntro(HttpServletRequest request, HttpServletResponse response) throws IOException {
		LocalDyn ld = new LocalDyn();
		String title = request.getParameter("title");
		String control = request.getParameter("control");
		ld.setTitle(title);
		ld.setControl(control);
		lds.addLocalDyn(ld);
		response.sendRedirect("/zywy/manager/LocalDyn/LocalDynAdd.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
