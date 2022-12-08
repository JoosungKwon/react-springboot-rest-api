# create database boot_camp_rating_net;
create database bootcamp_rating_net;
USE bootcamp_rating_net;


CREATE TABLE bootcamp
(
    camp_id     varchar(255)  NOT NULL PRIMARY KEY,
    name        varchar(30)   NOT NULL,
    description varchar(1000) NOT NULL,
    image       varchar(255)  NOT NULL
);

CREATE TABLE user
(
    user_id   varchar(255) NOT NULL PRIMARY KEY,
    nick_name varchar(20)  NOT NULL UNIQUE,
    password  varchar(20)  NOT NULL,
    email     varchar(30)  NOT NULL,
    phone     varchar(13)  NOT NULL UNIQUE,
    bootcamp  varchar(30)  NULL
);

CREATE TABLE camp_review
(
    review_id      varchar(255)  NOT NULL PRIMARY KEY,
    user_id        varchar(255)  NOT NULL,
    camp_id        varchar(255)  NOT NULL,
    title          varchar(30)   NOT NULL,
    content        varchar(300)  NOT NULL,
    rating         DECIMAL(5, 2) NOT NULL,
    created_at     TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_camp FOREIGN KEY (camp_id) REFERENCES bootcamp (camp_id) on delete cascade on update cascade,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES user (user_id) on delete cascade on update cascade
);
