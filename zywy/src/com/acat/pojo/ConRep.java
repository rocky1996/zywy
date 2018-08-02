package com.acat.pojo;

/*
 *	便民维修
 */
public class ConRep {
	private int conid;
	private String title;
	private String control;
	private String time;
	public int getConid() {
		return conid;
	}
	public String getTitle() {
		return title;
	}
	public String getControl() {
		return control;
	}
	public String getTime() {
		return time;
	}
	public void setConid(int conid) {
		this.conid = conid;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setControl(String control) {
		this.control = control;
	}
	public void setTime(String time) {
		this.time = time;
	}
}
