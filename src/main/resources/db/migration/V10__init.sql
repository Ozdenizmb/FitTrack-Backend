CREATE SCHEMA IF NOT EXISTS util_sch;
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS util_sch.favorite_data
(
    id                  uuid DEFAULT uuid_generate_v4(),
    user_id                   uuid NOT NULL,
    training_id               uuid NOT NULL,
    created_date              DATE NOT NULL,
    updated_date              DATE NOT NULL,
    version                   INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES util_sch.user_data(id),
    FOREIGN KEY (training_id) REFERENCES util_sch.training_data(id)
);