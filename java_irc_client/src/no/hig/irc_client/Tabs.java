package no.hig.irc_client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import jerklib.Channel;
import jerklib.Session;
import jerklib.events.IRCEvent;
import jerklib.events.JoinCompleteEvent;
import jerklib.events.JoinEvent;
import jerklib.events.MessageEvent;
import jerklib.events.NickListEvent;
import jerklib.events.IRCEvent.Type;
import jerklib.events.NoticeEvent;
import jerklib.events.PartEvent;
import jerklib.events.QuitEvent;
import jerklib.events.WhoisEvent;
import jerklib.events.modes.ModeAdjustment;
import jerklib.events.modes.ModeEvent;
import jerklib.listeners.IRCEventListener;
import jerklib.tasks.TaskImpl;

/**
 * Tab class for chat tabs
 * 
 * @author hyw
 * 
 */
public class Tabs extends JPanel implements IRCEventListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField inputField;
	public TextArea text;
	private JPopupMenu popMenue;
	public String channel;
	Channel chan;
	DefaultListModel<String> listModel;
	JList<DefaultListModel<String>> list;
	Client client = Client.getInstance();
	/**
	 * Buttons on the right click in list(with users)
	 */
	JButton op, deop, kick, whoise, devoice, voice;
	/**
	 * Frame for the kick window
	 */
	final JFrame kickFrame = new JFrame();

	/**
	 * Constructor
	 * 
	 * @param borderLayout
	 * @param s
	 *            (final Session)
	 * @param channel
	 *            (Channel Name)
	 */
	public Tabs(BorderLayout borderLayout, final Session s, final String channel) {
		super(borderLayout);
		listModel = new DefaultListModel<String>();
		text = new TextArea();
		inputField = new JTextField();

		GridLayout grid = new GridLayout(0, 1);
		// PopUpMenue
		popMenue = new JPopupMenu();

		popMenue.setLayout(grid);
		op = new JButton("Op");
		deop = new JButton("Deop");
		voice = new JButton("Voice");
		devoice = new JButton("Devoice");
		kick = new JButton("Kick");
		whoise = new JButton("Whoise");

		popMenue.add(op);
		popMenue.add(deop);
		popMenue.add(voice);
		popMenue.add(devoice);
		popMenue.add(kick);
		popMenue.add(whoise);

		this.channel = channel;
		chan = new Channel(channel, s);

		list = new JList(listModel);
		text.write("Joining Channel : " + channel, Color.BLUE, client
				.getSettings().getSize(), client.getSettings().getFont());

		text.setEditable(false);

		JScrollPane scrollPane = new JScrollPane(list,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		JScrollPane scrollPane2 = new JScrollPane(text,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		// /p.add(scrollPane, BorderLayout.EAST);

		JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				scrollPane, scrollPane2);
		add(split);
		add(inputField, BorderLayout.SOUTH);
		// Adds a MouseListerner to check stuff in the list like rigthclick ect
		list.addMouseListener(buttonMouseListener);
		/**
		 * To speak in the channel / also room for a more /commands like /j
		 * #channel - joins a channel
		 */
		inputField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent b) {
				inputField.setText("");
				text.write(b.getActionCommand(), client.getSettings()
						.getMyColor(), client.getSettings().getSize(), client
						.getSettings().getFont());
				if (b.getActionCommand().startsWith("/")) {

					String sw = b.getActionCommand().substring(1, 2);
					String message = b.getActionCommand().substring(3);
					switch (sw) {
					case "j": {

						client.joinChannel(message);
						break;
					}

					}

				} else
					// Speak the truth !
					chan.say(b.getActionCommand());
			}
		}); // End ActionListener
		// adds listener for irc events
		s.addIRCEventListener(this);
	}// END CONSTRUCTORd

	/**
	 * Destructor for when leaving a channel
	 * 
	 * @param s
	 *            (Session)
	 */
	public void destructor(Session s) {
		s.getChannel(channel).part("bye");
		s.removeIRCEventListener(this);
	}

	/**
	 * Checks if the nicks is oped or not return true or false
	 * 
	 * @param nick
	 * @return
	 */
	public boolean isUserOP(String nick) {
		Channel gc = chan.getSession().getChannel(channel);
		List<String> usersWithOP = gc.getNicksForMode(
				ModeAdjustment.Action.PLUS, 'o');
		for (String modNick : usersWithOP) {
			if (modNick.equals(nick))
				return true;
		}
		return false;
	}

	/**
	 * Checks if the nicks is voiced or not return true or false
	 * 
	 * @param nick
	 * @return
	 */
	public boolean isUserVoiced(String nick) {

		Channel gc = chan.getSession().getChannel(channel);
		List<String> usersWithOP = gc.getNicksForMode(
				ModeAdjustment.Action.PLUS, 'v');
		for (String modNick : usersWithOP) {
			if (modNick.equals(nick))
				return true;
		}
		return false;

	}

	/**
	 * Sorts the Channel list Reads it into a Arraylist then clears the LisModel
	 * list, sorts the arraylist with Collection, then reads @ into the list
	 * then +(voiced) then the rest. Sorts the first letter So ABC then abc ...
	 * 
	 * @+Aa
	 */
	public void sortList() {
		Object o;
		ArrayList al = new ArrayList();
		for (int i = 0; i < listModel.size(); i++) {

			o = listModel.elementAt(i);
			al.add(o);
		}

		Collections.sort(al);
		listModel.clear();
		for (int i = 0; i < al.size(); i++) {

			o = al.get(i);
			if (((String) o).charAt(0) == '@')

				listModel.addElement((String) o);
		}
		for (int i = 0; i < al.size(); i++) {
			o = al.get(i);
			if (((String) o).charAt(0) == '+')
				listModel.addElement((String) o);
		}
		for (int i = 0; i < al.size(); i++) {
			o = al.get(i);
			if (((String) o).charAt(0) != '@' && ((String) o).charAt(0) != '+')
				listModel.addElement((String) o);
		}
	}

	@Override
	public void receiveEvent(IRCEvent e) {
		client.setSession(e.getSession());

		if (e.getType() == Type.JOIN) {
			JoinEvent joinEvent = (JoinEvent) e;
			Channel gc = joinEvent.getChannel();
			if (channel.equals(gc.getName())) {
				text.write(
						"<" + joinEvent.getNick() + "> has joined the "
								+ gc.getName(), Color.BLUE, client
								.getSettings().getSize(), client.getSettings()
								.getFont());
				if (isUserOP(joinEvent.getNick())) {
					listModel.addElement("@" + joinEvent.getNick());
				} else if (isUserVoiced(joinEvent.getNick())) {
					listModel.addElement("+" + joinEvent.getNick());
				} else
					listModel.addElement("" + joinEvent.getNick());

				sortList();
			}
		} else if (e.getType() == Type.CHANNEL_MESSAGE) {

			MessageEvent me = (MessageEvent) e;
			Channel gc = me.getChannel();
			if (channel.equals(gc.getName())) {

				if (!me.getMessage().startsWith("/")) {
					text.writeNameAndMessage("<" + me.getNick() + "> ", client
							.getSettings().getNameColor(), me.getMessage(),
							client.getSettings().getTekstColor(), client
									.getSettings().getSize(), client
									.getSettings().getFont());

				}
			}
		} else if (e.getType() == Type.JOIN_COMPLETE) {
			System.out.println("join");
			JoinCompleteEvent jce = (JoinCompleteEvent) e;

		} else if (e.getType() == Type.PRIVATE_MESSAGE) {

			MessageEvent pm = (MessageEvent) e; // message event
			if (!client.findNick(pm.getNick())) {
				client.newPrivatTab(e.getSession(), pm.getNick(),
						pm.getMessage());

			}

		} else if (e.getType() == Type.PART) {
			PartEvent partEvent = (PartEvent) e;
			Channel gc = partEvent.getChannel();
			System.out.println(gc.getName());
			if (channel.equals(gc.getName())) {
				for (int i = 0; i < listModel.getSize(); i++) {

					String selected = listModel.get(i).toString();
					if (selected.startsWith("+")) {
						selected = selected.substring(1);
					}
					if (selected.startsWith("@")) {
						selected = selected.substring(1);
					}

					if (selected.equals(partEvent.getWho())) {
						text.write("<" + partEvent.getWho() + "> has quit the "
								+ gc.getName(), Color.RED, client.getSettings()
								.getSize(), client.getSettings().getFont());

						listModel.remove(i);
					}
				}
				sortList();
			}
		} else if (e.getType() == Type.NICK_LIST_EVENT) {
			NickListEvent ne = (NickListEvent) e;
			Channel gc = ne.getChannel();
			if (channel.equals(gc.getName())) {

				List<String> players = ne.getNicks();
				for (String nick : players) {

					if (isUserOP(nick)) {
						listModel.addElement("@" + nick);
					} else if (isUserVoiced(nick)) {
						listModel.addElement("+" + nick);
					} else
						listModel.addElement(nick);
				}

				sortList();

			}
		} else if (e.getType() == Type.QUIT) {
			QuitEvent quitEvent = (QuitEvent) e;

			for (int i = 0; i > listModel.getSize(); i++) {
				text.write(quitEvent.getNick() + "has quit the Channel",
						Color.RED, client.getSettings().getSize(), client
								.getSettings().getFont());
				String selected = listModel.get(i).toString();
				if (selected.startsWith("+")) {
					selected = selected.substring(1);
				}
				if (selected.startsWith("@")) {
					selected = selected.substring(1);
				}

				if (selected.equals(quitEvent.getNick())) {
					listModel.remove(i);
				}

				sortList();
			}

		} else if (e.getType() == Type.INVITE_EVENT) {

			text.write(e.getRawEventData(), Color.GREEN, client.getSettings()
					.getSize(), client.getSettings().getFont());
			;

		} else if (e.getType() == Type.NOTICE) {
			NoticeEvent event = (NoticeEvent) e;

			text.write(event.getNoticeMessage(), Color.RED, client
					.getSettings().getSize(), client.getSettings().getFont());
		} else if (e.getType() == Type.MODE_EVENT) {

			ModeEvent modeEvent = (ModeEvent) e;
			Channel gc = modeEvent.getChannel();
			if (channel.equals(gc.getName())) {
				String tmp = modeEvent.getModeAdjustments().toString();
				text.write(modeEvent.setBy() + " sets " + tmp, Color.gray,
						client.getSettings().getSize(), client.getSettings()
								.getFont());
				String mod = "";

				if (tmp.charAt(1) == '+') {
					if (tmp.charAt(2) == 'o' || tmp.charAt(2) == 'O') {
						mod = "@";
					}
					if (tmp.charAt(2) == 'v' || tmp.charAt(2) == 'V') {
						mod = "+";
					}
				}
				if (tmp.charAt(1) == '-') {
					if (tmp.charAt(2) == 'o' || tmp.charAt(2) == 'O') {
						mod = "";
					}
					if (tmp.charAt(2) == 'v' || tmp.charAt(2) == 'V') {
						mod = "";
					}
				}

				String userName = "" + tmp.subSequence(4, tmp.length() - 1);

				for (int i = 0; i < listModel.getSize(); i++) {
					String selected = listModel.get(i).toString();
					if (selected.startsWith("+")) {
						selected = selected.substring(1);
					}
					if (selected.startsWith("@")) {
						selected = selected.substring(1);
					}

					if (selected.equals(userName)) {
						listModel.set(i, mod + userName);
					} // casting the object
				}

			}
			sortList();
		}

	}
	/**
	 * Mouse listener on right click in the channelList
	 */
	private MouseListener buttonMouseListener = new MouseAdapter() {
		public void mousePressed(MouseEvent e) {
			if (e.getButton() == MouseEvent.BUTTON1) {//is mouse pressed down
				if (e.getClickCount() == 2) {	//Double click for private tab
					int selectedItem = list.getSelectedIndex();
					String selected = listModel.get(selectedItem);
					if (selected.charAt(1) == '+') {
						selected = selected.substring(1);
					} else if (selected.charAt(1) == '@') {
						selected = selected.substring(1);
					}
					
					client.newPrivatTab(client.getSession(), selected, null);
				}// End MouseEvent = DoubleClick
			}// End MouseEvent = leftClick
			if (e.getButton() == MouseEvent.BUTTON3) {// if right click
				int selectedItem = list.getSelectedIndex();	//gets the item selected
				String selc = listModel.get(selectedItem);	// Because of the way the list is @nick, +Nick , Nick  
															//-we get an offput in the the start of the string
				if (selc.startsWith("+")) {					// Therefore we have to switch between starting at 0 and 1 in the string.
					selc = selc.substring(1);
				} else if (selc.startsWith("@")) {
					selc = selc.substring(1);
				}
				final String selected = selc;
				//pops the menue up where mouse pointer is 
				popMenue.show(e.getComponent(), e.getX(), e.getY());
				// For demoting the O
				deop.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {

						chan.deop(selected);
					}
				});
				// For kicking a user , with a reason tekst boks , currently this sems to be buggy can not get the KiCKEvent to work 
				kick.addActionListener(new ActionListener() {	// Correctly

					@Override
					public void actionPerformed(ActionEvent arg0) {

						JButton cancle = new JButton("Cancle");
						JButton kickButton = new JButton("Kick");
						final JTextField field = new JTextField(20);
						JPanel pan = new JPanel();
						pan.add(new JLabel("Reason for kick: "));
						pan.add(field);
						kickFrame.add(pan, BorderLayout.NORTH);
						JPanel panel = new JPanel();
						panel.add(cancle);
						panel.add(kickButton);
						kickFrame.add(panel, BorderLayout.SOUTH);
						kickFrame.pack();
						kickFrame.setAlwaysOnTop(true);
						kickFrame.setVisible(true);
						kickButton.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								kickFrame.setVisible(false);
								int selectedItem = list.getSelectedIndex();

								for (int i = 0; i > listModel.getSize(); i++) {

									listModel.remove(selectedItem);

								}
								chan.kick(selected, field.getText());
							}

						});
						cancle.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								kickFrame.setVisible(false);
							}
						});

					}
				});
				// Voicing a user
				voice.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						int selectedItem = list.getSelectedIndex();

						chan.voice(selected);
					}
				});
				// De voicing a user
				devoice.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						int selectedItem = list.getSelectedIndex();
						final String selected = listModel.get(selectedItem)
								.substring(1);
						chan.deVoice(selected);
					}
				});
				// Promoting a user to O
				op.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						int selectedItem = list.getSelectedIndex();
						final String selected = listModel.get(selectedItem)
								.substring(1);
						chan.op(selected);
					}
				});
				// Sends a whoise request and displays it
				whoise.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						client.getSession().whois(selected);
						client.getSession().onEvent(
								new TaskImpl("WHOIS_EVENT") {
									public void receiveEvent(IRCEvent e) {
										WhoisEvent we = (WhoisEvent) e;

										text.write(
												we.getNick() + " : "
														+ we.getHost(),
												Color.BLUE, client
														.getSettings()
														.getSize(), client
														.getSettings()
														.getFont());
										text.write(
												"Channels in : "
														+ we.getChannelNames(),
												Color.BLUE, client
														.getSettings()
														.getSize(), client
														.getSettings()
														.getFont());
									}
								}, Type.WHOIS_EVENT);
					}
				});

			}// End MouseEvent = RigthClick

		}// End MousePressed
	};// End MouseListener

}// end Class
