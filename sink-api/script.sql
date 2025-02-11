-- Create the 'products' table
CREATE TABLE products (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2) NOT NULL  -- Price should not be NULL
);

-- Create the 'sales' table
CREATE TABLE sales (
    id SERIAL PRIMARY KEY,
    product_id INTEGER REFERENCES products(id) NOT NULL,  -- Foreign key referencing products
    quantity INTEGER NOT NULL CHECK (quantity > 0), -- Quantity must be positive
    sale_date DATE DEFAULT CURRENT_DATE
);

-- Example data insertion (optional)
INSERT INTO products (name, price) VALUES
('Laptop', 1200.00),
('Mouse', 25.00),
('Keyboard', 75.00);

INSERT INTO sales (product_id, quantity, sale_date) VALUES
(1, 1, '2024-07-28'),
(2, 3, '2024-07-28'),
(1, 2, '2024-07-29');

-- Query examples (helpful for testing and understanding the data)

-- Get the total sales for each product
SELECT p.name, SUM(s.quantity) AS total_sales
FROM sales s
JOIN products p ON s.product_id = p.id
GROUP BY p.name;

-- Get the total revenue for a specific date
SELECT SUM(s.quantity * p.price) AS total_revenue
FROM sales s
JOIN products p ON s.product_id = p.id
WHERE s.sale_date = '2024-07-28';  -- Change the date as needed

-- Find all sales of a specific product
SELECT s.*
FROM sales s
JOIN products p ON s.product_id = p.id
WHERE p.name = 'Laptop';

-- Find the most sold product
SELECT p.name, SUM(s.quantity) as total_sold
FROM sales s
JOIN products p ON s.product_id = p.id
GROUP BY p.name
ORDER BY total_sold DESC
LIMIT 1;