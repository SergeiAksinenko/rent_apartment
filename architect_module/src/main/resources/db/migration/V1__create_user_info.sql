CREATE TABLE IF NOT EXISTS user_info
(
    id                int8 PRIMARY KEY,
    nick_name         VARCHAR(255) UNIQUE NOT NULL,
    login             VARCHAR(255) UNIQUE NOT NULL,
    password          VARCHAR(255)        NOT NULL,
    email             VARCHAR(255) UNIQUE NOT NULL,
    registration_data TIMESTAMP           NOT NULL,
    token             VARCHAR(255)
);
CREATE SEQUENCE user_info_sequence start 2 increment 1;

INSERT INTO user_info(id,nick_name,login,password,email,registration_data,token)
VALUES (1,'Sergo','Sergo@newteckws.com','12345678','Sergo@newteckws.com','2024-09-14 09:49:29.754970','ba68ec33-af93-4e44-b21a-73f80983a902|2034-08-26T08:54:31.245276'),
       (2,'Bukla','Garry@potter.com','Gang212@','Garry@potter.com','2024-10-12 09:49:29.754970','ba68ec33-af93-4e44-b21a-73f80983a902|2034-08-26T08:54:31.245276')
