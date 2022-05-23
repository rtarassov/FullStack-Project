CREATE EXTENSION citext;
CREATE DOMAIN EMAIL AS citext
    CHECK ( value ~ '^[a-zA-Z0-9.!#$%&''*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$' );

CREATE TABLE IF NOT EXISTS storage  (
    id SERIAL PRIMARY KEY,
    location VARCHAR(15) NOT NULL,
    description VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    birth_date DATE NOT NULL,
    email EMAIL(62) NOT NULL UNIQUE,
    username VARCHAR(15) NOT NULL UNIQUE,
    password VARCHAR(15) NOT NULL,
    product_limit SMALLINT NOT NULL,
    user_type VARCHAR(15) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS product (
    id SERIAL PRIMARY KEY,
    buy_date DATE,
    name VARCHAR(20) NOT NULL,
    description VARCHAR(50),
    serial_number VARCHAR(20),
    price DOUBLE PRECISION NOT NULL,
    type VARCHAR(20) NOT NULL,
    picture BYTEA,
    storage_id 
)