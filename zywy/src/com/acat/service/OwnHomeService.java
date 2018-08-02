package com.acat.service;

import java.util.List;

import com.acat.pojo.OwnHome;
import com.acat.pojo.Page;
import com.acat.pojo.TeamCon;

public interface OwnHomeService {
	void addOwnHome(OwnHome oh);
	void updateOwnHome(OwnHome oh);
	void deleteOwnHome(int ownid);
	List<OwnHome> findAll();
	OwnHome findOwnHome(int ownid);
	OwnHome findMaxOwnHome();
	List<OwnHome> query(String title);
	
	public Page findPageRecords(String pagenum);
}
