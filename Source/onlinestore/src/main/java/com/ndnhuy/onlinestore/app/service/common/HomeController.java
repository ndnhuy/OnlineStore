package com.ndnhuy.onlinestore.app.service.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ndnhuy.onlinestore.domain.entity.Product;
import com.ndnhuy.onlinestore.domain.entity.Purchase;
import com.ndnhuy.onlinestore.domain.entity.PurchaseProduct;
import com.ndnhuy.onlinestore.repository.ProductRepository;
import com.ndnhuy.onlinestore.repository.PurchaseRepository;

@RestController
public class HomeController {
	
	@Autowired
	PurchaseRepository repo;
	
	@Autowired
	ProductRepository productRepo;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public Integer home() {
		
		Purchase p = repo.findOne(1);
		
		for (PurchaseProduct pd : p.getPurchaseProducts()) {
			System.out.println(pd.getProduct().getName());
			System.out.println(pd.getPurchase().getId());
		}
		
		PurchaseProduct pd = new PurchaseProduct();
		
		Product product = productRepo.findOne(2);
	
		
		pd.setProduct(product);
		pd.setPurchase(p);
		pd.setQuantity(99);
		
		p.getPurchaseProducts().get(0).setQuantity(123);
		
		repo.save(p);

		
		return 1;
	}
}
