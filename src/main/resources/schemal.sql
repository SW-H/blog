DROP TABLE post;
DROP TABLE member;

CREATE TABLE post
(
    id int AUTO_INCREMENT PRIMARY KEY,
    title TEXT NOT NULL,
    content LONGTEXT NOT NULL,
    memberId INT,
    hits INT,
    createdAt TIMESTAMP,
    updatedAt TIMESTAMP,
    status VARCHAR(20)
);

CREATE TABLE member
(
    id int AUTO_INCREMENT PRIMARY KEY,
    nickName TEXT NOT NULL,
    email TEXT NOT NULL,
    password TEXT NOT NULL,
    createdAt TIMESTAMP,
    updatedAt TIMESTAMP,
    status VARCHAR(20)
);
