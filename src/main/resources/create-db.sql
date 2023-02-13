DROP TABLE IF EXISTS users;
        CREATE TABLE users (
        id INTEGER PRIMARY KEY AUTO_INCREMENT,
        userName VARCHAR(50) NOT NULL,
        userPassword VARCHAR(150) NOT NULL,
        isEnabled BOOLEAN NOT NULL
        );
