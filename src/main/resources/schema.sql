CREATE TABLE USER (
  id VARCHAR(255) PRIMARY KEY,  
  username VARCHAR(255) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL,
  token VARCHAR(255)
);

-- Bcrypted passwords
INSERT INTO USER(id, username, password) VALUES('1', 'cartorgon', '$2a$10$8WQ/yR0cCOrO3HMdAorhr.kRpTTPghTb8ORPsj7VtvG5hcBjW3ZZK');
INSERT INTO USER(id, username, password) VALUES('2', 'fulanito', '$2a$10$kbk0hHHbQvi2IFCopOUlLehc9cAvY6kkhaH0BdZ.AiCB0S8OK0ixC');
INSERT INTO USER(id, username, password) VALUES('3', 'menganito', '$2a$10$nh3ATDbIiMI9vFyInETCAeJb5ypUbR1VJnqjnGbnFpM7XCjMbIrB2');