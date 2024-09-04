CREATE TABLE app.vote
(
    id BIGSERIAL,
    create_at TIMESTAMPTZ NOT NULL,
    artist_id BIGINT NOT NULL,
    about TEXT,
    PRIMARY KEY (id),
    FOREIGN KEY (artist_id) REFERENCES app.artist(id)
);

ALTER TABLE IF EXISTS app.vote
    OWNER to postgres;


CREATE TABLE app.cross_vote_genre
(
  vote_id BIGINT,
  genre_id BIGINT,
  FOREIGN KEY (vote_id) REFERENCES app.vote (id),
  FOREIGN KEY (genre_id) REFERENCES app.genre (id),
  UNIQUE (vote_id, genre_id)
);

ALTER TABLE IF EXISTS app.cross_vote_genre
    OWNER to postgres;


