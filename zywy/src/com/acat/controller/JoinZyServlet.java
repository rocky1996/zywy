package com.acat.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.acat.pojo.CarRep;
import com.acat.pojo.JoinZy;
import com.acat.pojo.Page;
import com.acat.service.JoinZyService;
import com.acat.service.impl.JoinZyServiceImpl;
import com.acat.util.FormBeanUtil;

/**
 * Servlet implementation class JoinZyServlet
 */
@WebServlet("/JoinZyServlet")
public class JoinZyServlet extends HttpServlet {
	private JoinZyService jzs = new JoinZyServiceImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operation = request.getParameter("operation");
		if("addJoinZy".equals(operation)){
			addJoinZy(request,response);
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
		if("updateJoinZyUI".equals(operation)){
			updateJoinZyUI(request,response);
		}
		if("updateJoinZy".equals(operation)){
			updateJoinZy(request,response);
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
		JoinZy jz = jzs.findMaxJoinZy();
		request.setAttribute("jz", jz);
		request.getRequestDispatcher("/manager/JoinZy/Qianduanshow.jsp").forward(request, response);
	}

	private void DelAllServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ids[] = request.getParameterValues("ids");
		if(ids!=null && ids.length>0){
			for(String id:ids){
				//s.delUserById(id);
				jzs.deleteJoinZy(Integer.parseInt(id));
			}
		}
		request.getRequestDispatcher("/servlet/JoinZyServlet?operation=showAll").forward(request, response);
	}

	private void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String str = request.getParameter("text");
		StringBuilder sb = new StringBuilder(str);
		sb.insert(0, "%").append("%");
		String string = sb.toString();
		List<JoinZy> jz = jzs.query(string);
		request.setAttribute("jz", jz);
		request.getRequestDispatcher("/manager/JoinZy/showquery.jsp").forward(request, response);
	}

	private void updateJoinZy(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JoinZy jz = FormBeanUtil.fillFormBean(JoinZy.class, request);
		jzs.updateJoinZy(jz);
		request.getRequestDispatcher("/servlet/JoinZyServlet?operation=showAll").forward(request, response);
	}

	private void updateJoinZyUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = (Integer.parseInt(request.getParameter("joinid")));	
		JoinZy jz = jzs.findJoinZy(id);
		request.setAttribute("jz", jz);
		request.getRequestDispatcher("/manager/CarRep/updateJoinZyUI.jsp").forward(request, response);
	}

	private void deleteOne(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = (Integer.parseInt(request.getParameter("joinid")));	
		jzs.deleteJoinZy(id);
		response.sendRedirect("/zywy/servlet/JoinZyServlet?operation=showAll");
	}

	private void showDetailOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = (Integer.parseInt(request.getParameter("joinid")));
		JoinZy jz = jzs.findJoinZy(id);
		request.setAttribute("jz", jz);
		request.getRequestDispatcher("/manager/JoinZy/JoinZyShowOne.jsp").forward(request, response);
	}

	private void showAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pagenum = request.getParameter("pagenum");
		Page page = jzs.findPageRecords(pagenum);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/manager/JoinZy/JoinZyShowAll.jsp").forward(request, response);
	}

	private void addJoinZy(HttpServletRequest request, HttpServletResponse response) throws IOException {
		JoinZy jz = new JoinZy();
		String title = request.getParameter("title");
		String control = request.getParameter("control");
		jz.setTitle(title);
		jz.setControl(control);
		jzs.addJoinZy(jz);
		response.sendRedirect("/zywy/manager/JoinZy/JoinZyAdd.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
