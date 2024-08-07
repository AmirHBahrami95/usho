package com.amir.usho.exc;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.http.HttpStatusCode;

// import org.springframework.beans.factory.annotation.Autowired;

@ControllerAdvice
public class WebExcHandler{

	// TODO add logging

	@ExceptionHandler(WebException.class)
	public ResponseEntity<WebException> handleError(WebException we){
		return new ResponseEntity<>(we,HttpStatusCode.valueOf(we.getStatusCode()));
	}

	@ExceptionHandler(org.springframework.web.client.HttpClientErrorException.NotFound.class)
	public ResponseEntity<WebException> handle404(){
		return new ResponseEntity<>(WebException.of(404,"Not Found"),HttpStatusCode.valueOf(404));
	}

}
