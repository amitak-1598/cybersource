package com.springboot.example.model;

public class DeviceInformation {

	private String ipAddress;
	private String httpAcceptContent;
	private String httpBrowserLanguage;
	private String httpBrowserJavaEnabled;
	private String httpBrowserJavaScriptEnabled;
	private String httpBrowserColorDepth;
	private String httpBrowserScreenHeight;
	private String httpBrowserScreenWidth;
	private String httpBrowserTimeDifference;
	private String userAgentBrowserValue;

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getHttpAcceptContent() {
		return httpAcceptContent;
	}

	public void setHttpAcceptContent(String httpAcceptContent) {
		this.httpAcceptContent = httpAcceptContent;
	}

	public String getHttpBrowserLanguage() {
		return httpBrowserLanguage;
	}

	public void setHttpBrowserLanguage(String httpBrowserLanguage) {
		this.httpBrowserLanguage = httpBrowserLanguage;
	}

	public String getHttpBrowserJavaEnabled() {
		return httpBrowserJavaEnabled;
	}

	public void setHttpBrowserJavaEnabled(String httpBrowserJavaEnabled) {
		this.httpBrowserJavaEnabled = httpBrowserJavaEnabled;
	}

	public String getHttpBrowserJavaScriptEnabled() {
		return httpBrowserJavaScriptEnabled;
	}

	public void setHttpBrowserJavaScriptEnabled(String httpBrowserJavaScriptEnabled) {
		this.httpBrowserJavaScriptEnabled = httpBrowserJavaScriptEnabled;
	}

	public String getHttpBrowserColorDepth() {
		return httpBrowserColorDepth;
	}

	public void setHttpBrowserColorDepth(String httpBrowserColorDepth) {
		this.httpBrowserColorDepth = httpBrowserColorDepth;
	}

	public String getHttpBrowserScreenHeight() {
		return httpBrowserScreenHeight;
	}

	public void setHttpBrowserScreenHeight(String httpBrowserScreenHeight) {
		this.httpBrowserScreenHeight = httpBrowserScreenHeight;
	}

	public String getHttpBrowserScreenWidth() {
		return httpBrowserScreenWidth;
	}

	public void setHttpBrowserScreenWidth(String httpBrowserScreenWidth) {
		this.httpBrowserScreenWidth = httpBrowserScreenWidth;
	}

	public String getHttpBrowserTimeDifference() {
		return httpBrowserTimeDifference;
	}

	public void setHttpBrowserTimeDifference(String httpBrowserTimeDifference) {
		this.httpBrowserTimeDifference = httpBrowserTimeDifference;
	}

	public String getUserAgentBrowserValue() {
		return userAgentBrowserValue;
	}

	public void setUserAgentBrowserValue(String userAgentBrowserValue) {
		this.userAgentBrowserValue = userAgentBrowserValue;
	}

	// ------------------ Helper Method to Populate Device Info ------------------
	public static DeviceInformation createSampleDeviceInfo() {
		DeviceInformation device = new DeviceInformation();
		device.setIpAddress("122.160.68.182");
		device.setHttpAcceptContent("text/html");
		device.setHttpBrowserLanguage("en-IN");
		device.setHttpBrowserJavaEnabled("N");
		device.setHttpBrowserJavaScriptEnabled("Y");
		device.setHttpBrowserColorDepth("24");
		device.setHttpBrowserScreenHeight("1200");
		device.setHttpBrowserScreenWidth("1920");
		device.setHttpBrowserTimeDifference("330");
		device.setUserAgentBrowserValue(
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/142.0.0.0 Safari/537.36");
		return device;
	}
}
