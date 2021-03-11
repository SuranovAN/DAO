select  product_name
from orders
    left join customers on orders.customer_id = customers.id
where customers.id in (select id from customers where lower(name) = lower(:name));