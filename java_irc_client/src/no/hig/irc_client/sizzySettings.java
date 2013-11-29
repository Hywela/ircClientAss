package no.hig.irc_client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class sizzySettings  {
	private String font = "Verdana";
	private int size = 20;
	ColorPicker picker;
	Color t_color,n_color,m_color;
	JFrame frame = new JFrame();	
	private String[] sizeDesc = { "8","10","12", "14","18","20","22" };
	 private String[] description = { "BLUE", "GREEN", "BLACK" };
	 private String[] fontDesc = {};
	public sizzySettings(JFrame frame) {
	    // Font info is obtained from the current graphics environment.
  	  GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        //--- Get an array of font names (smaller than the number of fonts)
  	fontDesc = ge.getAvailableFontFamilyNames();
 

		
		t_color = Color.black;
		n_color = Color.blue;
		m_color = Color.DARK_GRAY;
		this.frame = frame;
		
		JPanel panel = new JPanel();
		panel.add(new JLabel("Tekst Color : "));
	
		JPanel panel1 = new JPanel();
		panel1.add(new JLabel("Name Color : "));
	
		JPanel panel2 = new JPanel();
		panel2.add(new JLabel("My Color : "));
	
		
		final DefaultComboBoxModel fontmodel = new DefaultComboBoxModel(fontDesc);
		final DefaultComboBoxModel Sizemodel = new DefaultComboBoxModel(sizeDesc);
	        final DefaultComboBoxModel model = new DefaultComboBoxModel(description);
	        final DefaultComboBoxModel model2 = new DefaultComboBoxModel(description);
	        final DefaultComboBoxModel model3 = new DefaultComboBoxModel(description);
	        JComboBox comboBox = new JComboBox(model);
	        JComboBox comboBox1 = new JComboBox(model2);
	        JComboBox comboBox2 = new JComboBox(model3);
	        JComboBox SizecomboBox = new JComboBox(Sizemodel);
	        JComboBox fontcombo = new JComboBox(fontDesc);
	        panel.add(comboBox);
	        panel1.add(comboBox1);
	        panel2.add(comboBox2);
	        JPanel sizeP = new JPanel();
	        sizeP.add(new JLabel("Size : "));
	        sizeP.add(SizecomboBox);
	        sizeP.add(fontcombo);
	        
	        fontcombo.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					font = fontmodel.getSelectedItem().toString();
					System.out.println(font);
				}
			});
	    
	      comboBox.addActionListener(new ActionListener() {
	    	

	    	
			@Override
			public void actionPerformed(ActionEvent e) {
				
				  if(model.getSelectedItem().toString().equals("GREEN"))
		        	  t_color= Color.GREEN;
		        else if(model.getSelectedItem().toString().equals("BLACK"))
		        	  t_color= Color.GREEN;
		        else if(model.getSelectedItem().toString().equals("BLUE"))
		        	  t_color= Color.BLUE;
				
			}
		});
	      comboBox1.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent e) {
				  if(model2.getSelectedItem().toString().equals("GREEN"))
		        	  n_color= Color.GREEN;
		        else if(model2.getSelectedItem().toString().equals("BLACK"))
		        	  n_color= Color.GREEN;
		        else if(model2.getSelectedItem().toString().equals("BLUE"))
		        	  n_color= Color.BLUE;
			}
		});
	    comboBox2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				  if(model3.getSelectedItem().toString().equals("GREEN"))
		        	  m_color= Color.GREEN;
		        else if(model3.getSelectedItem().toString().equals("BLACK"))
		        	  m_color= Color.GREEN;
		        else if(model3.getSelectedItem().toString().equals("BLUE"))
		        	  m_color= Color.BLUE;
				
			}
		});
	     SizecomboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				size = Integer.parseInt(Sizemodel.getSelectedItem().toString());
			}
		});
	      
	    
	        
	      
		frame.setSize(new Dimension(500	, 200));
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
		p.add(panel, BorderLayout.CENTER);
		p.add(panel1, BorderLayout.EAST);
		p.add(panel2, BorderLayout.WEST );
		frame.add(p, BorderLayout.NORTH);
		frame.add(sizeP, BorderLayout.CENTER);
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
