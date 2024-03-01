CREATE TABLE BowlingParks (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    location VARCHAR(255) NOT NULL
);

CREATE TABLE Alleys (
	id SERIAL PRIMARY KEY,
	parkId INTEGER REFERENCES BowlingParks(id)
);

INSERT INTO BowlingParks (name, location) VALUES (
	'Paris Stalingrad park', 
	'5-1 Pl. de la Bataille de Stalingrad, 75010 Paris'
);
	
DO $$ 
BEGIN 
    FOR i IN 1..20 LOOP
        INSERT INTO Alleys (parkId) VALUES (1);
    END LOOP;
END $$;

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
	parkId INTEGER REFERENCES BowlingParks(id),
	alleyId INTEGER REFERENCES Alleys(id) CONSTRAINT ck_alleyId_in_range CHECK (alleyId >= 1 AND alleyId <= 20),
    status VARCHAR(20) DEFAULT 'PENDING'
);

INSERT INTO Orders (userId, parkId, alleyId) VALUES (
	1, 1, 3
);

INSERT INTO Orders (userId, parkId, alleyId) VALUES (
	1, 1, 3
);

CREATE TABLE OrderItems (
	id SERIAL PRIMARY KEY,
	orderId INTEGER REFERENCES Orders(id),
	productId INTEGER REFERENCES Products(id),
	quantity INTEGER NOT NULL
);

INSERT INTO OrderItems (orderId, productId, quantity) VALUES (
	1, 1, 3
);

INSERT INTO OrderItems (orderId, productId, quantity) VALUES (
	2, 2, 7
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
SELECT * FROM Alleys;
SELECT * FROM Users;
SELECT * FROM Products;
SELECT * FROM Orders;
SELECT * FROM OrderItems;
SELECT * FROM Payments;
SELECT * FROM Notifications;

DROP TABLE Notifications;
DROP TABLE Payments;
DROP TABLE OrderItems;
DROP TABLE Orders;
DROP TABLE Products;
DROP TABLE Users;
DROP TABLE Alleys;
DROP TABLE BowlingParks;