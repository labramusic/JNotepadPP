package hr.fer.zemris.java.hw11.jnotepadpp.local.swing;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import hr.fer.zemris.java.hw11.jnotepadpp.local.ILocalizationProvider;
import hr.fer.zemris.java.hw11.jnotepadpp.local.LocalizationProviderBridge;

/**
 * Defines the window listener for the given frame. The localization provider is
 * connected when the window opened, and disconnected when the window is closed.
 * 
 * @author labramusic
 *
 */
public class FormLocalizationProvider extends LocalizationProviderBridge {

	/**
	 * Initializes a new FormLocalizationProvider.
	 * 
	 * @param parent
	 *            localization provider
	 * @param frame
	 *            the frame
	 */
	public FormLocalizationProvider(ILocalizationProvider parent, JFrame frame) {
		super(parent);
		frame.addWindowListener(new WindowAdapter() {
			// registers itself as window listener

			@Override
			public void windowOpened(WindowEvent e) {
				connect();
			}

			@Override
			public void windowClosed(WindowEvent e) {
				disconnect();
			}
		});
	}

}
