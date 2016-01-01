select A.customerId, SUM(purchase_order.total) from
(select customer.id AS customerId, purchase.id from customer
left join purchase
on customer.id = purchase.customer_id
where customer.username <> 'admin'
order by customer.username) as A
left join purchase_order
on A.id = purchase_order.purchase_id
group by A.customerId;


select customer.id AS customerId, purchase.id from customer
left join purchase
on customer.id = purchase.customer_id
where customer.username <> 'admin'
order by customer.username;

select customer.username, purchase_order.total, product.name, product.price from customer, purchase, purchase_order, purchase_product, product
where purchase.customer_id = customer.id 
AND purchase.status_id <> 1 
AND purchase_order.purchase_id = purchase.id 
AND purchase.id = purchase_product.purchase_id
AND product.id = purchase_product.product_id
order by customer.username;



		

		
