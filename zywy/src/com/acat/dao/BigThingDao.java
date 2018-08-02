package com.acat.dao;

import java.util.List;

import com.acat.pojo.BigThing;
import com.acat.pojo.ComIntro;

public interface BigThingDao {
	void addBigThing(BigThing bt);
	void updateBigThing(BigThing bt);
	void deleteBigThing(int bigid);
	List<BigThing> findAll();
	BigThing findBigThing(int bigid);
	BigThing findMaxBigThing();
	List<BigThing> query(String title);
	
    int getTotalRecords();
	
	List<BigThing> findPageRecords(int startIndex,int pagesize);
}
