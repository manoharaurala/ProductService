CREATE TABLE categories
(
    created_at       datetime NULL,
    id               BIGINT AUTO_INCREMENT NOT NULL,
    last_modified_at datetime NULL,
    title            VARCHAR(255) NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE instructor
(
    id      BIGINT AUTO_INCREMENT NOT NULL,
    email   VARCHAR(255) NULL,
    name    VARCHAR(255) NULL,
    rating  VARCHAR(255) NULL,
    subject VARCHAR(255) NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE jt_instructors
(
    rating  VARCHAR(255) NULL,
    subject VARCHAR(255) NULL,
    user_id BIGINT NOT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (user_id)
);

CREATE TABLE jt_mentors
(
    company_name VARCHAR(255) NULL,
    user_id      BIGINT NOT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (user_id)
);

CREATE TABLE jt_tas
(
    number_ofhr INT    NOT NULL,
    user_id     BIGINT NOT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (user_id)
);

CREATE TABLE jt_users
(
    id    BIGINT AUTO_INCREMENT NOT NULL,
    email VARCHAR(255) NULL,
    name  VARCHAR(255) NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE mentor
(
    id           BIGINT AUTO_INCREMENT NOT NULL,
    email        VARCHAR(255) NULL,
    name         VARCHAR(255) NULL,
    company_name VARCHAR(255) NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE products
(
    price DOUBLE NULL,
    category_id      BIGINT NULL,
    created_at       datetime NULL,
    id               BIGINT AUTO_INCREMENT NOT NULL,
    last_modified_at datetime NULL,
    `description`    VARCHAR(255) NULL,
    image_url        VARCHAR(255) NULL,
    title            VARCHAR(255) NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE ta
(
    id          BIGINT AUTO_INCREMENT NOT NULL,
    email       VARCHAR(255) NULL,
    name        VARCHAR(255) NULL,
    number_ofhr INT NOT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

ALTER TABLE jt_instructors
    ADD CONSTRAINT FKfcvjoonghpm022veo6us1ifyp FOREIGN KEY (user_id) REFERENCES jt_users (id) ON DELETE NO ACTION;

ALTER TABLE jt_mentors
    ADD CONSTRAINT FKjf8q53loch2j5whhm3b4xq2hg FOREIGN KEY (user_id) REFERENCES jt_users (id) ON DELETE NO ACTION;

ALTER TABLE products
    ADD CONSTRAINT FKog2rp4qthbtt2lfyhfo32lsw9 FOREIGN KEY (category_id) REFERENCES categories (id) ON DELETE NO ACTION;

CREATE INDEX FKog2rp4qthbtt2lfyhfo32lsw9 ON products (category_id);

ALTER TABLE jt_tas
    ADD CONSTRAINT FKq53t3mrsynwbnu89l0bmkprsx FOREIGN KEY (user_id) REFERENCES jt_users (id) ON DELETE NO ACTION;