package com.ndnhuy.onlinestore.domain.domainservice.purchase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ndnhuy.onlinestore.app.dto.purchase.PurchaseDto;
import com.ndnhuy.onlinestore.domain.entity.Purchase;
import com.ndnhuy.onlinestore.repository.PurchaseRepository;

@Service
public class PurchaseServiceImpl implements PurchaseService {

	private static final Logger logger = Logger.getLogger(PurchaseServiceImpl.class);
	
	@Autowired
	private PurchaseRepository purchaseRepo;
	
	@Autowired
	private DozerBeanMapper mapper;
	
	@Override
	public PurchaseDto getPurchase(Integer id) {
		return mapper.map(purchaseRepo.findOne(id), PurchaseDto.class);
	}

	@Override
	public List<PurchaseDto> getAll() {
		List<PurchaseDto> purchaseDtoList = new ArrayList<PurchaseDto>();
		for (Purchase p : purchaseRepo.findAll()) {
			logger.info("Puchase customer: " + p.getCustomer().getId());
			purchaseDtoList.add(mapper.map(p, PurchaseDto.class));
		}
		return purchaseDtoList;
	}

	@Override
	public void add(PurchaseDto addedPurchaseDto) {
		purchaseRepo.save(mapper.map(addedPurchaseDto, Purchase.class));
	}

	

}
