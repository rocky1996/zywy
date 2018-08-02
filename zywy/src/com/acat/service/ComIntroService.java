package com.acat.service;

import java.util.List;

import com.acat.pojo.ComIntro;
import com.acat.pojo.Page;

public interface ComIntroService {
	void addComIntro(ComIntro ci);
	void updateComIntro(ComIntro ci);
	void deleteComIntro(int comid);
	List<ComIntro> findAll();
	ComIntro findComIntro(int comid );
	
	Page findPageRecords(String pagenum);
	List<ComIntro> query(String title);
	ComIntro findMaxComIntro();
}
