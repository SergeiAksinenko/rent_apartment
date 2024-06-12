CREATE TABLE IF NOT EXISTS  integration_info(
    id varchar,
    path varchar,
    key varchar
);

INSERT INTO integration_info(id, path, key)
VALUES ('GEO','https://api.opencagedata.com/geocode/v1/json?q=%s+%s&key=%s','ZTNkNzg1OTU5YmJlNDFhNzgwZDE3YzgzYjhhYThhNmU='),
       ('WEATHER','https://api.weather.yandex.ru/v2/forecast?lat=%s&lon=%s&lang=ru_RU','ODUyNzRlYTUtYWViNy00Yjk4LWE0ZWEtNzY5ZWE1YjgwMmE0');
