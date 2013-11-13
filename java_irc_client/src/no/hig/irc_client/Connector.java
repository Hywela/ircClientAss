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


public class Connector  {
	private ConnectionManager manager;
	Session session ;

	
	public Connector(){
		;
		/*
		 * ConnectionManager takes a Profile to use for new connections.
		 */
		manager = new ConnectionManager(new Profile("hyw")); 
		/*
		 * One instance of ConnectionManager can connect to many IRC networks.
		 * ConnectionManager#requestConnection(String) will return a Session object.
		 * The Session is the main way users will interact with this library and IRC
		 * networks
		 */
		session = manager.requestConnection("irc.worldirc.org");
 
		/*
		 * JerkLib fires IRCEvents to notify users of the lib of incoming events
		 * from a connected IRC server.
		 */
	
		
	}


	 public Session getSession() {
	return session;
	 }


	

}
