package hr.fer.zemris.java.hw11.jnotepadpp.actions;

import java.awt.event.KeyEvent;

import hr.fer.zemris.java.hw11.jnotepadpp.JNotepadPP;
import hr.fer.zemris.java.hw11.jnotepadpp.local.ILocalizationProvider;

/**
 * Action which copies the selected text from the text editor.
 * 
 * @author labramusic
 *
 */
public class CopyTextAction extends TextEditorAction {

	/**
	 * The default serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Initializes a CopyTextAction.
	 * 
	 * @param notepad
	 *            the notepad
	 * @param lp
	 *            localization provider
	 */
	public CopyTextAction(JNotepadPP notepad, ILocalizationProvider lp) {
		super("copyName", "control C", KeyEvent.VK_C, "copyDesc", notepad, e -> e.copy(), lp);
	}

}
