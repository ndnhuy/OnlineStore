package com.ndnhuy.onlinestore.app.service.cart;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ndnhuy.onlinestore.app.dto.common.RestSuccess;
import com.ndnhuy.onlinestore.app.dto.purchase.PurchaseDto;
import com.ndnhuy.onlinestore.commonutils.Constant;
import com.ndnhuy.onlinestore.commonutils.CurrentUser;
import com.ndnhuy.onlinestore.domain.domainservice.cart.CartService;


@RestController
@RequestMapping("/cart")
@Secured(Constant.ROLE_USER)
public class CartController {
	private static final Logger logger = Logger.getLogger(CartController.class);

	@Autowired
	private CartService cartService;
	
	@Autowired
	private CurrentUser currentUser;
	
	@RequestMapping(method=RequestMethod.GET)
	public RestSuccess getCart() {
		PurchaseDto dtoPurchase = cartService.findCartOfCustomer(currentUser.getCustomerId());
		
		logger.info("Find Cart of customer [customerId=" + currentUser.getCustomerId() + "], [(cart) purchase: id = " + dtoPurchase.getId() + ", customerId = " + dtoPurchase.getCustomerId() + "]");
		
		return new RestSuccess(HttpStatus.OK.value(), dtoPurchase, null);
	}
	
//	@RequestMapping(method=RequestMethod.POST)
//	public RestSuccess addProductIntoCart(@RequestParam("insert_product_id") Integer productId) {
//		logger.info("Add to cart of customer [id=" + currentUser.getCustomerId() + "] " + "the product [id=" + productId + "]");
//		
//		
//	}
	
}
