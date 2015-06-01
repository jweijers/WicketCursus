package nl.ovsoftware;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;

public class TestPage extends WebPage {
	private static final Logger LOG = LoggerFactory.getLogger(TestPage.class);

	private static final long serialVersionUID = 1L;

	public TestPage(final PageParameters parameters) {
		super(parameters);

		final FeedbackPanel feedback = new FeedbackPanel("feedback");
		feedback.setOutputMarkupId(true);
		add(feedback);

		final Model<String> name = new Model<String>("");
		final Model<String> comment = new Model<String>("");

		Form<?> form = new Form<Object>("form");
 		add(form);
		form.add(new TextField<String>("name", name));
		form.add(new TextArea<String>("comment", comment));
		form.add(new AjaxSubmitLink("submit") {
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				super.onSubmit(target, form);
				LOG.info("Message from: " + name.getObject());
				info("Submitted something: " + name.getObject());
				target.add(feedback);
			}
		});
    }
}
