package hr.fer.zemris.java.hw11.jnotepadpp.actions;

import java.awt.event.KeyEvent;

import hr.fer.zemris.java.hw11.jnotepadpp.JNotepadPP;
import hr.fer.zemris.java.hw11.jnotepadpp.local.ILocalizationProvider;

/**
 * Action which saves a document as a new document. If the new file path exists,
 * the user is asked if he wants to overwrite the file.
 * 
 * @author labramusic
 *
 */
public class SaveDocumentAsAction extends SaveAction {

	/**
	 * The default serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Initializes a SaveDocumentAsAction.
	 * 
	 * @param notepad
	 *            the notepad
	 * @param lp
	 *            localization provider
	 */
	public SaveDocumentAsAction(JNotepadPP notepad, ILocalizationProvider lp) {
		super("saveAsName", "control shift S", KeyEvent.VK_A, "saveAsDesc", notepad, null, lp);
	}

}
