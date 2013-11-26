package no.hig.irc_client;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;
import java.util.Vector;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import jerklib.Session;

public final class Client implements Serializable {

	private static final long serialVersionUID = 1L;
	private static Vector<String> pmVec;

	private static class clientLoader {
		private static final Client INSTANCE = new Client();
	}

	private Client() {
		if (clientLoader.INSTANCE != null) {
			throw new IllegalStateException("Already instantiated");
		}
	}

	public static Client getInstance() {
		return clientLoader.INSTANCE;
	}

	@SuppressWarnings("unused")
	private Client readResolve() {
		return clientLoader.INSTANCE;
	}

	public void serFrame(JFrame frame) {
		pmVec = new Vector<String>();
		this.frame = frame;
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		b.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(java.awt.event.ActionEvent evt) {

				if (con == null) {
					con = new Connector();
					session = con.getSession();
					connectTab();
				} else {
					session.close("close");
				}

			}
		});

		this.frame.add(b, BorderLayout.NORTH);
		this.frame.add(pane);
		frame.setSize(new Dimension(700, 300));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}

	Connector con = null;
	Session session = null;
	private JFrame frame;
	private final JTabbedPane pane = new JTabbedPane();
	private final JButton b = new JButton("Connect");

	public Client(JFrame frame) {

		this.frame = frame;
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		b.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(java.awt.event.ActionEvent evt) {

				if (con == null) {
					con = new Connector();
					session = con.getSession();
					connectTab();
				} else {
					session.close("close");
				}

			}
		});

		this.frame.add(b, BorderLayout.NORTH);
		this.frame.add(pane);
		frame.setSize(new Dimension(700, 300));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public void newTab(Session sess, String channel) {

		Tabs p = new Tabs(new BorderLayout(), true, sess, channel);
		pane.add(channel, p);
		initiCLoseTabCode(channel);

	}

	private void connectTab() {

		Tabs p = new Tabs(new BorderLayout(), false, session, "");
		pane.addTab("connector", p);
		initiCLoseTabCode("connector");
	}

	public void joinChannel(String chan) {

		session.join(chan);
		newTab(session, chan);
	}

	public void newPrivatTab(Session s, String nick) {
		PrivateMessage p = new PrivateMessage(new BorderLayout(), s, nick);
		pane.add(nick, p);

		pmVec.add(nick);
		initiCLoseTabCode(nick);
		pane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);

	}

	public void deleteNick(String s) {

		for (int i = 0; i < pmVec.size(); i++) {
			if (pmVec.get(i).equals(s))
				pmVec.remove(i);
		}

	}

	public boolean findNick(String s) {
		for (int i = 0; i < pmVec.size(); i++) {
			if (pmVec.get(i).equals(s))
				return true;
		}
		return false;
	}

	public void initiCLoseTabCode(String chan) {

		JPanel pnlTab = new JPanel(new GridBagLayout());
		pnlTab.setOpaque(false);
		JLabel lblTitle = new JLabel(chan);
		JButton btnClose = new CloseButton();

		pnlTab.add(lblTitle);
		lblTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));

		pnlTab.add(btnClose);

		btnClose.setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));

		pane.setTabComponentAt(pane.getTabCount() - 1, pnlTab);

		pane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
		btnClose.addMouseListener(buttonMouseListener);
		btnClose.addActionListener(actionListerner);
	}

	private final static MouseListener buttonMouseListener = new MouseAdapter() {
		public void mouseEntered(MouseEvent e) {
			Component component = e.getComponent();
			if (component instanceof AbstractButton) {
				AbstractButton button = (AbstractButton) component;
				button.setBorderPainted(true);
			}
		}

		public void mouseExited(MouseEvent e) {
			Component component = e.getComponent();
			if (component instanceof AbstractButton) {
				AbstractButton button = (AbstractButton) component;
				button.setBorderPainted(false);

			}

		}

	};
	public ActionListener actionListerner = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int index = pane.getSelectedIndex();
			if (index >= 1) {

				pane.remove(index);
			}
		}
	};

}
