package com.springboot.example.model;

public class ConsumerAuthenticationInformation {

	 private String deviceChannel;        // e.g., "BROWSER"
	    private String transactionMode;      // e.g., "eCommerce"
	    private String referenceId;
	    private String returnUrl;
	    private String cavv;
	    private String xid;

	    // ------------------ Getters and Setters ------------------
	    public String getDeviceChannel() {
	        return deviceChannel;
	    }

	    public String getReferenceId() {
			return referenceId;
		}

		public void setReferenceId(String referenceId) {
			this.referenceId = referenceId;
		}

		public void setDeviceChannel(String deviceChannel) {
	        this.deviceChannel = deviceChannel;
	    }

	    public String getTransactionMode() {
	        return transactionMode;
	    }

	    public void setTransactionMode(String transactionMode) {
	        this.transactionMode = transactionMode;
	    }

		public String getReturnUrl() {
			return returnUrl;
		}

		public void setReturnUrl(String returnUrl) {
			this.returnUrl = returnUrl;
		}

		public String getCavv() {
			return cavv;
		}

		public void setCavv(String cavv) {
			this.cavv = cavv;
		}

		public String getXid() {
			return xid;
		}

		public void setXid(String xid) {
			this.xid = xid;
		}
		

//	    // ------------------ Helper Method for Sample Data ------------------
//	    public static ConsumerAuthenticationInformation createSample() {
//	        ConsumerAuthenticationInformation info = new ConsumerAuthenticationInformation();
//	        info.setDeviceChannel("BROWSER");
//	        info.setTransactionMode("eCommerce");
//	        return info;
//	    }
}
