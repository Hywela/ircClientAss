package no.hig.irc_client;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class sizzySettings  {
	ColorPicker picker;
	Color t_color,n_color;
	JFrame frame = new JFrame();	
	public sizzySettings(JFrame frame) {
		this.frame = frame;
		frame.pack();
		JPanel panel = new JPanel();
		panel.add(new JLabel("Tekst Color : "));
		JButton b_tcolor = new JButton("Pick");
		JPanel panel1 = new JPanel();
		panel1.add(new JLabel("Name Color : "));
		JButton b_ncolor = new JButton("Pick");
		
		
		
		
		
		panel.add(b_tcolor);
				
		b_tcolor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				picker = new ColorPicker();
				t_color = picker.getColor();
				
			}
		});
		panel1.add(b_ncolor);
		
		b_ncolor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				picker = new ColorPicker();
				n_color = picker.getColor();
				
			}
		});
		
		
		frame.add(panel);
	}
	public void show(){
		frame.setVisible(true);
	}
	public void hide(){
		frame.setVisible(false);
	}
	

}
