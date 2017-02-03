package hr.fer.zemris.java.hw11.jnotepadpp.actions;

import java.awt.event.KeyEvent;

import hr.fer.zemris.java.hw11.jnotepadpp.JNotepadPP;
import hr.fer.zemris.java.hw11.jnotepadpp.local.ILocalizationProvider;

/**
 * Action which inverts the casing of the selected text.
 * 
 * @author labramusic
 *
 */
public class InvertCaseAction extends CaseAction {

	/**
	 * The default serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Initializes an InvertCaseAction.
	 * 
	 * @param notepad
	 *            the notepad
	 * @param lp
	 *            localization provider
	 */
	public InvertCaseAction(JNotepadPP notepad, ILocalizationProvider lp) {
		super("invertCaseName", "control I", KeyEvent.VK_I, "invertCaseDesc", notepad, text -> {
			char[] chars = text.toCharArray();
			int len = chars.length;
			for (int i = 0; i < len; ++i) {
				char c = chars[i];
				if (Character.isLowerCase(c)) {
					chars[i] = Character.toUpperCase(c);
				} else if (Character.isUpperCase(c)) {
					chars[i] = Character.toLowerCase(c);
				}
			}
			return new String(chars);
		} , lp);
	}

}
