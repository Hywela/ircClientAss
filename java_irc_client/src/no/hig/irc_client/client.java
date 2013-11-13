package no.hig.irc_client;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import jerklib.Channel;
import jerklib.Session;
import jerklib.events.IRCEvent;
import jerklib.events.MessageEvent;
import jerklib.events.IRCEvent.Type;
import jerklib.events.JoinCompleteEvent;
import jerklib.listeners.IRCEventListener;
import no.hig.irc_client.tabs;

public class client  implements IRCEventListener    {
	
	
	 Connector con = null; 
	 Session session = null;
	 private JFrame frame;
    private final JTabbedPane pane = new JTabbedPane();
    private final JButton b = new JButton("Rename Button");
    
    public client() {
        
    }
    public client(JFrame frame) {
        this.frame = frame;
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        b.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                int selectedIndex = pane.getSelectedIndex();
                pane.setTitleAt(selectedIndex, "Aa");
            }
        });
        this.frame.add(b,BorderLayout.NORTH);
        this.frame.add(pane);
    }	

    public void newTab(String type, String channel) {
    	
    
    	
    
    	
    		if(type=="connector"){
    				
    			 con = new Connector();
    			 session = con.getSession();
    			 session.addIRCEventListener(this);
    	    }
    		
   
    	 
     if (session!= null){
    	
    	 Tabs_head p = new Tabs_head(new BorderLayout(), type, session, channel);
            pane.add(channel,p);
            initTabComponent();
         
        pane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
        
       
        frame.setSize(new Dimension(700, 300));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
     }
     
    }

    private void initTabComponent() {
        pane.setTabComponentAt(pane.getTabCount()-1,
                 new tabs(pane));
    }
	

	 
	public void receiveEvent(IRCEvent e) {
		
		
		 if(e.getType() == Type.CONNECT_COMPLETE) {                
			

		 }
		 if (e.getType() == Type.CHANNEL_MESSAGE)
		{   
			MessageEvent me = (MessageEvent)e;
			 Channel gc = me.getChannel();  
		
			//}
		
			}
		else if (e.getType() == Type.JOIN_COMPLETE)
		{
			
		
			/* say hello and version number */
		}
		else
		{
		
			
		}
	}
    
}