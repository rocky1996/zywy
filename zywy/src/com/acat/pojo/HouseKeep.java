package com.acat.pojo;

/**
 * 家政服务
 * @author 伍金凡
 *
 */
public class HouseKeep {
	private int houseid;
	private String title;
	private String control;
	private String time;
	public int getHouseid() {
		return houseid;
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
	public void setHouseid(int houseid) {
		this.houseid = houseid;
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
