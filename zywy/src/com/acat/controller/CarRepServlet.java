package com.acat.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Request;

import com.acat.pojo.CarRep;
import com.acat.pojo.ComCul;
import com.acat.pojo.Page;
import com.acat.service.CarRepService;
import com.acat.service.impl.CarRepServiceImpl;
import com.acat.util.FormBeanUtil;

/**
 * Servlet implementation class CarRepServlet
 */
@WebServlet("/CarRepServlet")
public class CarRepServlet extends HttpServlet {
	private CarRepService crs = new CarRepServiceImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operation = request.getParameter("operation");
		if("addCarRep".equals(operation)){
			addCarRep(request,response);
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
		if("updateCarRepUI".equals(operation)){
			updateCarRepUI(request,response);
		}
		if("updateCarRep".equals(operation)){
			updateCarRep(request,response);
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
		CarRep cr = crs.findMaxCarRep();
		request.setAttribute("cr", cr);
		request.getRequestDispatcher("/manager/CarRep/Qianduanshow.jsp").forward(request, response);
	}

	private void DelAllServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ids[] = request.getParameterValues("ids");
		if(ids!=null && ids.length>0){
			for(String id:ids){
				//s.delUserById(id);
				crs.deleteCarRep(Integer.parseInt(id));
			}
		}
		request.getRequestDispatcher("/servlet/CarRepServlet?operation=showAll").forward(request, response);
	}

	private void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String str = request.getParameter("text");
		StringBuilder sb = new StringBuilder(str);
		sb.insert(0, "%").append("%");
		String string = sb.toString();
		List<CarRep> cr = crs.query(string);
		request.setAttribute("cr", cr);
		request.getRequestDispatcher("/manager/CarRep/showquery.jsp").forward(request, response);
	}

	private void updateCarRep(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CarRep cr = FormBeanUtil.fillFormBean(CarRep.class, request);
		crs.updateCarRep(cr);
		request.getRequestDispatcher("/servlet/CarRepServlet?operation=showAll").forward(request, response);	
	}

	private void updateCarRepUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = (Integer.parseInt(request.getParameter("carid")));	
		CarRep cr = crs.findCarRep(id);
		request.setAttribute("cr", cr);
		request.getRequestDispatcher("/manager/CarRep/updateCarRepUI.jsp").forward(request, response);
	}

	private void deleteOne(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = (Integer.parseInt(request.getParameter("carid")));	
		crs.deleteCarRep(id);
		response.sendRedirect("/zywy/servlet/CarRepServlet?operation=showAll");	
	}

	private void showDetailOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = (Integer.parseInt(request.getParameter("carid")));
		CarRep cr = crs.findCarRep(id);
		request.setAttribute("cr", cr);
		request.getRequestDispatcher("/manager/CarRep/CarRepShowOne.jsp").forward(request, response);
	}

	private void showAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pagenum = request.getParameter("pagenum");
		Page page = crs.findPageRecords(pagenum);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/manager/CarRep/CarRepShowAll.jsp").forward(request, response);
	}

	private void addCarRep(HttpServletRequest request, HttpServletResponse response) throws IOException {
		CarRep cr = new CarRep();
		String title = request.getParameter("title");
		String control = request.getParameter("control");
		cr.setTitle(title);
		cr.setControl(control);
		crs.addCarRep(cr);
		response.sendRedirect("/zywy/manager/CarRep/CarRepAdd.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
