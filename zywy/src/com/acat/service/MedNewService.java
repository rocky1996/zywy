package com.acat.service;

import java.util.List;

import com.acat.pojo.MedNew;
import com.acat.pojo.Page;

public interface MedNewService {
	void addMedNew(MedNew mn);
	void updateMedNew(MedNew mn);
	void deleteMedNew(int medid);
	List<MedNew> findAll();
	MedNew findMedNew(int medid);
	
	Page findPageRecords(String pagenum);
	List<MedNew> query(String title);
	MedNew findMaxMedNew();
}
