package com.ndnhuy.onlinestore.domain.domainservice.purchase;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ndnhuy.onlinestore.app.dto.product.ProductDtoPurchase;
import com.ndnhuy.onlinestore.app.dto.purchase.OrderDto;
import com.ndnhuy.onlinestore.app.dto.purchase.PurchaseDto;
import com.ndnhuy.onlinestore.commonutils.ConstPurchaseStatus;
import com.ndnhuy.onlinestore.domain.domainservice.generic.GenericServiceImpl;
import com.ndnhuy.onlinestore.domain.domainservice.product.ProductService;
import com.ndnhuy.onlinestore.domain.entity.purchase.Order;
import com.ndnhuy.onlinestore.domain.exception.EntityNotFoundException;

@Service
public class OrderServiceImpl extends GenericServiceImpl<Order, OrderDto, Integer> implements OrderService {

	@Autowired
	private PurchaseService purchaseService;

	@Autowired
	private ProductService productService;
	
	@Override
	public List<OrderDto> findOrdersOfCustomer(Integer customerId) {
		List<PurchaseDto> purchaseDtos = purchaseService.findPurchaseByCustomerIdAndStatusId(customerId, ConstPurchaseStatus.CLOSED.value());
		
		List<OrderDto> orders = new ArrayList<OrderDto>();
		for (PurchaseDto p : purchaseDtos) {
			OrderDto dto = null;
			try {
				dto = findOne(p.getId());
				dto.setProducts(p.getProducts());
				for (ProductDtoPurchase product : dto.getProducts()) {
					productService.addExtraInfoInto(product, p.getId());
				}
				
				orders.add(dto);
			} catch(EntityNotFoundException ex) {
				
			}
		}
		
		
		
		
		return orders;
	}

}
