package hr.fer.zemris.java.hw11.jnotepadpp.actions;

import java.awt.event.KeyEvent;
import java.text.Collator;
import java.util.Locale;

import hr.fer.zemris.java.hw11.jnotepadpp.JNotepadPP;
import hr.fer.zemris.java.hw11.jnotepadpp.local.ILocalizationProvider;

/**
 * Sorts the selected lines descending with the given collator.
 * 
 * @author labramusic
 *
 */
public class DescendingAction extends SortAction {

	/**
	 * The default serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Initializes a DescendingAction.
	 * 
	 * @param notepad
	 *            the notepad
	 * @param lp
	 *            localization provider
	 */
	public DescendingAction(JNotepadPP notepad, ILocalizationProvider lp) {
		super("sortDescendName", "shift D", KeyEvent.VK_D, "sortDescendDesc", notepad,
				(s1, s2) -> Collator.getInstance(Locale.forLanguageTag(lp.getLanguage())).compare(s2, s1), lp);
	}

}
