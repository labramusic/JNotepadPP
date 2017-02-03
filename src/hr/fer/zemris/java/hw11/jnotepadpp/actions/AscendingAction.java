package hr.fer.zemris.java.hw11.jnotepadpp.actions;

import java.awt.event.KeyEvent;
import java.text.Collator;
import java.util.Locale;

import hr.fer.zemris.java.hw11.jnotepadpp.JNotepadPP;
import hr.fer.zemris.java.hw11.jnotepadpp.local.ILocalizationProvider;

/**
 * Sorts the selected lines ascending with the given collator.
 * 
 * @author labramusic
 *
 */
public class AscendingAction extends SortAction {

	/**
	 * The default serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Initializes an AscendingAction.
	 * 
	 * @param notepad
	 *            the notepad
	 * @param lp
	 *            localization provider
	 */
	public AscendingAction(JNotepadPP notepad, ILocalizationProvider lp) {
		super("sortAscendName", "shift S", KeyEvent.VK_A, "sortAscendDesc", notepad,
				(s1, s2) -> Collator.getInstance(Locale.forLanguageTag(lp.getLanguage())).compare(s1, s2), lp);
	}

}
