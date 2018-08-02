package com.acat.dao;

import java.util.List;

import com.acat.pojo.ComCall;
import com.acat.pojo.ComIntro;

public interface ComCallDao {
	void addComCall(ComCall cc);
	void updateComCall(ComCall cc);
	void deleteComCall(int comid);
	List<ComCall> findAll();
	ComCall findComCall(int comid);
	ComCall findMaxComCall();
	List<ComCall> query(String title);
	
    int getTotalRecords();
	
	List<ComCall> findPageRecords(int startIndex,int pagesize);
}
