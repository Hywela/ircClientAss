//NO LONGER IN USE

package no.hig.irc_client;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AddServer extends JDialog {
	
	Server server;
	
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
	
	JButton cancel;
	JButton submit;

	//create profile constructor
	public AddServer(final JFrame frame){
		super(frame, Language.getMsg("addServer"));
		addLayout(frame);
		int defaultPort = 6667;
		portField.setText(""+defaultPort);
	}
	
	
	/**
	//edit profile constructor
	public AddServer(JFrame frame, Profile prof){
		super(frame, "Create Profile");
		setLayout(new FlowLayout());
		
		username.setText(prof.getUsername());
		realName.setText(prof.getRealName());
		primaryNick.setText(prof.getPrimaryNick());
		altNick1.setText(prof.getAltNick1());
		altNick2.setText(prof.getAltNick2());
		
		addLayout(frame);
	}
	**/
	
	//sets up the UI layout for the profile window and adds all the fields and button listeners
	private void addLayout(final JFrame frame){
		setLayout(new GridBagLayout());
		
		
		
		urlLabel = new JLabel(Language.getMsg("serverURL"));
		nameLabel = new JLabel(Language.getMsg("serverName"));
		portLabel = new JLabel(Language.getMsg("port"));
		continentLabel = new JLabel(Language.getMsg("continent"));
		stateLabel = new JLabel(Language.getMsg("state"));
		networkLabel = new JLabel(Language.getMsg("network"));
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
		submit = new JButton(Language.getMsg("Submit"));
		submit.setToolTipText(Language.getMsg("Submit"));
		cancel = new  JButton(Language.getMsg("Cancel"));
		cancel.setToolTipText(Language.getMsg("Cancel"));
		
		
		//Call submit function
		submit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				submit(frame);
			}
		});
		
		
		//cancel without saving
		cancel.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		
		GridBagConstraints c = new GridBagConstraints();
		//Labels
		c.insets = new Insets(1,5,0,5);
		c.anchor = GridBagConstraints.EAST;
		c.gridx = 0;
		c.gridy = 0;
		add(urlLabel, c);
		c.gridy = 1;
		add(nameLabel, c);
		c.gridy = 2;
		add(portLabel, c);
		c.gridy = 3;
		add(continentLabel, c);
		c.gridy = 4;
		add(stateLabel, c);
		c.gridy = 5;
		add(networkLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.CENTER;
		c.weightx = 1;
		c.gridwidth = 2;
		c.gridx = 1;
		c.gridy = 0;
		add(urlField, c);
		c.gridy = 1;
		add(nameField, c);
		c.gridy = 2;
		add(portField, c);
		c.gridy = 3;
		add(continentField, c);
		c.gridy = 4;
		add(stateField, c);
		c.gridy = 5;
		add(networkField, c);
		
		c.fill = GridBagConstraints.NONE;
		c.weightx = 0;
		c.gridwidth = 1;
		c.insets = new Insets(10,0,0,0);
		c.gridx = 1;
		c.gridy = 6;
		add(submit, c);
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 2;
		c.gridy = 6;
		add(cancel, c);
	}
	
	
	//Overwrites the profile with a new one
	private void submit(JFrame frame){
		frame.setVisible(false);
		server = new Server(urlField.getText(), nameField.getText(), Integer.parseInt(portField.getText()),
				continentField.getText(), stateField.getText() ,networkField.getText());
		ServerList list = new ServerList();
		list.add(server);
	}
}
