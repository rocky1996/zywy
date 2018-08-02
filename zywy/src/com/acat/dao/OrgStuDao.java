package com.acat.dao;

import java.util.List;

import com.acat.pojo.ComIntro;
import com.acat.pojo.OrgStu;

public interface OrgStuDao {
	void addOrgStu(OrgStu os);
	void updateOrgStu(OrgStu os);
	void deleteOrgStu(int orgid);
	List<OrgStu> findAll();
	OrgStu findOrgStu(int orgid);
	OrgStu findMaxOrgStu();
	List<OrgStu> query(String title);
	
    int getTotalRecords();
	
	List<OrgStu> findPageRecords(int startIndex,int pagesize);
}
