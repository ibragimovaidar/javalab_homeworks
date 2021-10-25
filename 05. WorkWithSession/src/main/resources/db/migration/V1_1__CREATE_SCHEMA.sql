DROP TABLE IF EXISTS account;

CREATE TABLE account (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(64),
    last_name VARCHAR(64),
    email VARCHAR(128) UNIQUE NOT NULL,
    password VARCHAR(64)
);
