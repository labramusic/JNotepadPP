package hr.fer.zemris.java.hw11.jnotepadpp.actions;

import java.awt.event.KeyEvent;

import hr.fer.zemris.java.hw11.jnotepadpp.JNotepadPP;
import hr.fer.zemris.java.hw11.jnotepadpp.local.ILocalizationProvider;

/**
 * Action which pastes text to the text editor.
 * 
 * @author labramusic
 *
 */
public class PasteTextAction extends TextEditorAction {

	/**
	 * The default serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Initializes a PasteTextAction.
	 * 
	 * @param notepad
	 *            the notepad
	 * @param lp
	 *            localization provider
	 */
	public PasteTextAction(JNotepadPP notepad, ILocalizationProvider lp) {
		super("pasteName", "control V", KeyEvent.VK_P, "pasteDesc", notepad, e -> e.paste(), lp);
	}

}
