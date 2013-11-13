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

import jerklib.ConnectionManager;
import jerklib.Session;
import jerklib.events.IRCEvent;
import jerklib.events.JoinCompleteEvent;
import jerklib.events.MessageEvent;
import jerklib.events.IRCEvent.Type;
import jerklib.listeners.IRCEventListener;
public class Tabs_head extends JPanel implements IRCEventListener {
	 private JTextField inputField;
	 public TextArea text;
	boolean notChat;
	 public Tabs_head(BorderLayout borderLayout, String type, final Session s) {
		// TODO Auto-generated constructor stub
		 super(borderLayout);
		 
		 text = new TextArea();
     	inputField= new JTextField();
     	
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
    	JList<String> list = new ChannelList();
    	notChat = false;
		
      
		JScrollPane scrollPane = new JScrollPane(list,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
		        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		add(scrollPane, BorderLayout.EAST);
		
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
		 
			s.addIRCEventListener(this);
		
	 }
	 

		@Override
		public void receiveEvent(IRCEvent e) {
		
			
			
	
			 if (e.getType() == Type.CHANNEL_MESSAGE)
			{
				MessageEvent me = (MessageEvent)e;
				
				text.write("<" + me.getNick() + ">"+ ":" + me.getMessage());
			}
			else if (e.getType() == Type.JOIN_COMPLETE)
			{
				final JoinCompleteEvent jce = (JoinCompleteEvent) e;
					
				/* say hello and version number */
				 inputField.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent b) {
						// TODO Auto-generated method stub
						inputField.setText("");
						text.write(b.getActionCommand());
						jce.getChannel().say( b.getActionCommand() );
					}
				}); 
				
			}
			else
			{
				if(notChat)
				text.write(e.getType() + " " + e.getRawEventData());
				
			}
		 
		 
		 
	}
}


