SELECT p.*
FROM products p
WHERE p.list_price = (SELECT MAX(list_price) FROM products)

SELECT * from products
WHERE products.product_id = 2

