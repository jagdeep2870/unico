package com.unico.enterprise.dao;

import java.security.GeneralSecurityException;

import com.unico.enterprise.domain.User;

public interface UserDao {
	
	public User getUser(String userName, String password);
}
