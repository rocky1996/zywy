package com.acat.service.impl;

import java.util.List;

import com.acat.dao.BigThingDao;
import com.acat.dao.impl.BigThingDaoImpl;
import com.acat.pojo.BigThing;
import com.acat.pojo.Page;
import com.acat.service.BigThingService;
import com.acat.util.getNowTime;

public class BigThingServiceImpl implements BigThingService{
	private BigThingDao btd = new BigThingDaoImpl();
	@Override
	public void addBigThing(BigThing bt) {
		bt.setTime(getNowTime.getTime());
		btd.addBigThing(bt);
	}

	@Override
	public void updateBigThing(BigThing bt) {
		bt.setTime(getNowTime.getTime());
		btd.updateBigThing(bt);
	}

	@Override
	public void deleteBigThing(int bigid) {
		btd.deleteBigThing(bigid);
	}

	@Override
	public List<BigThing> findAll() {
		return btd.findAll();
	}

	@Override
	public BigThing findBigThing(int bigid) {
		return btd.findBigThing(bigid);
	}

	@Override
	public Page findPageRecords(String pagenum) {
		int num = 1;//默认值ֵ
		
		if(pagenum != null && !"".equals(pagenum.trim())){//如果用户传进来的是null会这是空字符串，则说明第一次访问，
			num = Integer.parseInt(pagenum);
		}
		
		int totalrecords = btd.getTotalRecords();
		Page page = new Page(num, totalrecords);
		List records = btd.findPageRecords(page.getStartIndex(), page.getPagesize());
		page.setRecords(records);
		return page;
	}

	@Override
	public List<BigThing> query(String title) {
		return btd.query(title);
	}

	@Override
	public BigThing findMaxBigThing() {
		return btd.findMaxBigThing();
	}

}
