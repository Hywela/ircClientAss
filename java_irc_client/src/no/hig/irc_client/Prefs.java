package no.hig.irc_client;

import java.awt.Color;
import java.util.prefs.Preferences;

public class Prefs {
	private Preferences prefs;
	private String defaultServer, defFont;
	private int defaultPort;
	private int defT_color,defN_color,defM_color;
	private int defSize;
	
    /**
     * Creates node and declares default values
     */
    public Prefs(){
    	//Create node to store prefs
    	prefs = Preferences.userRoot().node(this.getClass().getName());
    	
    	defaultServer = "uk.quakenet.org";
    	defaultPort = 6667;
    	defSize = 12;
    	defFont = "Verdana";
    	
    	//default colors
    	defT_color = Color.black.getRGB();
		defN_color = Color.blue.getRGB();
		defM_color = Color.DARK_GRAY.getRGB();
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
    
    public void saveSettings(int t_color, int n_color, int m_color, int size, String font){
    	prefs.putInt("t_color", t_color);
    	prefs.putInt("n_color", n_color);
    	prefs.putInt("m_color", m_color);
    	prefs.putInt("fontSize", size);
    	prefs.put("font", font);
    }
    
    public int getTcolor(){
    	return prefs.getInt("t_color", defT_color);
    }
    
    public int getNcolor(){
    	return prefs.getInt("n_color", defN_color);
    }
    
    public int getMcolor(){
    	return prefs.getInt("m_color", defM_color);
    }
    
    public int getFontSize(){
    	return prefs.getInt("fontSize", defSize);
    }
    
    public String getFont(){
    	return prefs.get("font", defFont);
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
