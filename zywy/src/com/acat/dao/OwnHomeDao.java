package com.acat.dao;

import java.util.List;

import com.acat.pojo.CarRep;
import com.acat.pojo.OwnHome;

public interface OwnHomeDao {
	void addOwnHome(OwnHome oh);
	void updateOwnHome(OwnHome oh);
	void deleteOwnHome(int ownid);
	List<OwnHome> findAll();
	OwnHome findOwnHome(int ownid);
	OwnHome findMaxOwnHome();
	List<OwnHome> query(String title);
	
    int getTotalRecords();
	
	List<OwnHome> findPageRecords(int startIndex,int pagesize);
}
