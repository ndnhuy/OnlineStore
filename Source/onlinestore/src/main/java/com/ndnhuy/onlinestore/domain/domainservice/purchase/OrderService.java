package com.ndnhuy.onlinestore.domain.domainservice.purchase;

import java.util.List;

import com.ndnhuy.onlinestore.app.dto.purchase.OrderDto;
import com.ndnhuy.onlinestore.domain.domainservice.generic.GenericService;
import com.ndnhuy.onlinestore.domain.entity.purchase.Order;

public interface OrderService extends GenericService<Order, OrderDto, Integer>{
	List<OrderDto> findOrdersOfCustomer(Integer customerId);
	void updateOrderStatus(Integer orderId, String orderStatus);
}
