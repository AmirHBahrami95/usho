package com.amir.usho.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class User{
	
	private String uname;
	private String email;
	private String phoneNo;
	private boolean isActive;
	private Role role;
}
