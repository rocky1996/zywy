package com.acat.test;

import java.util.List;

import org.junit.Test;

import com.acat.dao.ComIntroDao;
import com.acat.dao.impl.ComIntroDaoImpl;
import com.acat.pojo.ComIntro;

public class ComIntroTest {
	private ComIntroDao cd = new ComIntroDaoImpl();
	@Test
	public void test(){
		ComIntro ci = cd.findMaxComIntro();
		System.out.println(ci.getControl());
		System.out.println(ci.getTitle());
		System.out.println(ci.getTime());
	}
	
	@Test
	public void test2(){
		List<ComIntro> list = cd.query("%缃�");
		System.out.println(list.size());
	}
	
	@Test
	public void test3(){
		ComIntro ci = cd.findComIntro(51);
		ci.setTitle("璋锋枃鍗氬闀�");
		cd.updateComIntro(ci);
		System.out.println("&&&");
	}
}
