package no.hig.irc_client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class sizzySettings  {
	private String font = "Verdana";
	private int size = 20;
	ColorPicker picker;
	Color t_color,n_color,m_color;
	JFrame frame = new JFrame();	
	public sizzySettings(JFrame frame) {
		
		t_color = Color.black;
		n_color = Color.blue;
		m_color = Color.DARK_GRAY;
		this.frame = frame;
		
		JPanel panel = new JPanel();
		panel.add(new JLabel("Tekst Color : "));
		JButton b_tcolor = new JButton("Pick");
		JPanel panel1 = new JPanel();
		panel1.add(new JLabel("Name Color : "));
		JButton b_ncolor = new JButton("Pick");
		JPanel panel2 = new JPanel();
		panel2.add(new JLabel("My Color : "));
		JButton b_mcolor = new JButton("Pick");
		
		
		
		
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
		panel2.add(b_mcolor);
		
		b_mcolor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				picker = new ColorPicker();
				m_color = picker.getColor();
				
			}
		});
		frame.setSize(new Dimension(500	, 200));
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
		p.add(panel, BorderLayout.CENTER);
		p.add(panel1, BorderLayout.EAST);
		p.add(panel2, BorderLayout.WEST );
		frame.add(p, BorderLayout.NORTH);
		JPanel last = new JPanel();
		JButton submit, cancel;
		submit = new JButton("Save");
		cancel = new JButton("Cancel");
		last.add(submit, BorderLayout.WEST);
		last.add(cancel, BorderLayout.EAST);
		frame.add(last, BorderLayout.SOUTH);
		submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				hide();
				
			}
		});
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				hide();
				
			}
		});
		
		
		
	}
	public void show(){
		frame.setVisible(true);
	}
	public void hide(){
		frame.setVisible(false);
	}
    public Color getTekstColor(){
    	
    	return t_color;
    }
    public Color getNameColor(){
    	
    	return n_color;
    }
	public Color getMyColor() {
	
		return m_color;
	}
	public int getSize() {
		
		return size;
	}
	public String getFont() {
		
		return font;
	}

}
