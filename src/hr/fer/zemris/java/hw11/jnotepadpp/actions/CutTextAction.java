package hr.fer.zemris.java.hw11.jnotepadpp.actions;

import java.awt.event.KeyEvent;

import hr.fer.zemris.java.hw11.jnotepadpp.JNotepadPP;
import hr.fer.zemris.java.hw11.jnotepadpp.local.ILocalizationProvider;

/**
 * Cuts the selected text from the text editor.
 * 
 * @author labramusic
 *
 */
public class CutTextAction extends TextEditorAction {

	/**
	 * The default serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Initializes a CutTextAction.
	 * 
	 * @param notepad
	 *            the notepad
	 * @param lp
	 *            localization provider
	 */
	public CutTextAction(JNotepadPP notepad, ILocalizationProvider lp) {
		super("cutName", "control X", KeyEvent.VK_T, "cutDesc", notepad, e -> e.cut(), lp);
	}

}
