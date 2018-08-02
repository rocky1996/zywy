package com.acat.service.impl;

import java.util.List;

import com.acat.dao.CarRepDao;
import com.acat.dao.impl.CarRepDaoImpl;
import com.acat.pojo.CarRep;
import com.acat.pojo.Page;
import com.acat.service.CarRepService;
import com.acat.util.getNowTime;

public class CarRepServiceImpl implements CarRepService{
	private CarRepDao crd = new CarRepDaoImpl();
	@Override
	public void addCarRep(CarRep cr) {
		cr.setTime(getNowTime.getTime());
		crd.addCarRep(cr);
	}

	@Override
	public void updateCarRep(CarRep cr) {
		cr.setTime(getNowTime.getTime());
		crd.updateCarRep(cr);
	}

	@Override
	public void deleteCarRep(int carid) {
		crd.deleteCarRep(carid);
	}

	@Override
	public List<CarRep> findAll() {
		return crd.findAll();
	}

	@Override
	public CarRep findCarRep(int carid) {
		return crd.findCarRep(carid);
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
	public List<CarRep> query(String title) {
		return crd.query(title);
	}

	@Override
	public CarRep findMaxCarRep() {
		return crd.findMaxCarRep();
	}

}
