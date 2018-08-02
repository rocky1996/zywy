package com.acat.service.impl;

import java.util.List;

import com.acat.dao.ConRepDao;
import com.acat.dao.impl.ConRepDaoImpl;
import com.acat.pojo.ConRep;
import com.acat.pojo.Page;
import com.acat.service.ConRepService;
import com.acat.util.getNowTime;

public class ConRepServiceImpl implements ConRepService{
	private ConRepDao crd = new ConRepDaoImpl(); 
	@Override
	public void addConRep(ConRep cr) {
		cr.setTime(getNowTime.getTime());
		crd.addConRep(cr);
	}

	@Override
	public void updateConRep(ConRep cr) {
		cr.setTime(getNowTime.getTime());
		crd.updateConRep(cr);
	}

	@Override
	public void deleteConRep(int conid) {
		crd.deleteConRep(conid);
	}

	@Override
	public List<ConRep> findAll() {
		return crd.findAll();
	}

	@Override
	public ConRep findConRep(int conid) {
		return crd.findConRep(conid);
	}

	@Override
	public Page findPageRecords(String pagenum) {
		int num = 1;//默认值ֵ
		
		if(pagenum != null && !"".equals(pagenum.trim())){//如果用户传进来的是null会这是空字符串，则说明第一次访问，
			num = Integer.parseInt(pagenum);
		}
		
		int totalrecords = crd.getTotalRecords();
		Page page = new Page(num, totalrecords);
		List records = crd.findPageRecords(page.getStartIndex(), page.getPagesize());
		page.setRecords(records);
		return page;
	}

	@Override
	public List<ConRep> query(String title) {
		return crd.query(title);
	}

	@Override
	public ConRep findMaxConRep() {
		return crd.findMaxConRep();
	}

}
