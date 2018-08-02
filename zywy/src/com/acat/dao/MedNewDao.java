package com.acat.dao;

import java.util.List;

import com.acat.pojo.LocalDyn;
import com.acat.pojo.MedNew;

public interface MedNewDao {
	void addMedNew(MedNew mn);
	void updateMedNew(MedNew mn);
	void deleteMedNew(int medid);
	List<MedNew> findAll();
	MedNew findMedNew(int medid);
	MedNew findMaxMedNew();
	List<MedNew> query(String title);
	
    int getTotalRecords();
	
	List<MedNew> findPageRecords(int startIndex,int pagesize);
}
