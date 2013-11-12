package no.hig.irc_client;

import java.awt.List;
import java.util.Vector;

import javax.swing.text.Document;

import jerklib.ConnectionManager;
import jerklib.Profile;
import jerklib.Session;
import jerklib.events.*;
import jerklib.events.IRCEvent.Type;
import jerklib.listeners.IRCEventListener;


public class Connector  implements IRCEventListener{
	private ConnectionManager manager;
	Session session ;
	client  client;
	
	public Connector(client client){
		this.client = client;
		/*
		 * ConnectionManager takes a Profile to use for new connections.
		 */
		manager = new ConnectionManager(new Profile("scripy")); 
		/*
		 * One instance of ConnectionManager can connect to many IRC networks.
		 * ConnectionManager#requestConnection(String) will return a Session object.
		 * The Session is the main way users will interact with this library and IRC
		 * networks
		 */
		Session session = manager.requestConnection("irc.freenode.net");
 
		/*
		 * JerkLib fires IRCEvents to notify users of the lib of incoming events
		 * from a connected IRC server.
		 */
		session.addIRCEventListener(this);
	
	}

	@Override
	public void receiveEvent(IRCEvent e) {
		if (e.getType() == Type.CONNECT_COMPLETE)
		{
			
			e.getSession().join("#jerklib");
 
		}
		else if (e.getType() == Type.CHANNEL_MESSAGE)
		{
			MessageEvent me = (MessageEvent)e;
			
			System.out.println("<" + me.getNick() + ">"+ ":" + me.getMessage());
		}
		else if (e.getType() == Type.JOIN_COMPLETE)
		{
			JoinCompleteEvent jce = (JoinCompleteEvent) e;
 
			/* say hello and version number */
		//	jce.getChannel().say("Hello from Jerklib " + ConnectionManager.getVersion());
		}
		else
		{
			
			client.text.write(e.getType() + " " + e.getRawEventData());
			System.out.println(e.getType() + " " + e.getRawEventData());
		}
		
	}


	 public Session getSession() {
	return session;
	 }


	

}
