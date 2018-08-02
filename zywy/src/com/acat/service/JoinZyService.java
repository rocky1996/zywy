package com.acat.service;

import java.util.List;

import com.acat.pojo.HouseKeep;
import com.acat.pojo.JoinZy;
import com.acat.pojo.Page;

public interface JoinZyService {
	void addJoinZy(JoinZy jz);
	void updateJoinZy(JoinZy jz);
	void deleteJoinZy(int joinid);
	List<JoinZy> findAll();
	JoinZy findJoinZy(int joinid);
	
	Page findPageRecords(String pagenum);
	List<JoinZy> query(String title);
	JoinZy findMaxJoinZy();
}
