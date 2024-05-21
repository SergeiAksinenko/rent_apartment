CREATE TABLE IF NOT EXISTS rent_assess_info(
                                                id int8 PRIMARY KEY ,
                                                comments varchar(2000),
                                                rating int4,
                                                registration_date timestamp,
                                                apartment_id int8 references apartment_info(id)
);

CREATE SEQUENCE rent_assess_info_sequence start 3 increment 1;

INSERT INTO rent_assess_info (id,comments,rating,registration_date,apartment_id)
VALUES (1,'комментарии к апартаментам',5,null,1),
       (2,'комментарии к апартаментам_V2',3,null,2);