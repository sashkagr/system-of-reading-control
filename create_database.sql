DROP DATABASE IF EXISTS system_of_reading_control;
CREATE DATABASE system_of_reading_control;
USE system_of_reading_control;

CREATE TABLE users
(
    id INT AUTO_INCREMENT NOT NULL,
    login VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    PRIMARY KEY (id)
);
