package com.amir.usho.db;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.ResultSet;
import java.sql.Types;

import java.util.List;
import java.util.Arrays;
import java.util.Optional;
import java.util.Collections;

import com.amir.usho.model.Url;

@Repository
public class UrlRepoJdbc implements UrlRepo{

	@Autowired private JdbcTemplate jd;

	@Override
	public Optional<Url> findById(Url u){
		PreparedStatementCreatorFactory pscf= 
			new PreparedStatementCreatorFactory(
				"SELECT * FROM url WHERE id=?",Types.INTEGER);
		PreparedStatementCreator psc=
			pscf.newPreparedStatementCreator(
				Arrays.asList(u.getId()));
		List<Url> us=jd.query(psc,this::rowToUrl);
		return us.size()<=0?
			Optional.empty():
			Optional.of(us.get(0));
	}

	@Override
	public List<Url> findAll(){
		return jd.query("SELECT * FROM url",this::rowToUrl);
	}

	@Override
	public List<Url> findAllBySrc(String src){
		PreparedStatementCreatorFactory pscf= 
			new PreparedStatementCreatorFactory(
				"SELECT * FROM url WHERE src=?",Types.VARCHAR);
		PreparedStatementCreator psc=
			pscf.newPreparedStatementCreator(
				Arrays.asList(src));
		return jd.query(psc,this::rowToUrl);
	}
	
	// get all those who point to a specific destination
	@Override
	public List<Url> findAllByDest(String dest){
		PreparedStatementCreatorFactory pscf= 
			new PreparedStatementCreatorFactory(
				"SELECT * FROM url WHERE dest=?",Types.VARCHAR);
		PreparedStatementCreator psc=
			pscf.newPreparedStatementCreator(
				Arrays.asList(dest));
		return jd.query(psc,this::rowToUrl);
	}
	
	@Override
	public Optional<Url> save(Url u){
		if(u.getId()==-1 || u.getId()==0)
			return insert(u);
		else return update(u);
	}

	private Optional<Url> insert(Url u){
		PreparedStatementCreatorFactory pscf=
			new PreparedStatementCreatorFactory(
				"INSERT INTO url(src,dest,namespace,accessScope) VALUES(?,?,?)",
				Types.VARCHAR,Types.VARCHAR,Types.VARCHAR
			);

		PreparedStatementCreator psc=
			pscf.newPreparedStatementCreator(
				Arrays.asList(
					u.getSrc(),u.getDest(),u.getNameSpace()));

		KeyHolder kh=new GeneratedKeyHolder();
		int nRes=jd.update(psc,kh);
		u.setId(kh.getKey().longValue());
		return nRes>=1?Optional.of(u):Optional.empty();
	}

	private Optional<Url> update(Url u){
		
		if(u.getAccessScope()==null)
			u.setAccessScope("public");

		PreparedStatementCreatorFactory pscf= new PreparedStatementCreatorFactory(
			"UPDATE url SET src=?, dest=?,namespace=? WHERE id=?",
				Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.INTEGER
		);

		PreparedStatementCreator psc=
			pscf.newPreparedStatementCreator(
				Arrays.asList(
					u.getSrc(),u.getDest(),
						u.getNameSpace(),u.getId()));

		int nRes=jd.update(psc);
		return nRes>=1?Optional.of(u):Optional.empty();
	}

	@Override
	public boolean deleteUrlById(Url u){
		PreparedStatementCreatorFactory pscf=
			new PreparedStatementCreatorFactory(
				"DELETE FROM url WHERE id=?",Types.INTEGER);
		PreparedStatementCreator psc= 
			pscf.newPreparedStatementCreator(
				Arrays.asList(u.getId()));
		return jd.update(psc)>0;
	}
	
	// TODO put these guys in their models
	private Url rowToUrl(ResultSet rs,int rowNum){
		Url u=new Url();
		try{
			u.setId(rs.getLong("id"));
			u.setNameSpace(rs.getString("namespace"));
			u.setSrc(rs.getString("src"));
			u.setDest(rs.getString("dest"));
			u.setAccessScope(rs.getString("access_scope"));
			u.setCreatedAt(rs.getTimestamp("created_at"));
		}catch(SQLException sqe){
			// TODO log exception
			return null;
		}
		return u;
	}

}
/**/
