package no.hig.irc_client;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import jerklib.Channel;
import jerklib.ConnectionManager;
import jerklib.Session;
import jerklib.events.ErrorEvent;
import jerklib.events.IRCEvent;
import jerklib.events.JoinCompleteEvent;
import jerklib.events.JoinEvent;
import jerklib.events.MessageEvent;
import jerklib.events.NickListEvent;
import jerklib.events.IRCEvent.Type;
import jerklib.events.PartEvent;
import jerklib.events.QuitEvent;
import jerklib.listeners.IRCEventListener;
import jerklib.tasks.TaskImpl;
public class Tabs extends JPanel {
	 private JTextField inputField;
	 public TextArea text;
	 
	 public String channel;
	 Channel chan; 
	 DefaultListModel<String> listModel;
	 JList<DefaultListModel<String>> list;
	
	boolean notChat;
	 public Tabs(BorderLayout borderLayout, boolean type,   final Session s, final String channel) {
		// TODO Auto-generated constructor stub
		 super(borderLayout);
		 listModel = new DefaultListModel<String>();
		 text = new TextArea();
     	 inputField= new JTextField();
     
     	
     	
     	 this.channel = channel;
     	 chan = new Channel(channel, s);
		 if(type == false){
  		
  		   notChat = true;
          
     		text.setEditable(false);
     		JScrollPane scrollPane = new JScrollPane(text,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
	        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
 
 
     		//  split.add(inputField,JSplitPane.BOTTOM);
     		
            add(scrollPane,BorderLayout.CENTER);
            
           //  p.add(,BorderLayout.CENTER);
            add(inputField,BorderLayout.SOUTH);
           
             }
    	
    	
		 if(type == true){	  
			
    
    	notChat = false;
    	list = new JList(listModel);
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
					
					System.out.println(b.getActionCommand());
					
					 if (b.getActionCommand().startsWith("/")){
						
					 String sw = b.getActionCommand().substring(1, 2);
					 String message = b.getActionCommand().substring(3); 
					switch(sw){
					case "j":{
							Client client = null;
						client = client.getInstance();
					
						client.joinChannel(message);
						break;
					}
					
					}
					
					
				}else 
					chan.say(b.getActionCommand());
				}
			}); 
		 
		 
	
		 
		 s.onEvent(new TaskImpl("NICK_LIST_EVENT")
			{
				public void receiveEvent(IRCEvent e)
				{
						 NickListEvent ne = ( NickListEvent ) e;
						 Channel gc = ne.getChannel();
						 if(channel.equals( gc.getName())){
							List<String> players = ne.getNicks();
							for (String nick : players )listModel.addElement(nick); 
							
						}
				}
			},Type.NICK_LIST_EVENT);
		 
		 s.onEvent(new TaskImpl("QUIT")
			{
				public void receiveEvent(IRCEvent e)
				{ QuitEvent quitEvent = (QuitEvent)e;
			
				if (listModel.getSize() > quitEvent.getChannelList().size()) {
					for(int i = 0; i>listModel.getSize(); i++){
						text.write(quitEvent.getNick() + "has quit the Channel");
						if( listModel.get(i)==quitEvent.getNick()){
							listModel.remove(i);
							}
						}
					}
				}
				
			},Type.QUIT);
		 
		 s.onEvent(new TaskImpl("JOIN")
			{
				public void receiveEvent(IRCEvent e)
				{  JoinEvent joinEvent = (JoinEvent)e;
				
				 Channel gc = joinEvent.getChannel();
				 if(channel.equals( gc.getName())){
					 text.write("<"+joinEvent.getNick() + "> has joined the "+gc.getName() );
							listModel.addElement(joinEvent.getNick());
					}
				
				}
			},Type.JOIN);
		 
		 s.onEvent(new TaskImpl("ERROR")
			{
				public void receiveEvent(IRCEvent e)
				{
					
					 ErrorEvent errorEvent = (ErrorEvent)e;
					 text.write(e.getRawEventData());
                      	
						}
				
			},Type.ERROR);
		 
		 
		 s.onEvent(new TaskImpl("PART")
			{
				public void receiveEvent(IRCEvent e)
				{
					
					 PartEvent partEvent = (PartEvent)e;
                     Channel gc = partEvent.getChannel();
                     if(channel.equals( gc.getName())){
                    		
               
        					for(int i = 0; i < listModel.getSize(); i++){
        						if( listModel.get(i).toString().equals(partEvent.getWho())){
        							text.write("<"+partEvent.getWho() + "> has quit the "+gc.getName());
        							
        								listModel.remove(i);}
        						}
						}
                 		
                     }
				
			},Type.PART);
		 
		 s.onEvent(new TaskImpl("CHANNEL_MESSAGE")
			{
				public void receiveEvent(IRCEvent e)
				{
					 MessageEvent me = (MessageEvent)e;
					 Channel gc = me.getChannel();
					 if(channel.equals( gc.getName())){
						 
						 if (!me.getMessage().startsWith("/")){
						 text.write("<"+me.getNick()+"> "+me.getMessage());
						 }
					 }
				 
				 if(notChat)
					 text.write(e.getType() + " " + e.getRawEventData());         
		              
                  	
						}
				
			},Type.CHANNEL_MESSAGE);
	
		 s.onEvent(new TaskImpl("CONNECT_COMPLETE")
			{
				public void receiveEvent(IRCEvent e)
				{
					               
		              System.out.println("CONNECT");
                     	
						}
				
			},Type.CONNECT_COMPLETE);
		 
		 s.onEvent(new TaskImpl("JOIN_COMPLETE")
			{
				public void receiveEvent(IRCEvent e)
				{
					
					  System.out.println("join");
			    	  JoinCompleteEvent jce = (JoinCompleteEvent)e;     
			    	
                      	
						}
				
			},Type.JOIN_COMPLETE);
		
		
	 }	 

	public void newTab(){
		
	}
	public void setText(String tx){
		 text.write(tx);
	}
	public void joinChannel(String chan){
		
	
		
	}	 
	

}


