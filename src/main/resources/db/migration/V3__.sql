ALTER TABLE categories_products
    ADD CONSTRAINT uc_categories_products_products UNIQUE (products_id);

ALTER TABLE categories_products
    ADD CONSTRAINT fk_catpro_on_category FOREIGN KEY (category_id) REFERENCES categories (id);

ALTER TABLE categories_products
    ADD CONSTRAINT fk_catpro_on_product FOREIGN KEY (products_id) REFERENCES products (id);

DROP TABLE st_users_seq;

DROP TABLE tpc_users_seq;