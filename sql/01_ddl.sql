    CREATE SCHEMA app
    AUTHORIZATION postgres;

CREATE TABLE app.artist
(
    id bigserial,
    name character varying(128) NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS app.artist
    OWNER to postgres;

CREATE TABLE app.genre
(
    id bigserial,
    name character varying(128) NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS app.genre
    OWNER to postgres;




