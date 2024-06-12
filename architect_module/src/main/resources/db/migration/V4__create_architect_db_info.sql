CREATE TABLE IF NOT EXISTS architect_db_info(
                                           id int8,
                                           table_name varchar,
                                           modification_type varchar,
                                           registration_date timestamp
);

CREATE SEQUENCE architect_db_info_sequence;