package no.hig.irc_client;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.naming.spi.StateFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import no.hig.irc_client.Language;

public class AddServer extends JDialog {
	
	String serverURL;
	int port;
	String continent;
	String state;
	String network;
	
	JLabel urlLabel;
	JLabel portLabel;
	JLabel continentLabel;
	JLabel stateLabel;
	JLabel networkLabel;
	JTextField urlField;
	JTextField portField;
	JTextField continentField;
	JTextField stateField;
	JTextField networkField;
	
	JButton cancel;
	JButton submit;

	//create profile constructor
	public AddServer(final JFrame frame){
		super(frame, Language.getMsg("addServer"));
		setLayout(new FlowLayout());

		addLayout(frame);
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
		
		urlLabel = new JLabel(Language.getMsg("serverURL"));
		portLabel = new JLabel(Language.getMsg("port"));
		continentLabel = new JLabel(Language.getMsg("continent"));
		stateLabel = new JLabel(Language.getMsg("state"));
		networkLabel = new JLabel(Language.getMsg("network"));
		urlField = new JTextField(32);
		urlField.setToolTipText(Language.getMsg("serverURL"));
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
		
		add(urlLabel);
		add(urlField);
		add(portLabel);
		add(portField);
		add(continentLabel);
		add(continentField);
		add(stateLabel);
		add(stateField);
		add(networkLabel);
		add(networkField);
		add(submit);
		add(cancel);
	}
	
	
	//Overwrites the profile with a new one
	private void submit(JFrame frame){
		frame.setVisible(false);
		serverURL = urlField.getText();
		port = Integer.parseInt(portField.getText());
		continent = continentField.getText();
		state = stateField.getText();
		network = networkField.getText();
	}
	
	public String getServerURL(){
		return serverURL;
	}
	
	public int getServerPort(){
		return port;
	}
	
}
