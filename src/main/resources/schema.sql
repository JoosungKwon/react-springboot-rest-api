CREATE TABLE bootcamp
(
    camp_id  varchar(255) NOT NULL PRIMARY KEY,
    name     varchar(30)  NOT NULL,
    location varchar(50)  NOT NULL,
    description    (1000)  NOT NULL,
);

CREATE TABLE user
(
    user_id   varchar(255) NOT NULL PRIMARY KEY,
    nick_name varchar(20)  NOT NULL,
    password  varchar(20)  NOT NULL,
    email     varchar(30)  NOT NULL,
    phone     varchar(13)  NOT NULL,
    address   varchar(50) NOT NULL,
    bootcamp  varchar(30) NULL
);

CREATE TABLE camp_review
(
    review_id  varchar(255) NOT NULL PRIMARY KEY,
    camp_id  varchar(255) NOT NULL,
    user_id     varchar(255)  NOT NULL
    rating DECIMAL(5,2)  NOT NULL,
    comment varchar(300) NOT NULL,
    FOREIGN KEY (camp_id) REFERENCES bootcamp(camp_id) on delete cascade on update cascade,
    FOREIGN KEY (user_id) REFERENCES user(user_id) on delete cascade on update cascade
);