CREATE TABLE product(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price INTEGER NOT NULL ,
    amount INTEGER NOT NULL DEFAULT 0
);