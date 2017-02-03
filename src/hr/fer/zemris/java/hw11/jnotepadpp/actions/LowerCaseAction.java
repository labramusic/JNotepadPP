package hr.fer.zemris.java.hw11.jnotepadpp.actions;

import java.awt.event.KeyEvent;

import hr.fer.zemris.java.hw11.jnotepadpp.JNotepadPP;
import hr.fer.zemris.java.hw11.jnotepadpp.local.ILocalizationProvider;

/**
 * Action which sets the casing of the selected text to lowercase.
 * 
 * @author labramusic
 *
 */
public class LowerCaseAction extends CaseAction {

	/**
	 * The default serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Initializes a LowerCaseAction.
	 * 
	 * @param notepad
	 *            the notepad
	 * @param lp
	 *            localization provider
	 */
	public LowerCaseAction(JNotepadPP notepad, ILocalizationProvider lp) {
		super("lowerCaseName", "control L", KeyEvent.VK_L, "lowerCaseDesc", notepad, text -> text.toLowerCase(), lp);
	}

}
