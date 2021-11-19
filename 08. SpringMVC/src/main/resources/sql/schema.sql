CREATE TABLE product(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(127) NOT NULL,
    price INTEGER NOT NULL,
    available_amount INTEGER DEFAULT 0 NOT NULL,
    description TEXT NOT NULL
);