package com.springboot.example.model;

import com.fasterxml.jackson.core.JsonProcessingException;

public class PayerAuthenticationRequest {
    private OrderInformation orderInformation;
    private PaymentInformation paymentInformation;
    private BuyerInformation buyerInformation;
    private DeviceInformation deviceInformation;
    private ConsumerAuthenticationInformation consumerAuthenticationInformation;
    private ClientReferenceInformation clientReferenceInformation;
    
    

    public OrderInformation getOrderInformation() {
		return orderInformation;
	}



	public void setOrderInformation(OrderInformation orderInformation) {
		this.orderInformation = orderInformation;
	}



	public PaymentInformation getPaymentInformation() {
		return paymentInformation;
	}



	public void setPaymentInformation(PaymentInformation paymentInformation) {
		this.paymentInformation = paymentInformation;
	}



	public BuyerInformation getBuyerInformation() {
		return buyerInformation;
	}



	public void setBuyerInformation(BuyerInformation buyerInformation) {
		this.buyerInformation = buyerInformation;
	}



	public DeviceInformation getDeviceInformation() {
		return deviceInformation;
	}



	public void setDeviceInformation(DeviceInformation deviceInformation) {
		this.deviceInformation = deviceInformation;
	}



	public ConsumerAuthenticationInformation getConsumerAuthenticationInformation() {
		return consumerAuthenticationInformation;
	}



	public void setConsumerAuthenticationInformation(ConsumerAuthenticationInformation consumerAuthenticationInformation) {
		this.consumerAuthenticationInformation = consumerAuthenticationInformation;
	}



	public ClientReferenceInformation getClientReferenceInformation() {
		return clientReferenceInformation;
	}



	public void setClientReferenceInformation(ClientReferenceInformation clientReferenceInformation) {
		this.clientReferenceInformation = clientReferenceInformation;
	}



	public String toJson() throws JsonProcessingException {
        // Use Jackson or Gson to serialize to JSON
        return new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(this);
    }
}
