	package no.hig.irc_client;
	
	
		import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
	
		import jerklib.Channel;
import jerklib.Session;
import jerklib.events.ErrorEvent;
import jerklib.events.IRCEvent;
import jerklib.events.MessageEvent;
import jerklib.events.IRCEvent.Type;
import jerklib.events.QuitEvent;
import jerklib.tasks.TaskImpl;
		
		public class PrivateMessage  extends JPanel {
			 private JTextField inputField;
			 public TextArea text;
			 
			 public String pmNick;
			
	
			
			boolean notChat;
			 public PrivateMessage(BorderLayout borderLayout,   final Session s, final String pmNick) {
				// TODO Auto-generated constructor stub
				 super(borderLayout);
			
				 text = new TextArea();
		     	 inputField= new JTextField();
		     
		     	
		     	
		     	 this.pmNick = pmNick;
		    
				
		    
		    	notChat = false;
		 
		      	text.write("Joining Private message with  : "+ pmNick, Color.BLUE);
		      	text.setEditable(false);
	     	
		      	
		      	JScrollPane scrollPane = new JScrollPane(text,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
		        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	     		scrollPane.setAutoscrolls(true);
	     	

	     		
	            add(scrollPane,BorderLayout.CENTER);
	          
	            add(inputField,BorderLayout.SOUTH);
				
			
		      
		        
				 inputField.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent b) {
							// TODO Auto-generated method stub
							inputField.setText("");
							text.write(b.getActionCommand(),Color.BLACK );
							
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
						
							s.sayPrivate(pmNick, b.getActionCommand());
						}
				}); 
	 
				 s.onEvent(new TaskImpl("PRIVATE_MESSAGE")
					{
						public void receiveEvent(IRCEvent e)
						{
							System.out.println("ddd");
							Client client = null;
							client = Client.getInstance();
			           MessageEvent pm = (MessageEvent)e; //message event
			           if(pm.getNick().equals(pmNick)){
			        	  		text.write(e.getType() + " " + pm.getMessage() , Color.BLACK); 
			           } 
        	
						}
						
					},Type.PRIVATE_MESSAGE);
				
				
			 }	 
	
			public void newTab(){
				
			}
			public void setText(String tx){
			
			}
			public void joinChannel(String chan){
				
			
				
			}
			
		}
	
	
	
