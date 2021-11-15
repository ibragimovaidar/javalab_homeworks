CREATE TABLE product (
    id BIGSERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    price BIGINT NOT NULL,
    description TEXT,
    available_amount INTEGER DEFAULT 0
);