package com.springboot.example.model;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RefundPaymentRequest {

	private ClientReferenceInformation clientReferenceInformation;
	private OrderInformation orderInformation;

	public RefundPaymentRequest() {
	}

	public RefundPaymentRequest(String code, String totalAmount, String currency) {
		ClientReferenceInformation clientRef = new ClientReferenceInformation();
		clientRef.setCode(code);
		this.clientReferenceInformation = clientRef;

	
		AmountDetails amount = new AmountDetails();
		amount.setTotalAmount(totalAmount);
		amount.setCurrency(currency);

		
		OrderInformation order = new OrderInformation();
		order.setAmountDetails(amount);
		this.orderInformation = order;
	}

	public String toJson() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(this);
	}

	public ClientReferenceInformation getClientReferenceInformation() {
		return clientReferenceInformation;
	}

	public void setClientReferenceInformation(ClientReferenceInformation clientReferenceInformation) {
		this.clientReferenceInformation = clientReferenceInformation;
	}

	public OrderInformation getOrderInformation() {
		return orderInformation;
	}

	public void setOrderInformation(OrderInformation orderInformation) {
		this.orderInformation = orderInformation;
	}

}
