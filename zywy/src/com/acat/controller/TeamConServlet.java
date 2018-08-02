package com.acat.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.acat.pojo.OrgStu;
import com.acat.pojo.Page;
import com.acat.pojo.TeamCon;
import com.acat.service.TeamConService;
import com.acat.service.impl.TeamConServiceImpl;
import com.acat.util.FormBeanUtil;

/**
 * Servlet implementation class TeamConServlet
 */
@WebServlet("/TeamConServlet")
public class TeamConServlet extends HttpServlet {
	private TeamConService tcs = new TeamConServiceImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operation = request.getParameter("operation");
		if("addTeamCon".equals(operation)){
			addTeamCon(request,response);
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
		if("updateTeamConUI".equals(operation)){
			updateTeamConUI(request,response);
		}
		if("updateTeamCon".equals(operation)){
			updateTeamCon(request,response);
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
		TeamCon tc = tcs.findMaxTeamCon();
		request.setAttribute("tc", tc);
		request.getRequestDispatcher("/manager/TeamCon/Qianduanshow.jsp").forward(request, response);
	}

	private void DelAllServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ids[] = request.getParameterValues("ids");
		if(ids!=null && ids.length>0){
			for(String id:ids){
				//s.delUserById(id);
				tcs.deleteTeamCon(Integer.parseInt(id));
			}
		}
		request.getRequestDispatcher("/servlet/TeamConServlet?operation=showAll").forward(request, response);
	}

	private void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String str = request.getParameter("text");
		StringBuilder sb = new StringBuilder(str);
		sb.insert(0, "%").append("%");
		String string = sb.toString();
		List<TeamCon> tc = tcs.query(string);
		request.setAttribute("tc", tc);
		request.getRequestDispatcher("/manager/TeamCon/showquery.jsp").forward(request, response);
	}

	private void updateTeamCon(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TeamCon tc = FormBeanUtil.fillFormBean(TeamCon.class, request);
		tcs.updateTeamCon(tc);
		request.getRequestDispatcher("/servlet/TeamConServlet?operation=showAll").forward(request, response);
	}

	private void updateTeamConUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = (Integer.parseInt(request.getParameter("teamid")));	
		TeamCon tc = tcs.findTeamCon(id);
		request.setAttribute("tc", tc);
		request.getRequestDispatcher("/manager/TeamCon/updateTeamConUI.jsp").forward(request, response);
	}

	private void deleteOne(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = (Integer.parseInt(request.getParameter("teamid")));	
		tcs.deleteTeamCon(id);
		response.sendRedirect("/zywy/servlet/TeamConServlet?operation=showAll");	
	}

	private void showDetailOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = (Integer.parseInt(request.getParameter("teamid")));
		TeamCon tc = tcs.findTeamCon(id);
		request.setAttribute("tc", tc);
		request.getRequestDispatcher("/manager/TeamCon/TeamConShowOne.jsp").forward(request, response);		
	}

	private void showAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pagenum = request.getParameter("pagenum");
		Page page = tcs.findPageRecords(pagenum);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/manager/TeamCon/TeamConShowAll.jsp").forward(request, response);
	}

	private void addTeamCon(HttpServletRequest request, HttpServletResponse response) throws IOException {
		TeamCon tc = new TeamCon(); 
		
		String title = request.getParameter("title");
		String control = request.getParameter("control");
		
		System.out.println(title);
		System.out.println(control);
		
		tc.setTitle(title);
		tc.setControl(control);
		tcs.addTeamCon(tc);
		response.sendRedirect("/zywy/manager/TeamCon/TeamConAdd.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
