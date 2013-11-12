package no.hig.irc_client;

import javax.swing.JTextArea;
import javax.swing.Scrollable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

public class TextArea extends JTextArea implements DocumentListener, Scrollable{

	 String newline = "\n";
	public TextArea (){
		
		
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		  updateLog(e, "inserted into");
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		  updateLog(e, "inserted into");
	}
    public void updateLog(DocumentEvent e, String action) {
        Document doc = (Document)e.getDocument();
        int changeLength = e.getLength();
        append(
            changeLength + " character" +
            ((changeLength == 1) ? " " : "s ") +
            action + doc.getProperty("name") + "."   +
            "  Text length = " + doc.getLength() );
    }
    public void write(String txt){
    	append(newline);
    	append(txt);
    }
    
}
