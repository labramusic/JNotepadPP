package hr.fer.zemris.java.hw11.jnotepadpp.actions;

import java.awt.event.KeyEvent;

import hr.fer.zemris.java.hw11.jnotepadpp.JNotepadPP;
import hr.fer.zemris.java.hw11.jnotepadpp.local.ILocalizationProvider;

/**
 * Action which sets the casing of the selected text to uppercase.
 * 
 * @author labramusic
 *
 */
public class UpperCaseAction extends CaseAction {

	/**
	 * The default serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Initializes an UpperCaseAction.
	 * 
	 * @param notepad
	 *            the notepad
	 * @param lp
	 *            localization provider
	 */
	public UpperCaseAction(JNotepadPP notepad, ILocalizationProvider lp) {
		super("upperCaseName", "control U", KeyEvent.VK_U, "upperCaseDesc", notepad, text -> text.toUpperCase(), lp);
	}

}
