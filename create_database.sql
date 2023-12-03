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
CREATE TABLE books
(
    id INT AUTO_INCREMENT NOT NULL,
    book_key VARCHAR(255) UNIQUE,
    title VARCHAR(255),
    description VARCHAR(255),
    cover VARCHAR(255),
    author VARCHAR(255),
    user_id INT,
    is_finished INT DEFAULT 0,
    start_date DATE,
    end_date DATE,
    PRIMARY KEY (id)
);