package com.acat.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.acat.util.getNowTime;


public class ImageServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter outt = response.getWriter();
		
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("utf-8");
	    response.setContentType("text/html;charset=UTF-8");
	    
		String storePath = getServletContext().getRealPath("/files");
		String fileName = null;
		try{
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			List<FileItem> items = upload.parseRequest(request);
			for(FileItem item:items){
				InputStream in = item.getInputStream();
				fileName = UUID.randomUUID().toString()+item.getName();
				OutputStream out = new FileOutputStream(storePath+"\\"+fileName);
				byte[] buf = new byte[1024];
				int len = 0;
				while((len=in.read(buf))!=-1){
					out.write(buf, 0, len);
				}
				System.out.println();
				out.close();
				in.close();
			}
			outt.print(fileName);
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("uu", "<script type='text/javascript'>alert('上传失败!!!')</script>");
			request.getRequestDispatcher("/manager/ComIntro/ComIntroAdd.jsp").forward(request, response);
		}
	     
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
