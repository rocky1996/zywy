package com.acat.service;

import java.util.List;

import com.acat.pojo.User;

public interface UserService {
	User login(String username, String password);
	List<User> findAll();
}
