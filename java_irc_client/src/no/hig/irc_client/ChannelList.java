package no.hig.irc_client;

import javax.swing.JList;

public class ChannelList extends JList<String> {

	String[] data = {"one", "two", "three", "four","one", "two", "three", "four","one", "two", "three", "four11111111", 
			"two", "three", "four","one", "two", "three", "four","one", "two", "three", 
			"four11111111", "two", "three", "four","one", "two", "three", "four","one", "two", "three", "four11111111"};
	public ChannelList(){
	
		setListData(data);
			
	
	}
}
