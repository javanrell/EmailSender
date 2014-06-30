package com.primerajunta.emailsender.tracking;

import com.primerajunta.emailsender.application.User;

public class WebTrackingGenerator implements TrackingGenerator {

	private String domain;
	
	public String getOpenEmailUrl(User user, String campaignName) {
		return domain + "/o/" + getEncryptedData(user, campaignName);
	}

	public String getEnterEmailUrl(User user, String campaignName, String page) {
		return domain + "/e/" + getEncryptedData(user, campaignName) + "/p/" + page;
	}

	public String getBuyEmailUrl(User user, String campaignName, String page) {
		return domain + "/b/" + getEncryptedData(user, campaignName) + "/p/" + page;
	}

	private String getEncryptedData(User user, String campaignName) {
		// TODO encrypt parameters 
		return user.getId() + "/" + campaignName;
	}

}
