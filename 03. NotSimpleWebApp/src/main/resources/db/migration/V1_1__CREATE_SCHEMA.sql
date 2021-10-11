DROP TABLE IF EXISTS word;
DROP TABLE IF EXISTS words_statistics;

CREATE TABLE words_statistics(
    id SERIAL PRIMARY KEY,
    folder VARCHAR(512),
    filename VARCHAR(128),
    created_at TIMESTAMP
);

CREATE TABLE word(
    id SERIAL PRIMARY KEY,
    word TEXT,
    value INTEGER,
    words_statistics_id INTEGER REFERENCES words_statistics(id),
    UNIQUE (word, words_statistics_id)
);
