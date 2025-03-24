CREATE TABLE IF NOT EXISTS workspaces (
   space_id SERIAL PRIMARY KEY,
   space_type VARCHAR (8) NOT NULL,
   price_per_hour DECIMAL (5, 2) NOT NULL,
   availability_status BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS reservations(
   booking_id SERIAL PRIMARY KEY,
   customer_name VARCHAR(15) NOT NULL,
   date VARCHAR(10) NOT NULL,
   start_time VARCHAR(5) NOT NULL,
   end_time VARCHAR(5) NOT NULL,
   space_id INT NOT NULL REFERENCES workspaces(space_id)
);


SELECT * FROM workspaces;
SELECT * FROM reservations;