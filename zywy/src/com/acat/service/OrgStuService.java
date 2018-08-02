package com.acat.service;

import java.util.List;

import com.acat.pojo.OrgStu;
import com.acat.pojo.Page;

public interface OrgStuService {
	void addOrgStu(OrgStu os);
	void updateOrgStu(OrgStu os);
	void deleteOrgStu(int orgid);
	List<OrgStu> findAll();
	OrgStu findOrgStu(int orgid);
	OrgStu findMaxOrgStu();
	List<OrgStu> query(String title);
	
	public Page findPageRecords(String pagenum);
}
