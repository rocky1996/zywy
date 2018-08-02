package com.acat.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.acat.pojo.ComCul;
import com.acat.pojo.OrgStu;
import com.acat.pojo.Page;
import com.acat.service.ComCulService;
import com.acat.service.impl.ComCulServiceImpl;
import com.acat.util.FormBeanUtil;

/**
 * Servlet implementation class ComCulServlet
 */
@WebServlet("/ComCulServlet")
public class ComCulServlet extends HttpServlet {
	private ComCulService ccs = new ComCulServiceImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operation = request.getParameter("operation");
		if("addComCul".equals(operation)){
			addComCul(request,response);
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
		if("updateComCulUI".equals(operation)){
			updateComCulUI(request,response);
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
		ComCul cc = ccs.findMaxComCul();
		request.setAttribute("cc", cc);
		request.getRequestDispatcher("/manager/ComCul/Qianduanshow.jsp").forward(request, response);	
	}


	private void DelAllServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ids[] = request.getParameterValues("ids");
		if(ids!=null && ids.length>0){
			for(String id:ids){
				//s.delUserById(id);
				ccs.deleteComCul(Integer.parseInt(id));
			}
		}
		request.getRequestDispatcher("/servlet/ComCulServlet?operation=showAll").forward(request, response);
	}

	private void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String str = request.getParameter("text");
		StringBuilder sb = new StringBuilder(str);
		sb.insert(0, "%").append("%");
		String string = sb.toString();
		List<ComCul> cc = ccs.query(string);
		request.setAttribute("cc", cc);
		request.getRequestDispatcher("/manager/ComCul/showquery.jsp").forward(request, response);
	}

	private void updateComCul(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ComCul cc = FormBeanUtil.fillFormBean(ComCul.class, request);
		ccs.updateComCul(cc);
		request.getRequestDispatcher("/servlet/ComCulServlet?operation=showAll").forward(request, response);		
	}

	private void updateComCulUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = (Integer.parseInt(request.getParameter("comid")));	
		ComCul cc = ccs.findComCul(id);
		request.setAttribute("cc", cc);
		request.getRequestDispatcher("/manager/ComCul/updateComCulUI.jsp").forward(request, response);
	}

	private void deleteOne(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = (Integer.parseInt(request.getParameter("comid")));	
		ccs.deleteComCul(id);
		response.sendRedirect("/zywy/servlet/ComCulServlet?operation=showAll");	
	}

	private void showDetailOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = (Integer.parseInt(request.getParameter("comid")));
		ComCul cc = ccs.findComCul(id);
		request.setAttribute("cc", cc);
		request.getRequestDispatcher("/manager/ComCul/ComCulShowOne.jsp").forward(request, response);		
	}

	private void showAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pagenum = request.getParameter("pagenum");
		Page page = ccs.findPageRecords(pagenum);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/manager/ComCul/ComCulShowAll.jsp").forward(request, response);		
	}

	private void addComCul(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ComCul cc = new ComCul(); 
		String title = request.getParameter("title");
		String control = request.getParameter("control");
		cc.setTitle(title);
		cc.setControl(control);
		ccs.addComCul(cc);
		response.sendRedirect("/zywy/manager/ComCul/ComCulAdd.jsp");
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
