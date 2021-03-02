CREATE DATABASE songguesser;

USE songguesser;

CREATE USER SongGuesser@localhost IDENTIFIED BY 'songguesser';

GRANT ALL privileges ON `songguesser`.* TO 'SongGuesser';
