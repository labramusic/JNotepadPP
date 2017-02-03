package hr.fer.zemris.java.hw11.jnotepadpp.local.swing;

import javax.swing.JToolBar;

import hr.fer.zemris.java.hw11.jnotepadpp.local.ILocalizationProvider;

/**
 * Localizable JToolBar.
 * 
 * @author labramusic
 *
 */
public class LJToolBar extends JToolBar {

	/**
	 * The default serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Initializes a new LJToolBar with the given name key and the localization
	 * provider.
	 * 
	 * @param key
	 *            name key
	 * @param lp
	 *            localization providjer
	 */
	public LJToolBar(String key, ILocalizationProvider lp) {
		updateText(key, lp);

		lp.addLocalizationListener(() -> {
			updateText(key, lp);
		});
	}

	/**
	 * Updates the toolbar text.
	 * 
	 * @param key
	 *            name key
	 * @param lp
	 *            localization provider
	 */
	private void updateText(String key, ILocalizationProvider lp) {
		setName(lp.getString(key));
	}

}
