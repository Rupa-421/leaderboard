-- !Ups
CREATE TABLE oscontribution(id VARCHAR(100) PRIMARY KEY, email_id VARCHAR(100) NOT NULL, contributed_on TIMESTAMP NOT NULL, title VARCHAR(255) NOT NULL);
CREATE TABLE conference(id VARCHAR(100) PRIMARY KEY, email_id VARCHAR(100) NOT NULL, delivered_on TIMESTAMP NOT NULL, title VARCHAR(255) NOT NULL);
-- !Downs
DROP TABLE oscontribution;
DROP TABLE conference;
