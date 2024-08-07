package com.amir.usho.db;

import java.util.List;
import java.util.Optional;

import com.amir.usho.model.Url;

public interface UrlRepo {
	
	public Optional<Url> findById(long id);
	public List<Url> findAll();
	public List<Url> findAllByDest(String dest);
	public List<Url> findAllBySrc(String src);
	// public List<Url> findAllByUser(User u);

	public Optional<Url> save(Url u);
	public boolean deleteUrlById(String id);
}
