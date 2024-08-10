package com.amir.usho.model;

import lombok.Getter;

@Getter
public enum AccessScope{
	
	PUBLIC("PUBLIC"),
	PRIVATE("PRIVATE");
	
	private final String scope;

	private AccessScope(String scope){
		this.scope=scope;
	}

}
