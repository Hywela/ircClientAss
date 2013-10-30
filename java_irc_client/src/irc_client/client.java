package irc_client;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import irc_client.tabs;

public class client  {
	
private JFrame frame;
    private final JTabbedPane pane = new JTabbedPane();
    private final JButton b = new JButton("Rename Button");

    public client(String title) {
        
    }
    public client(JFrame frame) {
        this.frame = frame;
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        b.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                int selectedIndex = pane.getSelectedIndex();
                pane.setTitleAt(selectedIndex, "Aa");
            }
        });
        this.frame.add(b,BorderLayout.NORTH);
        this.frame.add(pane);
    }

    public void newTab(String title, int index) {
        
      
            
            pane.add(title, new JLabel(title));
            initTabComponent(index);
        
        pane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
        frame.setSize(new Dimension(700, 300));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }



    private void initTabComponent(int i) {
        pane.setTabComponentAt(i,
                 new tabs(pane));
    }
}