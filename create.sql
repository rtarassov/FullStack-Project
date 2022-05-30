CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL NOT NULL,
    birth_date date,
    email varchar(40),
    name varchar(50),
    password varchar(25),
    product_limit BIGSERIAL NOT NULL,
    user_type varchar(20),
    username varchar(25),

    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS storage (
    id BIGSERIAL NOT NULL,
    description varchar(50),
    name varchar(30),

    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS storage_sub_storage (
    sub_storage_id BIGSERIAL PRIMARY KEY NOT NULL,
    depth SMALLSERIAL NOT NULL,
    storage_id BIGSERIAL NOT NULL
);

CREATE TABLE IF NOT EXISTS product (
    id BIGSERIAL NOT NULL,
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
    id BIGSERIAL NOT NULL,
    content oid,
    name varchar(50),

    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS users_storage (
    id BIGSERIAL NOT NULL,
    user_id BIGSERIAL NOT NULL,
    storage_id BIGSERIAL NOT NULL
);

CREATE TABLE IF NOT EXISTS product_storages (
    id BIGSERIAL NOT NULL,
    storages_id BIGSERIAL NOT NULL,
    product_id BIGSERIAL NOT NULL,

    PRIMARY KEY (id)
);

ALTER TABLE product_storages
    ADD CONSTRAINT storage_fk_id FOREIGN KEY (storages_id) REFERENCES storage(id),
    ADD CONSTRAINT product_fk_id FOREIGN KEY (product_id) REFERENCES product(id);

ALTER TABLE storage_sub_storage
    ADD CONSTRAINT parent_storage_fk_id FOREIGN KEY (storage_id) REFERENCES storage(id);

ALTER TABLE product
    ADD CONSTRAINT picture_fk_id FOREIGN KEY (picture_id) REFERENCES picture(id),
    ADD CONSTRAINT storage_fk_id FOREIGN KEY (storage_id) REFERENCES storage(id);

ALTER TABLE users_storage
    ADD CONSTRAINT users_storages_fk_users_id FOREIGN KEY (user_id) REFERENCES users(id),
    ADD CONSTRAINT users_storages_fk_storages_id FOREIGN KEY (storage_id) REFERENCES storage(id);

INSERT INTO storage (id, description, name) VALUES (3, 'Guest computer, no drawers', 'Guest computer table');
INSERT INTO storage (id, description, name) VALUES (2, 'Dining room', 'Dining room');
INSERT INTO storage (id, description, name) VALUES (1, 'Tom''s house', 'House 1');

INSERT INTO product (id, description, name, product_type, purchase_date, serial_number, price, picture_id, storage_id) VALUES (1, 'Wireless, with USB transmitter', 'DELL mouse', 'PERIPHERAL', '2018-11-22', 'mDELL20181122', 18.99, NULL, 3);
INSERT INTO product (id, description, name, product_type, purchase_date, serial_number, price, picture_id, storage_id) VALUES (2, 'Wireless, with USB transmitter', 'DELL keyboard', 'PERIPHERAL', '2018-11-22', 'kDELL20181122', 18.99, NULL, 3);
