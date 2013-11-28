package no.hig.irc_client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import jerklib.Channel;
import jerklib.Session;
import jerklib.events.ChannelListEvent;
import jerklib.events.ErrorEvent;
import jerklib.events.IRCEvent;
import jerklib.events.KickEvent;
import jerklib.events.IRCEvent.Type;
import jerklib.events.NoticeEvent;
import jerklib.listeners.IRCEventListener;

public class ConnectorTab extends JPanel implements IRCEventListener {
	private JTextField inputField;
	public TextArea text;
	Channel chan;
	Client client = Client.getInstance();
	final JFrame kickFrame = new JFrame();	
	public ConnectorTab(BorderLayout borderLayout,  Session s) {
		// TODO Auto-generated constructor stub
		super(borderLayout);
	
		text = new TextArea();
		inputField = new JTextField();
			text.setEditable(false);
			JScrollPane scrollPane = new JScrollPane(text,
					ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
					ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			scrollPane.setAutoscrolls(true);

			// split.add(inputField,JSplitPane.BOTTOM);

			add(scrollPane, BorderLayout.CENTER);

			// p.add(,BorderLayout.CENTER);
			add(inputField, BorderLayout.SOUTH);

		

	
		
		inputField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent b) {
				// TODO Auto-generated method stub
				inputField.setText("");
				text.write(b.getActionCommand(), Color.BLACK);
				if (b.getActionCommand().startsWith("/")) {

					String sw = b.getActionCommand().substring(1, 2);
					String message = b.getActionCommand().substring(3);
					switch (sw) {
					case "j": {

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
		client.setSession(e.getSession());
		 if (e.getType() == Type.CHANNEL_LIST_EVENT) {

				ChannelListEvent event = (ChannelListEvent) e;
				client.addToChanList(event.getChannelName());
				text.write(event.getChannelName(), Color.GREEN);
			}
	 if (e.getType() == Type.CONNECT_COMPLETE) {
		text.write("Connection Complete", Color.GREEN);
		client.chanelList();}
	
	
		 else if (e.getType() == Type.ERROR) {
			ErrorEvent errorEvent = (ErrorEvent) e;
			text.write(e.getRawEventData(), Color.BLACK);

		} else if (e.getType() == Type.INVITE_EVENT) {

			text.write(e.getRawEventData(), Color.GREEN);

		} else if (e.getType() == Type.NOTICE) {
			NoticeEvent event = (NoticeEvent) e;

			text.write(event.getNoticeMessage(), Color.RED);
		}
			else if (e.getType() == Type.KICK_EVENT) {
				KickEvent kickEvent = (KickEvent) e;
				Channel gc = kickEvent.getChannel();
		
				text.write("<" + kickEvent.getWho() + "> has been kicked by "
						+ kickEvent.byWho() + " Reason(\""+kickEvent.getMessage()+"\")"
						, Color.RED);
			
				
			
		}else{
				text.write(e.getRawEventData(), Color.RED);
		}

	}


		
}//end Class	