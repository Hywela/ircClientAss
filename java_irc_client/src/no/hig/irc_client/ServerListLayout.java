package no.hig.irc_client;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

@SuppressWarnings("serial")
public class ServerListLayout extends JDialog{
	
	private boolean serverAdded;
	private boolean newChanges;
	private int changedField;
	private boolean listenForChange;
	private JList list;
	private JButton cancel;
	private JButton addServer;
	private JButton submit;
	private JButton delete;
	private ServerList serverList;
	
	JLabel warningLabel;
	JLabel urlLabel;
	JLabel nameLabel;
	JLabel portLabel;
	JLabel continentLabel;
	JLabel stateLabel;
	JLabel networkLabel;
	JTextField urlField;
	JTextField nameField;
	JTextField portField;
	JTextField continentField;
	JTextField stateField;
	JTextField networkField;
	
    public ServerListLayout(final JFrame frame) {
    	newChanges = false;
    	changedField = -1;
    	layoutSetup();
    	
    	
    	//Listen for selections in the list and refresh the text boxes with corrext information
		list.addListSelectionListener(
				new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent event){
						if(serverAdded){
							list.setSelectedIndex(serverList.getLength()-1);
							serverAdded = false;
						}
						if(list.getSelectedIndex() >= 0){
							changedField = list.getSelectedIndex();			//Save the list item that have been changed
							updateTextBoxes(changedField);
							submit.setEnabled(true); 	//when an item is selected Activate submit and delete button
							delete.setEnabled(true);
						}else{
							updateTextBoxes(changedField);
							list.setEnabled(true);
						}
					}
				}
		);
		changeListenersSetup(); 		//Listen for changes to the text fields 
		buttonListenerSetup();			//Listen for button pushes
    }

    private void changeListenersSetup(){
    	// Listen for changes in the text
    			nameField.getDocument().addDocumentListener(new DocumentListener() {
    			  public void changedUpdate(DocumentEvent e) {
    				textFieldsChanged();
    			  }
    			  public void removeUpdate(DocumentEvent e) {
    				textFieldsChanged();
    			  }
    			  public void insertUpdate(DocumentEvent e) {
    			    textFieldsChanged();
    			  }
    			});
    			
    			// Listen for changes in the text
    			urlField.getDocument().addDocumentListener(new DocumentListener() {
    			  public void changedUpdate(DocumentEvent e) {
    				textFieldsChanged();
    			  }
    			  public void removeUpdate(DocumentEvent e) {
    				textFieldsChanged();
    			  }
    			  public void insertUpdate(DocumentEvent e) {
    			    textFieldsChanged();
    			  }
    			});
    			
    			// Listen for changes in the text
    			portField.getDocument().addDocumentListener(new DocumentListener() {
    			  public void changedUpdate(DocumentEvent e) {
    				textFieldsChanged();
    			  }
    			  public void removeUpdate(DocumentEvent e) {
    				textFieldsChanged();
    			  }
    			  public void insertUpdate(DocumentEvent e) {
    			    textFieldsChanged();
    			  }
    			});
    			
    			// Listen for changes in the text
    			continentField.getDocument().addDocumentListener(new DocumentListener() {
    			  public void changedUpdate(DocumentEvent e) {
    				textFieldsChanged();
    			  }
    			  public void removeUpdate(DocumentEvent e) {
    				textFieldsChanged();
    			  }
    			  public void insertUpdate(DocumentEvent e) {
    			    textFieldsChanged();
    			  }
    			});
    			
    			// Listen for changes in the text
    			stateField.getDocument().addDocumentListener(new DocumentListener() {
    			  public void changedUpdate(DocumentEvent e) {
    				textFieldsChanged();
    			  }
    			  public void removeUpdate(DocumentEvent e) {
    				textFieldsChanged();
    			  }
    			  public void insertUpdate(DocumentEvent e) {
    			    textFieldsChanged();
    			  }
    			});
    			
    			// Listen for changes in the text
    			networkField.getDocument().addDocumentListener(new DocumentListener() {
    			  public void changedUpdate(DocumentEvent e) {
    				textFieldsChanged();
    			  }
    			  public void removeUpdate(DocumentEvent e) {
    				textFieldsChanged();
    			  }
    			  public void insertUpdate(DocumentEvent e) {
    			    textFieldsChanged();
    			  }
    			});
    }
    
    //Fields have changed, call this function
	private void textFieldsChanged(){
		if(listenForChange && changedField >= 0){ 	//If the user changed the field and not the field updater
			submit.setText(Language.getMsg("saveChanges"));	//Change the connect button to save
			newChanges = true;
			list.setEnabled(false);							//Disable the list until changes is saved
			warningLabel.setVisible(true);					//Show notification
			warningLabel.setText(serverList.getServer(changedField).getName()+ " " + "Was changed");
		}
	}
		
    
	//Update text boxes with new information
	private void updateTextBoxes(int serverIndex){
    	listenForChange = false;
    	if(serverIndex < 0 || serverIndex > serverList.getLength()-1){
    		nameField.setText("");
	    	urlField.setText("");
	    	portField.setText("");
	    	continentField.setText("");
	    	stateField.setText("");
	    	networkField.setText("");
    	}else{
    		Server server = serverList.getServer(serverIndex);
	    	nameField.setText(server.getName());
	    	urlField.setText(server.getUrl());
	    	portField.setText(""+server.getPort());
	    	continentField.setText(server.getContinent());
	    	stateField.setText(server.getState());
	    	networkField.setText(server.getNetwork());
    	}
    	listenForChange = true;
    }
    
	//Set up the server list layout
    private void layoutSetup(){
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
      //Labels
        urlLabel = new JLabel(Language.getMsg("serverURL"));
		nameLabel = new JLabel(Language.getMsg("serverName"));
		portLabel = new JLabel(Language.getMsg("port"));
		continentLabel = new JLabel(Language.getMsg("continent"));
		stateLabel = new JLabel(Language.getMsg("state"));
		networkLabel = new JLabel(Language.getMsg("network"));
        warningLabel = new JLabel("THIS IS A WARNING");
        warningLabel.setForeground(Color.RED);
        warningLabel.setVisible(false);
		
		c.anchor = GridBagConstraints.NORTHEAST;
		c.insets = new Insets(3,5,0,0);
		c.weightx = 0;
		c.gridx = 1;
		c.gridy = 0;
		add(nameLabel, c);
		c.gridy = 1;
		add(urlLabel, c);
		c.gridy = 2;
		add(portLabel, c);
		c.gridy = 3;
		add(continentLabel, c);
		c.gridy = 4;
		add(stateLabel, c);
		c.gridy = 5;
		add(networkLabel, c);
		c.gridy = 6;
		c.gridx = 2;
		c.anchor = GridBagConstraints.CENTER;
		add(warningLabel, c);
	  //Text Fields
		
		urlField = new JTextField(32);
		urlField.setToolTipText(Language.getMsg("serverURL"));
		nameField = new JTextField(32);
		nameField.setToolTipText(Language.getMsg("serverName"));
		portField = new JTextField(32);
		portField.setToolTipText(Language.getMsg("port"));
		continentField = new JTextField(32);
		continentField.setToolTipText(Language.getMsg("continent"));
		stateField = new JTextField(32);
		stateField.setToolTipText(Language.getMsg("state"));
		networkField = new JTextField(32);
		networkField.setToolTipText(Language.getMsg("network"));
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.NORTHWEST;
		c.weightx = 1;
		c.gridx = 2;
		c.gridy = 0;
		add(nameField, c);
		c.gridy = 1;
		add(urlField, c);
		c.gridy = 2;
		add(portField, c);
		c.gridy = 3;
		add(continentField, c);
		c.gridy = 4;
		add(stateField, c);
		c.gridy = 5;
		add(networkField, c);
        
        
      //Buttons
		addServer = new JButton(Language.getMsg("addServer"));
		addServer.setToolTipText(Language.getMsg("addServer"));
        submit = new JButton(Language.getMsg("connect"));
		submit.setToolTipText(Language.getMsg("connect"));
		submit.setEnabled(false);
		delete = new JButton(Language.getMsg("delete"));
		delete.setToolTipText(Language.getMsg("delete"));
		delete.setEnabled(false);
		cancel = new  JButton(Language.getMsg("Cancel"));
		cancel.setToolTipText(Language.getMsg("Cancel"));
	
		c.fill = GridBagConstraints.NONE;
		c.weightx = 0;
		c.weighty = 0;
		c.gridy = 7;
		c.gridx = 0;
		c.anchor = GridBagConstraints.SOUTHEAST;
		add(addServer, c);
		c.anchor = GridBagConstraints.SOUTHWEST;
		c.gridx = 1;
		add(cancel, c);
		c.gridx = 2;
		add(submit, c);
		c.gridx = 3;
		add(delete, c);
		
        
        //List
        serverList = new ServerList();
        
        list = new JList (serverList.getNameList().toArray());
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		c.anchor = GridBagConstraints.EAST;
        c.gridx = 0;
		c.gridy = 0;
		c.gridheight = GridBagConstraints.RELATIVE;
		c.fill = GridBagConstraints.BOTH;
		c.weighty = 1;
		c.weightx = 0;
		add(new JScrollPane(list), c);
		
    }
    
    //set up button listeners
    private void buttonListenerSetup(){
    	//Call submit function
    			submit.addActionListener(new ActionListener(){
    				@Override
    				public void actionPerformed(ActionEvent e) {
    					submit();
    				}
    			});
    			
    			//Call submit function
    			addServer.addActionListener(new ActionListener(){
    				@Override
    				public void actionPerformed(ActionEvent e) {
    					addServer();
    				}
    			});
    			
    			//Call submit function
    			delete.addActionListener(new ActionListener(){
    				@Override
    				public void actionPerformed(ActionEvent e) {
    					deleteListItem();
    				}
    			});
    			
    			//cancel without saving
    			cancel.addActionListener(new ActionListener(){
    				@Override
    				public void actionPerformed(ActionEvent e) {
    					cancel();
    				}
    			});
    }
  
    //delete selected list item
    private void deleteListItem(){
    	serverList.deleteListItem(changedField);
    	updateList();
    	delete.setEnabled(false);
    	submit.setEnabled(false);
    	changedField = -1;
    	updateTextBoxes(changedField);
    }
    
    //Add new server
    private void addServer(){
    	serverAdded = true;
    	serverList.addNewServer(null, "New Server", 0, null, null, null);
    	updateList();
    	changedField = serverList.getLength()-1;
    	updateTextBoxes(changedField);
    	list.requestFocus();
    }
    
    //Connect or save changes to server
    private void submit(){
    	if(newChanges){//Save server list
    		//Update list item that was changed
    		serverList.updateListItem(changedField, urlField.getText(), nameField.getText(), Integer.parseInt(portField.getText()),
			continentField.getText(), stateField.getText() ,networkField.getText());
    		updateList();	//refresh the listview
    		warningLabel.setVisible(false);					//Hide the warning
    		submit.setText(Language.getMsg("connect"));		//Change button to connect
    		newChanges=false;								//set new changes to false after saving them to list
    		list.setEnabled(true);							//enable the list again
    	}else{ //connect to selected server
    		//TODO CONNECT TO SELECTED SERVER
    		setVisible(false);
    	}
    }
    
    //Cancel changes or if no changes are made, hide window
    private void cancel(){
    	if(newChanges){//Save server list
    		updateList();
    		updateTextBoxes(changedField);
    		submit.setText(Language.getMsg("connect"));
    		warningLabel.setVisible(false);
    		list.setEnabled(true);
    	}else{ //connect to selected server
    		setVisible(false);
    	}
    }
    
    //update the displayed list
    private void updateList(){
    	list.setListData(serverList.getNameList().toArray());
    	
    }
}