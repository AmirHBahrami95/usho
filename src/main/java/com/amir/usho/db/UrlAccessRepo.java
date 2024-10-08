package com.amir.usho.db;

import java.util.List;
import java.util.Optional;

import com.amir.usho.model.UrlAccess;

// generated by "sprimp"
public interface UrlAccessRepo{
	List<UrlAccess> findAll();
	Optional<UrlAccess> findById(UrlAccess ua);
	// List<UrlAccess> findAllByIp(String ip);
	// List<UrlAccess> fiindAllByVisitTs(Timestamp ts);
	// List<UrlAccess> findAllByXFowradedFor(String xff);
	Optional<UrlAccess> save(UrlAccess ua);
}
