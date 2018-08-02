package com.acat.pojo;
/**
 * 汽车维修
 * @author 伍金凡
 *
 */
public class CarRep {
	private int carid;
	private String title;
	private String control;
	private String time;
	public int getCarid() {
		return carid;
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
	public void setCarid(int carid) {
		this.carid = carid;
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
