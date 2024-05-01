DROP TABLE IF EXISTS words;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    id INT NOT NULL AUTO_INCREMENT,
    username VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(id)
);

CREATE TABLE categories
(
    category_id INT NOT NULL AUTO_INCREMENT,
    category_name VARCHAR(100) NOT NULL,
    user_id INT NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(category_id),
    FOREIGN KEY(user_id) REFERENCES users(id)
);

CREATE TABLE words
(
    id INT NOT NULL AUTO_INCREMENT,
    category_id INT NOT NULL,
    word VARCHAR(100) NOT NULL,
    word_explain VARCHAR(255) NOT NULL,
    user_id INT NOT NULL,
    wrong_times INT DEFAULT 0,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(id),
    FOREIGN KEY(category_id) REFERENCES categories(category_id),
    FOREIGN KEY(user_id) REFERENCES users(id)
);
