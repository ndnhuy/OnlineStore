package com.ndnhuy.onlinestore.app.service.cart;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
		
		logger.info("Find Cart of customer [customerId=" + currentUser.getCustomerId() + 
				", username=" + currentUser.getUsername() + 
				"], [(cart) purchase: id = " + dtoPurchase.getId() + ", customerId = " + 
				dtoPurchase.getCustomerId() + "]");
		
		return new RestSuccess(HttpStatus.OK.value(), dtoPurchase, null);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public RestSuccess addProductIntoCart(@RequestParam(value="insert_product_id", required=true) Integer productId) {
		//TODO maybe I can try to use PATCH to add product into cart.
		
		logger.info("Add to cart of customer [username=" + currentUser.getUsername() + "] " + "the product [id=" + productId + "]");
		
		if (productId != null)
			cartService.addProductIntoCart(currentUser.getCustomerId(), productId);
		
		return new RestSuccess(HttpStatus.CREATED.value(), null, 
								"The product with id " + productId + " has been added into cart of customer id " + currentUser.getCustomerId());
	}
	
	@RequestMapping(value="/products/{productId}", method=RequestMethod.DELETE)
	public RestSuccess removeProductFromCart(@PathVariable("productId") Integer productId) {
		logger.info("Remove product id " + productId + " from cart of customer [username is " + currentUser.getUsername() + "]");
		
		cartService.removeProductFromCart(productId, currentUser.getCustomerId());
		
		return new RestSuccess(HttpStatus.OK.value(), null, "The product id " + productId 
				+ " has been removed from cart of customer " 
				+ currentUser.getUsername());
	}
	
	@RequestMapping(value="/products/{productId}", method=RequestMethod.PATCH)
	public RestSuccess updatePartialProductInCart(@PathVariable("productId") Integer productId, 
													@RequestParam("quantity") Integer quantity) {
		logger.info("Partially update product has id " + productId 
				+ " in cart of customer [username is " 
				+ currentUser.getUsername() + "]");
		
		cartService.updateQuantityOfProductInCart(quantity, currentUser.getCustomerId(), productId);
		
		return new RestSuccess(HttpStatus.OK.value(), null,
				ServletUriComponentsBuilder.fromCurrentContextPath().path("/cart").toUriString());
	}
	
}
