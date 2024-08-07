CREATE DATABASE IF NOT EXISTS url_db;
USE url_db;

DROP TABLE IF EXISTS usr;
DROP TABLE IF EXISTS url;

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
	id INT AUTO_INCREMENT PRIMARY KEY,

	/* used as namespace */
	/*usr_uname VARCHAR(64) DEFAULT NULL,	
	FOREIGN KEY(usr_uname) REFERENCES usr(uname)
	*/
	src VARCHAR(256) NOT NULL,
	dest VARCHAR(256) NOT NULL,
	namespace VARCHAR(32) NOT NULL
);

/* put any source of analysis here */
CREATE TABLE url_analysis(
	id INT NOT NULL,

	/* how many times is this requested */
	n_requested UNSIGNED INT DEFAULT 0,

	/* how squeezed is 'dest' compared to 'src' */
	perc_efficiency UNSIGNED INT DEFAULT 0,
	FOREIGN KEY(id) REFERENCES url(id),
	PRIMARY KEY(id) 
);
