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
    username varchar(255) NOT NULL,
    password_hash varchar(255) NOT NULL,
    PRIMARY KEY(username)
);

INSERT INTO users (username, password_hash) 
VALUES ("admin", "$2a$12$Y6.JQvFcdsy.3y.pV9WZuukJHyqfjQHTRthPtSkPbf9Hvv7NUthae");
