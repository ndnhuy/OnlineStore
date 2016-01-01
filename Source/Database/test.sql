


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



select A.customer_id, SUM(purchase_order.total) from
(select customer.id AS customer_id, customer.username AS username, purchase.id AS purchaseId from customer
left join purchase
on customer.id = purchase.customer_id
where customer.username <> 'admin'
order by customer.username) as A
left join purchase_order
on A.purchaseId = purchase_order.purchase_id
group by A.customer_id;

select customer.username, sum_table.sum, purchase.id AS purchase_id,
	purchase_order AS order_id, product.name, product.price
from customer, (select A.customer_id, SUM(purchase_order.total) from
		(select customer.id AS customer_id, customer.username AS username, purchase.id AS purchaseId from customer
		left join purchase
		on customer.id = purchase.customer_id
		where customer.username <> 'admin'
		order by customer.username) as A
		left join purchase_order
		on A.purchaseId = purchase_order.purchase_id
		group by A.customer_id) AS sum_table,
		purchase,
		purchase_order,
		purchase_product,
		product
where customer.id = sum_table.customer_id
	AND purchase.customer_id = customer.id
	AND purchase_order.purchase_id = purchase.id
	AND purchase_product.purchase_id = purchase_order.purchase_id
	AND product.id = purchase_product.product_id;




select customer.username, sum_table.sum AS total,
	purchase_order.purchase_id AS order_id, purchase_order.total AS subtotal, purchase_order.order_date
from customer, (select A.customer_id, SUM(purchase_order.total) from
		(select customer.id AS customer_id, customer.username AS username, purchase.id AS purchaseId from customer
		left join purchase
		on customer.id = purchase.customer_id
		where customer.username <> 'admin'
		order by customer.username) as A
		left join purchase_order
		on A.purchaseId = purchase_order.purchase_id
		group by A.customer_id) AS sum_table,
		purchase,
		purchase_order
where customer.id = sum_table.customer_id
	AND purchase.customer_id = customer.id
	AND purchase_order.purchase_id = purchase.id
	;

		

		
