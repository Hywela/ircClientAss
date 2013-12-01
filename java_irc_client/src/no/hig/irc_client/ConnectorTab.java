package no.hig.irc_client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import jerklib.Channel;
import jerklib.Session;
import jerklib.events.ChannelListEvent;
import jerklib.events.ConnectionLostEvent;
import jerklib.events.ErrorEvent;
import jerklib.events.IRCEvent;
import jerklib.events.IRCEvent.Type;
import jerklib.events.KickEvent;
import jerklib.events.ServerInformationEvent;
import jerklib.events.ServerVersionEvent;
import jerklib.listeners.IRCEventListener;
/**
 * takes care of the Connection and act as the main tab
 * @author hyw
 *
 */
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
				text.write(b.getActionCommand(), client.getSettings().getMyColor(),
						client.getSettings().getSize(), client.getSettings().getFont());
				if (b.getActionCommand().startsWith("/")) {

					String sw = b.getActionCommand().substring(1, 2);
					String message = b.getActionCommand().substring(3);
					switch (sw) {
					case "j": {

						client.joinChannel(message);
						break;
					}

					}
				}
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
		
				if(event.getNumberOfUser() > 100) // incase there are a Billion channels 
					//-on a server
				client.addToChanList(event.getChannelName());
			}
	 if (e.getType() == Type.CONNECT_COMPLETE) {
		text.write("Connection Complete", Color.GREEN,
				client.getSettings().getSize(), client.getSettings().getFont());
		//client.chanelList();	// if you want to start the chan list at start up
		}
	
	
		 else if (e.getType() == Type.ERROR) {
			ErrorEvent errorEvent = (ErrorEvent) e;
			text.write(e.getRawEventData(), Color.BLACK,
					client.getSettings().getSize(), client.getSettings().getFont());

		
		} else if (e.getType() == Type.SERVER_INFORMATION) {
			ServerInformationEvent errorEvent = (ServerInformationEvent) e;
			text.write(errorEvent.getRawEventData(), Color.BLACK,
					client.getSettings().getSize(), client.getSettings().getFont());

		
		} else if (e.getType() == Type.SERVER_VERSION_EVENT) {
			ServerVersionEvent errorEvent = (ServerVersionEvent) e;
			text.write(errorEvent.getHostName(), Color.BLACK,
					client.getSettings().getSize(), client.getSettings().getFont());
			text.write(errorEvent.getVersion(), Color.BLACK,
					client.getSettings().getSize(), client.getSettings().getFont());
		
		} else if (e.getType() == Type.CONNECTION_LOST) {
			ConnectionLostEvent errorEvent = (ConnectionLostEvent) e;
			text.write(errorEvent.getRawEventData(), Color.BLACK,
					client.getSettings().getSize(), client.getSettings().getFont());

		
		}
			else if (e.getType() == Type.KICK_EVENT) {
				KickEvent kickEvent = (KickEvent) e;
				Channel gc = kickEvent.getChannel();
		
				text.write("<" + kickEvent.getWho() + "> "+Language.getMsg("wasKickedBy")+" "
						+ kickEvent.byWho() + " "+Language.getMsg("reason")+"(\""+kickEvent.getMessage()+"\")"
						, Color.RED,client.getSettings().getSize(), client.getSettings().getFont());
			
				
			
		}else text.write(e.getRawEventData(), Color.BLACK,
				client.getSettings().getSize(), client.getSettings().getFont());

	}


		
}//end Class	
