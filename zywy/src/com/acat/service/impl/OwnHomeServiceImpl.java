package com.acat.service.impl;

import java.util.List;

import com.acat.dao.OwnHomeDao;
import com.acat.dao.impl.OwnHomeDaoImpl;
import com.acat.pojo.OwnHome;
import com.acat.pojo.Page;
import com.acat.service.OwnHomeService;
import com.acat.util.getNowTime;

public class OwnHomeServiceImpl implements OwnHomeService{
	private OwnHomeDao ohd = new OwnHomeDaoImpl();
	@Override
	public void addOwnHome(OwnHome oh) {
		oh.setTime(getNowTime.getTime());
		ohd.addOwnHome(oh);
	}

	@Override
	public void updateOwnHome(OwnHome oh) {
		oh.setTime(getNowTime.getTime());
		ohd.updateOwnHome(oh);
	}

	@Override
	public void deleteOwnHome(int ownid) {
		ohd.deleteOwnHome(ownid);		
	}

	@Override
	public List<OwnHome> findAll() {
		return ohd.findAll();
	}

	@Override
	public OwnHome findOwnHome(int ownid) {
		return ohd.findOwnHome(ownid);
	}

	@Override
	public OwnHome findMaxOwnHome() {
		return ohd.findMaxOwnHome();
	}

	@Override
	public List<OwnHome> query(String title) {
		return ohd.query(title);
	}

	@Override
	public Page findPageRecords(String pagenum) {
		int num = 1;//默认值ֵ
		
		if(pagenum != null && !"".equals(pagenum.trim())){//如果用户传进来的是null会这是空字符串，则说明第一次访问，
			num = Integer.parseInt(pagenum);
		}
		
		int totalrecords = ohd.getTotalRecords();
		Page page = new Page(num, totalrecords);
		List records = ohd.findPageRecords(page.getStartIndex(), page.getPagesize());
		page.setRecords(records);
		return page;
	}

}
