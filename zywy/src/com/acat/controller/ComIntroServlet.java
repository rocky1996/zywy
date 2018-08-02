package com.acat.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.acat.pojo.ComIntro;
import com.acat.pojo.Page;
import com.acat.service.ComIntroService;
import com.acat.service.impl.ComIntroServiceImpl;
import com.acat.util.FormBeanUtil;
import com.acat.util.StringTest;
import com.acat.util.getNowTime;

/**
 * Servlet implementation class ComIntroServlet
 */
@WebServlet("/ComIntroServlet")
public class ComIntroServlet extends HttpServlet {
	private ComIntroService cis = new ComIntroServiceImpl();
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
		
		if("updateComIntroUI".equals(operation)){
			updateComIntroUI(request,response);
		}
		
		if("updateComIntro".equals(operation)){
			updateComIntro(request,response);
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
		ComIntro ci = cis.findMaxComIntro();
		request.setAttribute("ci", ci);
		request.getRequestDispatcher("/manager/ComIntro/Qianduanshow.jsp").forward(request, response);	
	}

	private void DelAllServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ids[] = request.getParameterValues("ids");
		if(ids!=null && ids.length>0){
			for(String id:ids){
				//s.delUserById(id);
				cis.deleteComIntro(Integer.parseInt(id));
			}
		}
		request.getRequestDispatcher("/servlet/ComIntroServlet?operation=showAll").forward(request, response);	
	}

	private void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String str = request.getParameter("text");
		
//		System.out.println("***");
//		System.out.println(str);
//		System.out.println("^^^");
//		
		StringBuilder sb = new StringBuilder(str);
		sb.insert(0, "%").append("%");
		String string = sb.toString();
//		
//		System.out.println("***");
//		System.out.println(string);
//		System.out.println("^^^");
//		
		List<ComIntro> cs = cis.query(string);
		request.setAttribute("cs", cs);
		request.getRequestDispatcher("/manager/ComIntro/showquery.jsp").forward(request, response);	
	}

	private void updateComIntro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		ComIntro ci = new ComIntro();
//		String title = request.getParameter("title");
//		String control = request.getParameter("control");
//		
//		System.out.println(title);
//		System.out.println(control);
//		
//		ci.setTitle(title);
//		System.out.println("111");
//		ci.setControl(control);
//		System.out.println("222");
		
		ComIntro ci = FormBeanUtil.fillFormBean(ComIntro.class, request);
		System.out.println(ci.getTitle());
		cis.updateComIntro(ci);
		System.out.println("###");
		//request.setAttribute("uu", "<script type='text/javascript'>alert('上传成功!!!')</script>");
		request.getRequestDispatcher("/servlet/ComIntroServlet?operation=showAll").forward(request, response);	
	}

	private void updateComIntroUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = (Integer.parseInt(request.getParameter("comid")));	
		ComIntro ci = cis.findComIntro(id);
		request.setAttribute("ci", ci);
		request.getRequestDispatcher("/manager/ComIntro/updateComIntroUI.jsp").forward(request, response);
	}

	private void deleteOne(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = (Integer.parseInt(request.getParameter("comid")));	
		cis.deleteComIntro(id);
		response.sendRedirect("/zywy/servlet/ComIntroServlet?operation=showAll");
	}

	private void showDetailOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = (Integer.parseInt(request.getParameter("comid")));	
		
		System.out.println(id);
		
		ComIntro ci = cis.findComIntro(id);
		request.setAttribute("ci", ci);
		request.getRequestDispatcher("/manager/ComIntro/ComIntroShowOne.jsp").forward(request, response);
	}

	private void showAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		List<ComIntro> ci = cis.findAll();
//		request.setAttribute("ci", ci);
//		request.getRequestDispatcher("/manager/ComIntro/ComIntroShowAll.jsp").forward(request, response);
		
		String pagenum = request.getParameter("pagenum");
		Page page = cis.findPageRecords(pagenum);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/manager/ComIntro/ComIntroShowAll.jsp").forward(request, response);
		
//		List<ComIntro> cs = cis.findAll();
//		request.getSession().setAttribute("cs", cs);
//		response.sendRedirect("/zyw/ceshi.jsp");
	}

	private void addComIntro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
//		String resultPath = "";
//		String storePath = getServletContext().getRealPath("/files");
//		
//		String value = null;
//		InputStream in = null;
//		OutputStream out = null;
		
//		 FileOutputStream fos = null;//写
//		 FileInputStream fis = null;//读 
		
//		System.out.println(storePath);
		
//		System.out.println("----------1-----------");
		
//		try {
//			ComIntro ci = new ComIntro();
//			
//			DiskFileItemFactory factory = new DiskFileItemFactory();
//			ServletFileUpload upload = new ServletFileUpload(factory);
//			
//			System.out.println("----------2-----------");
//			
//			//解析请求
//			List<FileItem> items = upload.parseRequest(request);
//			
//			System.out.println("@1");
//			
//			for(FileItem item:items){
//				if(item.isFormField()){
//					//封装数据到JavaBean中
//					String fieldName = item.getFieldName();//字段名，即javabean的属性名，除了图片
//					String fieldValue = item.getString(request.getCharacterEncoding());
//					BeanUtils.setProperty(ci, fieldName, fieldValue);//除了图片路径，其他数据都有了
//				}
////				else{
////					in = item.getInputStream();
////					String fileName = getNowTime.getTime().toString();
////					
////					ci.setControl(fileName);
////					OutputStream out = new FileOutputStream(storePath+"\\"+fileName);
////					byte[] buf = new byte[1024];
////					int len = -1;
////					while((len=in.read(buf))!=-1){
////						out.write(buf, 0, len);
////					}
////					out.close();
////					in.close();
////				}
//			}
//			cis.addComIntro(ci);
//			request.getSession().setAttribute("ci", ci);
//			request.setAttribute("uu", "<script type='text/javascript'>alert('上传成功!!!')</script>");
//			request.getRequestDispatcher("/manager/ComIntro/ComIntroAdd.jsp").forward(request, response);
//		} catch (Exception e) {
//			e.printStackTrace();
//			request.setAttribute("uu", "<script type='text/javascript'>alert('上传失败!!!')</script>");
//			try {
//				request.getRequestDispatcher("/manager/ComIntro/ComIntroAdd.jsp").forward(request, response);
//			} catch (ServletException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//		}
		
		
		ComIntro ci = new ComIntro();
		String title = request.getParameter("title");
		String control = request.getParameter("control");
		System.out.println(title);
		System.out.println(control);
		//String con = StringTest.tran(control);
		ci.setTitle(title);
		ci.setControl(control);
		cis.addComIntro(ci);
		response.sendRedirect("/zywy/manager/ComIntro/ComIntroAdd.jsp");
//		ComIntro ci = new ComIntro();
//		
//		try{
//			DiskFileItemFactory factory = new DiskFileItemFactory();
//			ServletFileUpload upload = new ServletFileUpload(factory);
//			List<FileItem> items = upload.parseRequest(request);
//			
//			for(FileItem item:items){
//				if(item.isFormField()){
//					String fieldName = item.getFieldName();//字段名，即javabean的属性名，除了图片
//					String fieldValue = item.getString(request.getCharacterEncoding());
//					
//					System.out.println(fieldName);
//					System.out.println(fieldValue);
//					
//					BeanUtils.setProperty(ci, fieldName, fieldValue);//除了图片路径，其他数据都有了
//				}
//			}
//			cis.addComIntro(ci);
//			request.getSession().setAttribute("ci", ci);
//			request.setAttribute("uu", "<script type='text/javascript'>alert('上传成功!!!')</script>");
//			request.getRequestDispatcher("/manager/ComIntro/ComIntroAdd.jsp").forward(request, response);
//		}catch(Exception e){
//			e.printStackTrace();
//		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
