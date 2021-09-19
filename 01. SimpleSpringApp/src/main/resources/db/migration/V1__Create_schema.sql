DROP TABLE IF EXISTS word_stat;
DROP TABLE IF EXISTS stats_record;


CREATE TABLE stats_record (
  id SERIAL PRIMARY KEY,
  description VARCHAR(255)
);

CREATE TABLE word_stat (
    id SERIAL PRIMARY KEY,
    word VARCHAR(127),
    value INTEGER,
    stats_record_id INTEGER REFERENCES stats_record(id),
    UNIQUE(stats_record_id, word)
);