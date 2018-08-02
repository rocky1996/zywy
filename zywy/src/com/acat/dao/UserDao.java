package com.acat.dao;

import java.util.List;

import com.acat.pojo.User;

public interface UserDao {
	User login(String username,String password);
	List<User> findAll();
}
