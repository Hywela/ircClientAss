package no.hig.irc_client;

import jerklib.ConnectionManager;
import jerklib.Profile;
import jerklib.Session;

/**
 * Connrctor Class
 * 
 * @author hyw
 * 
 */
public class Connector {
	private ConnectionManager manager;
	Session session;
	Prefs p;
	ProfileSettings profile;

	public Connector() {

	}
/**
 * Makes a new Connection
 **/
	public void newConnection() {
		p = new Prefs();
		/*
		 * ConnectionManager takes a Profile to use for new connections.
		 */
		profile = new ProfileSettings();
		manager = new ConnectionManager(profile.getProfile());
		/*
		 * One instance of ConnectionManager can connect to many IRC networks.
		 * ConnectionManager#requestConnection(String) will return a Session
		 * object. The Session is the main way users will interact with this
		 * library and IRC networks
		 */

		System.out.println(p.getLastServer());
		System.out.println(p.getLastPort());
		session = manager.requestConnection(p.getLastServer(), p.getLastPort());
		/*
		 * JerkLib fires IRCEvents to notify users of the lib of incoming events
		 * from a connected IRC server.
		 */
	}
/**
 * tells the manger to quit;
 */
	public void quit() {
		manager.quit();
	}
/**
 * returns a Session
 * @return
 */
	public Session getSession() {
		return session;
	}

}
