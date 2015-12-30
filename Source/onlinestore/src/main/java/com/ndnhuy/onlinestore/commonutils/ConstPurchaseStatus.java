package com.ndnhuy.onlinestore.commonutils;

public enum ConstPurchaseStatus {
	ORDERING(1, "ORDERING"),
	CLOSED(2, "CLOSED");

	private final int value;
	private final String statusName;
	
	private ConstPurchaseStatus(int value, String statusName) {
		this.value = value;
		this.statusName = statusName;
	}
	
	public int value() {
		return this.value;
	}
	
	public String getStatusName() {
		return this.statusName;
	}
	
	@Override
	public String toString() {
		return this.statusName;
	}
}
