package no.hig.irc_client;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *Class to handle the list of Server ellements with 
 *functions for saving and loading the list of servers
 *to file
 *
 *Will try to load servers from file each time it is created
 * 
 * @author Uhu
 *
 */
public class ServerList {

	private ArrayList<Server> list = new ArrayList<Server>();

	/**
	 * Constructor tries to load the server list from file
	 */
	public ServerList() {
		loadList();
	}

	/**
	 * Deletes a list item at a selected index position
	 * 
	 * @param indexToDelete	The index number for the object to be deleted
	 */
	public void deleteListItem(int indexToDelete) {
		list.remove(indexToDelete);
		saveList();
	}

	/**
	 * Adds a new server to the list
	 * 
	 * @param server 	The server to be added
	 */
	void add(Server server) {
		list.add(server);
		saveList();
	}
	
	/**
	 * Adds a new server from base elements
	 * 
	 * @param serverName
	 * @param serverUrl
	 * @param serverPort
	 * @param serverContinent
	 * @param serverState
	 * @param serverNetwork
	 */
	public void addNewServer(String serverName, String serverUrl,
			int serverPort, String serverContinent, String serverState,
			String serverNetwork) {
		Server nServer = new Server(serverUrl, serverName, serverPort,
				serverContinent, serverState, serverNetwork);
		add(nServer);
	}

	/**
	 * Updates all the information of a server at a selected index position
	 * 
	 * 
	 * @param index		The index of the server that is to be changed
	 * @param serverUrl
	 * @param serverName
	 * @param serverPort
	 * @param serverContinent
	 * @param serverState
	 * @param serverNetwork
	 */
	void updateListItem(int index, String serverUrl, String serverName,
			int serverPort, String serverContinent, String serverState,
			String serverNetwork) {
		getServer(index).update(serverUrl, serverName, serverPort,
				serverContinent, serverState, serverNetwork);
		saveList();
	}

	/**
	 * Tries to load the server list from the file serverlist.bin
	 * in root folder
	 */
	private void loadList() {
		File f = new File("serverlist.bin");
		if (f.exists()) {		//if file exsists try to read
			try {
				ObjectInputStream ois = new ObjectInputStream(
						new FileInputStream(f));
				list.clear();
				try { 					//try to read objects from the file
					while (true) {
						Server server = (Server) ois.readObject();
						list.add(server);
					}
				} catch (EOFException eofe) {
					// end of file
				} catch (ClassCastException cce) {
					System.err.println("error wrong filetype");
				} catch (ClassNotFoundException cnfe) {
					System.err.println("lost base types.");
				} catch (IOException ioe) {
					System.err.println("error reading file");
				} finally {

				}
				ois.close();
			} catch (IOException ioe) {
				System.err.println("Feil på filhåndteringen under lesing");
			}
		}
	}

	/**
	 * Saves all servers into the file serverlist.bin
	 */
	private void saveList() {
		File f = new File("serverlist.bin");
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(f));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		for (int i = 0; i < list.size(); i++) {
			try {
				oos.writeObject(list.get(i));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			oos.close();
		} catch (IOException e) {
		}
	}

	/**
	 * returns a selected server
	 * 
	 * @param index	index of the server to be returned
	 * @return	return selected server object
	 */
	Server getServer(int index) {
		return list.get(index);
	}

	
	/**
	 * Creates an ArrayList<String> containing all server names
	 * 
	 * @return	Array list of the server names
	 */
	ArrayList<String> getNameList() {
		ArrayList<String> nameList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			nameList.add(list.get(i).getName());
		}
		return nameList;
	}

	/**
	 * 
	 * @return	Returns the length of the ArrayList of servers
	 */
	public int getLength() {
		return list.size();
	}
}
