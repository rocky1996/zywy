package com.acat.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.acat.pojo.ComIntro;
import com.acat.pojo.OrgStu;
import com.acat.pojo.Page;
import com.acat.service.OrgStuService;
import com.acat.service.impl.OrgStuServiceImpl;
import com.acat.util.FormBeanUtil;

/**
 * Servlet implementation class OrgStuServlet
 */
@WebServlet("/OrgStuServlet")
public class OrgStuServlet extends HttpServlet {
	private OrgStuService oss = new OrgStuServiceImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operation = request.getParameter("operation");
		if("addOrgStu".equals(operation)){
			addOrgStu(request,response);
		}
		if("showAll".equals(operation)){
			showAll(request,response);
		}
		if("showDetailOne".equals(operation)){
			showDetailOne(request,response);
		}
		if("deleteOne".equals(operation)){
			deleteOne(request, response);
		}
		if("updateOrgStuUI".equals(operation)){
			updateOrgStuUI(request,response);
		}
		if("updateOrgStu".equals(operation)){
			updateOrgStu(request,response);
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
		OrgStu os = oss.findMaxOrgStu();
		request.setAttribute("os", os);
		request.getRequestDispatcher("/manager/OrgStu/Qianduanshow.jsp").forward(request, response);	
	}


	private void DelAllServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ids[] = request.getParameterValues("ids");
		if(ids!=null && ids.length>0){
			for(String id:ids){
				//s.delUserById(id);
				oss.deleteOrgStu(Integer.parseInt(id));
			}
		}
		request.getRequestDispatcher("/servlet/OrgStuServlet?operation=showAll").forward(request, response);
	}


	private void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String str = request.getParameter("text");
		StringBuilder sb = new StringBuilder(str);
		sb.insert(0, "%").append("%");
		String string = sb.toString();
		List<OrgStu> cs = oss.query(string);
		request.setAttribute("cs", cs);
		request.getRequestDispatcher("/manager/OrgStu/showquery.jsp").forward(request, response);
	}


	private void updateOrgStu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OrgStu os = FormBeanUtil.fillFormBean(OrgStu.class, request);
		oss.updateOrgStu(os);
		request.getRequestDispatcher("/servlet/OrgStuServlet?operation=showAll").forward(request, response);
	}


	private void updateOrgStuUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = (Integer.parseInt(request.getParameter("orgid")));	
		OrgStu os = oss.findOrgStu(id);
		request.setAttribute("os", os);
		request.getRequestDispatcher("/manager/OrgStu/updateOrgStuUI.jsp").forward(request, response);
	}


	private void deleteOne(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = (Integer.parseInt(request.getParameter("orgid")));	
		oss.deleteOrgStu(id);
		response.sendRedirect("/zywy/servlet/OrgStuServlet?operation=showAll");		
	}


	private void showDetailOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = (Integer.parseInt(request.getParameter("orgid")));
		OrgStu os = oss.findOrgStu(id);
		request.setAttribute("os", os);
		request.getRequestDispatcher("/manager/OrgStu/OrgStuShowOne.jsp").forward(request, response);
	}


	private void showAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pagenum = request.getParameter("pagenum");
		Page page = oss.findPageRecords(pagenum);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/manager/OrgStu/OrgStuShowAll.jsp").forward(request, response);		
	}


	private void addOrgStu(HttpServletRequest request, HttpServletResponse response) throws IOException {
		OrgStu os = new OrgStu();
		String title = request.getParameter("title");
		String control = request.getParameter("control");
		os.setTitle(title);
		os.setControl(control);
		oss.addOrgStu(os);
		response.sendRedirect("/zywy/manager/OrgStu/OrgStuAdd.jsp");
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
