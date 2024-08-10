package com.amir.usho.control;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;
import java.util.List;

import com.amir.usho.model.Url;
import com.amir.usho.model.UrlAccess;
import com.amir.usho.db.UrlRepo;
import com.amir.usho.exc.WebException;
import com.amir.usho.service.UrlService;

@RestController
@RequestMapping(path="/api/url")
public class UrlController{

	@Autowired
	private UrlService urlService;

	private UrlAccess makeUa(HttpServletRequest req){
		UrlAccess ua=new UrlAccess();
		String xff=req.getHeader("x-forwarded-for");
		if(xff!= null){
			ua.setXForwardedFor(xff);
		}
		else ua.setXForwardedFor("-");
		ua.setIp(req.getRemoteAddr());
		System.out.println("URL_BULLSHIT:"+ua.getXForwardedFor()+" //// "+ua.getIp());
		return ua;
	}
	
	@GetMapping(path="/{id}",produces="application/json")
	public ResponseEntity<Url> getUrl(@PathVariable String id,@Autowired HttpServletRequest req) throws WebException{
		Url u=new Url();
		try{
			u.setId(Long.valueOf(id));
		}catch(NumberFormatException nfe){
			throw WebException.of(400,"Wrong Id Format");
		}
		Optional o=urlService.findUrlById(u,makeUa(req));
		if(!o.isPresent())
			throw WebException.of(404,"Not Found");
		return ResponseEntity.of(o);
	}
	
	/**/
	@GetMapping(path="/all",produces="application/json")
	public List<Url> getAllUrls(){
		return urlService.findAllUrls();
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
