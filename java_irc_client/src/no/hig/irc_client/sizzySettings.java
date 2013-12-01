package no.hig.irc_client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Set Color and font settings
 * 
 * @author hyw
 * @author Uhu
 *
 */
public class sizzySettings  {
	String font = "Verdana";
	private int size = 20;
	Color t_color,n_color,m_color,nt_color,nn_color,nm_color;;
	JFrame frame = new JFrame();	
	private String[] sizeDesc = { "8","10","12", "14","18","20","22" };
	 private String[] description = { "BLUE", "GREEN", "BLACK" };
	 private String[] fontDesc = {};
	 private String nFont;
	 int nSize;
	 Prefs pref;
	 
	/**
	 * Open settings window
	 * 
	 * @param frame
	 */
	public sizzySettings(JFrame frame) {
	    // Font info is obtained from the current graphics environment.
  	  GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        //--- Get an array of font names (smaller than the number of fonts)
  	  fontDesc = ge.getAvailableFontFamilyNames();
  	  pref = new Prefs();
  	
		t_color = new Color(pref.getTcolor());
		n_color = new Color(pref.getNcolor());
		m_color = new Color(pref.getMcolor());
		size = pref.getFontSize();
		nSize = size;
		font = pref.getFont();
		nFont = font;
		nt_color = t_color;
		nn_color = n_color;
		nm_color = m_color;
		this.frame = frame;
		
		JPanel panel = new JPanel();
		panel.add(new JLabel(Language.getMsg("textColor")+" : "));
	
		JPanel panel1 = new JPanel();
		panel1.add(new JLabel(Language.getMsg("nameColor")+" : "));
	
		JPanel panel2 = new JPanel();
		panel2.add(new JLabel(Language.getMsg("myColor")+" : "));
	
		
		final DefaultComboBoxModel fontmodel = new DefaultComboBoxModel(fontDesc);
		final DefaultComboBoxModel Sizemodel = new DefaultComboBoxModel(sizeDesc);
	        final DefaultComboBoxModel model = new DefaultComboBoxModel(description);
	        final DefaultComboBoxModel model2 = new DefaultComboBoxModel(description);
	        final DefaultComboBoxModel model3 = new DefaultComboBoxModel(description);
	        JComboBox comboBox = new JComboBox(model);
	        JComboBox comboBox1 = new JComboBox(model2);
	        JComboBox comboBox2 = new JComboBox(model3);
	        JComboBox SizecomboBox = new JComboBox(Sizemodel);
	        JComboBox fontcombo = new JComboBox(fontmodel);
	        panel.add(comboBox);
	        panel1.add(comboBox1);
	        panel2.add(comboBox2);
	        JPanel sizeP = new JPanel();
	        sizeP.add(new JLabel(Language.getMsg("Size") +" : "));
	        sizeP.add(SizecomboBox);
	        sizeP.add(fontcombo);
	        fontmodel.setSelectedItem(pref.getFont());
	        Sizemodel.setSelectedItem(""+pref.getFontSize());
	        
	        fontcombo.addActionListener(new ActionListener() {
			
				@Override
				public void actionPerformed(ActionEvent arg0) {
					nFont = fontmodel.getSelectedItem().toString();
				}
			});
	    
	      comboBox.addActionListener(new ActionListener() {
	    	

	    	
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(model.getSelectedItem().toString().equals("GREEN"))
		        	  nt_color= Color.GREEN;
		        else if(model.getSelectedItem().toString().equals("BLACK"))
		        	  nt_color= Color.GREEN;
		        else if(model.getSelectedItem().toString().equals("BLUE"))
		        	  nt_color= Color.BLUE;
				
			}
		});
	      comboBox1.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent e) {
				  if(model2.getSelectedItem().toString().equals("GREEN"))
		        	  nn_color= Color.GREEN;
		        else if(model2.getSelectedItem().toString().equals("BLACK"))
		        	  nn_color= Color.GREEN;
		        else if(model2.getSelectedItem().toString().equals("BLUE"))
		        	  nn_color= Color.BLUE;
			}
		});
	    comboBox2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				  if(model3.getSelectedItem().toString().equals("GREEN"))
		        	  nm_color= Color.GREEN;
		        else if(model3.getSelectedItem().toString().equals("BLACK"))
		        	  nm_color= Color.GREEN;
		        else if(model3.getSelectedItem().toString().equals("BLUE"))
		        	  nm_color= Color.BLUE;
				
			}
		});
	     SizecomboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				nSize = Integer.parseInt(Sizemodel.getSelectedItem().toString());
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
		submit = new JButton(Language.getMsg("Submit"));
		cancel = new JButton(Language.getMsg("Cancel"));
		last.add(submit, BorderLayout.WEST);
		last.add(cancel, BorderLayout.EAST);
		frame.add(last, BorderLayout.SOUTH);
		submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				t_color = nt_color;
				n_color = nn_color;
				m_color = nm_color;
				font = nFont;
				size = nSize;
				pref.saveSettings(t_color.getRGB(), n_color.getRGB(), m_color.getRGB(), size, font);
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
	
	
	/**
	 * Hide Frame
	 */
	public void hide(){
		frame.setVisible(false);
	}
    /**
     * @return text color
     */
    public Color getTekstColor(){
    	
    	return t_color;
    }
    /**
     * @return name color 
     */
    public Color getNameColor(){
    	
    	return n_color;
    }
	/**
	 * @return my texts color
	 */
	public Color getMyColor() {
	
		return m_color;
	}
	/**
	 * @return font size
	 */
	public int getSize() {
		
		return size;
	}
	/**
	 * @return font type
	 */
	public String getFont() {
		
		return font;
	}

}
