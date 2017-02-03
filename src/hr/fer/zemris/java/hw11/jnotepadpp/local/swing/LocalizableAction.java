package hr.fer.zemris.java.hw11.jnotepadpp.local.swing;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;

import hr.fer.zemris.java.hw11.jnotepadpp.local.ILocalizationProvider;

/**
 * Represents an abstract action which is localizable with the given
 * localization provider.
 * 
 * @author labramusic
 *
 */
public abstract class LocalizableAction extends AbstractAction {

	/**
	 * The default serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Initializes a new localizable action with the given keys and localization
	 * provider.
	 * 
	 * @param nameKey
	 *            action name key
	 * @param accel
	 *            key accelerator
	 * @param mnemonic
	 *            key mnemonic
	 * @param descKey
	 *            action description key
	 * @param lp
	 *            localization provider
	 */
	public LocalizableAction(String nameKey, String accel, int mnemonic, String descKey, ILocalizationProvider lp) {
		updateValues(nameKey, accel, mnemonic, descKey, lp);

		lp.addLocalizationListener(() -> {
			updateValues(nameKey, accel, mnemonic, descKey, lp);
		});

	}

	/**
	 * Updates the values of the action map.
	 * 
	 * @param nameKey
	 *            action name key
	 * @param accel
	 *            key accelerator
	 * @param mnemonic
	 *            key mnemonic
	 * @param descKey
	 *            action description key
	 * @param lp
	 *            localization provider
	 */
	private void updateValues(String nameKey, String accel, int mnemonic, String descKey, ILocalizationProvider lp) {
		putValue(Action.NAME, lp.getString(nameKey));
		putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(accel));
		putValue(Action.MNEMONIC_KEY, mnemonic);
		putValue(Action.SHORT_DESCRIPTION, lp.getString(descKey));
	}

}
