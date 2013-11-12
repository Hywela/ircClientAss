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
import jerklib.Session;
import no.hig.irc_client.tabs;

public class client  {
	 private JTextField inputField;
	 public TextArea text;
private JFrame frame;
    private final JTabbedPane pane = new JTabbedPane();
    private final JButton b = new JButton("Rename Button");

    public client(String title) {
        
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

    public void newTab(String title, int index, String type) {
     text = new TextArea();
        	
        	   JPanel p = new JPanel(new BorderLayout());
        	   
        	   
        	   
        	   if(type == "connector"){
        	        
        		   
               	
           		inputField= new JTextField("Input");
                
       text.setEditable(false);
       JScrollPane scrollPane = new JScrollPane(text,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
		        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
       
       
           		//  split.add(inputField,JSplitPane.BOTTOM);
           		
                  p.add(scrollPane,BorderLayout.CENTER);
                  
                 //  p.add(,BorderLayout.CENTER);
                   p.add(inputField,BorderLayout.SOUTH);
                 
                   }
        	   
        	   
        	if(type == "chat"){
        
        	
        	
        	JList<String> list = new ChannelList();
        	
    		inputField= new JTextField("Input");
          
    		JScrollPane scrollPane = new JScrollPane(list,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
    		        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    		p.add(scrollPane, BorderLayout.EAST);
    		
    		JScrollPane scrollPane2 = new JScrollPane(text,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
    		        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    		///p.add(scrollPane, BorderLayout.EAST);
    		
    		  JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
    				  scrollPane, scrollPane2);
    		//  split.add(inputField,JSplitPane.BOTTOM);
         //   p.add(list,BorderLayout.EAST);
    		  p.add(split);
          //  p.add(,BorderLayout.CENTER);
            p.add(inputField,BorderLayout.SOUTH);
          
            }
            pane.add(title,p);
       
            initTabComponent(index);
        
        pane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
        
       
        frame.setSize(new Dimension(700, 300));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
    }



    private void initTabComponent(int i) {
        pane.setTabComponentAt(pane.getTabCount()-1,
                 new tabs(pane));
    }
    
}