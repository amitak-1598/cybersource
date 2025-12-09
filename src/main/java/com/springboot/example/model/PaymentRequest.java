package com.springboot.example.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentRequest {

	private ClientReferenceInformation clientReferenceInformation;
	private PaymentInformation paymentInformation;
	private OrderInformation orderInformation;
	private ProcessingInformation processingInformation;
	// getters & setters
	public ClientReferenceInformation getClientReferenceInformation() {
		return clientReferenceInformation;
	}

	public ProcessingInformation getProcessingInformation() {
		return processingInformation;
	}

	public void setProcessingInformation(ProcessingInformation processingInformation) {
		this.processingInformation = processingInformation;
	}

	public void setClientReferenceInformation(ClientReferenceInformation clientReferenceInformation) {
		this.clientReferenceInformation = clientReferenceInformation;
	}

	public PaymentInformation getPaymentInformation() {
		return paymentInformation;
	}

	public void setPaymentInformation(PaymentInformation paymentInformation) {
		this.paymentInformation = paymentInformation;
	}

	public OrderInformation getOrderInformation() {
		return orderInformation;
	}

	public void setOrderInformation(OrderInformation orderInformation) {
		this.orderInformation = orderInformation;
	}
	
	 public String toJson() {
	        try {
	            ObjectMapper mapper = new ObjectMapper();
	            return mapper.writeValueAsString(this);
	        } catch (JsonProcessingException e) {
	            e.printStackTrace();
	            return "{}";
	        }
	    }


}
