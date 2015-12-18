package com.ndnhuy.onlinestore.app.service.product;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ndnhuy.onlinestore.app.dto.common.RestSuccess;
import com.ndnhuy.onlinestore.app.dto.customer.CustomerDto;
import com.ndnhuy.onlinestore.app.dto.product.ProductDto;
import com.ndnhuy.onlinestore.domain.domainservice.product.ProductService;


@RestController
@RequestMapping("/products")
public class ProductController {

	private static final Logger logger = Logger.getLogger(ProductController.class);

	@Autowired
	private ProductService productService;
	
	@RequestMapping(method=RequestMethod.GET)
	public RestSuccess getProducts() {
		logger.info("Get all products");
		
		Collection<ProductDto> dtoProducts = productService.findAll();
		
		logger.info("Products: ");
		for (ProductDto dto : dtoProducts) {
			logger.info("Id: " + dto.getId() + ", name: " + dto.getName());
		}
		
		return new RestSuccess(HttpStatus.OK.value(), dtoProducts);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public RestSuccess getProduct(@PathVariable("id") Integer id) {
		logger.info("Get product: id = " + id);
		
		ProductDto dtoProduct = productService.findOne(id);
		
		logger.info("Customer [id = " + dtoProduct.getId() + ", name = " + dtoProduct.getName());
		
		return new RestSuccess(HttpStatus.OK.value(), dtoProduct, null);
	}
}
