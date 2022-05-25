CREATE TABLE IF NOT EXISTS public.users (
    id BIGINT NOT NULL,
    birth_date date,
    email character varying(40),
    name character varying(50),
    password character varying(25),
    product_limit BIGINT NOT NULL,
    user_type character varying(20),
    username character varying(25),

    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.storage (
    id BIGINT NOT NULL,
    description character varying(50),
    name character varying(30),

    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.product (
    id BIGINT NOT NULL,
    description character varying(50),
    name character varying(25),
    product_type character varying(20),
    purchase_date date,
    serial_number character varying(25),
    price double precision,
    picture_id BIGINT,
    storage_id BIGINT,

    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.picture (
    id BIGINT NOT NULL,
    content oid,
    name character varying(50),

    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.users_storage (
    id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    storage_id BIGINT NOT NULL
);

CREATE TABLE IF NOT EXISTS public.product_picture (
    id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    picture_id BIGINT NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.product_storages (
    id BIGINT NOT NULL,
    storages_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,

    PRIMARY KEY (id)
);

ALTER TABLE public.product_picture
    ADD CONSTRAINT product_fk_id FOREIGN KEY (product_id) REFERENCES product(id),
    ADD CONSTRAINT picture_fk_id FOREIGN KEY (picture_id) REFERENCES picture(id);

ALTER TABLE public.product_storages
    ADD CONSTRAINT storage_fk_id FOREIGN KEY (storages_id) REFERENCES storage(id),
    ADD CONSTRAINT product_fk_id FOREIGN KEY (product_id) REFERENCES product(id);

ALTER TABLE public.product
    ADD CONSTRAINT storage_fk_id FOREIGN KEY (storage_id) REFERENCES storage(id),
    ADD CONSTRAINT picture_fk_id FOREIGN KEY (picture_id) REFERENCES picture(id);

ALTER TABLE public.users_storage
    ADD CONSTRAINT users_storages_fk_users_id FOREIGN KEY (user_id) REFERENCES users(id),
    ADD CONSTRAINT users_storages_fk_storages_id FOREIGN KEY (storage_id) REFERENCES storage(id);