package com.acat.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.acat.dao.UserDao;
import com.acat.pojo.User;
import com.acat.util.DbcpUtil;

public class UserDaoImpl implements UserDao{
	
	private QueryRunner qr = new QueryRunner(DbcpUtil.getDataSource()); 
	
	@Override
	public User login(String username, String password) {
		try{
			return qr.query("select * from zyw_user where username=? and password=?", new BeanHandler<User>(User.class),username,password);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<User> findAll() {
		try{
			return qr.query("select * from zyw_user", new BeanListHandler<User>(User.class));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
}
