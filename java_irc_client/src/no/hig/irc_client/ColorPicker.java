package no.hig.irc_client;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;
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
    
              
    
    
    public ColorPicker()
    {
        guiFrame = new JFrame();
        //make sure the program exits when the frame closes
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   
        
        //This will center the JFrame in the middle of the screen
        guiFrame.setLocationRelativeTo(null);
        
        

        //button for the show dialog method
        
        
        //button for the create dialog method
      
                //this users a JColorchooser instance in combination
                //with a JDialog to create a color chooser dialog. It's modeless
                //and the OK and Cancel buttons can be listened to.
                final JColorChooser colorChooser = new JColorChooser();
                JDialog dialog = JColorChooser.createDialog(guiFrame, 
                        "Set Text Area color", false, colorChooser
                        , new ActionListener()
                          {
                              @Override
                             public void actionPerformed(ActionEvent event)
                             {
                               //this actionListenerr is for the OK button
                            	  color = colorChooser.getColor(); 
                             }
                          }
                        , new ActionListener()
                         {
                             @Override
                             public void actionPerformed(ActionEvent event)
                             {
                               //this actionListener is for the cancel button
                               tracker.append("\nCancel button clicked..");
                             } 
                         }
                        );
                dialog.setVisible(true);
   }      
    
    public Color getColor(){
    	
    	return color;
    }

   
}