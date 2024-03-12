INSERT INTO bowling_parks (name, location) VALUES (
    'Paris Stalingrad park',
    '5-1 Pl. de la Bataille de Stalingrad, 75010 Paris'
), (
    'Paris République park',
    '5 Avenue de la République, 75011 Paris'
);

DO $$
BEGIN
    FOR i IN 1..20 LOOP
            INSERT INTO alleys (number, park_id) VALUES (i, 1);
            INSERT INTO alleys (number, park_id) VALUES (i, 2);
        END LOOP;
END $$;

INSERT INTO users (first_name, last_name, name, email, password, phone_number, role) VALUES
    ('John', 'Doe', 'johnDoe', 'john.doe@example.com', '123456789', '06 01 02 03 04', 'CUSTOMER'),
    ('Jane', 'Dole', 'janeDole', 'jane.Dole@example.com', '123456789', '06 01 02 03 04', 'CUSTOMER'),
    ('Bob', 'Smiths', 'bobSmiths', 'bob.smiths@mail.example', '123456789', '06 01 02 03 04', 'CUSTOMER'),
    ('Alice', 'Brown', 'aliceBrown', 'alice.brown@test.com', '123456789', '06 01 02 03 04', 'CUSTOMER');

INSERT INTO users (name, email, password, role, assigned_bowling_park_id) VALUES (
    'catalogManager1', 'catalog.manager1.paris-stalingrad@bowlingpark.fr', '123456789', 'AGENT', 1
), (
    'orderManager1', 'order.manager1.paris-stalingrad@bowlingpark.fr', '123456789', 'AGENT', 1
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

INSERT INTO orders (park_id, alley_number) VALUES (1, 3);

INSERT INTO orders (park_id, alley_number) VALUES (2, 4);

INSERT INTO order_items (user_id, order_id, product_id, quantity)VALUES
    (1, 1, 1, 3),
    (2, 1, 2, 7);

INSERT INTO order_items (user_id, order_id, product_id, quantity)VALUES
    (3, 2, 3, 1),
    (4, 2, 4, 8);

INSERT INTO payments (user_id, order_id, amount) VALUES
    (1, 2, 8750),
    (2, 2, 2750);