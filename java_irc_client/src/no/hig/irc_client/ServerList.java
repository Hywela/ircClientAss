package no.hig.irc_client;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ServerList{

	private ArrayList<Server> list = new ArrayList<Server>();
	public ServerList(){
		loadList();
	}
	
	public void deleteListItem(int indexToDelete){
		list.remove(indexToDelete);
		saveList();
	}
	
	void add(Server server){
		list.add(server);
		saveList();
	}
	
	public void addNewServer(String serverName, String serverUrl, int serverPort, String serverContinent, String serverState, String serverNetwork){
		Server nServer = new Server(serverUrl, serverName, serverPort, serverContinent, serverState, serverNetwork);
		add(nServer);
	}
	
	void updateListItem(int index, String serverUrl, String serverName, int serverPort, String serverContinent, String serverState, String serverNetwork){
		getServer(index).update(serverUrl, serverName, serverPort, serverContinent, serverState, serverNetwork);
		saveList();
	}
	
	private void loadList(){
		File f = new File("serverlist.bin");
		if (f.exists()){
			try {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
				list.clear();
				try {
					while (true) {
						Server server = (Server)ois.readObject();
						list.add(server);
					}
				} catch (EOFException eofe) {
					//end of file
				} catch (ClassCastException cce) {
					System.err.println ("error wrong filetype");
				} catch (ClassNotFoundException cnfe) {
					System.err.println ("lost base types.");
				} catch (IOException ioe) {
					System.err.println ("error reading file");
				} finally {
					
				}
				ois.close();
			} catch (IOException ioe) {
				System.err.println("Feil på filhåndteringen under lesing");
			}
		}
	}
	
	private void saveList(){
		File f = new File("serverlist.bin");
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(f));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		for (int i=0; i<list.size(); i++){
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
	
	Server getServer(int index){
		return list.get(index);
	}
	
	ArrayList<String> getNameList(){
		ArrayList<String> nameList = new ArrayList<String>();
		for(int i = 0; i < list.size(); i++){
			nameList.add(list.get(i).getName());
		}
		return nameList;
	}	
}
