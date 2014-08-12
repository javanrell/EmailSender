package com.primerajunta.emailsender.tracking;

import com.primerajunta.emailsender.model.User;

public class WebTrackingGenerator implements TrackingGenerator {

	private String domain;
	
	public String getOpenEmailUrl(User user, String campaignName) {
		return domain + "/o/" + getEncryptedData(user, campaignName);
	}

	public String getAccessFromEmailUrl(User user, String campaignName, String page) {
		return domain + "/a/" + getEncryptedData(user, campaignName) + "/p/" + page;
	}

	public String getBuyFromEmailUrl(User user, String campaignName, String page) {
		return domain + "/b/" + getEncryptedData(user, campaignName) + "/p/" + page;
	}
	
	public String getRegisterFromEmailUrl(User user, String campaignName, String page) {
		return domain + "/r/" + getEncryptedData(user, campaignName) + "/p/" + page;
	}

	private String getEncryptedData(User user, String campaignName) {
		// TODO encrypt parameters 
		return user.getId() + "/" + campaignName;
	}

}
