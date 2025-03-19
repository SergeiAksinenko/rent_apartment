CREATE TABLE IF NOT EXISTS apartment_info
(
    id                int8 PRIMARY KEY,
    number_of_rooms   int4,
    area              int4,
    price_per_night   int4,
    start_date        varchar,
    end_date          varchar,
    total_guest       int4,
    availability      varchar,
    description       varchar,
    registration_date timestamp,
    global_rating     double precision
);

CREATE SEQUENCE apartment_info_sequence start 3 increment 1;

INSERT INTO apartment_info (id, number_of_rooms, area, price_per_night, start_date, end_date, total_guest, availability,
                            description, registration_date, global_rating)
VALUES (1, 4, 40, 3500, null, null, 3, 'true', 'описание квартиры', null, null),
       (2, 5, 69, 5500, null, null, 2, 'false', 'описание квартиры_2', null, null);

-- Функция логирования
CREATE OR REPLACE FUNCTION log_new_apartment()
    returns trigger as
$$
Begin
    insert into log_table_info(description) values ('Добавлены новые апартаменты ' || New.description);
    return new;
end;
$$ language plpgsql;


-- Тригер для таблицы apartment_info

CREATE TRIGGER apartment_info_save_trigger
    after insert on apartment_info
    For each row execute function log_new_apartment();


