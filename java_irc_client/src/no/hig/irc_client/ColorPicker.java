package no.hig.irc_client;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;


 
public class ColorPicker  {    

 
	JFrame guiFrame;
    JTextArea tracker; 
    JPanel optPanel;
    Color color;
    
    int size;
    String font;
    //Note: Typically the main method will be in a
    //separate class. As this is a simple one class
    //example it's all in the one class.
    
    private String[] description = { "BLUE", "GREEN", "BLACK" };
    
    
    public ColorPicker()
    {
    	
        guiFrame = new JFrame();
        //make sure the program exits when the frame closes
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   
        
        //This will center the JFrame in the middle of the screen
        guiFrame.setLocationRelativeTo(null);
        
        
       final JPanel panel = new JPanel();
        panel.add(new JLabel("Please make a selection:"));
        DefaultComboBoxModel model = new DefaultComboBoxModel(description);
       
        JComboBox comboBox = new JComboBox(model);
        panel.add(comboBox);
guiFrame.add(panel);
      
                //this users a JColorchooser instance in combination
                //with a JDialog to create a color chooser dialog. It's modeless
                //and the OK and Cancel buttons can be listened to.
         
    }
    
    public Color getColor(){
    	
    	return color;
    }

   
}