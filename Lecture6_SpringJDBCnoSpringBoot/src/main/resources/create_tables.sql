CREATE TABLE offers
(
    id INT AUTO_INCREMENT NOT NULL,
    text VARCHAR(600) NOT NULL,
    username VARCHAR(60) NOT NULL,
    PRIMARY KEY (id, username)
);
CREATE TABLE users
(
    username VARCHAR(60) PRIMARY KEY NOT NULL,
    password VARCHAR(80),
    enabled TINYINT,
    email VARCHAR(60) NOT NULL,
    name VARCHAR(45) NOT NULL,
    authority VARCHAR(45) NOT NULL
);
ALTER TABLE offers ADD FOREIGN KEY (username) REFERENCES users (username);
CREATE INDEX fk_offers_users_idx ON offers (username);