package com.ndnhuy.onlinestore.domain.domainservice.purchase;

import java.util.List;

import com.ndnhuy.onlinestore.app.dto.purchase.PurchaseDto;

public interface PurchaseService {
	PurchaseDto getPurchase(Integer id);
	List<PurchaseDto> getAll();
	void add(PurchaseDto addedPurchaseDto);
}
