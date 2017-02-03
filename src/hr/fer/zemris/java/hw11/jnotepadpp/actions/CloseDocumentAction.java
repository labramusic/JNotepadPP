package hr.fer.zemris.java.hw11.jnotepadpp.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import hr.fer.zemris.java.hw11.jnotepadpp.JNotepadPP;
import hr.fer.zemris.java.hw11.jnotepadpp.local.ILocalizationProvider;
import hr.fer.zemris.java.hw11.jnotepadpp.local.swing.LocalizableAction;
import hr.fer.zemris.java.hw11.jnotepadpp.util.FileUtil;

/**
 * Action which closes the currently opened document. The user is asked if he
 * wants to save changes to the document before closing, if the document was
 * previously modified.
 * 
 * @author labramusic
 *
 */
public class CloseDocumentAction extends LocalizableAction {

	/**
	 * The default serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The notepad.
	 */
	private JNotepadPP notepad;

	/**
	 * Initializes a CloseDocumentAction.
	 * 
	 * @param notepad
	 *            the notepad
	 * @param lp
	 *            localization provider
	 */
	public CloseDocumentAction(JNotepadPP notepad, ILocalizationProvider lp) {
		super("closeName", "control W", KeyEvent.VK_C, "closeDesc", lp);
		this.notepad = notepad;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		FileUtil.closeTab(notepad, notepad.getTabbedPane().getSelectedIndex());
	}

}
