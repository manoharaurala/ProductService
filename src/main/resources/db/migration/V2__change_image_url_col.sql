ALTER TABLE products
    ADD img_url VARCHAR(255) NULL;

DROP TABLE st_users_seq;

DROP TABLE tpc_users_seq;

ALTER TABLE products
DROP
COLUMN image_url;