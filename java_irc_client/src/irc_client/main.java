package irc_client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import irc_client.Language;
import irc_client.client;



//hei

public class main extends JFrame{
private client client;
private JFrame frame;
	private main(){
	 frame = new JFrame("");
	client=new client(frame);
		fileMenu();
		
		
		
	}
	
	
public void newTab(){
	 
	client.newTab("ddd", 0);
	client.newTab("ff", 1);
	client.newTab("dd", 2);
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
	 
	 public void fileMenu() {

         JMenuBar menuBar = new JMenuBar();

         // Add the menubar to the frame
         frame.setJMenuBar(menuBar);

         // Define and add two drop down menu to the menubar
         JMenu fileMenu = new JMenu(Language.getMsg("file"));
         JMenu editMenu = new JMenu(Language.getMsg("edit"));
         menuBar.add(fileMenu);
         menuBar.add(editMenu);

         // Create and add simple menu item to one of the drop down menu
         JMenuItem newAction = new JMenuItem(Language.getMsg("new"));
         JMenuItem openAction = new JMenuItem(Language.getMsg("open_file"));
         JMenuItem saveAction = new JMenuItem(Language.getMsg("save_file"));
         JMenuItem exitAction = new JMenuItem(Language.getMsg("exit"));
         JMenuItem genAction = new JMenuItem(Language.getMsg("export"));
         JMenuItem newItemAction = new JMenuItem(Language.getMsg("add"));
         JMenuItem deleteItemAction = new JMenuItem(Language.getMsg("delete"));

         // file menu buttons
         fileMenu.add(newAction);
         fileMenu.add(openAction);
         fileMenu.add(saveAction);
         fileMenu.addSeparator();
         fileMenu.add(genAction);
         fileMenu.add(exitAction);

         // edit menu buttons
         editMenu.add(newItemAction);
         editMenu.add(deleteItemAction);

         // file menu actions
         newAction.addActionListener(new ActionListener() {
                 public void actionPerformed(ActionEvent ae) {
                         
                       
                 }
         });

         openAction.addActionListener(new Load());

         saveAction.addActionListener(new Save());

         genAction.addActionListener(new SaveReport());

         exitAction.addActionListener(new ActionListener() {
                 public void actionPerformed(ActionEvent ae) {
                         System.exit(0);
                 }
         });

         // edit menu button actions
         newItemAction.addActionListener(new ActionListener() {
                 public void actionPerformed(ActionEvent ae) {
                       
                 }
         });

         deleteItemAction.addActionListener(new ActionListener() {
                 public void actionPerformed(ActionEvent ae) {
                         
                 }
         });

 }
}
