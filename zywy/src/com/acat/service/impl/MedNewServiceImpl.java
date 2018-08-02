package com.acat.service.impl;

import java.util.List;

import com.acat.dao.MedNewDao;
import com.acat.dao.impl.MedNewDaoImpl;
import com.acat.pojo.MedNew;
import com.acat.pojo.Page;
import com.acat.service.MedNewService;
import com.acat.util.getNowTime;

public class MedNewServiceImpl implements MedNewService{
	private MedNewDao mnd = new MedNewDaoImpl();
	@Override
	public void addMedNew(MedNew mn) {
		mn.setTime(getNowTime.getTime());
		mnd.addMedNew(mn);
	}

	@Override
	public void updateMedNew(MedNew mn) {
		mn.setTime(getNowTime.getTime());
		mnd.updateMedNew(mn);
	}

	@Override
	public void deleteMedNew(int medid) {
		mnd.deleteMedNew(medid);		
	}

	@Override
	public List<MedNew> findAll() {
		return mnd.findAll();
	}

	@Override
	public MedNew findMedNew(int medid) {
		return mnd.findMedNew(medid);
	}

	@Override
	public Page findPageRecords(String pagenum) {
		int num = 1;//默认值ֵ
		
		if(pagenum != null && !"".equals(pagenum.trim())){//如果用户传进来的是null会这是空字符串，则说明第一次访问，
			num = Integer.parseInt(pagenum);
		}
		
		int totalrecords = mnd.getTotalRecords();
		Page page = new Page(num, totalrecords);
		List records = mnd.findPageRecords(page.getStartIndex(), page.getPagesize());
		page.setRecords(records);
		return page;
	}

	@Override
	public List<MedNew> query(String title) {
		return mnd.query(title);
	}

	@Override
	public MedNew findMaxMedNew() {
		return mnd.findMaxMedNew();
	}

}
