CREATE TABLE BowlingPark (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    location VARCHAR(255) NOT NULL,
    qrCode VARCHAR(50) UNIQUE NOT NULL,
	alleys JSONB
);

INSERT INTO BowlingPark (name, location, qrCode, alleys) VALUES(
							   'Paris Stalingrad park', 
							   '5-1 Pl. de la Bataille de Stalingrad, 75010 Paris', 
							   'https://bowlingpark.fr/park/paris-stalingrad',
							   '[
							   	   {"id": 1, "qrCode": "https://bowlingpark.fr/park/paris-stalingrad/alley/1"},
								   {"id": 2, "qrCode": "https://bowlingpark.fr/park/paris-stalingrad/alley/2"},
								   {"id": 3, "qrCode": "https://bowlingpark.fr/park/paris-stalingrad/alley/3"},
								   {"id": 4, "qrCode": "https://bowlingpark.fr/park/paris-stalingrad/alley/4"},
								   {"id": 5, "qrCode": "https://bowlingpark.fr/park/paris-stalingrad/alley/5"},
								   {"id": 6, "qrCode": "https://bowlingpark.fr/park/paris-stalingrad/alley/6"},
								   {"id": 7, "qrCode": "https://bowlingpark.fr/park/paris-stalingrad/alley/7"},
								   {"id": 8, "qrCode": "https://bowlingpark.fr/park/paris-stalingrad/alley/8"},
								   {"id": 9, "qrCode": "https://bowlingpark.fr/park/paris-stalingrad/alley/9"},
								   {"id": 10, "qrCode": "https://bowlingpark.fr/park/paris-stalingrad/alley/10"},
								   {"id": 11, "qrCode": "https://bowlingpark.fr/park/paris-stalingrad/alley/11"},
								   {"id": 12, "qrCode": "https://bowlingpark.fr/park/paris-stalingrad/alley/12"},
								   {"id": 13, "qrCode": "https://bowlingpark.fr/park/paris-stalingrad/alley/13"},
								   {"id": 14, "qrCode": "https://bowlingpark.fr/park/paris-stalingrad/alley/14"},
								   {"id": 15, "qrCode": "https://bowlingpark.fr/park/paris-stalingrad/alley/15"},
								   {"id": 16, "qrCode": "https://bowlingpark.fr/park/paris-stalingrad/alley/16"},
								   {"id": 17, "qrCode": "https://bowlingpark.fr/park/paris-stalingrad/alley/17"},
								   {"id": 18, "qrCode": "https://bowlingpark.fr/park/paris-stalingrad/alley/18"},
								   {"id": 19, "qrCode": "https://bowlingpark.fr/park/paris-stalingrad/alley/19"},
								   {"id": 20, "qrCode": "https://bowlingpark.fr/park/paris-stalingrad/alley/20"}
							   ]');
							   
SELECT * FROM BowlingPark;