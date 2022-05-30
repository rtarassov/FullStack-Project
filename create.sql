CREATE TABLE IF NOT EXISTS users (
    id BIGINT NOT NULL,
    birth_date date,
    email varchar(40),
    name varchar(50),
    password varchar(25),
    product_limit BIGINT NOT NULL,
    user_type varchar(20),
    username varchar(25),

    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS storage (
    id BIGINT NOT NULL,
    description varchar(50),
    name varchar(30),
    sub_storage BIGINT REFERENCES storage(id),

    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS product (
    id BIGINT NOT NULL,
    description varchar(50),
    name varchar(25),
    product_type varchar(20),
    purchase_date date,
    serial_number varchar(25),
    price double precision,
    picture_id BIGINT,
    storage_id BIGINT,

    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS picture (
    id BIGINT NOT NULL,
    content oid,
    name varchar(50),

    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS users_storage (
    id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    storage_id BIGINT NOT NULL
);

CREATE TABLE IF NOT EXISTS product_storages (
    id BIGINT NOT NULL,
    storages_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,

    PRIMARY KEY (id)
);

ALTER TABLE product_storages
    ADD CONSTRAINT storage_fk_id FOREIGN KEY (storages_id) REFERENCES storage(id),
    ADD CONSTRAINT product_fk_id FOREIGN KEY (product_id) REFERENCES product(id);

ALTER TABLE product
    ADD CONSTRAINT picture_fk_id FOREIGN KEY (picture_id) REFERENCES picture(id);

ALTER TABLE users_storage
    ADD CONSTRAINT users_storages_fk_users_id FOREIGN KEY (user_id) REFERENCES users(id),
    ADD CONSTRAINT users_storages_fk_storages_id FOREIGN KEY (storage_id) REFERENCES storage(id);

INSERT INTO storage (id, description, name, sub_storage) VALUES (3, 'Guest computer', 'On the table, no drawers', NULL);
INSERT INTO storage (id, description, name, sub_storage) VALUES (2, 'Dining room', 'Dining room', 3);
INSERT INTO storage (id, description, name, sub_storage) VALUES (1, 'Tom''s house', 'House 1', 2);

INSERT INTO product (id, description, name, product_type, purchase_date, serial_number, price, picture_id, storage_id) VALUES (1, 'Wireless, with USB transmitter', 'DELL mouse', 'PERIPHERAL', '2018-11-22', 'mDELL20181122', 18.99, NULL, 3);
INSERT INTO product (id, description, name, product_type, purchase_date, serial_number, price, picture_id, storage_id) VALUES (2, 'Wireless, with USB transmitter', 'DELL keyboard', 'PERIPHERAL', '2018-11-22', 'kDELL20181122', 18.99, NULL, 3);
