CREATE SCHEMA IF NOT EXISTS util_sch;

CREATE TABLE IF NOT EXISTS util_sch.category_data
(
    id                       INT NOT NULL,
    name                     VARCHAR NOT NULL,
    PRIMARY KEY (id)
);