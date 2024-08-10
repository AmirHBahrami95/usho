package com.amir.usho.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.sql.Timestamp;

@Data
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class UrlAccess{
	private Url url; // easier for joins
	private String ip;
	private String xForwardedFor;
	private Timestamp visitTs;
}
