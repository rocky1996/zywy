package com.acat.dao;

import java.util.List;

import com.acat.pojo.CarRep;
import com.acat.pojo.ComCul;

public interface CarRepDao {
	void addCarRep(CarRep cr);
	void updateCarRep(CarRep cr);
	void deleteCarRep(int carid);
	List<CarRep> findAll();
	CarRep findCarRep(int carid);
	CarRep findMaxCarRep();
	List<CarRep> query(String title);
	
    int getTotalRecords();
	
	List<CarRep> findPageRecords(int startIndex,int pagesize);
}
