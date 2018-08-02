package com.acat.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.acat.pojo.BigThing;
import com.acat.pojo.ComNew;
import com.acat.pojo.MedNew;
import com.acat.pojo.Page;
import com.acat.service.MedNewService;
import com.acat.service.impl.MedNewServiceImpl;
import com.acat.util.FormBeanUtil;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class MedNewServlet
 */
@WebServlet("/MedNewServlet")
public class MedNewServlet extends HttpServlet {
	private MedNewService mns = new MedNewServiceImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operation = request.getParameter("operation");
		if("addMedNew".equals(operation)){
			addMedNew(request,response);
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
		if("updateMedNewUI".equals(operation)){
			updateMedNewUI(request,response);
		}
		if("updateMedNew".equals(operation)){
			updateMedNew(request,response);
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
		if("showDetailAll".equals(operation)){
			showDetailAll(request,response);
		}
	}

	private void showDetailAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		List<MedNew> cs = mns.findAll();
		JSONArray jsonArray = JSONArray.fromObject(cs);
		out.print(jsonArray.toString());
	}

	private void DelAllServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ids[] = request.getParameterValues("ids");
		if(ids!=null && ids.length>0){
			for(String id:ids){
				//s.delUserById(id);
				mns.deleteMedNew(Integer.parseInt(id));
			}
		}
		request.getRequestDispatcher("/servlet/MedNewServlet?operation=showAll").forward(request, response);
	}

	private void showMaxId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MedNew mn = mns.findMaxMedNew();
		request.setAttribute("mn", mn);
		request.getRequestDispatcher("/manager/MedNew/Qianduanshow.jsp").forward(request, response);
	}

	private void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String str = request.getParameter("text");
		StringBuilder sb = new StringBuilder(str);
		sb.insert(0, "%").append("%");
		String string = sb.toString();
		List<MedNew> mn = mns.query(string);
		request.setAttribute("mn", mn);
		request.getRequestDispatcher("/manager/MedNew/showquery.jsp").forward(request, response);
	}

	private void updateMedNew(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MedNew mn = FormBeanUtil.fillFormBean(MedNew.class, request);
		mns.updateMedNew(mn);
		request.getRequestDispatcher("/servlet/MedNewServlet?operation=showAll").forward(request, response);	
	}

	private void updateMedNewUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = (Integer.parseInt(request.getParameter("medid")));	
		MedNew mn = mns.findMedNew(id);
		request.setAttribute("mn", mn);
		request.getRequestDispatcher("/manager/MedNew/updateMedNewUI.jsp").forward(request, response);
	}

	private void deleteOne(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = (Integer.parseInt(request.getParameter("medid")));	
		mns.deleteMedNew(id);
		response.sendRedirect("/zywy/servlet/MedNewServlet?operation=showAll");
	}

	private void showDetailOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = (Integer.parseInt(request.getParameter("medid")));
		MedNew mn = mns.findMedNew(id);
		request.setAttribute("mn", mn);
		request.getRequestDispatcher("/manager/MedNew/MedNewShowOne.jsp").forward(request, response);	
	}

	private void showAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pagenum = request.getParameter("pagenum");
		Page page = mns.findPageRecords(pagenum);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/manager/MedNew/MedNewShowAll.jsp").forward(request, response);
	}

	private void addMedNew(HttpServletRequest request, HttpServletResponse response) throws IOException {
		MedNew mn = new MedNew();
		String title = request.getParameter("title");
		String control = request.getParameter("control");
		mn.setTitle(title);
		mn.setControl(control);
		mns.addMedNew(mn);
		response.sendRedirect("/zywy/manager/MedNew/MedNewAdd.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
