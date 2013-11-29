package no.hig.irc_client;

import java.awt.Color;

import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.Scrollable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

public class TextArea extends JTextPane  implements DocumentListener, Scrollable{

	 String newline = "\n";
	public TextArea (){
		
		
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}


    
    public void write(String txt, Color color, int size, String Font){
    	
    try {
   	      Document doc = getDocument();
   	      StyleContext context = new StyleContext();
   	   // build a style
   	 Style style = context.addStyle("test", null);
   	  
   	 
   	 StyleConstants.setFontFamily(style, Font);
   	 StyleConstants.setFontSize(style, size);
   	   // set some style properties
   	   StyleConstants.setForeground(style, color);
   	 doc.insertString(doc.getLength(), newline, null);
   	      doc.insertString(doc.getLength(), txt, style);
   	   } catch(BadLocationException exc) {
   	      exc.printStackTrace();
   	   }
    	 setCaretPosition(getDocument().getLength());
   }

	@Override
	public void insertUpdate(DocumentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeUpdate(DocumentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void writeNameAndMessage(String txt, Color nameColor,
			String message, Color tekstColor, int size, String font) {
		try {
	   	      Document doc = getDocument();
	   	      StyleContext context = new StyleContext();
	   	   
	   	      Style style = context.addStyle("test", null);
		   	 StyleConstants.setFontFamily(style, font);
		   	 StyleConstants.setFontSize(style, size);
		   	   // set some style properties
		   	   StyleConstants.setForeground(style, nameColor);
	    	 doc.insertString(doc.getLength(), newline, null);
	   	      doc.insertString(doc.getLength(), txt, style);
	   	  style = context.addStyle("test", null);
	   	 StyleConstants.setFontFamily(style, font);
	   	 StyleConstants.setFontSize(style, size);
	   	   // set some style properties
	   	   StyleConstants.setForeground(style, tekstColor);
	   	 
	
	   	      doc.insertString(doc.getLength(), message, style);
	   	   } catch(BadLocationException exc) {
	   	      exc.printStackTrace();
	   	   }
	    	 setCaretPosition(getDocument().getLength());
	   }

		
	}
  


