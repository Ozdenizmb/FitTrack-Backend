CREATE SCHEMA IF NOT EXISTS util_sch;
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS util_sch.training_data
(
    id                  uuid DEFAULT uuid_generate_v4(),
    user_id             uuid NOT NULL,
    title                    VARCHAR NOT NULL UNIQUE,
    description              VARCHAR NOT NULL,
    duration                 TIME NOT NULL,
    category                 INT NOT NULL,
    image_name               VARCHAR NOT NULL UNIQUE,
    created_date             DATE NOT NULL,
    updated_date             DATE NOT NULL,
    version                  INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES util_sch.user_data(id),
    FOREIGN KEY (category) REFERENCES util_sch.category_data(id)
);
