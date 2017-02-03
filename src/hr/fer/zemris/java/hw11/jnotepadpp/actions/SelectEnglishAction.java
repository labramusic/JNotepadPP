package hr.fer.zemris.java.hw11.jnotepadpp.actions;

import java.awt.event.KeyEvent;

import hr.fer.zemris.java.hw11.jnotepadpp.local.ILocalizationProvider;

/**
 * Action which selects English as the current language.
 * 
 * @author labramusic
 *
 */
public class SelectEnglishAction extends SelectLanguageAction {

	/**
	 * The default serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Initializes a SelectEnglishAction.
	 * 
	 * @param lp
	 *            localization provider
	 */
	public SelectEnglishAction(ILocalizationProvider lp) {
		super("selEnglishName", "control 2", KeyEvent.VK_E, "selEnglishDesc", "en", lp);
	}

}
