package com.acat.pojo;

import java.util.List;

public class Page {
	private List records;
	private int pagesize=9;//每页显示的记录数
	private int pagenum;//当前页码
	private int totalpage;//总页数
	private int startIndex;//每页开始的记录索引
	private int totalrecords;//总记录数
	
	//显示的页码
	private int startPage;
	private int endPage;
	
	public int getStartPage() {
		return startPage;
	}


	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}


	public int getEndPage() {
		return endPage;
	}


	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}


	public Page(int pagenum,int totalrecords){
		this.pagenum = pagenum;
		this.totalrecords = totalrecords;
		
		//计算每页开始的记录索引
		startIndex = (pagenum-1)*pagesize;
		
		//计算总页数
		totalpage = totalrecords%pagesize==0?totalrecords/pagesize:(totalrecords/pagesize+1);
		
		//显示的页码
		if(totalpage<=8){
			startPage = 1;
			endPage = totalpage;
		}else{
			startPage = pagenum-1;
			endPage = pagenum+1;
			if(startPage<1){
				startPage=1;
				endPage=9;
			}
			
			if(endPage>totalpage){
				endPage = totalpage;
				startPage = totalpage-4;
			}
		}
	}
	
	
	public List getRecords() {
		return records;
	}
	public void setRecords(List records) {
		this.records = records;
	}
	public int getPagesize() {
		return pagesize;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	public int getPagenum() {
		return pagenum;
	}
	public void setPagenum(int pagenum) {
		this.pagenum = pagenum;
	}
	public int getTotalpage() {
		return totalpage;
	}
	public void setTotalpage(int totalpage) {
		this.totalpage = totalpage;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public int getTotalrecords() {
		return totalrecords;
	}
	public void setTotalrecords(int totalrecords) {
		this.totalrecords = totalrecords;
	}
	
}
