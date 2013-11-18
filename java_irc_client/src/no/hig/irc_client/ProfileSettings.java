package no.hig.irc_client;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import no.hig.irc_client.Language;

public class ProfileSettings extends JDialog {
	
	Profile profile;
	JLabel usernameLabel;
	JLabel realNameLabel;
	JLabel primaryNickLabel;
	JLabel altNick1Label;
	JLabel altNick2Label;
	JTextField username;
	JTextField realName;
	JTextField primaryNick;
	JTextField altNick1;
	JTextField altNick2;
	
	JButton cancel;
	JButton submit;

	//create profile constructor
	public ProfileSettings(final JFrame frame){
		super(frame, Language.getMsg("profileSettings"));
		setLayout(new FlowLayout());

		addLayout(frame);
	}
	
	
	//edit profile constructor
	public ProfileSettings(JFrame frame, Profile prof){
		super(frame, "Create Profile");
		setLayout(new FlowLayout());
		
		username.setText(prof.getUsername());
		realName.setText(prof.getRealName());
		primaryNick.setText(prof.getPrimaryNick());
		altNick1.setText(prof.getAltNick1());
		altNick2.setText(prof.getAltNick2());
		
		addLayout(frame);
	}
	
	
	//sets up the UI layout for the profile window and adds all the fields and button listeners
	private void addLayout(final JFrame frame){
		
		usernameLabel = new JLabel(Language.getMsg("Username"));
		realNameLabel = new JLabel(Language.getMsg("RealName"));
		primaryNickLabel = new JLabel(Language.getMsg("PrimaryNick"));
		altNick1Label = new JLabel(Language.getMsg("AltNick1"));
		altNick2Label = new JLabel(Language.getMsg("AltNick2"));
		username = new JTextField(32);
		username.setToolTipText(Language.getMsg("Username"));
		realName = new JTextField(32);
		realName.setToolTipText(Language.getMsg("RealName"));
		primaryNick = new JTextField(32);
		primaryNick.setToolTipText(Language.getMsg("PrimaryNick"));
		altNick1 = new JTextField(32);
		altNick1.setToolTipText(Language.getMsg("AltNick1"));
		altNick2 = new JTextField(32);
		altNick2.setToolTipText(Language.getMsg("AltNick2"));
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
		
		add(usernameLabel);
		add(username);
		add(realNameLabel);
		add(realName);
		add(primaryNickLabel);
		add(primaryNick);
		add(altNick1Label);
		add(altNick1);
		add(altNick2Label);
		add(altNick2);
		add(submit);
		add(cancel);
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
