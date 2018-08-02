package com.acat.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.acat.pojo.ComCul;
import com.acat.pojo.ComNew;
import com.acat.pojo.Page;
import com.acat.service.ComNewService;
import com.acat.service.impl.ComNewServiceImpl;
import com.acat.util.FormBeanUtil;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class ComNewServlet
 */
@WebServlet("/ComNewServlet")
public class ComNewServlet extends HttpServlet {
	private ComNewService cns = new ComNewServiceImpl();
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
		if("updateComNewUI".equals(operation)){
			updateComNewUI(request,response);
		}
		if("updateComNew".equals(operation)){
			updateComNew(request,response);
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
		List<ComNew> cs = cns.findAll();
		JSONArray jsonArray = JSONArray.fromObject(cs);
		out.print(jsonArray.toString());
	}

	private void showMaxId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ComNew cn = cns.findMaxComNew();
		request.setAttribute("cn", cn);
		request.getRequestDispatcher("/manager/ComNew/Qianduanshow.jsp").forward(request, response);
	}

	private void DelAllServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ids[] = request.getParameterValues("ids");
		if(ids!=null && ids.length>0){
			for(String id:ids){
				//s.delUserById(id);
				cns.deleteComNew(Integer.parseInt(id));
			}
		}
		request.getRequestDispatcher("/servlet/ComNewServlet?operation=showAll").forward(request, response);
	}

	private void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String str = request.getParameter("text");
		StringBuilder sb = new StringBuilder(str);
		sb.insert(0, "%").append("%");
		String string = sb.toString();
		List<ComNew> cn = cns.query(string);
		request.setAttribute("cn", cn);
		request.getRequestDispatcher("/manager/ComNew/showquery.jsp").forward(request, response);
	}

	private void updateComNew(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ComNew cn = FormBeanUtil.fillFormBean(ComNew.class, request);
		cns.updateComNew(cn);
		request.getRequestDispatcher("/servlet/ComNewServlet?operation=showAll").forward(request, response);
	}

	private void updateComNewUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = (Integer.parseInt(request.getParameter("comid")));	
		ComNew cn = cns.findComNew(id);
		request.setAttribute("cn", cn);
		request.getRequestDispatcher("/manager/ComNew/updateComNewUI.jsp").forward(request, response);
	}

	private void deleteOne(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = (Integer.parseInt(request.getParameter("comid")));	
		cns.deleteComNew(id);
		response.sendRedirect("/zywy/servlet/ComNewServlet?operation=showAll");	
	}

	private void showDetailOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = (Integer.parseInt(request.getParameter("comid")));
		ComNew cn = cns.findComNew(id);
		request.setAttribute("cn", cn);
		request.getRequestDispatcher("/manager/ComNew/ComNewShowOne.jsp").forward(request, response);
	}

	private void showAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pagenum = request.getParameter("pagenum");
		Page page = cns.findPageRecords(pagenum);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/manager/ComNew/ComNewShowAll.jsp").forward(request, response);
	}

	private void addComIntro(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ComNew cn = new ComNew(); 
		String title = request.getParameter("title");
		String control = request.getParameter("control");
		cn.setTitle(title);
		cn.setControl(control);
		cns.addComNew(cn);
		response.sendRedirect("/zywy/manager/ComNew/ComNewAdd.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
