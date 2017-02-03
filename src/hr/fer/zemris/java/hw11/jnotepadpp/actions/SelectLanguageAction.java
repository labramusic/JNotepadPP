package hr.fer.zemris.java.hw11.jnotepadpp.actions;

import java.awt.event.ActionEvent;

import hr.fer.zemris.java.hw11.jnotepadpp.local.ILocalizationProvider;
import hr.fer.zemris.java.hw11.jnotepadpp.local.LocalizationProvider;
import hr.fer.zemris.java.hw11.jnotepadpp.local.swing.LocalizableAction;

/**
 * Abstract action which selects the language of the GUI.
 * 
 * @author labramusic
 *
 */
public abstract class SelectLanguageAction extends LocalizableAction {

	/**
	 * The default serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The selected language.
	 */
	private String language;

	/**
	 * Initializes a SelectLanguageAction.
	 * 
	 * @param nameKey
	 *            action name key
	 * @param accel
	 *            key accelerator
	 * @param mnemonic
	 *            key mnemonic
	 * @param descKey
	 *            action description key
	 * @param language
	 *            selected language
	 * @param lp
	 *            localization provider
	 */
	public SelectLanguageAction(String nameKey, String accel, int mnemonic, String descKey, String language,
			ILocalizationProvider lp) {
		super(nameKey, accel, mnemonic, descKey, lp);
		this.language = language;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		LocalizationProvider.getInstance().setLanguage(language);
	}

}
