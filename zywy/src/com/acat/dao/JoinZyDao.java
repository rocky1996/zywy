package com.acat.dao;

import java.util.List;

import com.acat.pojo.ComNew;
import com.acat.pojo.JoinZy;

public interface JoinZyDao {
	void addJoinZy(JoinZy jz);
	void updateJoinZy(JoinZy jz);
	void deleteJoinZy(int joinid);
	List<JoinZy> findAll();
	JoinZy findJoinZy(int joinid);
	JoinZy findMaxJoinZy();
	List<JoinZy> query(String title);
	
    int getTotalRecords();
	
	List<JoinZy> findPageRecords(int startIndex,int pagesize);
}
