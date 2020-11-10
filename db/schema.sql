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
    type_id int
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
