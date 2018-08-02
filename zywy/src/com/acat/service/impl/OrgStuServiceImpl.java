package com.acat.service.impl;

import java.util.List;

import com.acat.dao.OrgStuDao;
import com.acat.dao.impl.OrgStuDaoImpl;
import com.acat.pojo.OrgStu;
import com.acat.pojo.Page;
import com.acat.service.OrgStuService;
import com.acat.util.getNowTime;

public class OrgStuServiceImpl implements OrgStuService{
	private OrgStuDao osd = new OrgStuDaoImpl();

	@Override
	public void addOrgStu(OrgStu os) {
		os.setTime(getNowTime.getTime());
		osd.addOrgStu(os);
	}

	@Override
	public void updateOrgStu(OrgStu os) {
		osd.updateOrgStu(os);		
	}

	@Override
	public void deleteOrgStu(int orgid) {
		osd.deleteOrgStu(orgid);
	}

	@Override
	public List<OrgStu> findAll() {
		return osd.findAll();
	}

	@Override
	public OrgStu findOrgStu(int orgid) {
		return osd.findOrgStu(orgid);
	}

	@Override
	public OrgStu findMaxOrgStu() {
		return osd.findMaxOrgStu();
	}

	@Override
	public List<OrgStu> query(String title) {
		return osd.query(title);
	}

	@Override
	public Page findPageRecords(String pagenum) {
		int num = 1;//默认值ֵ
		
		if(pagenum != null && !"".equals(pagenum.trim())){//如果用户传进来的是null会这是空字符串，则说明第一次访问，
			num = Integer.parseInt(pagenum);
		}
		
		int totalrecords = osd.getTotalRecords();
		Page page = new Page(num, totalrecords);
		List records = osd.findPageRecords(page.getStartIndex(), page.getPagesize());
		page.setRecords(records);
		return page;
	}
	
	
}
