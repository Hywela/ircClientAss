package no.hig.irc_client;

import java.util.prefs.Preferences;

public class Prefs {
	private Preferences prefs;
	private String defaultServer;
	private int defaultPort;
	
    /**
     * Creates node and declares default values
     */
    public Prefs(){
    	//Create node to store prefs
    	prefs = Preferences.userRoot().node(this.getClass().getName());
    	
    	defaultServer = "uk.quakenet.org";
    	defaultPort = 6667;
    }
    
    /**
     * Saves last used server information to be used when requesting a connection
     * 
     * @param url
     * @param port
     */
    public void saveLastServer(String url, int port){
    	setLastServer(url);
    	setLastPort(port);
    }
    
    public String getLastServer(){
    	return prefs.get("lastServerURL", defaultServer);
    }
	public void setLastServer(String url){
		prefs.put("lastServerURL", url);
	}
	
	public int getLastPort(){
    	return prefs.getInt("lastPort", defaultPort);
    }
	public void setLastPort(int port){
		prefs.putInt("lastPort", port);
	}
	
	public String getUsername(){
    	return prefs.get("username", "");
    }
	public void setUsername(String username){
		prefs.put("username", username);
	}
	
	public String getRealName(){
    	return prefs.get("realName", "");
    }
	public void setRealName(String realName){
		prefs.put("realName", realName);
	}
	
	public String getPrimaryNick(){
    	return prefs.get("primaryNick", "");
    }
	public void setPrimaryNick(String primaryNick){
		prefs.put("primaryNick", primaryNick);
	}
	
	public String getAltNick(){
    	return prefs.get("altNick", "");
    }
	public void setAltNick(String altNick){
		prefs.put("altNick", altNick);
	}
	
	/**
	 * Saves all info about the user
	 * 
	 * @param username
	 * @param realName
	 * @param primaryNick
	 * @param altNick
	 */
	public void saveProfile(String username, String realName, String primaryNick, String altNick){
		prefs.put("username", username);
		prefs.put("realName", realName);
		prefs.put("primaryNick", primaryNick);
		prefs.put("altNick", altNick);
	}
	
    
}
