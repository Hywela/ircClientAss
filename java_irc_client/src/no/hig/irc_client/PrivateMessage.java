package no.hig.irc_client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import jerklib.Session;
import jerklib.events.IRCEvent;
import jerklib.events.IRCEvent.Type;
import jerklib.events.MessageEvent;
import jerklib.tasks.TaskImpl;

/**
 * Private messaging tab
 * 
 * @author hyw
 * 
 */
public class PrivateMessage extends JPanel {
	private JTextField inputField;
	TextArea text;
	private Client client = Client.getInstance();
	private String pmNick;

	boolean notChat;
	/**
	 * 
	 * @param borderLayout
	 * @param s(session)
	 * @param pmNick
	 */
	public PrivateMessage(BorderLayout borderLayout, final Session s,
			final String pmNick) {
		// TODO Auto-generated constructor stub
		super(borderLayout);

		text = new TextArea();
		inputField = new JTextField();

		this.pmNick = pmNick;

		notChat = false;

		text.write(Language.getMsg("JoiningPrivateChatWith")+"  : " + pmNick, Color.BLUE,
				client.getSettings().getSize(), client.getSettings().getFont());
		text.setEditable(false);

		JScrollPane scrollPane = new JScrollPane(text,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setAutoscrolls(true);

		add(scrollPane, BorderLayout.CENTER);

		add(inputField, BorderLayout.SOUTH);

		inputField.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent b) {
				inputField.setText("");
				text.write(b.getActionCommand(), client.getSettings()
						.getMyColor(), client.getSettings().getSize(), client
						.getSettings().getFont());
				System.out.println(b.getActionCommand());

				if (b.getActionCommand().startsWith("/")) {

					String sw = b.getActionCommand().substring(1, 2);
					String message = b.getActionCommand().substring(3);
					switch (sw) {
					case "j": {
						Client client = null;
						client = client.getInstance();

						client.joinChannel(message);
						break;
					}

					}

				} else
					
					s.sayPrivate(pmNick, b.getActionCommand());
			}
		});

		s.onEvent(new TaskImpl("PRIVATE_MESSAGE") {
			public void receiveEvent(IRCEvent e) {
				Client client = null;
				client = Client.getInstance();
				MessageEvent pm = (MessageEvent) e; // message event
				if (pm.getNick().equals(pmNick)) {
					text.write(pm.getMessage(), client.getSettings()
							.getTekstColor(), client.getSettings().getSize(),
							client.getSettings().getFont());
				}

			}

		}, Type.PRIVATE_MESSAGE);

	}
}
