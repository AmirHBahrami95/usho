package com.amir.usho.model;

import lombok.Getter;

@Getter
public enum Role{
	
	USER("USER"),
	ADMIN("ADMIN"),
	MOD("MOD");
	
	private final String roleStr;

	private Role(String roleStr){
		this.roleStr=roleStr;
	}

}
