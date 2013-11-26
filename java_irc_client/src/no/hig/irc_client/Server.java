package no.hig.irc_client;

import java.io.Serializable;

public class Server implements Serializable {

	String url;
	String name;
	int port;
	String continent;
	String state;
	String network;
	
	Server(String serverUrl, String serverName, int serverPort, String serverContinent, String serverState, String serverNetwork ){
		url = serverUrl;
		name = serverName == null ? url : serverName;			//name set to url if empty
		port = serverPort;
		continent = serverContinent;
		state = serverState;
		network = serverNetwork;
	}
	
	String getUrl(){
		return url;
	}
}
