package com.acat.service.impl;

import java.util.List;

import com.acat.dao.ComCulDao;
import com.acat.dao.impl.ComCulDaoImpl;
import com.acat.pojo.ComCul;
import com.acat.pojo.Page;
import com.acat.service.ComCulService;
import com.acat.util.getNowTime;

public class ComCulServiceImpl implements ComCulService{
	private ComCulDao ccd = new ComCulDaoImpl();

	@Override
	public void addComCul(ComCul cc) {
		cc.setTime(getNowTime.getTime());
		ccd.addComCul(cc);
	}

	@Override
	public void updateComCul(ComCul cc) {
		cc.setTime(getNowTime.getTime());
		ccd.updateComCul(cc);
	}

	@Override
	public void deleteComCul(int comid) {
		ccd.deleteComCul(comid);	
	}

	@Override
	public List<ComCul> findAll() {
		return ccd.findAll();
	}

	@Override
	public ComCul findComCul(int comid) {
		return ccd.findComCul(comid);
	}

	@Override
	public Page findPageRecords(String pagenum) {
		int num = 1;//默认值ֵ
		
		if(pagenum != null && !"".equals(pagenum.trim())){//如果用户传进来的是null会这是空字符串，则说明第一次访问，
			num = Integer.parseInt(pagenum);
		}
		
		int totalrecords = ccd.getTotalRecords();
		Page page = new Page(num, totalrecords);
		List records = ccd.findPageRecords(page.getStartIndex(), page.getPagesize());
		page.setRecords(records);
		return page;
	}

	@Override
	public List<ComCul> query(String title) {
		return ccd.query(title);
	}

	@Override
	public ComCul findMaxComCul() {
		return ccd.findMaxComCul();
	}	
}
