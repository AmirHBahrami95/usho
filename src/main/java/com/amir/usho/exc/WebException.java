package com.amir.usho.exc;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class WebException extends Exception{

	public static WebException of(int statusCode,String userError){
		return WebException.of(statusCode,userError,"[NO_LOG_MSG]");
	}
	
	public static WebException of( int statusCode, String userError, String serverError){
		WebException we=new WebException();
		we.setStatusCode(statusCode);
		we.setUserError(userError);
		we.setServerError(serverError);
		return we;
	}

	private int statusCode;
	private String userError;
	private String serverError; // for logging purposes
}
