package no.hig.irc_client;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.plaf.basic.BasicButtonUI;
/**
 * Implemts a closeButton and label on the top of the tab
 * @author hyw
 *
 */
public class CloseButton extends JButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CloseButton() {
		int size = 17;
		setPreferredSize(new Dimension(size, size));
		setToolTipText(Language.getMsg("closeTab"));
		// Make the button looks the same for all Laf's
		setUI(new BasicButtonUI());
		// Make it transparent
		setContentAreaFilled(false);
		// No need to be focusable
		setFocusable(false);
		setBorder(BorderFactory.createEtchedBorder());
		setBorderPainted(false);
		// Making nice rollover effect
		

		setRolloverEnabled(true);
		// Close the proper tab by clicking the button

	}

	// we don't want to update UI for this button
	public void updateUI() {
	}

	/**
	 * Paints a cros x  at the tab
	 */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g.create();
		// shift the image for pressed buttons
		if (getModel().isPressed()) {
			g2.translate(1, 1);
		}
		g2.setStroke(new BasicStroke(2));
		g2.setColor(Color.BLACK);
		if (getModel().isRollover()) {
			g2.setColor(Color.MAGENTA);
		}
		int delta = 6;
		g2.drawLine(delta, delta, getWidth() - delta - 1, getHeight() - delta
				- 1);
		g2.drawLine(getWidth() - delta - 1, delta, delta, getHeight() - delta
				- 1);
		g2.dispose();
	}

}
