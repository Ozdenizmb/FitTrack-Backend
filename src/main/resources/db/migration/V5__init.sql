CREATE SCHEMA IF NOT EXISTS util_sch;

CREATE TABLE IF NOT EXISTS util_sch.difficulty_data
(
    id                       INT NOT NULL,
    name                     VARCHAR NOT NULL,
    PRIMARY KEY (id)
);