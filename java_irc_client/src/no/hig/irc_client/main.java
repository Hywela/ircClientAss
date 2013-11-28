package no.hig.irc_client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import no.hig.irc_client.Language;
import no.hig.irc_client.Client;
import jerklib.Session;


//hei

public class main extends JFrame{
private int tabcount=0;
private Client client;
private JFrame frame;
	private main(){
	 frame = new JFrame("");
	client = client.getInstance();
	client.serFrame(frame);
		fileMenu();
		
	}
	
	
public void newTab(){

	
}
public void newChannelTab(){
	client.joinChannel("#test_hywel");

}
	
	 public static void main(String[] args) {
		 
	     Language.setLocale(args);
	        //Schedule a job for the event dispatch thread:
	        //creating and showing this application's GUI.
	        SwingUtilities.invokeLater(new Runnable(){
	            public void run(){
	                //Turn off metal's use of bold fonts
	             UIManager.put("swing.boldMetal", Boolean.FALSE);
	              new main().newTab();
	             
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
		
		/**JFrame frame = new JFrame("Server List");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        //Create and set up the content pane.
        ServerListLayout gui = new ServerListLayout();
        //gui.setOpaque(true); //content panes must be opaque
        frame.setContentPane(gui);
        frame.setLocation(300, 300);
        frame.pack();
        frame.setVisible(true);
        **/
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
         JMenuItem conAction = new JMenuItem("newConnection");
         JMenuItem saveAction = new JMenuItem(Language.getMsg("save_file"));
         JMenuItem exitAction = new JMenuItem(Language.getMsg("exit"));
         JMenuItem newItemAction = new JMenuItem(Language.getMsg("add"));
         JMenuItem deleteItemAction = new JMenuItem(Language.getMsg("delete"));

         // file menu buttons
         fileMenu.add(serverListAction);
         fileMenu.add(channelListAction);
         fileMenu.add(conAction);
         fileMenu.add(saveAction);
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
            	 
             }
     });
         
         conAction.addActionListener(new ActionListener(){
        	  public void actionPerformed(ActionEvent ae) {
        		  //newChannelTab();
				  client.chanelList();
        	  }
        	 
        	 
     });

         saveAction.addActionListener(new ActionListener(){
       	  public void actionPerformed(ActionEvent ae) {
    		  
    	  }
      });
       	  
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
