package com.acat.service;

import java.util.List;

import com.acat.pojo.ComNew;
import com.acat.pojo.ConRep;
import com.acat.pojo.Page;

public interface ConRepService {
	void addConRep(ConRep cr);
	void updateConRep(ConRep cr);
	void deleteConRep(int conid);
	List<ConRep> findAll();
	ConRep findConRep(int conid);
	
	Page findPageRecords(String pagenum);
	List<ConRep> query(String title);
	ConRep findMaxConRep();
}
