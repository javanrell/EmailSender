package com.primerajunta.emailsender.web;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * Allows emails receivers to unsubscribe
 * @author jvanrell
 *
 */
public class UnsubscribePage extends WebPage {

	public UnsubscribePage(PageParameters parameters) {
		super(parameters);
		add(new AjaxButton("confirm") {

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				super.onSubmit(target, form);
				unsubscribeUserFromList();
			}
			
		});
	}

	protected void unsubscribeUserFromList() {
		// TODO Auto-generated method stub
	}

}
