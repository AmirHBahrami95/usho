package com.amir.usho.db;

import java.util.List;
import java.util.Optional;

import com.amir.usho.model.User;

public interface UserRepo{
	
	public List<User> findAll();

	public Optional<User> save(User u);
	public Optional<User> findByUname(String uname);
	
	public Optional<User> update(User u); // it's uname should be consistent
}
