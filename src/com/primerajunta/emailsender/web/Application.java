package com.primerajunta.emailsender.web;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.settings.IExceptionSettings;
import org.apache.wicket.util.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application extends WebApplication {
	
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

	@Override
	public Class<? extends Page> getHomePage() {
		return HomePage.class;
	}

	@Override
	public void init() {
		logger.info("Inicializando aplicacion");
		
		boolean development = true;
		getDebugSettings().setAjaxDebugModeEnabled(development);
		getDebugSettings().setComponentUseCheck(development);

		if (development)
			getExceptionSettings().setUnexpectedExceptionDisplay(
					IExceptionSettings.SHOW_EXCEPTION_PAGE);
		else
			getExceptionSettings().setUnexpectedExceptionDisplay(
					IExceptionSettings.SHOW_INTERNAL_ERROR_PAGE);

		getRequestCycleSettings().setResponseRequestEncoding("utf-8");

		getMarkupSettings().setDefaultMarkupEncoding("utf-8");
		getMarkupSettings().setStripWicketTags(!development);

		if (development) {
			getResourceSettings().setResourcePollFrequency(Duration.ONE_SECOND);
		}
		getResourceSettings().setThrowExceptionOnMissingResource(true);
		
		mountPage("/user/unsubscribe", UnsubscribePage.class);
	}

}
