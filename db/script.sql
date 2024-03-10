CREATE TABLE bowling_parks (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    location VARCHAR(255) NOT NULL
);

INSERT INTO bowling_parks (name, location) VALUES (
	'Paris Stalingrad park', 
	'5-1 Pl. de la Bataille de Stalingrad, 75010 Paris'
);

INSERT INTO bowling_parks (name, location) VALUES (
	'Paris République park', 
	'5 Avenue de la République, 75011 Paris'
);

CREATE TABLE alleys (
    number INT CONSTRAINT ck_number_in_range CHECK (number >= 1 AND number <= 20),
    park_id INTEGER REFERENCES bowling_parks (id),
    PRIMARY KEY (number, park_id)
);
	
DO $$ 
BEGIN 
    FOR i IN 1..20 LOOP
        INSERT INTO alleys (number, park_id) VALUES (i, 1);
        INSERT INTO alleys (number, park_id) VALUES (i, 2);
    END LOOP;
END $$;

CREATE TYPE role AS ENUM('CUSTOMER', 'AGENT');

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    name VARCHAR(50) UNIQUE,
    email VARCHAR(100) UNIQUE,
    password VARCHAR(255) NOT NULL,
    phone_number VARCHAR(20),
    role ROLE NOT NULL,
    assigned_bowling_park_id INTEGER REFERENCES bowling_parks (id)
);

INSERT INTO users (first_name, last_name, name, email, password, phone_number, role) VALUES (
	'John', 'Doe', 'johnDoe', 'john.doe@example.com', '123456789', '06 01 02 03 04', 'CUSTOMER'
);

INSERT INTO users (name, email, password, role, assigned_bowling_park_id) VALUES (
	'catalogManager1', 'catalog.manager1.paris-stalingrad@bowlingpark.fr', '123456789', 'AGENT', 1
);

INSERT INTO users (name, email, password, role, assigned_bowling_park_id) VALUES (
	'orderManager1', 'order.manager1.paris-stalingrad@bowlingpark.fr', '123456789', 'AGENT', 1
);
	
CREATE TABLE products (
    id SERIAL PRIMARY KEY,
    park_id INTEGER REFERENCES bowling_parks (id),
    name VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    price INTEGER NOT NULL
);

INSERT INTO products (name, park_id, description, price) VALUES (
	'Bowling pin', 1, 'Bowling pin. Used to play bowling', 1250
);

INSERT INTO products (name, park_id, description, price) VALUES (
	'Bowling ball', 1, 'Bowling ball. Used to play bowling', 2750
);

INSERT INTO products (name, park_id, price) VALUES (
	'Beer', 2, 399
);

INSERT INTO products (name, park_id, price) VALUES (
	'T-shirt', 2, 200
);

CREATE TYPE status AS ENUM('PENDING', 'IN_PROGRESS', 'FAILED', 'COMPLETED', 'CANCELLED');

CREATE TABLE orders (
    id SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES users(id),
    park_id INTEGER REFERENCES bowling_parks (id),
    alley_number INTEGER NOT NULL,
    FOREIGN KEY (alley_number, park_id) REFERENCES alleys (number, park_id),
    status STATUS DEFAULT 'PENDING'
);

INSERT INTO orders (user_id, park_id, alley_number) VALUES (
	1, 1, 3
);

INSERT INTO orders (user_id, park_id, alley_number) VALUES (
	1, 1, 3
);

CREATE TABLE order_items (
	id SERIAL PRIMARY KEY,
	order_id INTEGER REFERENCES orders(id),
	product_id INTEGER REFERENCES products(id),
	quantity INTEGER NOT NULL
);

INSERT INTO order_items (order_id, product_id, quantity) VALUES (
	1, 1, 3
);

INSERT INTO order_items (order_id, product_id, quantity) VALUES (
	2, 2, 7
);

CREATE TABLE payments (
    id SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES users(id),
    order_id INTEGER REFERENCES orders(id),
    amount INTEGER NOT NULL,
    status STATUS DEFAULT 'PENDING'
);

INSERT INTO payments (user_id, order_id, amount) VALUES (
	1, 1, 8750
);

INSERT INTO payments (user_id, order_id, amount) VALUES (
	1, 2, 2750
);

CREATE TABLE notifications (
    id SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES users(id),
    message VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO notifications (user_id, message) VALUES (
	1, 'thank you for joining!'
);

INSERT INTO notifications (user_id, message) VALUES (
	2, 'thank you for singing a contract with us!'
);
