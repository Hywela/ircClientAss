package no.hig.irc_client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import jerklib.Channel;

public class ChannelList extends JList<Object> implements ActionListener {

	
	public ChannelList(Channel chan){
	
	DefaultListModel<String> listModel;
	listModel = new DefaultListModel<String>();
	listModel.addElement((String) chan.getName().toString());
	  Iterator<String> nicks = chan.getNicks().iterator();
      
      while (nicks.hasNext()){                //does the list contain anymore users?
              String nextNick;
              nextNick = nicks.next();        //next element in the list
              chan.addNick(nicks.toString());   
                     listModel.addElement((String) nextNick.toString());
              }	
	



setListData(listModel.toArray());
			
	
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
