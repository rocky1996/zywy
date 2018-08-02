package com.acat.service;

import java.util.List;

import com.acat.pojo.ComCall;
import com.acat.pojo.ComNew;
import com.acat.pojo.Page;

public interface ComCallService {
	void addComCall(ComCall cc);
	void updateComCall(ComCall cc);
	void deleteComCall(int comid);
	List<ComCall> findAll();
	ComCall findComCall(int comid);
	
	Page findPageRecords(String pagenum);
	List<ComCall> query(String title);
	ComCall findMaxComCall();
}
