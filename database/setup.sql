CREATE DATABASE soundtrack_guesser;

USE soundtrack_guesser;

CREATE USER SoundTrackGuesser IDENTIFIED BY 'soundtrackguesser';

GRANT ALL privileges ON `soundtrack_guesser`.* TO 'SoundTrackGuesser';

CREATE TABLE matches (
    id int NOT NULL AUTO_INCREMENT,
    trackid varchar(22) NOT NULL,
    movieid int NOT NULL,
    PRIMARY KEY (id)
);
