package com.acat.service;

import java.util.List;

import com.acat.pojo.BigThing;
import com.acat.pojo.Page;

public interface BigThingService {
	void addBigThing(BigThing bt);
	void updateBigThing(BigThing bt);
	void deleteBigThing(int bigid);
	List<BigThing> findAll();
	BigThing findBigThing(int bigid);
	
	Page findPageRecords(String pagenum);
	List<BigThing> query(String title);
	BigThing findMaxBigThing();
}
