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
public class Url{

	// src -> dest
	private long id;
	private String nameSpace;
	private String src;
	private String dest;
	
}
