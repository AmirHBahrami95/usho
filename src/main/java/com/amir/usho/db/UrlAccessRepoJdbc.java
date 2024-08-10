package com.amir.usho.db;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;

import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.ResultSet;
import java.sql.Types;

import java.util.List;
import java.util.Arrays;
import java.util.Optional;
import java.util.Collections;

import com.amir.usho.model.Url;
import com.amir.usho.model.UrlAccess;

@Repository
public class UrlAccessRepoJdbc implements UrlAccessRepo{
	
	@Autowired private JdbcTemplate jd;

	@Override
	public List<UrlAccess> findAll(){
		return jd.query("SELECT * FROM url_access_view",this::rowToUrlAccess);
	}


	@Override
	public Optional<UrlAccess> findById(UrlAccess ua){
		PreparedStatementCreatorFactory pscf= 
			new PreparedStatementCreatorFactory(
				"SELECT * FROM url_access_view WHERE id=?",Types.INTEGER);
		PreparedStatementCreator psc=
			pscf.newPreparedStatementCreator(
				Arrays.asList(ua.getUrl().getId()));
		List<UrlAccess> us=jd.query(psc,this::rowToUrlAccess);
		return us.size()<=0?
			Optional.empty():
			Optional.of(us.get(0));
	}

	@Override
	public Optional<UrlAccess> save(UrlAccess ua){
		PreparedStatementCreatorFactory pscf=
			new PreparedStatementCreatorFactory(
				"INSERT INTO url_access(url_id,ip,x_forwarded_for)"
				+"VALUES(?,?,?) ",
				Types.INTEGER,
				Types.VARCHAR,
				Types.VARCHAR
			);

		PreparedStatementCreator psc=
			pscf.newPreparedStatementCreator(
				Arrays.asList(
					ua.getUrl().getId(),
					ua.getIp(),
					ua.getXForwardedFor())
			);

			KeyHolder kh=new GeneratedKeyHolder();
			int nRes=jd.update(psc,kh);
			ua.getUrl().setId(kh.getKey().longValue());
			return nRes>=1?Optional.of(ua):Optional.empty();
	}

	private UrlAccess rowToUrlAccess(ResultSet rs,int rowNum){
		UrlAccess ua=new UrlAccess();
		try{
			ua.setUrl(rowToUrl(rs,rowNum));
			ua.setIp(rs.getString("ip"));
			ua.setXForwardedFor(rs.getString("x_forwarded_for"));
			ua.setVisitTs(rs.getTimestamp("visit_ts"));
		}catch(SQLException sqle){
			return null;
		}
		return ua;
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
