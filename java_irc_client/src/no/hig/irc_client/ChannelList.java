package no.hig.irc_client;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import jerklib.Channel;
import jerklib.Session;
import jerklib.events.NickListEvent;

public class ChannelList extends JList<Object> implements ActionListener {

		DefaultListModel<String> listModel;
	public ChannelList(){
		
	

	listModel = new DefaultListModel<String>();

	

setListData(listModel.toArray());
			
	
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}


public void setNick(String nick){
	
	listModel.addElement(nick);
}
}