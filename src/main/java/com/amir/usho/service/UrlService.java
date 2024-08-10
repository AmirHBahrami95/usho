package com.amir.usho.service;

import java.util.List;
import java.util.Optional;

import com.amir.usho.model.Url;
import com.amir.usho.model.UrlAccess;

public interface UrlService{
	List<Url> findAllUrls();
	// List<Url> findAllBySrc(String src);
	// List<Url> findAllByDest(String dest);
	Optional<Url> findUrlById(Url u,UrlAccess ua);
	Optional<Url> findUrlById(Url u);
	Optional<Url> save(Url u);
}
