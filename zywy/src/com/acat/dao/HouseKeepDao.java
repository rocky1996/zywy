package com.acat.dao;

import java.util.List;

import com.acat.pojo.ComNew;
import com.acat.pojo.HouseKeep;

public interface HouseKeepDao {
	void addHouseKeep(HouseKeep hk);
	void updateHouseKeep(HouseKeep hk);
	void deleteHouseKeep(int houseid);
	List<HouseKeep> findAll();
	HouseKeep findHouseKeep(int houseid);
	HouseKeep findMaxHouseKeep();
	List<HouseKeep> query(String title);
	
    int getTotalRecords();
	
	List<HouseKeep> findPageRecords(int startIndex,int pagesize);
}
