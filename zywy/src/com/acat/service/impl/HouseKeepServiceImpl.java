package com.acat.service.impl;

import java.util.List;

import com.acat.dao.HouseKeepDao;
import com.acat.dao.impl.HouseKeepDaoImpl;
import com.acat.pojo.HouseKeep;
import com.acat.pojo.Page;
import com.acat.service.HouseKeepService;
import com.acat.util.getNowTime;

public class HouseKeepServiceImpl implements HouseKeepService{
	private HouseKeepDao hkd = new HouseKeepDaoImpl();
	@Override	
	public void addHouseKeep(HouseKeep hk) {
		hk.setTime(getNowTime.getTime());
		hkd.addHouseKeep(hk);
	}

	@Override
	public void updateHouseKeep(HouseKeep hk) {
		hk.setTime(getNowTime.getTime());
		hkd.updateHouseKeep(hk);
	}

	@Override
	public void deleteHouseKeep(int houseid) {
		hkd.deleteHouseKeep(houseid);		
	}

	@Override
	public List<HouseKeep> findAll() {
		return hkd.findAll();
	}

	@Override
	public HouseKeep findHouseKeep(int houseid) {
		return hkd.findHouseKeep(houseid);
	}

	@Override
	public Page findPageRecords(String pagenum) {
		int num = 1;//默认值ֵ
		
		if(pagenum != null && !"".equals(pagenum.trim())){//如果用户传进来的是null会这是空字符串，则说明第一次访问，
			num = Integer.parseInt(pagenum);
		}
		
		int totalrecords = hkd.getTotalRecords();
		Page page = new Page(num, totalrecords);
		List records = hkd.findPageRecords(page.getStartIndex(), page.getPagesize());
		page.setRecords(records);
		return page;
	}

	@Override
	public List<HouseKeep> query(String title) {
		return hkd.query(title);
	}

	@Override
	public HouseKeep findMaxHouseKeep() {
		return hkd.findMaxHouseKeep();
	}

}
