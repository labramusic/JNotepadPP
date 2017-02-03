package hr.fer.zemris.java.hw11.jnotepadpp.local.swing;

import javax.swing.JMenu;

import hr.fer.zemris.java.hw11.jnotepadpp.local.ILocalizationProvider;

/**
 * Localizable JMenu.
 * 
 * @author labramusic
 *
 */
public class LJMenu extends JMenu {

	/**
	 * The default serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Initializes a new LJMenu with the given name key and localization
	 * provider.
	 * 
	 * @param key
	 *            name key
	 * @param lp
	 *            localization provider
	 */
	public LJMenu(String key, ILocalizationProvider lp) {
		updateText(key, lp);

		lp.addLocalizationListener(() -> {
			updateText(key, lp);
		});
	}

	/**
	 * Updates menu text.
	 * 
	 * @param key
	 *            name key
	 * @param lp
	 *            localization provider
	 */
	private void updateText(String key, ILocalizationProvider lp) {
		setText(lp.getString(key));
	}

}
