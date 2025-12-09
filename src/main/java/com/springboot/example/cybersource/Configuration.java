package com.springboot.example.cybersource;

import java.util.Properties;

public class Configuration {
	public static Properties getMerchantDetails() {
		Properties props = new Properties();

		props.setProperty("authenticationType", "http_signature");
		
		//props.setProperty("merchantID", "testrest");
		props.setProperty("merchantID", "123456_1763793531");
		props.setProperty("runEnvironment", "apitest.cybersource.com");
		props.setProperty("requestJsonPath", "src/main/resources/request.json");

		// MetaKey Parameters
		props.setProperty("portfolioID", "");
		props.setProperty("useMetaKey", "false");


		// HTTP Parameters
//		props.setProperty("merchantKeyId", "08c94330-f618-42a3-b09d-e1e43be5efda");
		props.setProperty("merchantKeyId", "fe16ccc1-eb17-4ca7-b4c0-041fdef9c941");
//		props.setProperty("merchantsecretKey", "yBJxy6LjM2TmcPGu+GaJrHtkke25fPpUX+UY6/L/1tE=");
		props.setProperty("merchantsecretKey", "r5x2Ll053H7XRsUUhf/eAIYLKJVBnhPBBEjiz8pMSb4=");
		
		
		// Logging to be enabled or not.
		props.setProperty("enableLog", "true");
		// Log directory Path
		props.setProperty("logDirectory", "log");
		props.setProperty("logFilename", "cybs");

		// Log file size in KB
		props.setProperty("logMaximumSize", "5M");


		props.setProperty("defaultDeveloperId", "");

		return props;

	}
	

	
}

