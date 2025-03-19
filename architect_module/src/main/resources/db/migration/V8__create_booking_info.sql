CREATE TABLE IF NOT EXISTS booking_info(
    id int8 PRIMARY KEY NOT NULL ,
    booking_time TIMESTAMP  ,
    start_booking TIMESTAMP ,
    end_booking TIMESTAMP,
    total_sum_booking BIGINT,
  fk_apartment int8 REFERENCES apartment_info(id),
   fk_user int8 REFERENCES user_info(id),
   fk_product int8 REFERENCES product_info(id)

);

CREATE SEQUENCE booking_info_sequence START 1 INCREMENT 1;