package no.hig.irc_client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 * Imports standard list of servers you get with mirc
 * 
 * @author Uhu
 *
 */
public class ImportServers implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser chooser = new JFileChooser(new File("."));
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		if (chooser.showOpenDialog(new JFrame()) == JFileChooser.CANCEL_OPTION)
			return;
		File f = chooser.getSelectedFile();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(f.getAbsolutePath()));
			String line = null;
			while ((line = reader.readLine()) != null) {
			    prosessLine(line);
			}
		} catch (IOException ioe) {
			System.err.println("Feil på filhåndteringen.");
		}
	}
	
	
	/**
	 * Extracts information out of a String line from the text file
	 * 
	 * @param line
	 */
	private void prosessLine(String line){
		Server s = new Server();
		if (line.contains("SERVER:")) {
			String[] clean= line.split("=");					//removes numbering
			String[] lineParts= clean[1].split(":");	
		    s.setName(lineParts[1]);				//Name = url
		    s.setUrl(lineParts[1]);					//URL
		    s.setPort(Integer.parseInt(lineParts[2].substring(0, 4)));	//Port
		    s.setNetwork(lineParts[3]);					//Network
		    if(lineParts[0].contains(", ")){
		    	String[] strPart = lineParts[0].split(", ");	//split for location data
		    	s.setContinent(strPart[0]);					//Continent
		    	s.setState(strPart[1].replace("SERVER", ""));					//State
		    }
		    
		    ServerList list = new ServerList();
		    list.add(s);
		    
		} else {
		    return;
		}
	}
}