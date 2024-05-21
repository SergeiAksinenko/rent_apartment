CREATE TABLE IF NOT EXISTS user_info(
    id int8 PRIMARY KEY,
    nick_name varchar,
    login varchar,
    password varchar,
    registration_data timestamp,
    token varchar
);
CREATE SEQUENCE user_info_sequence start 2 increment 1;

INSERT INTO user_info(id, nick_name,login,password,registration_data,token)
VALUES (1,'test','test','test',null,'ed35ec58-8212-46fd-915e-d035afbdcd10|2034-04-21T16:35:41.708437');