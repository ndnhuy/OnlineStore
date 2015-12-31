package com.ndnhuy.onlinestore.app.service.product;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ndnhuy.onlinestore.app.dto.common.RestSuccess;
import com.ndnhuy.onlinestore.app.dto.product.ProductDto;
import com.ndnhuy.onlinestore.domain.domainservice.product.ProductService;


@RestController
@RequestMapping("/products")
public class ProductController {

	private static final Logger logger = Logger.getLogger(ProductController.class);

	@Autowired
	private ProductService productService;
	
	@RequestMapping(method=RequestMethod.GET)
	public RestSuccess getProducts(HttpServletRequest request) {
		logger.info("Get all products");
		
		Collection<ProductDto> dtoProducts = productService.filter(request);
		
		List<List<ProductDto>> showProducts = new ArrayList<List<ProductDto>>();
		
		
		logger.info("Products: ");
		
		int i = 0;
		List<ProductDto> chunk = new ArrayList<ProductDto>();
		for (ProductDto dto : dtoProducts) {
			logger.info("Id: " + dto.getId() + ", name: " + dto.getName());
			chunk.add(dto);
			i++;
			if (i == 3) {
				showProducts.add(chunk);
				chunk = new ArrayList<ProductDto>();
				i = 0;
			}
		}
		
		if (dtoProducts.size() % 3 != 0) {
			showProducts.add(chunk);
		}
		
		
		return new RestSuccess(HttpStatus.OK.value(), showProducts);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public RestSuccess getProduct(@PathVariable("id") Integer id) {
		logger.info("Get product: id = " + id);
		
		ProductDto dtoProduct = productService.findOne(id);
		
		logger.info("Customer [id = " + dtoProduct.getId() + ", name = " + dtoProduct.getName());
		
		return new RestSuccess(HttpStatus.OK.value(), dtoProduct, null);
	}
	
}
