package com.ndnhuy.onlinestore.domain.domainservice.cart;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ndnhuy.onlinestore.app.dto.product.ProductDtoPurchase;
import com.ndnhuy.onlinestore.app.dto.purchase.OrderDto;
import com.ndnhuy.onlinestore.app.dto.purchase.PurchaseDto;
import com.ndnhuy.onlinestore.commonutils.ConstPurchaseStatus;
import com.ndnhuy.onlinestore.commonutils.Constant;
import com.ndnhuy.onlinestore.domain.domainservice.product.ProductService;
import com.ndnhuy.onlinestore.domain.domainservice.purchase.OrderService;
import com.ndnhuy.onlinestore.domain.domainservice.purchase.PurchaseService;
import com.ndnhuy.onlinestore.domain.entity.purchase.Purchase;
import com.ndnhuy.onlinestore.domain.entity.purchase.PurchaseStatus;
import com.ndnhuy.onlinestore.domain.exception.AppException;
import com.ndnhuy.onlinestore.domain.exception.EntityNotFoundException;
import com.ndnhuy.onlinestore.repository.PurchaseRepository;
import com.ndnhuy.onlinestore.repository.PurchaseStatusRepository;

@Service
@Transactional
public class CartServiceImpl implements CartService {

	private final static Logger logger = Logger.getLogger(CartServiceImpl.class);
	
	@Autowired
	private PurchaseService purchaseService;
	
	
	//TODO what the hell are these 2 repositories here?
	@Autowired
	private PurchaseRepository purchaseRepository;
	
	@Autowired
	private PurchaseStatusRepository purchaseStatusReposirory;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private DozerBeanMapper mapper;
	
	@Override
	public PurchaseDto findCartOfCustomer(Integer customerId) {
		logger.debug("Find cart of customer [id=" + customerId + "]");
		
		PurchaseDto dtoPurchase = null;
		try {
			dtoPurchase = getOrderingPurchase(customerId);
		} catch (EntityNotFoundException ex) {
			PurchaseDto newPurchase = new PurchaseDto();
			newPurchase.setCustomerId(customerId);
			newPurchase.setStatusId(ConstPurchaseStatus.ORDERING.value());
			
			dtoPurchase = purchaseService.add(newPurchase);
			
			dtoPurchase.setProducts(new ArrayList<ProductDtoPurchase>());
		}
		
		
		Double total = 0.0;
		
		logger.debug(String.format("Add extra info into each %s inside %s", ProductDtoPurchase.class.getSimpleName(), PurchaseDto.class.getSimpleName()));
		for (ProductDtoPurchase p : dtoPurchase.getProducts()) {
			
			productService.addExtraInfoInto(p, dtoPurchase.getId());
			
			total += p.getSubtotal();
		}
		
		dtoPurchase.setTotal(total);
		logger.debug(String.format("Return %s [id=%d, total=%f]", PurchaseDto.class.getSimpleName(), dtoPurchase.getId(), dtoPurchase.getTotal()));
		
		return dtoPurchase;
	}

	@Override
	public void addProductIntoCart(Integer customerId, Integer productId) {
		logger.debug("Add product [id=" + productId + "] into cart of customer [id=" + customerId + "]");
		
		PurchaseDto dtoPurchase = getOrderingPurchase(customerId);
		
		purchaseService.addProductIntoPurchase(productId, dtoPurchase.getId());
	}

	@Override
	public boolean removeProductFromCart(Integer productId, Integer customerId) {
		
		logger.debug("Delete product id " + productId + " from cart of customer id " + customerId);
		
		PurchaseDto dtoPurchase = getOrderingPurchase(customerId);
		
		return purchaseService.removeProductFromPurchase(productId, dtoPurchase.getId());
	}

	@Override
	public void updateQuantityOfProductInCart(Integer quantity, Integer customerId,
			Integer productId) {

		PurchaseDto dtoPurchase = getOrderingPurchase(customerId);
		
		purchaseService.updateQuantityOfProductInPurchase(quantity, productId, dtoPurchase.getId());
		
	}
	
	public PurchaseDto getOrderingPurchase(Integer customerId) {
		List<PurchaseDto> dtoPurchases = purchaseService.findPurchaseByCustomerIdAndStatusId(customerId, ConstPurchaseStatus.ORDERING.value());
		return dtoPurchases.get(0);
	}

	@Override
	public OrderDto checkout(Integer customerId, OrderDto orderDto) {
		PurchaseDto purchaseDto = getOrderingPurchase(customerId);
		if (purchaseDto.getProducts() == null || purchaseDto.getProducts().isEmpty()) {
			throw new AppException(HttpStatus.CONFLICT.value(), "Your cart is empty", "There are no products belong to purchase id " + purchaseDto.getId());
		}
		
		Double total = 0.0;
		for (ProductDtoPurchase p : purchaseDto.getProducts()) {
			productService.addExtraInfoInto(p, purchaseDto.getId());
			total += p.getSubtotal();
		}
		
		orderDto.setId(purchaseDto.getId());
		orderDto.setOrderStatus(Constant.ORDER_STATUS_NEW);
		orderDto.setTotal(total);
		
		//TODO change status of purchase after ordering, shouldn't use purchaseRepo like here
		Purchase purchase = purchaseRepository.findOne(purchaseDto.getId());
		purchase.setPurchaseStatus(purchaseStatusReposirory.findOne(2));
		
		OrderDto savedOrderDto = orderService.add(orderDto);
		savedOrderDto.setProducts(purchaseDto.getProducts());
		
		//Create new purchase as cart
		PurchaseDto newPurchase = new PurchaseDto();
		newPurchase.setCustomerId(customerId);
		newPurchase.setStatusId(ConstPurchaseStatus.ORDERING.value());
		
		purchaseService.add(newPurchase);
		
		return savedOrderDto;
	}

}
