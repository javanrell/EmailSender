package com.primerajunta.emailsender.tracking;

import com.primerajunta.emailsender.model.User;

public interface TrackingGenerator {

	public String getOpenEmailUrl(User user, String campaignName);
	
}
