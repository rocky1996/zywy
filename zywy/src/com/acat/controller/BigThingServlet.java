package com.acat.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.acat.pojo.BigThing;
import com.acat.pojo.ComCul;
import com.acat.pojo.Page;
import com.acat.service.BigThingService;
import com.acat.service.impl.BigThingServiceImpl;
import com.acat.util.FormBeanUtil;

/**
 * Servlet implementation class BigThingServlet
 */
@WebServlet("/BigThingServlet")
public class BigThingServlet extends HttpServlet {
	private BigThingService bts = new BigThingServiceImpl();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operation = request.getParameter("operation");
		if("addBigThing".equals(operation)){
			addBigThing(request,response);
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
		if("updateBigThingUI".equals(operation)){
			updateBigThingUI(request,response);
		}
		if("updateBigThing".equals(operation)){
			updateBigThing(request,response);
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
		BigThing bt = bts.findMaxBigThing();
		request.setAttribute("bt", bt);
		request.getRequestDispatcher("/manager/BigThing/Qianduanshow.jsp").forward(request, response);	
	}

	private void DelAllServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ids[] = request.getParameterValues("ids");
		if(ids!=null && ids.length>0){
			for(String id:ids){
				//s.delUserById(id);
				bts.deleteBigThing(Integer.parseInt(id));
			}
		}
		request.getRequestDispatcher("/servlet/BigThingServlet?operation=showAll").forward(request, response);
	}

	private void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String str = request.getParameter("text");
		StringBuilder sb = new StringBuilder(str);
		sb.insert(0, "%").append("%");
		String string = sb.toString();
		List<BigThing> bt = bts.query(string);
		request.setAttribute("bt", bt);
		request.getRequestDispatcher("/manager/BigThing/showquery.jsp").forward(request, response);
	}

	private void updateBigThing(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BigThing bt = FormBeanUtil.fillFormBean(BigThing.class, request);
		bts.updateBigThing(bt);
		request.getRequestDispatcher("/servlet/BigThingServlet?operation=showAll").forward(request, response);	
	}

	private void updateBigThingUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = (Integer.parseInt(request.getParameter("bigid")));	
		BigThing bt = bts.findBigThing(id);
		request.setAttribute("bt", bt);
		request.getRequestDispatcher("/manager/BigThing/updateBigThingUI.jsp").forward(request, response);
	}

	private void deleteOne(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = (Integer.parseInt(request.getParameter("bigid")));	
		bts.deleteBigThing(id);
		response.sendRedirect("/zywy/servlet/BigThingServlet?operation=showAll");
	}

	private void showDetailOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = (Integer.parseInt(request.getParameter("bigid")));
		BigThing bt = bts.findBigThing(id);
		request.setAttribute("bt", bt);
		request.getRequestDispatcher("/manager/BigThing/BigThingShowOne.jsp").forward(request, response);	
	}

	private void showAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pagenum = request.getParameter("pagenum");
		Page page = bts.findPageRecords(pagenum);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/manager/BigThing/BigThingShowAll.jsp").forward(request, response);
	}

	private void addBigThing(HttpServletRequest request, HttpServletResponse response) throws IOException {
		BigThing bt = new BigThing();
		String title = request.getParameter("title");
		String control = request.getParameter("control");
		bt.setTitle(title);
		bt.setControl(control);
		bts.addBigThing(bt);
		response.sendRedirect("/zywy/manager/BigThing/BigThingAdd.jsp");		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
