package no.hig.irc_client;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import jerklib.Channel;
import jerklib.ConnectionManager;
import jerklib.Session;
import jerklib.events.IRCEvent;
import jerklib.events.JoinCompleteEvent;
import jerklib.events.MessageEvent;
import jerklib.events.IRCEvent.Type;
import jerklib.listeners.IRCEventListener;
public class Tabs_head extends JPanel {
	 private JTextField inputField;
	 public TextArea text;
	 String channel;
	 Channel chan;
	boolean notChat;
	 public Tabs_head(BorderLayout borderLayout, String type,  Session s, final String channel) {
		// TODO Auto-generated constructor stub
		 super(borderLayout);
		 
		 text = new TextArea();
     	inputField= new JTextField();
     
     	
     	
     	this.channel = channel;
     	 chan = new Channel(channel, s);
		 if(type == "connector"){
  		
  		   notChat = true;
         	
  	
          
     		text.setEditable(false);
     		JScrollPane scrollPane = new JScrollPane(text,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
	        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
 
 
     		//  split.add(inputField,JSplitPane.BOTTOM);
     		
            add(scrollPane,BorderLayout.CENTER);
            
           //  p.add(,BorderLayout.CENTER);
             add(inputField,BorderLayout.SOUTH);
           
             }
    	
    	
		 if(type == "chat"){	  
			 s.join(channel);
    	 ChannelList list = new ChannelList(chan);
    	notChat = false;
		
      	 text.write("Joining Channel : "+ channel);
      	 
      	text.setEditable(false);
 	
      	 
		JScrollPane scrollPane = new JScrollPane(list,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
		        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		
		JScrollPane scrollPane2 = new JScrollPane(text,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
		        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		///p.add(scrollPane, BorderLayout.EAST);
		
		  JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				  scrollPane, scrollPane2);
		//  split.add(inputField,JSplitPane.BOTTOM);
     //   p.add(list,BorderLayout.EAST);
		 add(split);
      //  p.add(,BorderLayout.CENTER);
        add(inputField,BorderLayout.SOUTH);
      
        }
		 inputField.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent b) {
					// TODO Auto-generated method stub
					inputField.setText("");
					text.write(b.getActionCommand());
					chan.say(b.getActionCommand());
				}
			}); 
		s.addIRCEventListener(new IRCEventListener() {
			
			@Override
			public void receiveEvent(IRCEvent e) {
				// TODO Auto-generated method stub
				 if (e.getType() == Type.CHANNEL_MESSAGE)
					{    
					 MessageEvent me = (MessageEvent)e;
					 Channel gc = me.getChannel();
					 if(channel.equals( gc.getName())){
						 text.write("<"+me.getNick()+"> "+me.getMessage());}
				 }
				 
				 if(notChat)
					 text.write(e.getType() + " " + e.getRawEventData());
		}
		});
		
	 }	 

	public void setText(String tx){
		
	}
		 
	

}


