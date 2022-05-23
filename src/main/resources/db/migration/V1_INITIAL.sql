CREATE DOMAIN EMAIL AS text
    CHECK ( value ~ '^[a-zA-Z0-9.!#$%&''*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$' );

CREATE TABLE IF NOT EXISTS storages  (
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

CREATE TABLE IF NOT EXISTS products (
    id SERIAL PRIMARY KEY,
    storage_id INT,
    buy_date DATE,
    name VARCHAR(20) NOT NULL,
    description VARCHAR(50),
    serial_number VARCHAR(20),
    price DOUBLE PRECISION NOT NULL,
    type VARCHAR(20) NOT NULL,
    picture BYTEA,
    CONSTRAINT fk_storage
        FOREIGN KEY (storage_id)
            REFERENCES storages(id)
);

CREATE TABLE IF NOT EXISTS user_storages (
    user_id INT,
    storage_id INT,
    PRIMARY KEY (user_id, storage_id),
        CONSTRAINT fk_user FOREIGN KEY(user_id) REFERENCES users(id),
        CONSTRAINT fk_storage FOREIGN KEY(storage_id) REFERENCES storages(id)
);

CREATE TABLE IF NOT EXISTS storage_products (
    storage_id INT,
    product_id INT,
    PRIMARY KEY(storage_id, product_id),
        CONSTRAINT fk_storage FOREIGN KEY(storage_id) REFERENCES storages(id),
        CONSTRAINT fk_product FOREIGN KEY(product_id) REFERENCES products(id)
);

