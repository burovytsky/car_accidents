CREATE TABLE types
(
    id   serial PRIMARY KEY,
    name varchar(2000)
);

CREATE TABLE rules
(
    id   serial PRIMARY KEY,
    name varchar(2000)
);

CREATE TABLE accidents
(
    id      serial PRIMARY KEY,
    name    varchar(2000),
    description    text,
    address varchar(2000),
    type_id int,
    foreign key (type_id) references types(id)
);

DROP TABLE IF EXISTS accident_rules;

CREATE TABLE accident_rules
(
    accident_id int,
    rule_id     int,
    UNIQUE (accident_id, rule_id),
    FOREIGN KEY (accident_id) REFERENCES accidents (id),
    FOREIGN KEY (rule_id) REFERENCES rules (id)
);

CREATE TABLE users
(
    username VARCHAR(50)  NOT NULL,
    password VARCHAR(100) NOT NULL,
    enabled  boolean default true,
    PRIMARY KEY (username)
);

CREATE TABLE authorities
(
    username  VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    FOREIGN KEY (username) REFERENCES users (username)
);