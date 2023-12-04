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
    book_key VARCHAR(700) UNIQUE,
    title VARCHAR(700),
    description VARCHAR(700),
    cover VARCHAR(700),
    author VARCHAR(700),
    user_id INT,
    is_finished INT DEFAULT 0,
    start_date DATE,
    end_date DATE,
    PRIMARY KEY (id)
);