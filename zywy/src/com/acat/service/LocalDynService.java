package com.acat.service;

import java.util.List;

import com.acat.pojo.ComNew;
import com.acat.pojo.LocalDyn;
import com.acat.pojo.Page;

public interface LocalDynService {
	void addLocalDyn(LocalDyn ld);
	void updateLocalDyn(LocalDyn ld);
	void deleteLocalDyn(int localid);
	List<LocalDyn> findAll();
	LocalDyn findLocalDyn(int localid);
	
	Page findPageRecords(String pagenum);
	List<LocalDyn> query(String title);
	LocalDyn findMaxLocalDyn();
}
