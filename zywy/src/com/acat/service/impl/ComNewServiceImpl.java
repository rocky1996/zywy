package com.acat.service.impl;

import java.util.List;

import com.acat.dao.ComNewDao;
import com.acat.dao.impl.ComNewDaoImpl;
import com.acat.pojo.ComNew;
import com.acat.pojo.Page;
import com.acat.service.ComNewService;
import com.acat.util.getNowTime;

public class ComNewServiceImpl implements ComNewService{
	private ComNewDao cnd = new ComNewDaoImpl();
	@Override
	public void addComNew(ComNew tn) {
		tn.setTime(getNowTime.getTime());
		cnd.addComNew(tn);
	}

	@Override
	public void updateComNew(ComNew tn) {
		tn.setTime(getNowTime.getTime());
		cnd.updateComNew(tn);
	}

	@Override
	public void deleteComNew(int comid) {
		cnd.deleteComNew(comid);
	}

	@Override
	public List<ComNew> findAll() {
		return cnd.findAll();
	}

	@Override
	public ComNew findComNew(int comid) {
		return cnd.findComNew(comid);
	}

	@Override
	public Page findPageRecords(String pagenum) {
		int num = 1;//默认值ֵ
		
		if(pagenum != null && !"".equals(pagenum.trim())){//如果用户传进来的是null会这是空字符串，则说明第一次访问，
			num = Integer.parseInt(pagenum);
		}
		
		int totalrecords = cnd.getTotalRecords();
		Page page = new Page(num, totalrecords);
		List records = cnd.findPageRecords(page.getStartIndex(), page.getPagesize());
		page.setRecords(records);
		return page;
	}

	@Override
	public List<ComNew> query(String title) {
		return cnd.query(title);
	}

	@Override
	public ComNew findMaxComNew() {
		return cnd.findMaxComNew();
	}

}
