package no.hig.irc_client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;

public class ChannelList extends JList<String> implements ActionListener {

	String[] data = {"one", "two", "three", "four","one", "two", "three", "four","one", "two", "three", "four11111111", 
			"two", "three", "four","one", "two", "three", "four","one", "two", "three", 
			"four11111111", "two", "three", "four","one", "two", "three", "four","one", "two", "three", "four11111111"};
	public ChannelList(){
	
		setListData(data);
			
	
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
