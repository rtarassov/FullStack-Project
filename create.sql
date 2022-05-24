CREATE TABLE IF NOT EXISTS public.users (
    id SERIAL NOT NULL,
    birth_date date,
    email character varying(40),
    name character varying(50),
    password character varying(25),
    product_limit integer NOT NULL,
    user_type character varying(20),
    username character varying(25),

    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.storage (
    id SERIAL NOT NULL,
    description character varying(50),
    name character varying(30),

    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.product (
    id SERIAL NOT NULL,
    description character varying(50),
    name character varying(25),
    product_type character varying(20),
    purchase_date date,
    serial_number character varying(25),
    price double precision,
    picture_id bigint,
    storage_id bigint,

    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.picture (
    id SERIAL NOT NULL,
    content oid,
    name character varying(50),

    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.users_storage (
    id SERIAL NOT NULL,
    users_id BIGINT NOT NULL,
    storages_id BIGINT NOT NULL
);

CREATE TABLE IF NOT EXISTS public.product_picture (
    id SERIAL NOT NULL,
    product_id BIGINT NOT NULL,
    picture_id BIGINT NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.storage_products (
    id SERIAL NOT NULL,
    storage_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,

    PRIMARY KEY (id)
);

ALTER TABLE public.product_picture
    ADD CONSTRAINT product_fk_id FOREIGN KEY (product_id) REFERENCES product(id),
    ADD CONSTRAINT picture_fk_id FOREIGN KEY (picture_id) REFERENCES picture(id);

ALTER TABLE public.storage_products
    ADD CONSTRAINT storage_fk_id FOREIGN KEY (storage_id) REFERENCES storage(id),
    ADD CONSTRAINT product_fk_id FOREIGN KEY (product_id) REFERENCES product(id);

ALTER TABLE public.product
    ADD CONSTRAINT storage_fk_id FOREIGN KEY (storage_id) REFERENCES storage(id),
    ADD CONSTRAINT picture_fk_id FOREIGN KEY (picture_id) REFERENCES picture(id);

ALTER TABLE public.users_storage
    ADD CONSTRAINT users_storages_fk_users_id FOREIGN KEY (users_id) REFERENCES users(id),
    ADD CONSTRAINT users_storages_fk_storages_id FOREIGN KEY (storages_id) REFERENCES storage(id);