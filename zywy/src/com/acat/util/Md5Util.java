package com.acat.util;

import java.security.MessageDigest;

import sun.misc.BASE64Encoder;

public class Md5Util {
	public static String encoder(String message){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] b = md.digest(message.getBytes());
            return new BASE64Encoder().encode(b);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
	
	public static void main(String[] args) {
		System.out.println(Md5Util.encoder("123456"));
	}
}
