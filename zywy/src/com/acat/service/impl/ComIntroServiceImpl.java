package com.acat.service.impl;

import java.util.Date;
import java.util.List;

import com.acat.dao.ComIntroDao;
import com.acat.dao.impl.ComIntroDaoImpl;
import com.acat.pojo.ComIntro;
import com.acat.pojo.Page;
import com.acat.service.ComIntroService;
import com.acat.util.getNowTime;

public class ComIntroServiceImpl implements ComIntroService{
	private ComIntroDao cid = new ComIntroDaoImpl();
	@Override
	public void addComIntro(ComIntro ci) {
		ci.setTime(getNowTime.getTime());
		cid.addComIntro(ci);		
	}

	@Override
	public void updateComIntro(ComIntro ci) {
		ci.setTime(getNowTime.getTime());
		cid.updateComIntro(ci);		
	}

	@Override
	public void deleteComIntro(int comid) {
		cid.deleteComIntro(comid);
	}

	@Override
	public List<ComIntro> findAll() {
		return cid.findAll();
	}

	@Override
	public ComIntro findComIntro(int comid) {
		return cid.findComIntro(comid);
	}

	@Override
	public Page findPageRecords(String pagenum) {
		int num = 1;//默认值ֵ
		
		if(pagenum != null && !"".equals(pagenum.trim())){//如果用户传进来的是null会这是空字符串，则说明第一次访问，
			num = Integer.parseInt(pagenum);
		}
		
		int totalrecords = cid.getTotalRecords();
		Page page = new Page(num, totalrecords);
		List records = cid.findPageRecords(page.getStartIndex(), page.getPagesize());
		page.setRecords(records);
		return page;
	}

	@Override
	public List<ComIntro> query(String title) {
		return cid.query(title);
	}

	@Override
	public ComIntro findMaxComIntro() {
		return cid.findMaxComIntro();
	}
}
