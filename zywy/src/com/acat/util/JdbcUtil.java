package com.acat.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import javax.naming.spi.DirStateFactory.Result;

public class JdbcUtil {
	
	private static String driverClassName;
	private static String url;
	private static String username;
	private static String password;
	
	static{
		try{
			InputStream in = JdbcUtil.class.getClassLoader().getResourceAsStream("dbcpconfig.properties");
			Properties props = new Properties();
			props.load(in);
			driverClassName = props.getProperty("driverClassName");
			url = props.getProperty("url");
			username = props.getProperty("username");
			password = props.getProperty("password");
			Class.forName("com.mysql.jdbc.Driver");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws Exception{
		return DriverManager.getConnection(url, username, password);
	}
	
	public static void liu(ResultSet rs,Statement stmt,Connection conn){
		if(rs!=null){
			try{
				rs.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			
			rs=null;
		}
		
		if(stmt!=null){
			try{
				stmt.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			
			stmt=null;
		}
		
		if(conn!=null){
			try{
				conn.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			
			conn=null;
		}
	}
}
