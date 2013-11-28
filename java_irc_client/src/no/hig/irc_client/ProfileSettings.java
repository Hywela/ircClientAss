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
	
	Prefs p;
	jerklib.Profile profile;
	JLabel usernameLabel;
	JLabel realNameLabel;
	JLabel primaryNickLabel;
	JLabel altNickLabel;
	JTextField username;
	JTextField realName;
	JTextField primaryNick;
	JTextField altNick;
	
	
	JButton cancel;
	JButton submit;

	//create profile constructor
	public ProfileSettings(final JFrame frame){
		super(frame, Language.getMsg("profileSettings"));

		p = new Prefs();
		addLayout(frame);
		username.setText(p.getUsername());
		realName.setText(p.getRealName());
		primaryNick.setText(p.getPrimaryNick());
		altNick.setText(p.getAltNick());
	}
	public ProfileSettings(){
		p = new Prefs();
		if(p.getUsername().equals(null)||p.getUsername().equals("")){
			addLayout(new JFrame());
		}else{
			profile = new jerklib.Profile(p.getUsername(),p.getRealName(), p.getPrimaryNick(), p.getAltNick());
		}
	}
	
	//sets up the UI layout for the profile window and adds all the fields and button listeners
	private void addLayout(final JFrame frame){
		setLayout(new GridBagLayout());
		
		usernameLabel = new JLabel(Language.getMsg("Username"));
		realNameLabel = new JLabel(Language.getMsg("RealName"));
		primaryNickLabel = new JLabel(Language.getMsg("PrimaryNick"));
		altNickLabel = new JLabel(Language.getMsg("AltNick"));
		username = new JTextField(32);
		username.setToolTipText(Language.getMsg("Username"));
		realName = new JTextField(32);
		realName.setToolTipText(Language.getMsg("RealName"));
		primaryNick = new JTextField(32);
		primaryNick.setToolTipText(Language.getMsg("PrimaryNick"));
		altNick = new JTextField(32);
		altNick.setToolTipText(Language.getMsg("AltNick"));
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
		add(usernameLabel, c);
		c.gridy = 1;
		add(realNameLabel, c);
		c.gridy = 2;
		add(primaryNickLabel, c);
		c.gridy = 3;
		add(altNickLabel, c);
		c.gridy = 4;
	
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
		add(altNick, c);
		c.gridy = 4;
		
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
		p.saveProfile(username.getText(), realName.getText(), primaryNick.getText(), altNick.getText());
		profile = new jerklib.Profile(username.getText(), realName.getText(), primaryNick.getText(), altNick.getText());
	}
	
	//returns current profile
	public jerklib.Profile getProfile(){
		return profile;
	}
	
}
