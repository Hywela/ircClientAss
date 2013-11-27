package no.hig.irc_client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ScrollPaneConstants;

import jerklib.Channel;
import jerklib.Session;
import jerklib.events.ErrorEvent;
import jerklib.events.IRCEvent;
import jerklib.events.JoinCompleteEvent;
import jerklib.events.JoinEvent;
import jerklib.events.KickEvent;
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

public class Tabs extends JPanel implements IRCEventListener {
	private JTextField inputField;
	public TextArea text;
	private JPopupMenu popMenue;
	public String channel;
	Channel chan;
	DefaultListModel<String> listModel;
	JList<DefaultListModel<String>> list;
	Client client = Client.getInstance();
	JButton op, deop, kick, whoise;
	boolean notChat;

	public Tabs(BorderLayout borderLayout, boolean type, final Session s,
			final String channel) {
		// TODO Auto-generated constructor stub
		super(borderLayout);
		listModel = new DefaultListModel<String>();
		text = new TextArea();
		inputField = new JTextField();

		
		GridLayout grid = new GridLayout(0,1);
		//PopUpMenue
		popMenue = new JPopupMenu();

		popMenue.setLayout(grid);
		op = new JButton("Op");

		deop = new JButton("Deop");
	
		kick = new JButton("Kick");

		whoise = new JButton("Whoise");
	
		popMenue.add(op);
		popMenue.add(deop);
		popMenue.add(kick);
		popMenue.add(whoise);
		this.channel = channel;
		chan = new Channel(channel, s);
		if (type == false) {

			notChat = true;

			text.setEditable(false);
			JScrollPane scrollPane = new JScrollPane(text,
					ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
					ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			scrollPane.setAutoscrolls(true);

			// split.add(inputField,JSplitPane.BOTTOM);

			add(scrollPane, BorderLayout.CENTER);

			// p.add(,BorderLayout.CENTER);
			add(inputField, BorderLayout.SOUTH);

		}

		if (type == true) {

			notChat = false;
			list = new JList(listModel);
			text.write("Joining Channel : " + channel, Color.BLUE);

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
			// split.add(inputField,JSplitPane.BOTTOM);
			// p.add(list,BorderLayout.EAST);
			add(split);
			// p.add(,BorderLayout.CENTER);
			add(inputField, BorderLayout.SOUTH);
			list.addMouseListener(buttonMouseListener);
		}
		inputField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent b) {
				// TODO Auto-generated method stub
				inputField.setText("");
				text.write(b.getActionCommand(), Color.BLACK);

				System.out.println(b.getActionCommand());

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
					chan.say(b.getActionCommand());
			}
		});
		s.addIRCEventListener(this);
	}// END CONSTRUCTORd
public void destructor(Session s){
	s.getChannel(channel).part("bye");
	s.removeIRCEventListener(this);
	 
	
}
	@Override
	public void receiveEvent(IRCEvent e) {
		// TODO Auto-generated method stub
		client.session = e.getSession();
		if (e.getType() == Type.JOIN) {
			JoinEvent joinEvent = (JoinEvent) e;
			Channel gc = joinEvent.getChannel();
			System.out.println(gc.getName());
			if (channel.equals(gc.getName())) {
				text.write(
						"<" + joinEvent.getNick() + "> has joined the "
								+ gc.getName(), Color.BLUE);
				listModel.addElement(joinEvent.getNick());
			}

		} else if (e.getType() == Type.CHANNEL_MESSAGE) {
			MessageEvent me = (MessageEvent) e;
			Channel gc = me.getChannel();
			if (channel.equals(gc.getName())) {

				if (!me.getMessage().startsWith("/")) {
					text.write("<" + me.getNick() + "> " + me.getMessage(),
							Color.BLACK);
				}
			}

			if (notChat)
				text.write(e.getType() + " " + e.getRawEventData(), Color.BLUE);

		} else if (e.getType() == Type.CONNECT_COMPLETE) {
			System.out.println("CONNECT");
			client.chanelList();
		} else if (e.getType() == Type.JOIN_COMPLETE) {
			System.out.println("join");
			JoinCompleteEvent jce = (JoinCompleteEvent) e;
		} else if (e.getType() == Type.SERVER_INFORMATION) {
			if (notChat)
				text.write(e.getType() + " " + e.getRawEventData(), Color.BLUE);
		} else if (e.getType() == Type.SERVER_VERSION_EVENT) {
			if (notChat)
				text.write(e.getType() + " " + e.getRawEventData(), Color.BLUE);
		} else if (e.getType() == Type.CONNECTION_LOST) {
			if (notChat)
				text.write(e.getType() + " " + e.getRawEventData(), Color.BLUE);

		} else if (e.getType() == Type.PRIVATE_MESSAGE) {

			
			MessageEvent pm = (MessageEvent) e; // message event
			if (!client.findNick(pm.getNick())) {
				client.newPrivatTab(e.getSession(), pm.getNick(), pm.getMessage());
				
			}

		} else if (e.getType() == Type.PART) {
			PartEvent partEvent = (PartEvent) e;
			Channel gc = partEvent.getChannel();
			System.out.println(gc.getName());
			if (channel.equals(gc.getName())) {
				for (int i = 0; i < listModel.getSize(); i++) {
					if (listModel.get(i).toString().equals(partEvent.getWho())) {
						text.write("<" + partEvent.getWho() + "> has quit the "
								+ gc.getName(), Color.RED);

						listModel.remove(i);
					}
				}
			}
		} else if (e.getType() == Type.NICK_LIST_EVENT) {
			NickListEvent ne = (NickListEvent) e;
			Channel gc = ne.getChannel();
			if (channel.equals(gc.getName())) {			
				List<String> players = ne.getNicks();
				for (String nick : players)
					listModel.addElement(nick);

			}
		} else if (e.getType() == Type.QUIT) {
			QuitEvent quitEvent = (QuitEvent) e;

			
			if (listModel.getSize() > quitEvent.getChannelList().size()) {
				for (int i = 0; i > listModel.getSize(); i++) {
					text.write(quitEvent.getNick() + "has quit the Channel",
							Color.RED);
					if (listModel.get(i) == quitEvent.getNick()) {
						listModel.remove(i);
					}
				}
			}
		} else if (e.getType() == Type.ERROR) {
			ErrorEvent errorEvent = (ErrorEvent) e;
			text.write(e.getRawEventData(), Color.BLACK);

		} else if (e.getType() == Type.INVITE_EVENT) {

			text.write(e.getRawEventData(), Color.GREEN);

		} else if (e.getType() == Type.NOTICE) {
			NoticeEvent event = (NoticeEvent) e;

			text.write(event.getNoticeMessage(), Color.RED);
		} else if (e.getType() == Type.MODE_EVENT) {
			//getNicksForMode
			if(!notChat){
			ModeEvent modeEvent = (ModeEvent)e;     
         //casting the object
			
            Channel gc = modeEvent.getChannel();   
            if (channel.equals(gc.getName())) {
        
          List<ModeAdjustment> tmd;
        ListModel< ModeAdjustment> td;
        td = new DefaultListModel<>();
     
          List<ModeAdjustment> tmd1 = chan.getChannelModes();
				
          
          
          
          text.write(tmd1.toString(), Color.PINK);
            	String tmp = modeEvent.getModeAdjustments().toString();
            	text.write(tmp, Color.PINK);
             	text.write(modeEvent.setBy(), Color.PINK);
            	
            }
            	
            }
		} else if (e.getType() == Type.MOTD) {
		} else if (e.getType() == Type.KICK_EVENT) {
			KickEvent kickEvent = (KickEvent) e;
			Channel gc = kickEvent.getChannel();
			System.out.println(gc.getName());
			if (channel.equals(gc.getName())) {
				for (int i = 0; i < listModel.getSize(); i++) {
					if (listModel.get(i).toString().equals(kickEvent.getWho())) {
						text.write("<" + kickEvent.getWho() + "> has quit the "
								+ gc.getName(), Color.RED);

						listModel.remove(i);
					}
				}
			}

		} else {
			if(notChat)
			text.write(e.getRawEventData(), Color.RED);
		}

	}

	private MouseListener buttonMouseListener = new MouseAdapter() {
		public void mousePressed( MouseEvent e ) {  
			if(e.getButton() == MouseEvent.BUTTON1){
			if(e.getClickCount() == 2)
		    {	int selectedItem = list.getSelectedIndex();
		        String selected = listModel.get(selectedItem);
		        
		  
		        client.newPrivatTab(client.session, selected, null); 
		        }}
		     if(e.getButton() == MouseEvent.BUTTON3) {
		    	 
		        int selectedItem = list.getSelectedIndex();
		        final String selected = listModel.get(selectedItem);
		        text.write(selected, Color.RED);
		    	popMenue.show(e.getComponent(), e.getX(), e.getY());
		    	
		    	deop.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						chan.deop(selected);
						
					}
				});
		    	
			    		
		    	kick.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						chan.kick(selected, "not implemented");
						
					}
				});
		    	op.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						chan.op(selected);
						
					}
				});		    	
		    	whoise.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						client.session.whois(selected);
						
						client.session.onEvent(new TaskImpl("WHOIS_EVENT") {
							public void receiveEvent(IRCEvent e) {
								WhoisEvent we = (WhoisEvent)e;
		text.write(we.getNick() + " : " + we.getHost(), Color.BLUE);
		text.write("Channels in : " + we.getChannelNames(), Color.BLUE);					
								}

						

						}, Type.WHOIS_EVENT);
						
						
					}
				});		    	
		    	
		     }
		    
		}


};

}

