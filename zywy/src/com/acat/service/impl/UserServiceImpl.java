package com.acat.service.impl;

import java.util.List;

import com.acat.dao.UserDao;
import com.acat.dao.impl.UserDaoImpl;
import com.acat.pojo.User;
import com.acat.service.UserService;

public class UserServiceImpl implements UserService{
	private UserDao dao = new UserDaoImpl();
	@Override
	public User login(String username, String password) {
		// TODO Auto-generated method stub
		return dao.login(username, password);
	}
	@Override
	public List<User> findAll() {
		return dao.findAll();
	}

}
