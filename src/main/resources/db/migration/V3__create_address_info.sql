CREATE TABLE IF NOT EXISTS address_info(
    id int8 PRIMARY KEY ,
    street varchar,
    city varchar,
    postal_code int4,
    apartment_id int8 references apartment_info(id)
);

CREATE SEQUENCE address_info_sequence start 3 increment 1;

INSERT INTO address_info (id,street,city,postal_code,apartment_id)
VALUES (1,'Красная','Москва',66830,1),
       (2,'Центральная','Новосибирск',61200,2);