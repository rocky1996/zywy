package com.acat.service.impl;

import java.util.List;

import com.acat.dao.LocalDynDao;
import com.acat.dao.impl.LocalDynDaoImpl;
import com.acat.pojo.LocalDyn;
import com.acat.pojo.Page;
import com.acat.service.LocalDynService;
import com.acat.util.getNowTime;

public class LocalDynServiceImpl implements LocalDynService{
	private LocalDynDao ldd = new LocalDynDaoImpl();
	@Override
	public void addLocalDyn(LocalDyn ld) {
		ld.setTime(getNowTime.getTime());
		ldd.addLocalDyn(ld);
	}

	@Override
	public void updateLocalDyn(LocalDyn ld) {
		ld.setTime(getNowTime.getTime());
		ldd.updateLocalDyn(ld);
	}

	@Override
	public void deleteLocalDyn(int localid) {
		ldd.deleteLocalDyn(localid);		
	}

	@Override
	public List<LocalDyn> findAll() {
		return ldd.findAll();
	}

	@Override
	public LocalDyn findLocalDyn(int localid) {
		return ldd.findLocalDyn(localid);
	}

	@Override
	public Page findPageRecords(String pagenum) {
		int num = 1;//默认值ֵ
		
		if(pagenum != null && !"".equals(pagenum.trim())){//如果用户传进来的是null会这是空字符串，则说明第一次访问，
			num = Integer.parseInt(pagenum);
		}
		
		int totalrecords = ldd.getTotalRecords();
		Page page = new Page(num, totalrecords);
		List records = ldd.findPageRecords(page.getStartIndex(), page.getPagesize());
		page.setRecords(records);
		return page;
	}

	@Override
	public List<LocalDyn> query(String title) {
		return ldd.query(title);
	}

	@Override
	public LocalDyn findMaxLocalDyn() {
		return ldd.findMaxLocalDyn();
	}

}
