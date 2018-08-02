package com.acat.pojo;
/**
 * 媒体新闻
 * @author 伍金凡
 *
 */
public class MedNew {
	private int medid;
	private String title;
	private String control;
	private String time;
	public int getMedid() {
		return medid;
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
	public void setMedid(int medid) {
		this.medid = medid;
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
