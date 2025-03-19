CREATE TABLE IF NOT EXISTS log_table_info
(
    id BIGINT PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    local_date_registration TIMESTAMP DEFAULT now(),
    description VARCHAR
);

CREATE SEQUENCE log_table_info_sequence;


