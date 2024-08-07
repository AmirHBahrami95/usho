package com.amir.usho.control;

import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.List;

import com.amir.usho.model.Url;
import com.amir.usho.db.UrlRepo;
import com.amir.usho.exc.WebException;

@RestController
@RequestMapping(path="/api/url")
public class UrlController{

	@Autowired
	private UrlRepo urlRepo;
	
	@GetMapping(path="/{id}",produces="application/json")
	public ResponseEntity<Url> getUrl(@PathVariable String id) throws WebException{
		Optional<Url> o=Optional.empty();
		long idLong=-1l;
		try{
			idLong=Long.valueOf(id);
			o=urlRepo.findById(idLong);
		}catch(NumberFormatException nfe){
			throw WebException.of(400,"Id must be numeric");
		}

		if(!o.isPresent())
			throw WebException.of(404,"Not Found");

		return ResponseEntity.of(o);
	}
	
	/**/
	@GetMapping(path="/all",produces="application/json")
	public List<Url> getAllUrls(){
		return urlRepo.findAll();
	}

	@GetMapping(path="/ping",produces="text/plain")
	public String pong(){
		return "pong";
	}
	/**/

	/*
	@PutMapping(value="/put",consumes="application/json",produces="application/json")
	public ResponseEntity<Url> putUrl(@RequestBody Url u){
		Optional<Url> o=urlRepo.save(u);
		return o.isPresent?
			ResponseEntity.of(o.get()):
			ResponseEntity.BodyBuilder.badRequest();
	}
	/**/

	// @PutMapping("/update/{id}")
  // public ResponseEntity<Url> update(@RequestBody Employee newEmployee, @PathVariable Long id){
	
  // }
	
}
