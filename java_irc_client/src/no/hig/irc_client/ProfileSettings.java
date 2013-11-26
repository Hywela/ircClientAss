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

		addLayout(frame);
	}
	
	
	//edit profile constructor
	public ProfileSettings(JFrame frame, Profile prof){
		super(frame, "Create Profile");
		
		username.setText(prof.getUsername());
		realName.setText(prof.getRealName());
		primaryNick.setText(prof.getPrimaryNick());
		altNick1.setText(prof.getAltNick1());
		altNick2.setText(prof.getAltNick2());
		
		addLayout(frame);
	}
	
	
	//sets up the UI layout for the profile window and adds all the fields and button listeners
	private void addLayout(final JFrame frame){
		setLayout(new GridBagLayout());
		
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
		
		GridBagConstraints c = new GridBagConstraints();
		//Labels
		c.anchor = GridBagConstraints.EAST;
		c.gridx = 0;
		c.gridy = 0;
		add(usernameLabel, c);
		c.gridy = 1;
		add(realNameLabel, c);
		c.gridy = 2;
		add(primaryNickLabel, c);
		c.gridy = 3;
		add(altNick1Label, c);
		c.gridy = 4;
		add(altNick2Label, c);
	
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.CENTER;
		c.weightx = 1;
		c.gridwidth = 2;
		c.gridx = 1;
		c.gridy = 0;
		add(username, c);
		c.gridy = 1;
		add(realName, c);
		c.gridy = 2;
		add(primaryNick, c);
		c.gridy = 3;
		add(altNick1, c);
		c.gridy = 4;
		add(altNick2, c);
		
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
		profile = new Profile(username.getText(), realName.getText(), primaryNick.getText(), altNick1.getText(), altNick2.getText());
	}
	
	//returns current profile
	public Profile getProfile(){
		return profile;
	}
	
}
