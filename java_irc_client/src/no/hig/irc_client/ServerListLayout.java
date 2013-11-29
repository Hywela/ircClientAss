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

/**
 * 
 * Class for displaying, adding, deleting, editing and selecting a Server
 * 
 * 
 * @author Uhu
 * 
 */
@SuppressWarnings("serial")
public class ServerListLayout extends JDialog {

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
		layoutSetup(); 		//adding the layout

		// Listen for selections in the list and refresh the text boxes with
		// correct information
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				if (serverAdded) { 	//if a new server is added set that as selected
					list.setSelectedIndex(serverList.getLength() - 1);
					serverAdded = false;
				}
				if (list.getSelectedIndex() >= 0) { 	//if server have been selected in the list
					changedField = list.getSelectedIndex(); // Save the list
															// item that have
															// been changed
					updateTextBoxes(changedField);
					submit.setEnabled(true); // when an item is selected
												// Activate submit and delete
												// button
					delete.setEnabled(true);
				} else { 				//typicaly happens when a list item have been deleted
					updateTextBoxes(changedField); 	//empty the text boxes
					list.setEnabled(true);			//reenable the list
				}
			}
		});
		changeListenersSetup(); // Listen for changes to the text fields
		buttonListenerSetup(); // Listen for button pushes
	}

	
	/**
	 * Sets up all the document listeners for each of the text fields
	 * Calls the function textFieldsCHanged() when a change to one of the fields is made
	 */
	private void changeListenersSetup() {
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
		continentField.getDocument().addDocumentListener(
				new DocumentListener() {
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

	
	
	
	
	/**
	 * Function taking care of what hapends when a field have been changed,
	 * Changes submit function to save server instead of selecting requiering the
	 * user to save changes before continuing.
	 *
	 */
	private void textFieldsChanged() {
		if (listenForChange && changedField >= 0) { // If the user changed the
													// field and not the field
													// updater
			submit.setText(Language.getMsg("saveChanges")); // Change the submit
															// button to save
			newChanges = true;
			list.setEnabled(false); // Disable the list until changes is saved
			submit.setEnabled(true);
			warningLabel.setVisible(true); // Show notification
			warningLabel.setText(serverList.getServer(changedField).getName()
					+ " " + "Was changed");
		}
	}

	// Update text boxes with new information
	/**
	 * Updates the text boxes to contain information about the selected server
	 * if out of bounds or bellow 0 the boxes will be cleared
	 * 
	 * @param serverIndex 	server index that is selected
	 */
	private void updateTextBoxes(int serverIndex) {
		listenForChange = false; 	//disables the functionality of the text field listeners
		if (serverIndex < 0 || serverIndex > serverList.getLength() - 1) {		//Clears out the fields if 
			nameField.setText(""); 												//no valid server is selected
			urlField.setText("");
			portField.setText("");
			continentField.setText("");
			stateField.setText("");
			networkField.setText("");
		} else {																//else display information about server
			Server server = serverList.getServer(serverIndex);
			nameField.setText(server.getName());
			urlField.setText(server.getUrl());
			portField.setText("" + server.getPort());
			continentField.setText(server.getContinent());
			stateField.setText(server.getState());
			networkField.setText(server.getNetwork());
		}
		listenForChange = true; 	//reactivate change listener functionality
	}

	
	
	
	/**
	 * Sets up and positions all the layout elements
	 */
	private void layoutSetup() {
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		// Labels
		urlLabel = new JLabel(Language.getMsg("serverURL"));
		nameLabel = new JLabel(Language.getMsg("serverName"));
		portLabel = new JLabel(Language.getMsg("port"));
		continentLabel = new JLabel(Language.getMsg("continent"));
		stateLabel = new JLabel(Language.getMsg("state"));
		networkLabel = new JLabel(Language.getMsg("network"));
		warningLabel = new JLabel("THIS IS A WARNING");		//and should not be displayed.... ever
		warningLabel.setForeground(Color.RED);
		warningLabel.setVisible(false);

		//Label positioning
		c.anchor = GridBagConstraints.NORTHEAST;
		c.insets = new Insets(3, 5, 0, 0);
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

		
		// Text Fields

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

		// Buttons
		addServer = new JButton(Language.getMsg("addServer"));
		addServer.setToolTipText(Language.getMsg("addServer"));
		submit = new JButton(Language.getMsg("Submit"));
		submit.setToolTipText(Language.getMsg("Submit"));
		submit.setEnabled(false);
		delete = new JButton(Language.getMsg("delete"));
		delete.setToolTipText(Language.getMsg("delete"));
		delete.setEnabled(false);
		cancel = new JButton(Language.getMsg("Cancel"));
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

		// List
		serverList = new ServerList();

		list = new JList(serverList.getNameList().toArray());
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

	// set up button listeners
	/**
	 * Set upp listeners for all the buttons
	 */
	private void buttonListenerSetup() {
		// Call submit function
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				submit();
			}
		});

		// Call add function
		addServer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addServer();
			}
		});

		// Call delete function
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteListItem();
			}
		});

		// Call cancel function
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cancel();
			}
		});
	}

	
	/**
	 *	Deletes the currently active list item, called when delete button is pressed
	 */
	private void deleteListItem() {
		serverList.deleteListItem(changedField); //delete item from ServerList
		updateList();
		delete.setEnabled(false);
		submit.setEnabled(false);
		changedField = -1;
		updateTextBoxes(changedField);
	}

	
	/**
	 *  add a new server to the list, called when new server button is pressed
	 */
	private void addServer() {
		serverAdded = true;
		serverList.addNewServer(null, Language.getMsg("newServer"), 0, null, null, null);
		updateList();
		changedField = serverList.getLength() - 1;
		updateTextBoxes(changedField);
		list.requestFocus();
	}

	
	/**
	 * If changes have been made it saves the changes to the ServerList
	 * else if a server is selected it will save it to preferences to be used next
	 * time user connects to a server.
	 */
	private void submit() {
		if (newChanges) {// Save server list
			// Update list item that was changed
			serverList.updateListItem(changedField, urlField.getText(),
					nameField.getText(), Integer.parseInt(portField.getText()),
					continentField.getText(), stateField.getText(),
					networkField.getText());
			updateList(); // refresh the listview
			warningLabel.setVisible(false); // Hide the warning
			submit.setText(Language.getMsg("Submit")); // Change button to
														// submit
			newChanges = false; // set new changes to false after saving them to
								// list
			list.setEnabled(true); // enable the list again
		} else { // submit to selected server
			new Prefs().saveLastServer(urlField.getText(),
					Integer.parseInt(portField.getText()));
			setVisible(false);
		}
	}


	/**
	 * If changes have been made to a server it will reset them to the previous saved information
	 * if no changes is made it will hide the window
	 */
	private void cancel() {
		if (newChanges) {// Save server list
			updateList();
			updateTextBoxes(changedField);
			submit.setText(Language.getMsg("Submit"));
			warningLabel.setVisible(false);
			list.setEnabled(true);
		} else { // submit to selected server
			setVisible(false);
		}
	}

	
	/**
	 *	Update the listView to display changes in the server list 
	 */
	private void updateList() {
		list.setListData(serverList.getNameList().toArray());

	}
}