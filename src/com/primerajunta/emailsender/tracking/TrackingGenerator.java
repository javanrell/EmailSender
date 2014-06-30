package com.primerajunta.emailsender.tracking;

import com.primerajunta.emailsender.application.User;

public interface TrackingGenerator {

	public String getOpenEmailUrl(User user, String campaignName);
	
}
