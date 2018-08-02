package com.acat.util;

import java.io.InputStream;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;
public class DbcpUtil {
	
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
}
