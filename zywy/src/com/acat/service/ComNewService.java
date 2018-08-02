package com.acat.service;

import java.util.List;

import com.acat.pojo.ComIntro;
import com.acat.pojo.ComNew;
import com.acat.pojo.Page;

public interface ComNewService {
	void addComNew(ComNew tn);
	void updateComNew(ComNew tn);
	void deleteComNew(int comid);
	List<ComNew> findAll();
	ComNew findComNew(int comid);
	
	Page findPageRecords(String pagenum);
	List<ComNew> query(String title);
	ComNew findMaxComNew();
}
