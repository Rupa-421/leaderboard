-- !Ups

CREATE TABLE techhub(id VARCHAR(100) PRIMARY KEY, email_id VARCHAR(100) NOT NULL, uploaded_on TIMESTAMP NOT NULL, title VARCHAR(255) NOT NULL);

-- !Downs

DROP TABLE techhub;
