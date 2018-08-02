package com.acat.dao;

import java.util.List;

import com.acat.pojo.ConRep;
import com.acat.pojo.HouseKeep;

public interface ConRepDao {
	void addConRep(ConRep cr);
	void updateConRep(ConRep cr);
	void deleteConRep(int conid);
	List<ConRep> findAll();
	ConRep findConRep(int conid);
	ConRep findMaxConRep();
	List<ConRep> query(String title);
	
    int getTotalRecords();
	
	List<ConRep> findPageRecords(int startIndex,int pagesize);
}
