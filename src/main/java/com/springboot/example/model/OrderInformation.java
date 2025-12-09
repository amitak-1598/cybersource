package com.springboot.example.model;

public class OrderInformation {

	private AmountDetails amountDetails;
    private BillTo billTo;

    // getters & setters
    public AmountDetails getAmountDetails() { return amountDetails; }
    public void setAmountDetails(AmountDetails amountDetails) { this.amountDetails = amountDetails; }
    public BillTo getBillTo() { return billTo; }
    public void setBillTo(BillTo billTo) { this.billTo = billTo; }
}
