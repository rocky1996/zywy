package com.acat.service;

import java.util.List;

import com.acat.pojo.ComCul;
import com.acat.pojo.ComIntro;
import com.acat.pojo.Page;

public interface ComCulService {
	void addComCul(ComCul cc);
	void updateComCul(ComCul cc);
	void deleteComCul(int comid);
	List<ComCul> findAll();
	ComCul findComCul(int comid);
	
	Page findPageRecords(String pagenum);
	List<ComCul> query(String title);
	ComCul findMaxComCul();
}
