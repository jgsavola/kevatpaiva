BEGIN;

CREATE SCHEMA kevatpaiva;

SET search_path TO kevatpaiva, public;

CREATE TABLE viitetyyppi (
    viitetyyppi text PRIMARY KEY
);

CREATE TABLE viite (
    viiteid serial PRIMARY KEY,
    viitetyyppi text NOT NULL REFERENCES viitetyyppi (viitetyyppi),
    author text,
    title text,
    journal text,
    volume integer,
    "number" integer,
    "year" integer,
    pages text,
    publisher text
);

INSERT INTO viitetyyppi (viitetyyppi) VALUES ('article');

COMMIT;
