package no.hig.irc_client;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTable;

public class ServerList extends JDialog {

	private ArrayList<Server> list = new ArrayList<Server>();
	private JTable table = new JTable();
	
	
	public ServerList(){
		System.out.println("Creating serverlist");
		loadList();
		System.out.println("Server List created");
	}
	
	public ServerList(final JFrame frame){
		loadList();
		
		
	}
	
	void add(Server server){
		list.add(server);
		saveList();
	}
	
	private void loadList(){
		File f = new File("serverlist.bin");
		if (f.exists()){
			System.out.println("File Exists");
			try {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
				list.clear();
				try {
					System.out.println("Reading stuff from file: ");
					while (true) {
						Server server = (Server)ois.readObject();
						System.out.println(server.getUrl());
						list.add(server);
						System.out.println("added to list");
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
		System.out.println("Saving List..");
		//print stuff to se whats up
		for(int i = 0; i < list.size(); i++){
			System.out.println(list.get(i).getUrl());
		}
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
		System.out.println("List saved");
	}
	
	Server getServer(int index){
		return list.get(index);
	}
}
