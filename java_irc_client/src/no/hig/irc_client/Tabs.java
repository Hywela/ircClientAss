package no.hig.irc_client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import jerklib.Channel;
import jerklib.Session;
import jerklib.events.ErrorEvent;
import jerklib.events.IRCEvent;
import jerklib.events.JoinCompleteEvent;
import jerklib.events.JoinEvent;
import jerklib.events.MessageEvent;
import jerklib.events.NickListEvent;
import jerklib.events.IRCEvent.Type;
import jerklib.events.NoticeEvent;
import jerklib.events.PartEvent;
import jerklib.events.QuitEvent;
import jerklib.listeners.IRCEventListener;

public class Tabs extends JPanel implements IRCEventListener {
	private JTextField inputField;
	public TextArea text;

	public String channel;
	Channel chan;
	DefaultListModel<String> listModel;
	JList<DefaultListModel<String>> list;

	boolean notChat;

	public Tabs(BorderLayout borderLayout, boolean type, final Session s,
			final String channel) {
		// TODO Auto-generated constructor stub
		super(borderLayout);
		listModel = new DefaultListModel<String>();
		text = new TextArea();
		inputField = new JTextField();

		this.channel = channel;
		chan = new Channel(channel, s);
		if (type == false) {

			notChat = true;

			text.setEditable(false);
			JScrollPane scrollPane = new JScrollPane(text,
					ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
					ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			scrollPane.setAutoscrolls(true);

			// split.add(inputField,JSplitPane.BOTTOM);

			add(scrollPane, BorderLayout.CENTER);

			// p.add(,BorderLayout.CENTER);
			add(inputField, BorderLayout.SOUTH);

		}

		if (type == true) {

			notChat = false;
			list = new JList(listModel);
			text.write("Joining Channel : " + channel, Color.BLUE);

			text.setEditable(false);

			JScrollPane scrollPane = new JScrollPane(list,
					ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
					ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

			JScrollPane scrollPane2 = new JScrollPane(text,
					ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
					ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			// /p.add(scrollPane, BorderLayout.EAST);

			JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
					scrollPane, scrollPane2);
			// split.add(inputField,JSplitPane.BOTTOM);
			// p.add(list,BorderLayout.EAST);
			add(split);
			// p.add(,BorderLayout.CENTER);
			add(inputField, BorderLayout.SOUTH);

		}
		inputField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent b) {
				// TODO Auto-generated method stub
				inputField.setText("");
				text.write(b.getActionCommand(), Color.BLACK);

				System.out.println(b.getActionCommand());

				if (b.getActionCommand().startsWith("/")) {

					String sw = b.getActionCommand().substring(1, 2);
					String message = b.getActionCommand().substring(3);
					switch (sw) {
					case "j": {
						Client client = null;
						client = Client.getInstance();

						client.joinChannel(message);
						break;
					}

					}

				} else
					chan.say(b.getActionCommand());
			}
		});
		s.addIRCEventListener(this);
	}// END CONSTRUCTORd

	@Override
	public void receiveEvent(IRCEvent e) {
		// TODO Auto-generated method stub

		if (e.getType() == Type.JOIN) {
			JoinEvent joinEvent = (JoinEvent) e;
			Channel gc = joinEvent.getChannel();
			System.out.println(gc.getName());
			if (channel.equals(gc.getName())) {
				text.write(
						"<" + joinEvent.getNick() + "> has joined the "
								+ gc.getName(), Color.BLUE);
				listModel.addElement(joinEvent.getNick());
			}

		} else if (e.getType() == Type.CHANNEL_MESSAGE) {
			MessageEvent me = (MessageEvent) e;
			Channel gc = me.getChannel();
			if (channel.equals(gc.getName())) {

				if (!me.getMessage().startsWith("/")) {
					text.write("<" + me.getNick() + "> " + me.getMessage(),
							Color.BLACK);
				}
			}

			if (notChat)
				text.write(e.getType() + " " + e.getRawEventData(), Color.BLUE);

		} else if (e.getType() == Type.CONNECT_COMPLETE) {
			System.out.println("CONNECT");
		} else if (e.getType() == Type.JOIN_COMPLETE) {
			System.out.println("join");
			JoinCompleteEvent jce = (JoinCompleteEvent) e;
		} else if (e.getType() == Type.SERVER_INFORMATION) {
			if (notChat)
				text.write(e.getType() + " " + e.getRawEventData(), Color.BLUE);
		} else if (e.getType() == Type.SERVER_VERSION_EVENT) {
			if (notChat)
				text.write(e.getType() + " " + e.getRawEventData(), Color.BLUE);
		} else if (e.getType() == Type.CONNECTION_LOST) {
			if (notChat)
				text.write(e.getType() + " " + e.getRawEventData(), Color.BLUE);

		} else if (e.getType() == Type.PRIVATE_MESSAGE) {

			Client client = null;
			client = Client.getInstance();
			MessageEvent pm = (MessageEvent) e; // message event
			if (!client.findNick(pm.getNick())) {
				client.newPrivatTab(e.getSession(), pm.getNick());
			}

		} else if (e.getType() == Type.PART) {
			PartEvent partEvent = (PartEvent) e;
			Channel gc = partEvent.getChannel();
			System.out.println(gc.getName());
			if (channel.equals(gc.getName())) {

				for (int i = 0; i < listModel.getSize(); i++) {
					if (listModel.get(i).toString().equals(partEvent.getWho())) {
						text.write("<" + partEvent.getWho() + "> has quit the "
								+ gc.getName(), Color.RED);

						listModel.remove(i);
					}
				}
			}
		} else if (e.getType() == Type.NICK_LIST_EVENT) {
			NickListEvent ne = (NickListEvent) e;
			Channel gc = ne.getChannel();
			if (channel.equals(gc.getName())) {
				List<String> players = ne.getNicks();
				for (String nick : players)
					listModel.addElement(nick);

			}
		} else if (e.getType() == Type.QUIT) {
			QuitEvent quitEvent = (QuitEvent) e;

			if (listModel.getSize() > quitEvent.getChannelList().size()) {
				for (int i = 0; i > listModel.getSize(); i++) {
					text.write(quitEvent.getNick() + "has quit the Channel",
							Color.RED);
					if (listModel.get(i) == quitEvent.getNick()) {
						listModel.remove(i);
					}
				}
			}
		} else if (e.getType() == Type.ERROR) {
			ErrorEvent errorEvent = (ErrorEvent) e;
			text.write(e.getRawEventData(), Color.BLACK);

		} else if (e.getType() == Type.INVITE_EVENT) {

			text.write(e.getRawEventData(), Color.GREEN);

		} else if (e.getType() == Type.NOTICE) {
			NoticeEvent event = (NoticeEvent) e;

			text.write(event.getNoticeMessage(), Color.RED);

		} else {

			text.write(e.getRawEventData(), Color.RED);
		}

	}

}
