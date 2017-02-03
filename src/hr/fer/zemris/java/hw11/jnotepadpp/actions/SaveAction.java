package hr.fer.zemris.java.hw11.jnotepadpp.actions;

import java.awt.event.ActionEvent;
import java.nio.file.Path;

import hr.fer.zemris.java.hw11.jnotepadpp.JNotepadPP;
import hr.fer.zemris.java.hw11.jnotepadpp.local.ILocalizationProvider;
import hr.fer.zemris.java.hw11.jnotepadpp.local.swing.LocalizableAction;
import hr.fer.zemris.java.hw11.jnotepadpp.util.FileUtil;

/**
 * Abstract action which saves a document to the given file path. The file path
 * can be null.
 * 
 * @author labramusic
 *
 */
public abstract class SaveAction extends LocalizableAction {

	/**
	 * The default serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The notepad.
	 */
	private JNotepadPP notepad;

	/**
	 * Opened file path.
	 */
	private Path openedFilePath;

	/**
	 * Initializes a SaveAction.
	 * 
	 * @param nameKey
	 *            action name key
	 * @param accel
	 *            key accelerator
	 * @param mnemonic
	 *            key mnemonic
	 * @param descKey
	 *            action description key
	 * @param notepad
	 *            the notepad
	 * @param openedFilePath
	 *            openede file path
	 * @param lp
	 *            localization provider
	 */
	public SaveAction(String nameKey, String accel, int mnemonic, String descKey, JNotepadPP notepad,
			Path openedFilePath, ILocalizationProvider lp) {
		super(nameKey, accel, mnemonic, descKey, lp);
		this.notepad = notepad;
		this.openedFilePath = openedFilePath;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		FileUtil.saveFile(notepad, openedFilePath);
	}

}
