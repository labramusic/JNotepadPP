package hr.fer.zemris.java.hw11.jnotepadpp.actions;

import java.awt.event.KeyEvent;

import hr.fer.zemris.java.hw11.jnotepadpp.local.ILocalizationProvider;

/**
 * Action which selects Croatian as the current language.
 * 
 * @author labramusic
 *
 */
public class SelectCroatianAction extends SelectLanguageAction {

	/**
	 * The default serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Initializes a SelectCroatianAction.
	 * 
	 * @param lp
	 *            localization provider
	 */
	public SelectCroatianAction(ILocalizationProvider lp) {
		super("selCroatianName", "control 1", KeyEvent.VK_H, "selCroatianDesc", "hr", lp);
	}

}
