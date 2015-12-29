package com.ndnhuy.onlinestore.app.service.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.sync.Patch;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ndnhuy.onlinestore.app.dto.product.ProductDto;
import com.ndnhuy.onlinestore.domain.entity.product.Product;
import com.ndnhuy.onlinestore.domain.exception.AppException;
import com.ndnhuy.onlinestore.repository.CustomerRepository;

@RestController
@Transactional
public class HomeController {
	
	@Autowired
	private CustomerRepository repo;
	
	@Autowired
	private DozerBeanMapper mapper;
	
	@PersistenceContext
	private EntityManager em;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public Object home(HttpServletRequest request) {
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery query = builder.createQuery();
		Root<Product> from = query.from(Product.class);
		
		EntityType<Product> type = em.getMetamodel().entity(Product.class);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		String[] keys = new String[] {
				Product.FILTER_KEY_NAME,
				"lessThan_" + Product.FILTER_KEY_PRICE,
				"greaterThan_" + Product.FILTER_KEY_PRICE,
				"between_" + Product.FILTER_KEY_PRICE,
				Product.FILTER_KEY_OPERATING_SYSTEM,
				Product.FILTER_KEY_BRAND
		};
		
		
		for (String key : keys) {
			
			String[] values = request.getParameterValues(key);
			if (values != null) {
				Path<String> operatingSystemOrBrandPath = null;
				try {
					switch (key) {
				    case Product.FILTER_KEY_NAME:
				    	List<Predicate> namePredicates = new ArrayList<Predicate>();
				    	for (String value : values) {
				    		namePredicates.add(builder.equal(builder
									.upper(from.get(type.getDeclaredSingularAttribute(Product.FILTER_KEY_NAME, String.class))), 
										value.toUpperCase()));
				    	}
				    	
				    	predicates.add(builder.or(namePredicates.toArray(new Predicate[] {})));
				    	break;
				    case Product.FILTER_KEY_OPERATING_SYSTEM:
				    	if (operatingSystemOrBrandPath == null) {
					    	operatingSystemOrBrandPath = from.get(Product.FILTER_KEY_OPERATING_SYSTEM).get(Product.FILTER_KEY_OPERATING_SYSTEM_NAME);
				    	}
				    case Product.FILTER_KEY_BRAND:
				    	if (operatingSystemOrBrandPath == null) {
					    	operatingSystemOrBrandPath = from.get(Product.FILTER_KEY_BRAND).get(Product.FILTER_KEY_BRAND_NAME);
				    	}

				    	List<Predicate> osPredicates = new ArrayList<Predicate>();
				    	for (String value : values) {
				    		osPredicates.add(builder.equal(builder.upper(operatingSystemOrBrandPath),value.toUpperCase()));
				    	}
				    	
				    	predicates.add(builder.or(osPredicates.toArray(new Predicate[] {})));
				    	
				    	break;

				    case "lessThan_" + Product.FILTER_KEY_PRICE:
				    	List<Predicate> ltPricePredicates = new ArrayList<Predicate>();
				    	for (String value : values) {
				    		Path<Double> pricePath = from.get(Product.FILTER_KEY_PRICE);
				    		ltPricePredicates.add(builder.lessThan(pricePath, new Double(value)));
				    	}
				    	
				    	predicates.add(builder.or(ltPricePredicates.toArray(new Predicate[] {})));
				    	break;
				    case "greaterThan_" + Product.FILTER_KEY_PRICE:
				    	List<Predicate> gtPricePredicates = new ArrayList<Predicate>();
				    	for (String value : values) {
				    		Path<Double> pricePath = from.get(Product.FILTER_KEY_PRICE);
				    		gtPricePredicates.add(builder.greaterThan(pricePath, new Double(value)));
				    	}
				    	
				    	predicates.add(builder.or(gtPricePredicates.toArray(new Predicate[] {})));
				    	break;
				    case "between_" + Product.FILTER_KEY_PRICE:
				    	List<Predicate> betweenPricePredicates = new ArrayList<Predicate>();
				    	for (String value : values) {
				    		try {
					    		List<String> betweenPrices = Arrays.asList(value.split(","));
						    	
						    	Path<Double> pricePath = from.get(Product.FILTER_KEY_PRICE);
						    	betweenPricePredicates.add(builder.between(pricePath, 
						    									Double.parseDouble(betweenPrices.get(0)),
						    									Double.parseDouble(betweenPrices.get(1))));
					    	} catch(ArrayIndexOutOfBoundsException ex) {
					    		throw new AppException(HttpStatus.BAD_REQUEST.value(), null, "The 'between_price' has no enough values", ex);
					    	}
				    	}
				    	
				    	predicates.add(builder.or(betweenPricePredicates.toArray(new Predicate[] {})));
				    	
				    	break;
				    
					}
				} catch (NumberFormatException ex) {
					throw new AppException(HttpStatus.BAD_REQUEST.value(), null, "Invalid request: " + ex.getMessage());
				}
			}
		}
		
		query.select(from)
				.where(predicates.toArray(new Predicate[] {}));
		
		
		List<Product> results = em.createQuery(query).getResultList();
		
		List<ProductDto> dtos = new ArrayList<ProductDto>();
		for (Product p : results) {
			System.out.println(p.getName());
			
			dtos.add(mapper.map(p, ProductDto.class));
		}
		
		
		return dtos;
	}
	
	@RequestMapping(value="/", method=RequestMethod.PATCH)
	public Integer home2(@RequestBody Patch patch) {

		
		return 1;
	}
}
