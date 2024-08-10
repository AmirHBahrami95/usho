package com.amir.usho.db;

import java.util.List;
import java.util.Optional;

import com.amir.usho.model.Url;

public interface UrlRepo {
	Optional<Url> findById(Url u);
	List<Url> findAll();
	List<Url> findAllByDest(String dest);
	List<Url> findAllBySrc(String src);
	// List<Url> findAllByUser(User u);
	Optional<Url> save(Url u);
	boolean deleteUrlById(Url u);
}
