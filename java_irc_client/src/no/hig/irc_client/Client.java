package no.hig.irc_client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.Serializable;
import java.util.List;
import java.util.Vector;

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
import jerklib.events.ErrorEvent;
import jerklib.events.IRCEvent;
import jerklib.events.MessageEvent;
import jerklib.events.NickListEvent;
import jerklib.events.QuitEvent;
import jerklib.events.IRCEvent.Type;
import jerklib.events.JoinCompleteEvent;
import jerklib.listeners.IRCEventListener;
import jerklib.tasks.TaskImpl;
import no.hig.irc_client.DeleteTabs;




public final class Client implements Serializable {

    private static final long serialVersionUID = 1L;
    private static Vector<String> pmVec ;
    private static class clientLoader {
        private static final Client INSTANCE = new Client();
    }

    private Client() {
        if (clientLoader.INSTANCE != null) {
            throw new IllegalStateException("Already instantiated");
        }
    }

    public static Client getInstance() {
        return clientLoader.INSTANCE;
    }

    @SuppressWarnings("unused")
    private Client readResolve() {
        return clientLoader.INSTANCE;
    }
    
    public void serFrame(JFrame frame){
    	pmVec = new Vector<String>();
    	  this.frame = frame;
          this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          b.addActionListener(new java.awt.event.ActionListener() {

              public void actionPerformed(java.awt.event.ActionEvent evt) {
                  
                  if (con == null){
                  	con = new Connector();
                  	session = con.getSession();
                  	connectTab();	
                  	}else{ session.close("close");}
                  
              }
          });
      
       
          this.frame.add(b,BorderLayout.NORTH);
          this.frame.add(pane);
          frame.setSize(new Dimension(700, 300));
          frame.setLocationRelativeTo(null);
          frame.setVisible(true);
    	
    	
    }



    


	Connector con = null; 
	Session session = null;
	private JFrame frame;
    private final JTabbedPane pane = new JTabbedPane();
    private final JButton b = new JButton("Connect");
    

    public Client(JFrame frame) {
    	
        this.frame = frame;
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        b.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
                if (con == null){
                	con = new Connector();
                	session = con.getSession();
                	connectTab();	
                	}else{ session.close("close");}
                
            }
        });
    
     
        this.frame.add(b,BorderLayout.NORTH);
        this.frame.add(pane);
        frame.setSize(new Dimension(700, 300));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }	

    public void newTab(Session sess, String channel) {
    		
    	 
  
    	
    	 Tabs p = new Tabs(new BorderLayout(),true, sess, channel);
            pane.add(channel,p);
            initTabComponent();  

        pane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
        

    
    }
    
    private void connectTab(){
    	
    	 Tabs p = new Tabs(new BorderLayout(), false, session, "");
         pane.add("Connector",p);
         initTabComponent();  
         
  
      
     pane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
    }
    
    private void initTabComponent() {
        pane.setTabComponentAt(pane.getTabCount()-1,
                 new DeleteTabs(pane));
    }


  
      
	
public void joinChannel(String chan){
	
	 session.join(chan);
	 newTab(session, chan );
}
  
public void newPrivatTab(Session s, String nick){
  	 PrivateMessage p = new PrivateMessage(new BorderLayout(), s, nick);
     pane.add(nick,p);
     initTabComponent();  
     pmVec.add(nick);
 pane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
	
}
public void deleteNick(String s){
	
	for (int i = 0; i < pmVec.size(); i++){
		if(pmVec.get(i).equals(s))
			pmVec.remove(i);
	}
	
}
public boolean findNick(String s){
	for (int i = 0; i < pmVec.size(); i++){
		if(pmVec.get(i).equals(s))
			return true;
	}
	return false;
}
    
}