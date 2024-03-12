CREATE TABLE bowling_parks (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    location VARCHAR(255) NOT NULL
);

CREATE TABLE alleys (
    number INT CONSTRAINT ck_number_in_range CHECK (number >= 1 AND number <= 20),
    park_id INTEGER REFERENCES bowling_parks (id),
    PRIMARY KEY (number, park_id)
);

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
	
CREATE TABLE products (
    id SERIAL PRIMARY KEY,
    park_id INTEGER REFERENCES bowling_parks (id),
    name VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    price INTEGER NOT NULL
);

CREATE TYPE status AS ENUM('PENDING', 'IN_PROGRESS', 'FAILED', 'COMPLETED', 'CANCELLED');

CREATE TABLE orders (
    id SERIAL PRIMARY KEY,
    park_id INTEGER NOT NULL ,
    alley_number INTEGER NOT NULL,
    FOREIGN KEY (alley_number, park_id) REFERENCES alleys (number, park_id),
    status STATUS DEFAULT 'PENDING'
);

CREATE TABLE order_items (
	user_id INTEGER NOT NULL REFERENCES users(id),
	order_id INTEGER NOT NULL REFERENCES orders(id),
	product_id INTEGER NOT NULL REFERENCES products(id),
	quantity INTEGER NOT NULL,
	PRIMARY KEY (user_id, order_id, product_id)
);

CREATE TABLE payments (
    id SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES users(id),
    order_id INTEGER REFERENCES orders (id),
    amount INTEGER NOT NULL,
    status STATUS DEFAULT 'PENDING'
);