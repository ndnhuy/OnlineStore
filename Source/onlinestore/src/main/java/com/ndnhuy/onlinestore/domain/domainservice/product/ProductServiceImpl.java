package com.ndnhuy.onlinestore.domain.domainservice.product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ndnhuy.onlinestore.app.dto.product.ProductDto;
import com.ndnhuy.onlinestore.app.dto.product.ProductDtoPurchase;
import com.ndnhuy.onlinestore.domain.domainservice.generic.GenericServiceImpl;
import com.ndnhuy.onlinestore.domain.entity.product.Product;
import com.ndnhuy.onlinestore.domain.exception.AppException;
import com.ndnhuy.onlinestore.repository.PurchaseRepository;

@Service
public class ProductServiceImpl extends GenericServiceImpl<Product, ProductDto, Integer> implements ProductService {

	private final static Logger logger = Logger.getLogger(ProductServiceImpl.class);
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private PurchaseRepository purchaseRepository;
	
	@Override
	public void addExtraInfoInto(ProductDtoPurchase dto, Integer purchaseId) {
		logger.debug("Add extra info (quantity, subtotal) into ProductDtoPurchase [id=" + dto.getId() + ", name=" + dto.getName() + "]");
		
		dto.setQuantity(purchaseRepository
								.findQuantityOfProductInPurchase(purchaseId, dto.getId()));
		
		dto.setSubtotal(dto.getPrice() * dto.getQuantity());

		logger.debug("ProductDtoPurchase has quantity " + dto.getQuantity() + " and subtotal " + dto.getSubtotal());
	}

	@Override
	public List<ProductDto> filter(HttpServletRequest request) {
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
				Product.FILTER_KEY_BRAND,
				Product.FILTER_KEY_COLOR
		};
		
		
		for (String key : keys) {
			
			String[] values = request.getParameterValues(key);
			if (values != null) {
				Path<String> operatingSystemOrBrandOrColorPath = null;
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
				    case Product.FILTER_KEY_COLOR:
				    	if (operatingSystemOrBrandOrColorPath == null) {
					    	operatingSystemOrBrandOrColorPath = from.get(Product.FILTER_KEY_COLOR);
				    	}
				
				    case Product.FILTER_KEY_OPERATING_SYSTEM:
				    	if (operatingSystemOrBrandOrColorPath == null) {
					    	operatingSystemOrBrandOrColorPath = from.get(Product.FILTER_KEY_OPERATING_SYSTEM).get(Product.FILTER_KEY_OPERATING_SYSTEM_NAME);
				    	}
				    case Product.FILTER_KEY_BRAND:
				    	if (operatingSystemOrBrandOrColorPath == null) {
					    	operatingSystemOrBrandOrColorPath = from.get(Product.FILTER_KEY_BRAND).get(Product.FILTER_KEY_BRAND_NAME);
				    	}
				    		
				    	List<Predicate> osPredicates = new ArrayList<Predicate>();
				    	for (String value : values) {
				    		osPredicates.add(builder.equal(builder.upper(operatingSystemOrBrandOrColorPath),value.toUpperCase()));
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
			dtos.add(mapper.map(p, ProductDto.class));
		}
		
		return dtos;
	}
	
}
