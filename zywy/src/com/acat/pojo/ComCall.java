package com.acat.pojo;

/**
 * 投诉电话
 * @author 伍金凡
 *
 */
public class ComCall {
	private int comid;
	private String title;
	private String control;
	private String time;
	public int getComid() {
		return comid;
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
	public void setComid(int comid) {
		this.comid = comid;
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
