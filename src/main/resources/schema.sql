/* XXX DO NOT DROP THE DB FOR EASIER TESTING XXX */
CREATE DATABASE IF NOT EXISTS url_db;
USE url_db;

DROP TABLE IF EXISTS  usr;
DROP VIEW  IF EXISTS  url_access_view;
DROP TABLE IF EXISTS  url_access;
DROP TABLE IF EXISTS  url;
/*
CREATE TABLE usr(
	uname VARCHAR(32) PRIMARY KEY,
	passwd VARCHAR(256) NOT NULL,
	email VARCHAR(32) UNIQUE NOT NULL,
	phone_no VARCHAR(16) DEFAULT NULL,
	is_active BOOLEAN DEFAULT TRUE,
	role ENUM('USER','ADMIN','MOD') DEFAULT 'USER'
);
*/

CREATE TABLE url(
  id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  
  /*
    usr_uname VARCHAR(64) DEFAULT NULL, 
    FOREIGN KEY(usr_uname) REFERENCES usr(uname)
  */
  src VARCHAR(256) NOT NULL,
  dest VARCHAR(256) NOT NULL,
  namespace VARCHAR(32) NOT NULL,
  access_scope ENUM('public','private') DEFAULT 'public',
  created_at TIMESTAMP DEFAULT NOW(),
	UNIQUE(namespace,src)
);

CREATE TABLE url_access(
  url_id INT UNSIGNED NOT NULL,
  ip VARCHAR(128) NOT NULL, /* for both ipv4 and 6 */
  x_forwared_for VARCHAR(128) DEFAULT NULL,
  visit_ts TIMESTAMP DEFAULT NOW(),

	FOREIGN KEY (url_id) REFERENCES url(id)
);

CREATE VIEW url_access_view AS
	SELECT 
		u.id as id,
		u.src AS src,
		u.dest AS dest,
		u.namespace AS namespace,
		ua.ip AS IP,
		ua.x_forwared_for AS x_forwared_for,
		ua.visit_ts AS visit_ts
	FROM 
		url u
	INNER JOIN
		url_access ua
	ON 
		ua.url_id=u.id
	ORDER BY
		visit_ts,
		namespace,
		dest,
		src
	ASC
;
