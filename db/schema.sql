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

CREATE TABLE authorities (
                             id serial primary key,
                             authority VARCHAR(50) NOT NULL unique
);

CREATE TABLE users (
                       id serial primary key,
                       username VARCHAR(50) NOT NULL unique,
                       password VARCHAR(100) NOT NULL,
                       enabled boolean default true,
                       authority_id int not null references authorities(id)
);

insert into authorities (authority) values ('ROLE_USER');
insert into authorities (authority) values ('ROLE_ADMIN');

insert into users (username, password, authority_id)
values ('root', '$2a$10$Ngun9jd7t/8LwiTrA6hhWOF6qGZcdheawE957dtcgkGhCubzwX3Ii',
        (select id from authorities where authority = 'ROLE_ADMIN'));