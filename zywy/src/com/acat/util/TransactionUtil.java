package com.acat.util;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

public class TransactionUtil {
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	private static DataSource ds;
	
	static{
		try{
			InputStream in = DbcpUtil.class.getClassLoader().getResourceAsStream("dbcpconfig.properties");
			Properties prop = new Properties();
			prop.load(in);
			ds = BasicDataSourceFactory.createDataSource(prop);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static DataSource getDataSource(){
		return ds;
	}
	
	public static Connection getConnection(){
		try{
			Connection conn = tl.get();
			if(conn==null){
				conn = ds.getConnection();
				tl.set(conn);
			}
			return conn;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	public static void startTransaction(){
		try{
			Connection conn = tl.get();
			if(conn==null){
				conn = ds.getConnection();
				//tl.set(conn);
			}
			conn.setAutoCommit(false);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	public static void rollback(){
		try{
			Connection conn = tl.get();
			if(conn==null){
				conn = ds.getConnection();
				//tl.set(conn);
			}
			conn.rollback();
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	public static void commit(){
		try{
			Connection conn = tl.get();
			if(conn==null){
				conn = ds.getConnection();
				//tl.set(conn);
			}
			conn.commit();
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	public static void relase(){
		try{
			Connection conn = tl.get();
			if(conn==null){
				conn.close();
				tl.remove();
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
}
