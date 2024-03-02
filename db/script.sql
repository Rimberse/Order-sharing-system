CREATE TABLE BowlingParks (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    location VARCHAR(255) NOT NULL
);

INSERT INTO BowlingParks (name, location) VALUES (
	'Paris Stalingrad park', 
	'5-1 Pl. de la Bataille de Stalingrad, 75010 Paris'
);

INSERT INTO BowlingParks (name, location) VALUES (
	'Paris République park', 
	'5 Avenue de la République, 75011 Paris'
);

CREATE TABLE Alleys (
    number INTEGER CONSTRAINT ck_number_in_range CHECK (number >= 1 AND number <= 20),
    parkId INTEGER REFERENCES BowlingParks(id),
    PRIMARY KEY (number, parkId)
);
	
DO $$ 
BEGIN 
    FOR i IN 1..20 LOOP
        INSERT INTO Alleys (number, parkId) VALUES (i, 1);
		INSERT INTO Alleys (number, parkId) VALUES (i, 2);
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
    price INTEGER NOT NULL
);

INSERT INTO Products (name, description, price) VALUES (
	'Bowling pin', 'Bowling pin. Used to play bowling', 1250
);

INSERT INTO Products (name, description, price) VALUES (
	'Bowling ball', 'Bowling ball. Used to play bowling', 2750
);

INSERT INTO Products (name, price) VALUES (
	'Beer', 399
);

INSERT INTO Products (name, price) VALUES (
	'T-shirt', 200
);

CREATE TABLE Orders (
    id SERIAL PRIMARY KEY,
    userId INTEGER REFERENCES Users(id),
    parkId INTEGER REFERENCES BowlingParks(id),
    alleyNumber INTEGER NOT NULL,
    FOREIGN KEY (alleyNumber, parkId) REFERENCES Alleys (number, parkId),
    status VARCHAR(20) DEFAULT 'PENDING'
);

INSERT INTO Orders (userId, parkId, alleyNumber) VALUES (
	1, 1, 3
);

INSERT INTO Orders (userId, parkId, alleyNumber) VALUES (
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
    amount INTEGER NOT NULL,
    status VARCHAR(20) DEFAULT 'PENDING'
);

INSERT INTO Payments (userId, orderId, amount) VALUES (
	1, 1, 8750
);

INSERT INTO Payments (userId, orderId, amount) VALUES (
	1, 2, 2750
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
