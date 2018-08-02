package com.acat.dao;

import java.util.List;
import com.acat.pojo.LocalDyn;

public interface LocalDynDao {
	void addLocalDyn(LocalDyn ld);
	void updateLocalDyn(LocalDyn ld);
	void deleteLocalDyn(int localid);
	List<LocalDyn> findAll();
	LocalDyn findLocalDyn(int localid);
	LocalDyn findMaxLocalDyn();
	List<LocalDyn> query(String title);
	
    int getTotalRecords();
	
	List<LocalDyn> findPageRecords(int startIndex,int pagesize);
}
