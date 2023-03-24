DROP DATABASE IF EXISTS tech_database;
CREATE DATABASE tech_database;

\c tech_database;

CREATE TABLE IF NOT EXISTS owner
(
    owner_id        INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    owner_name      VARCHAR(255) NOT NULL,
    owner_birth_date DATE         NOT NULL
);

CREATE TABLE IF NOT EXISTS pet
(
    pet_id        INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    pet_name      VARCHAR(255) NOT NULL,
    pet_birth_date DATE         NOT NULL,
    owner_id      INT,
    CONSTRAINT owner_id FOREIGN KEY (owner_id) REFERENCES owner (owner_id),
    breed        VARCHAR(255) NOT NULL,
    color        VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS friend
(
    pet_id INTEGER,
    friend_id INTEGER,
    CONSTRAINT pet_id FOREIGN KEY (pet_id) REFERENCES pet (pet_id) ON DELETE CASCADE,
    CONSTRAINT friend_id FOREIGN KEY (friend_id) REFERENCES pet (pet_id) ON DELETE CASCADE,
    PRIMARY KEY (pet_id, friend_id)
);