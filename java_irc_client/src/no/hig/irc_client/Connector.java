package no.hig.irc_client;

import jerklib.ConnectionManager;
import jerklib.Profile;
import jerklib.Session;


public class Connector  {
	private ConnectionManager manager;
	Session session ;

	
	public Connector(){
		
		/*
		 * ConnectionManager takes a Profile to use for new connections.
		 */
		ProfileSettings profile = new ProfileSettings();
	
		/*
		 * One instance of ConnectionManager can connect to many IRC networks.
		 * ConnectionManager#requestConnection(String) will return a Session object.
		 * The Session is the main way users will interact with this library and IRC
		 * networks
		 */
		
	
 
		/*
		 * JerkLib fires IRCEvents to notify users of the lib of incoming events
		 * from a connected IRC server.
		 */
	
		
	}
public void newConnection(){
	ProfileSettings profile = new ProfileSettings();
	manager = new ConnectionManager(profile.getProfile()); 
	session = manager.requestConnection("uk.quakenet.org");
}
	public void quit(){
		manager.quit();
	}
	 public Session getSession() {
	return session;
	 }


	

}
