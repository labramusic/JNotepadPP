package hr.fer.zemris.java.hw11.jnotepadpp.actions;

import java.awt.event.KeyEvent;

import hr.fer.zemris.java.hw11.jnotepadpp.local.ILocalizationProvider;

/**
 * Action which selects German as the current language.
 * 
 * @author labramusic
 *
 */
public class SelectGermanAction extends SelectLanguageAction {

	/**
	 * The default serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Initializes a SelectGermanAction.
	 * 
	 * @param lp
	 *            localization provider
	 */
	public SelectGermanAction(ILocalizationProvider lp) {
		super("selGermanName", "control 3", KeyEvent.VK_D, "selGermanDesc", "de", lp);
	}

}
