package com.amir.usho.db;

import java.util.List;
import java.util.Optional;

import com.amir.usho.model.User;

public interface UserRepo{
	List<User> findAll();
	Optional<User> save(User u);
	Optional<User> findByUname(String uname);
	Optional<User> update(User u); // it's uname should be consistent
}
