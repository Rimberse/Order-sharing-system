CREATE TABLE BowlingParks (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    location VARCHAR(255) NOT NULL,
    qrCode VARCHAR(50) UNIQUE NOT NULL,
	alleys JSONB
);

INSERT INTO BowlingParks (name, location, qrCode, alleys) VALUES (
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

CREATE TABLE Users (
    id SERIAL PRIMARY KEY,
	firstName VARCHAR(50),
	lastName VARCHAR(50),
    name VARCHAR(50) UNIQUE,
    email VARCHAR(100) UNIQUE,
    password VARCHAR(255) NOT NULL,
	phoneNumber VARCHAR(20),
	role VARCHAR(10) NOT NULL,
	assignedBowlingParkId INTEGER REFERENCES BowlingParks(id)
);

INSERT INTO Users (firstName, lastName, name, email, password, phoneNumber, role) VALUES (
	'John', 'Doe', 'johnDoe', 'john.doe@example.com', '123456789', '06 01 02 03 04', 'customer'
);

INSERT INTO Users (name, email, password, role, assignedBowlingParkId) VALUES (
	'catalogManager1', 'catalog.manager1.paris-stalingrad@bowlingpark.fr', '123456789', 'agent', 1
);

INSERT INTO Users (name, email, password, role, assignedBowlingParkId) VALUES (
	'orderManager1', 'order.manager1.paris-stalingrad@bowlingpark.fr', '123456789', 'agent', 1
);
	
CREATE TABLE Products (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
	description VARCHAR(255),
	quantity INTEGER NOT NULL,
    price DECIMAL(10, 2) NOT NULL
);

INSERT INTO Products (name, description, quantity, price) VALUES (
	'Bowling pin', 'Bowling pin. Used to play bowling', 25, 12.50
);

INSERT INTO Products (name, description, quantity, price) VALUES (
	'Bowling ball', 'Bowling ball. Used to play bowling', 7, 27.50
);

INSERT INTO Products (name, quantity, price) VALUES (
	'Beer', 75, 3.99
);

INSERT INTO Products (name, quantity, price) VALUES (
	'T-shirt', 55, 2
);

CREATE TABLE Orders (
    id SERIAL PRIMARY KEY,
    userId INTEGER REFERENCES Users(id),
    productId INTEGER REFERENCES Products(id),
	parkId INTEGER REFERENCES BowlingParks(id),
	alleyNumber INTEGER NOT NULL CONSTRAINT ck_AlleyNumber_in_range CHECK (alleyNumber >= 1 AND alleyNumber <= 20),
    quantity INTEGER NOT NULL,
    status VARCHAR(20) DEFAULT 'PENDING'
);

INSERT INTO Orders (userId, productId, parkId, alleyNumber, quantity) VALUES (
	1, 1, 1, 3, 7
);

INSERT INTO Orders (userId, productId, parkId, alleyNumber, quantity) VALUES (
	1, 2, 1, 3, 3
);

CREATE TABLE Payments (
    id SERIAL PRIMARY KEY,
    userId INTEGER REFERENCES Users(id),
    orderId INTEGER REFERENCES Orders(id),
    amount DECIMAL(10, 2) NOT NULL,
    status VARCHAR(20) DEFAULT 'PENDING'
);

INSERT INTO Payments (userId, orderId, amount) VALUES (
	1, 1, 87.5
);

INSERT INTO Payments (userId, orderId, amount) VALUES (
	1, 2, 27.5
);

CREATE TABLE Notifications (
    id SERIAL PRIMARY KEY,
    userId INTEGER REFERENCES Users(id),
    message VARCHAR(255) NOT NULL,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO Notifications (userId, message) VALUES (
	1, 'thank you for joining!'
);

INSERT INTO Notifications (userId, message) VALUES (
	2, 'thank you for singing a contract with us!'
);

SELECT * FROM BowlingParks;
SELECT * FROM Users;
SELECT * FROM Products;
SELECT * FROM Orders;
SELECT * FROM Payments;
SELECT * FROM Notifications;