package com.acat.service.impl;

import java.util.List;

import com.acat.dao.ComCallDao;
import com.acat.dao.impl.ComCallDaoImpl;
import com.acat.pojo.ComCall;
import com.acat.pojo.Page;
import com.acat.service.ComCallService;
import com.acat.util.getNowTime;

public class ComCallServiceImpl implements ComCallService{
	private ComCallDao ccd = new ComCallDaoImpl();
	@Override	
	public void addComCall(ComCall cc) {
		cc.setTime(getNowTime.getTime());	
		ccd.addComCall(cc);
	}

	@Override
	public void updateComCall(ComCall cc) {
		cc.setTime(getNowTime.getTime());
		ccd.updateComCall(cc);
	}

	@Override
	public void deleteComCall(int comid) {
		ccd.deleteComCall(comid);	
	}

	@Override
	public List<ComCall> findAll() {
		return ccd.findAll();
	}

	@Override
	public ComCall findComCall(int comid) {
		return ccd.findComCall(comid);
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
	public List<ComCall> query(String title) {
		return ccd.query(title);
	}

	@Override
	public ComCall findMaxComCall() {
		return ccd.findMaxComCall();
	}
	
}
