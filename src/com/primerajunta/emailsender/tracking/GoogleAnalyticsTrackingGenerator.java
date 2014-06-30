package com.primerajunta.emailsender.tracking;

import java.util.UUID;

import com.primerajunta.emailsender.application.User;


/**
 * Google Analytics tracking url generator
 * @author jvanrell
 *
 */
public class GoogleAnalyticsTrackingGenerator implements TrackingGenerator {

	// Tracking Id Google Analytics
	private String trackingId;
	
	public GoogleAnalyticsTrackingGenerator(String trackingId) {
		this.trackingId = trackingId;
	}
	
	public String getOpenEmailUrl(User user, String campaignName) {
		StringBuilder urlOpen = new StringBuilder();
		urlOpen.append("http://www.google-analytics.com/collect");		
		//Protocol version within Google Analytics
		urlOpen.append("?");
		urlOpen.append("v=1");
		//Google Analytics Tracking Id
		urlOpen.append("&");
		urlOpen.append("tid=" + trackingId);
		//User ID to track
		urlOpen.append("&");
		urlOpen.append("cid=" + generateTrackingRecord(user, campaignName));
		//Tells Google Analytics this is an Event Hit Type
		urlOpen.append("&");
		urlOpen.append("t=event");
		//The Event Category helps segment various events
		urlOpen.append("&");
		urlOpen.append("ec=email");
		//The Event Action helps specify exactly what happened
		urlOpen.append("&");
		urlOpen.append("ea=open");
		//Event Label specifies a unique identification for this recipient
		urlOpen.append("&");
		urlOpen.append("el=recipient_id");
		//Campaign Source allows segmentation of campaign types
		urlOpen.append("&");
		urlOpen.append("cs=newsletter2");
		//Campaign Medium could segment social vs. email, etc.
		urlOpen.append("&");
		urlOpen.append("cm=email");
		//Campaign Name identifies the campaign to you
		urlOpen.append("&");
		urlOpen.append("cn=" + campaignName);
		
		return urlOpen.toString();
	}
	
	private UUID generateTrackingRecord(User user, String campaignName) {
		UUID userUUID = UUID.randomUUID();
		System.out.println(user.getName() + ": " + userUUID);
		// TODO guardar el UUID asociado a este user en esta campaña.
		return userUUID;
	}

}
