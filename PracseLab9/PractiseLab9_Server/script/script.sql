SELECT p.*
FROM products p
WHERE p.list_price = (SELECT MAX(list_price) FROM products);

SELECT *
from products
WHERE products.product_id = 2;

SELECT oi.product_id, COUNT(oi.quantity)
FROM order_items oi
Group By oi.product_id
ORDER BY COUNT (oi.quantity) DESC