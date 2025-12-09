package com.springboot.example.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CapturePaymentRequest {

	private ClientReferenceInformation clientReferenceInformation;
	private OrderInformation orderInformation;

	// Getters and Setters
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
	
	 public String toJson() throws Exception {
	        ObjectMapper mapper = new ObjectMapper();
	        return mapper.writeValueAsString(this);
	    }

}
