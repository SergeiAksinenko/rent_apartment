UPDATE integration_info
SET path = 'https://api.opencagedata.com/geocode/v1/json?q=%s+%s&key=%s&language=ru'
WHERE id = 'GEO';