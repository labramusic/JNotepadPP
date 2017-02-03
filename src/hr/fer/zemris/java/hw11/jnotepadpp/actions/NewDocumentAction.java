package hr.fer.zemris.java.hw11.jnotepadpp.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import hr.fer.zemris.java.hw11.jnotepadpp.JNotepadPP;
import hr.fer.zemris.java.hw11.jnotepadpp.local.ILocalizationProvider;
import hr.fer.zemris.java.hw11.jnotepadpp.local.swing.LocalizableAction;
import hr.fer.zemris.java.hw11.jnotepadpp.util.FileUtil;

/**
 * Action which creates a new document in a new tab. The default tab name is
 * "untitled".
 * 
 * @author labramusic
 *
 */
public class NewDocumentAction extends LocalizableAction {

	/**
	 * The default serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The notepad.
	 */
	private JNotepadPP notepad;

	/**
	 * Localization provider.
	 */
	private ILocalizationProvider lp;

	/**
	 * Initializes a NewDocumentAction.
	 * 
	 * @param notepad
	 *            the notepad
	 * @param lp
	 *            localization provider
	 */
	public NewDocumentAction(JNotepadPP notepad, ILocalizationProvider lp) {
		super("newName", "control N", KeyEvent.VK_N, "newDesc", lp);
		this.notepad = notepad;
		this.lp = lp;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		FileUtil.newTab(notepad, lp.getString("untitledTabName"), null);
	}

}
