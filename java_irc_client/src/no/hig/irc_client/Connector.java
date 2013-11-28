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
	Prefs p = new Prefs();
	ProfileSettings profile = new ProfileSettings();
	manager = new ConnectionManager(profile.getProfile());
	System.out.println(p.getLastServer());
	System.out.println(p.getLastPort());
	session = manager.requestConnection(p.getLastServer(), p.getLastPort());
}
	public void quit(){
		manager.quit();
	}
	 public Session getSession() {
	return session;
	 }


	

}
