package com.amir.usho.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.List;

import com.amir.usho.model.Url;
import com.amir.usho.model.UrlAccess;

import com.amir.usho.db.UrlRepo;
import com.amir.usho.db.UrlAccessRepo;

@Service
public class UrlServiceImpl implements UrlService{

	@Autowired
	private UrlRepo urlRepo;
	private UrlAccessRepo urlAccessRepo;

	@Override
	public List<Url> findAllUrls(){
		return urlRepo.findAll();
	}
	
	@Override
	public Optional<Url> findUrlById(Url u){
		return findUrlById(u,null);
	}
	
	@Override
	public Optional<Url> findUrlById(Url u,UrlAccess ua){
		if(ua!=null){
			ua.setUrl(u); // just to be sure
			urlAccessRepo.save(ua);
		}
		return urlRepo.findById(u);
	}

	@Override
	public Optional<Url> save(Url u){
		return urlRepo.save(u);
	}

}
