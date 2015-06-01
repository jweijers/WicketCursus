package nl.ovsoftware;

import org.apache.wicket.Page;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;

public class TestPage extends WebPage {
	private static final Logger LOG = LoggerFactory.getLogger(TestPage.class);

	private static final long serialVersionUID = 1L;

	private static final List<Message> messageStore = new LinkedList<Message>();

	public TestPage(final PageParameters parameters) {
		super(parameters);

		final FeedbackPanel feedback = new FeedbackPanel("feedback");
		feedback.setOutputMarkupId(true);
		add(feedback);

		final Model<String> name = new Model<String>("");
		final Model<String> comment = new Model<String>("");

		final ListView<Message> messageList = new ListView<Message>("messages", messageStore) {
			@Override
			protected void populateItem(ListItem<Message> item) {
				item.add(new Label("sender", item.getModel().getObject().getSender()));
				item.add(new Label("msg", item.getModel().getObject().getMessage()));
			}
		};
		messageList.setOutputMarkupId(true);
		add(messageList);

		/*final Model<String> messages = new Model<String>("");
		final MultiLineLabel multiLineLabel = new MultiLineLabel("messages", messages);
		multiLineLabel.setOutputMarkupId(true);
		add(multiLineLabel);
*/
		Form<?> form = new Form<Object>("form");
 		add(form);
		final FormComponent<String> nameField = new TextField<String>("name", name).setRequired(true);
		nameField.setOutputMarkupId(true);
		form.add(nameField);

		final FormComponent<String> commentField = new TextArea<String>("comment", comment).setRequired(true);
		commentField.setOutputMarkupId(true);
		form.add(commentField);
		form.add(new Button("submit") {
			@Override
			public void onSubmit() {
				messageStore.add(new Message(name.getObject(), comment.getObject()));
				getSession().info("Submitted something: " + name.getObject());
				getRequestCycle().setResponsePage(TestPage.class);


				/*name.setObject("");
				comment.setObject("");
				target.add(nameField);
				target.add(commentField);
				LOG.info("Message from: " + name.getObject());
				info("Submitted something: " + name.getObject());
				target.add(feedback);
				target.add(messageList);*/
				/*StringBuilder output = new StringBuilder();
				for (Message m : messageStore){
					output.append(m.getSender() + ": " + m.getMessage() + "\n");
				}*/
				//messages.setObject(output.toString());
				//target.add(multiLineLabel);
			}
		});
    }
}
