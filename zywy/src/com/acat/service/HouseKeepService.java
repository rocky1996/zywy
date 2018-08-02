package com.acat.service;

import java.util.List;

import com.acat.pojo.HouseKeep;
import com.acat.pojo.LocalDyn;
import com.acat.pojo.Page;

public interface HouseKeepService {
	void addHouseKeep(HouseKeep hk);
	void updateHouseKeep(HouseKeep hk);
	void deleteHouseKeep(int houseid);
	List<HouseKeep> findAll();
	HouseKeep findHouseKeep(int houseid);
	
	Page findPageRecords(String pagenum);
	List<HouseKeep> query(String title);
	HouseKeep findMaxHouseKeep();
}
