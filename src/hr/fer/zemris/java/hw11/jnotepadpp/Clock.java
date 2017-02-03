package hr.fer.zemris.java.hw11.jnotepadpp;

import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Insets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;

/**
 * A clock component which displays the current time and date.
 * 
 * @author labramusic
 *
 */
public class Clock extends JComponent {

	/**
	 * The default serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The current time and date.
	 */
	private volatile String timeDate;

	/**
	 * True if stop is requested.
	 */
	private volatile boolean stopRequested;

	/**
	 * Date and time formatter of given format.
	 */
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd    HH:mm:ss");

	/**
	 * Instantiates a new clock. Every half a second the time is updated.
	 */
	public Clock() {
		updateTimeDate();

		Thread t = new Thread(() -> {
			while (true) {
				try {
					Thread.sleep(500);
				} catch (Exception ex) {
				}
				if (stopRequested)
					break;
				SwingUtilities.invokeLater(() -> {
					updateTimeDate();
				});
			}
		});
		t.setDaemon(true);
		t.start();
	}

	/**
	 * Stops the clock.
	 */
	public void stop() {
		stopRequested = true;
	}

	/**
	 * Updates the current date and time.
	 */
	private void updateTimeDate() {
		timeDate = formatter.format(LocalDateTime.now());
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		Insets ins = getInsets();
		Dimension dim = getSize();

		FontMetrics fm = g.getFontMetrics();
		int w = fm.stringWidth(timeDate);
		int h = fm.getAscent();

		g.drawString(timeDate, ins.left + dim.width - w, ins.top + h);
	}

}
