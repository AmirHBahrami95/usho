package com.amir.usho.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.sql.Timestamp;
// import java.sql.SQLException;
// import java.sql.ResultSet;

@Data
@Getter @Setter 
@NoArgsConstructor 
@AllArgsConstructor
public class Url{

	private long id;
	private String nameSpace;
	private String src;
	private String dest;
	private String accessScope; 
	private Timestamp createdAt;

	/*
	public Url rowToUrl(ResultSet rs,int rowNum){
		Url u=new Url();
		try{
			u.setId(rs.getLong("id"));
			u.setNameSpace(rs.getString("namespace"));
			u.setSrc(rs.getString("src"));
			u.setDest(rs.getString("dest"));
			u.setAccessScope(rs.getString("access_scope"));
			u.setVisitTs(rs.getTimestamp("visit_ts"));
		}catch(SQLException sqe){
			// TODO log exception
			return null;
		}
		return u;
	}
	*/
}
