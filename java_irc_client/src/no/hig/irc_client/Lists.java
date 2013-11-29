package no.hig.irc_client;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Lists extends JFrame {

	private JList list;
	private static String[] listContent = {"black","blue","red","white"};
	private static Color[] colors = {Color.BLACK, Color.BLUE, Color.RED, Color.WHITE};
	
	public Lists(){
		//super("title");
		//setLayout(new FlowLayout());
		
		list = new JList (listContent);
		list.setVisibleRowCount(10);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		add(new JScrollPane(list));
		
		
		list.addListSelectionListener(
				new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent event){
						// TODO make a poppup menu with user settings
						//getContentPane().setBackground(colors[list.getSelectedIndex()]);
					}
				}
		);
	}
}


