package com.springboot.example.model;

public class BuyerInformation {

	private String mobilePhone;

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public static BuyerInformation createSample() {
		BuyerInformation buyer = new BuyerInformation();
		buyer.setMobilePhone("8191064692");
		return buyer;
	}
}
