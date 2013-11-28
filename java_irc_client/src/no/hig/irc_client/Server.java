package no.hig.irc_client;

import java.io.Serializable;

public class Server implements Serializable {

	private int defaultPort = 6667;
	private String url;
	private String name;
	private int port;
	private String continent;
	private String state;
	private String network;
	
	Server(String serverUrl, String serverName, int serverPort, String serverContinent, String serverState, String serverNetwork ){
		url = serverUrl;
		name = serverName == null ? url : serverName;			//name set to url if empty
		
		if(serverPort < 1000){
			port = defaultPort;
		}else{
			port = serverPort;
		}
		continent = serverContinent;
		state = serverState;
		network = serverNetwork;
	}
	
	public void update(String serverUrl, String serverName, int serverPort, String serverContinent, String serverState, String serverNetwork){
		url = serverUrl;
		name = serverName == null ? url : serverName;			//name set to url if empty
		if(serverPort < 1000){
			port = defaultPort;
		}else{
			port = serverPort;
		}
		continent = serverContinent;
		state = serverState;
		network = serverNetwork;
	}
	
	public String getUrl(){
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getContinent() {
		return continent;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getNetwork() {
		return network;
	}

	public void setNetwork(String network) {
		this.network = network;
	}
}
