ALTER TABLE statistic_info ADD COLUMN apartment_id BIGINT;
ALTER TABLE statistic_info ADD CONSTRAINT fk_apartment FOREIGN KEY(apartment_id) REFERENCES apartment_info(id);