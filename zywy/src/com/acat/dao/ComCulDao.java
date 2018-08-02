package com.acat.dao;

import java.util.List;

import com.acat.pojo.ComCul;
import com.acat.pojo.ComIntro;

public interface ComCulDao {
	void addComCul(ComCul cc);
	void updateComCul(ComCul cc);
	void deleteComCul(int comid);
	List<ComCul> findAll();
	ComCul findComCul(int comid);
	ComCul findMaxComCul();
	List<ComCul> query(String title);
	
    int getTotalRecords();
	
	List<ComCul> findPageRecords(int startIndex,int pagesize);
}
