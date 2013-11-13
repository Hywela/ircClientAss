package no.hig.irc_client;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ProfileSettings extends JDialog {
	
	Profile profile;
	JLabel label;
	JTextField username;
	JTextField realName;
	JTextField primaryNick;
	JTextField altNick1;
	JTextField altNick2;
	
	JButton cancel;
	JButton submit;

	//create profile constructor
	public ProfileSettings(final JFrame frame){
		super(frame, "Create Profile");
		setLayout(new FlowLayout());
		
		username = new JTextField(30);
		realName = new JTextField(30);
		primaryNick = new JTextField(30);
		altNick1 = new JTextField(30);
		altNick2 = new JTextField(30);

		addLayout(frame);
	}
	
	
	//edit profile constructor
	public ProfileSettings(JFrame frame, Profile prof){
		super(frame, "Create Profile");
		setLayout(new FlowLayout());
		
		username = new JTextField(prof.getUsername(), 30);
		realName = new JTextField(prof.getRealName(), 30);
		primaryNick = new JTextField(prof.getPrimaryNick(), 30);
		altNick1 = new JTextField(prof.getAltNick1(), 30);
		altNick2 = new JTextField(prof.getAltNick2(), 30);

		addLayout(frame);
	}
	
	
	//sets up the UI layout for the profile window and adds all the fields and button listeners
	private void addLayout(final JFrame frame){
		submit = new JButton("Submit");
		cancel = new  JButton("cancel");
		
		
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
		
		
		add(username);
		add(realName);
		add(primaryNick);
		add(altNick1);
		add(altNick2);
		add(cancel);
		add(submit);
	}
	
	
	//Overwrites the profile with a new one
	private void submit(JFrame frame){
		frame.setVisible(false);
		profile = new Profile(username.getText(), realName.getText(), primaryNick.getText(), altNick1.getText(), altNick2.getText());
	}
	
	//returns current profile
	public Profile getProfile(){
		return profile;
	}
	
}
