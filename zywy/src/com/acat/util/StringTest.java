package com.acat.util;

public class StringTest {
	public static String tran(String str){
		StringBuilder sb = null;
		if(str.contains("![](")){
			String[] string = str.split("\\!");
			for(int i=0;i<string.length;i++){
				str = string[0]+" !"+string[1];
				sb = new StringBuilder(str);
			}
		}
		return sb.toString();
	}
	
	public static String pinjie(String str){
		StringBuilder sb = new StringBuilder(str);
		sb.insert(0, "%").append("%");
		String string = sb.toString();
		return string;
	}
	
//	public static void main(String[] args) {
//		String str = "鐜嬬珛娑�;
//		System.out.println(StringTest.pinjie(str));
//	}
}
