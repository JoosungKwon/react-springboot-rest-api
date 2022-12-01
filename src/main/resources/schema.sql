CREATE TABLE bootcamp
(
    camp_id  varchar(255) NOT NULL PRIMARY KEY,
    name     varchar(30)  NOT NULL,
    location varchar(50)  NOT NULL,
    description (1000) NOT NULL,
);

CREATE TABLE user
(
    user_id   varchar(255) NOT NULL PRIMARY KEY,
    nick_name varchar(20)  NOT NULL UNIQUE,
    password  varchar(20)  NOT NULL,
    email     varchar(30)  NOT NULL,
    phone     varchar(13)  NOT NULL UNIQUE,
    address   varchar(50)  NOT NULL,
    bootcamp  varchar(30) NULL
);

CREATE TABLE camp_review
(
    review_id      varchar(255)  NOT NULL PRIMARY KEY,
    camp_name      varchar(255)  NOT NULL,
    user_nick_name varchar(255)  NOT NULL,
    title          varchar(30)   NOT NULL,
    comment        varchar(300)  NOT NULL,
    rating         DECIMAL(5, 2) NOT NULL,
    created_at     TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at     TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_camp FOREIGN KEY (camp_name) REFERENCES bootcamp (name) on delete cascade on update cascade,
    CONSTRAINT fk_user FOREIGN KEY (user_nick_name) REFERENCES user (nick_name) on delete cascade on update cascade
);