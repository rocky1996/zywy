package com.acat.dao;

import java.util.List;

import com.acat.pojo.ComIntro;
import com.acat.pojo.ComNew;

public interface ComNewDao {
	void addComNew(ComNew cn);
	void updateComNew(ComNew cn);
	void deleteComNew(int comid);
	List<ComNew> findAll();
	ComNew findComNew(int comid);
	ComNew findMaxComNew();
	List<ComNew> query(String title);
	
    int getTotalRecords();
	
	List<ComNew> findPageRecords(int startIndex,int pagesize);
}
