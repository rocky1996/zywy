package com.acat.service;

import java.util.List;

import com.acat.pojo.CarRep;
import com.acat.pojo.ComCul;
import com.acat.pojo.Page;

public interface CarRepService {
	void addCarRep(CarRep cr);
	void updateCarRep(CarRep cr);
	void deleteCarRep(int carid);
	List<CarRep> findAll();
	CarRep findCarRep(int carid);
	
	Page findPageRecords(String pagenum);
	List<CarRep> query(String title);
	CarRep findMaxCarRep();
}
