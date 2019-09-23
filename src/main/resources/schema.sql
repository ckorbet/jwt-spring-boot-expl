CREATE TABLE USER (
  id VARCHAR(255) PRIMARY KEY,  
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  token VARCHAR(255)
);

INSERT INTO USER(id, username, password) VALUES('1', 'cartorgon', 'cartorgon');
INSERT INTO USER(id, username, password) VALUES('2', 'fulanito', 'fulanito');
INSERT INTO USER(id, username, password) VALUES('3', 'menganito', 'menganito');