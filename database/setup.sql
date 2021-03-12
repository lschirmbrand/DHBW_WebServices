CREATE DATABASE songguesser;

USE songguesser;

CREATE USER SongGuesser@localhost IDENTIFIED BY 'songguesser';

GRANT ALL privileges ON `songguesser`.* TO 'SongGuesser';

CREATE TABLE matches (
    id int NOT NULL AUTO_INCREMENT,
    spotifyid varchar(22) NOT NULL,
    tmdbid int NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE users (
    id int NOT NULL AUTO_INCREMENT,
    username text NOT NULL,
    password_hash text NOT NULL,
    PRIMARY KEY(id)
);

INSERT INTO users (username, password_hash) 
VALUES ("root", SHA2("root"))