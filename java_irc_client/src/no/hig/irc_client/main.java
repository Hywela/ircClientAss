package no.hig.irc_client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;


//hei

public class main extends JFrame{
private int tabcount=0;
private Client client;
private JFrame frame;
Prefs p;
	private main(){
	 frame = new JFrame("");
	client = client.getInstance();
	client.serFrame(frame);
		fileMenu();
		p = new Prefs();

		if( p.getUsername().equals("")){
			profileMenu();
		}
	}	
public void newTab(){

	
}
public void ChannelList(){
	client.chanelList();
}
	 public static void main(String[] args) {
		 
	     Language.setLocale(args);
	        //Schedule a job for the event dispatch thread:
	        //creating and showing this application's GUI.
	        SwingUtilities.invokeLater(new Runnable(){
	            public void run(){
	                //Turn off metal's use of bold fonts
	             UIManager.put("swing.boldMetal", Boolean.FALSE);
	              new main();
	             
	            }
	        });
	    }
	 
	public void profileMenu(){
		ProfileSettings gui = new ProfileSettings(main.this);
		gui.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		gui.setSize(460, 200);
		gui.setLocation(300,300);
		gui.setVisible(true);
	 }
	
	public void openServerList(){
		
		ServerListLayout gui = new ServerListLayout(main.this);
		gui.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		gui.setSize(460, 225);
		gui.setLocation(300,300);
		gui.setVisible(true);
	}

	
	 public void fileMenu() {

         JMenuBar menuBar = new JMenuBar();

         // Add the menubar to the frame
         frame.setJMenuBar(menuBar);

         // Define and add two drop down menu to the menubar
         JMenu fileMenu = new JMenu(Language.getMsg("file"));
         JMenu editMenu = new JMenu(Language.getMsg("edit"));
     //    JMenu connectorMenu = new JMenu(Language.getMsg("New Connection"));
         menuBar.add(fileMenu);
         menuBar.add(editMenu);

         // Create and add simple menu item to one of the drop down menu
         JMenuItem serverListAction = new JMenuItem(Language.getMsg("serverList"));
         JMenuItem channelListAction = new JMenuItem(Language.getMsg("channelList"));
         JMenuItem profileSettingsAction = new JMenuItem(Language.getMsg("profileSettings"));
         JMenuItem conAction = new JMenuItem(Language.getMsg("Settings"));
         JMenuItem importServers = new JMenuItem(Language.getMsg("importServers"));
         JMenuItem exitAction = new JMenuItem(Language.getMsg("exit"));
         JMenuItem newItemAction = new JMenuItem(Language.getMsg("add"));
         JMenuItem deleteItemAction = new JMenuItem(Language.getMsg("delete"));

         // file menu buttons
         fileMenu.add(serverListAction);
         fileMenu.add(channelListAction);
         fileMenu.add(conAction);
         fileMenu.add(importServers);
         fileMenu.addSeparator();
         fileMenu.add(exitAction);

         // edit menu buttons
         editMenu.add(profileSettingsAction);
         editMenu.add(newItemAction);
         editMenu.add(deleteItemAction);

         // file menu actions
         serverListAction.addActionListener(new ActionListener() {
                 public void actionPerformed(ActionEvent ae) {
                	 openServerList();
                	 //openNewServerWindow();
                 }
         });

         channelListAction.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent ae) {
            	 ChannelList();
             }
     });
         
         conAction.addActionListener(new ActionListener(){
        	  public void actionPerformed(ActionEvent ae) {
        		 
        		  client.settings();
        	  }
        	 
        	 
     });

         importServers.addActionListener(new ImportServers());
       	  
         exitAction.addActionListener(new ActionListener() {
                 public void actionPerformed(ActionEvent ae) {
                         System.exit(0);
                 }
         });

         // edit menu button actions
         profileSettingsAction.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent ae) {
					profileMenu();	
             }
     });

 }
}
