package hr.fer.zemris.java.hw11.jnotepadpp.actions;

import java.awt.event.KeyEvent;
import java.nio.file.Path;

import hr.fer.zemris.java.hw11.jnotepadpp.JNotepadPP;
import hr.fer.zemris.java.hw11.jnotepadpp.local.ILocalizationProvider;

/**
 * Action which saves a document. If the document is opened in a file path, it's
 * saved on the same file path, otherwise the user is asked to select its file
 * path.
 * 
 * @author labramusic
 *
 */
public class SaveDocumentAction extends SaveAction {

	/**
	 * The default serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Initializes a SaveDocumentAction.
	 * 
	 * @param notepad
	 *            the notepad
	 * @param openedFilePath
	 *            opened file path
	 * @param lp
	 *            localization provider
	 */
	public SaveDocumentAction(JNotepadPP notepad, Path openedFilePath, ILocalizationProvider lp) {
		super("saveName", "control S", KeyEvent.VK_S, "saveDesc", notepad, openedFilePath, lp);
	}

}
