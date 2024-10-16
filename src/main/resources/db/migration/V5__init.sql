CREATE SCHEMA IF NOT EXISTS util_sch;
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS util_sch.asset_data
(
    id                  uuid DEFAULT uuid_generate_v4(),
    name                     VARCHAR NOT NULL UNIQUE,
    type                     VARCHAR NOT NULL,
    image_path               VARCHAR NOT NULL UNIQUE,
    PRIMARY KEY (id)
);