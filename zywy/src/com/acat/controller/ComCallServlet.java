package com.acat.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.acat.pojo.CarRep;
import com.acat.pojo.ComCall;
import com.acat.pojo.Page;
import com.acat.service.ComCallService;
import com.acat.service.impl.ComCallServiceImpl;
import com.acat.util.FormBeanUtil;

/**
 * Servlet implementation class ComCallServlet
 */
@WebServlet("/ComCallServlet")
public class ComCallServlet extends HttpServlet {
	private ComCallService ccs = new ComCallServiceImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operation = request.getParameter("operation");
		if("addComCall".equals(operation)){
			addComCall(request,response);
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
		if("updateComCallUI".equals(operation)){
			updateComCallUI(request,response);
		}
		if("updateComCall".equals(operation)){
			updateComCall(request,response);
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
		ComCall cc = ccs.findMaxComCall();
		request.setAttribute("cc", cc);
		request.getRequestDispatcher("/manager/ComCall/Qianduanshow.jsp").forward(request, response);
	}

	private void DelAllServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ids[] = request.getParameterValues("ids");
		if(ids!=null && ids.length>0){
			for(String id:ids){
				//s.delUserById(id);
				ccs.deleteComCall(Integer.parseInt(id));
			}
		}
		request.getRequestDispatcher("/servlet/ComCallServlet?operation=showAll").forward(request, response);
	}

	private void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String str = request.getParameter("text");
		StringBuilder sb = new StringBuilder(str);
		sb.insert(0, "%").append("%");
		String string = sb.toString();
		List<ComCall> cc = ccs.query(string);
		request.setAttribute("cc", cc);
		request.getRequestDispatcher("/manager/ComCall/showquery.jsp").forward(request, response);
	}

	private void updateComCall(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ComCall cc = FormBeanUtil.fillFormBean(ComCall.class, request);
		ccs.updateComCall(cc);
		request.getRequestDispatcher("/servlet/ComCallServlet?operation=showAll").forward(request, response);
	}

	private void updateComCallUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = (Integer.parseInt(request.getParameter("comid")));	
		ComCall cc = ccs.findComCall(id);
		request.setAttribute("cc", cc);
		request.getRequestDispatcher("/manager/ComCall/updateComCallUI.jsp").forward(request, response);
	}

	private void deleteOne(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = (Integer.parseInt(request.getParameter("joinid")));	
		ccs.deleteComCall(id);
		response.sendRedirect("/zywy/servlet/ComCallServlet?operation=showAll");	
	}

	private void showDetailOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = (Integer.parseInt(request.getParameter("comid")));
		ComCall cc = ccs.findComCall(id);
		request.setAttribute("cc", cc);
		request.getRequestDispatcher("/manager/ComCall/ComCallShowOne.jsp").forward(request, response);
	}

	private void showAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pagenum = request.getParameter("pagenum");
		Page page = ccs.findPageRecords(pagenum);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/manager/ComCall/ComCallShowAll.jsp").forward(request, response);
	}

	private void addComCall(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ComCall cc = new ComCall();
		String title = request.getParameter("title");
		String control = request.getParameter("control");
		cc.setTitle(title);
		cc.setControl(control);
		ccs.addComCall(cc);
		response.sendRedirect("/zywy/manager/ComCall/ComCallAdd.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
