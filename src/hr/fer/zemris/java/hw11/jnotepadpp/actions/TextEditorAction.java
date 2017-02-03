package hr.fer.zemris.java.hw11.jnotepadpp.actions;

import java.awt.event.ActionEvent;

import hr.fer.zemris.java.hw11.jnotepadpp.JNotepadPP;
import hr.fer.zemris.java.hw11.jnotepadpp.local.ILocalizationProvider;
import hr.fer.zemris.java.hw11.jnotepadpp.local.swing.LocalizableAction;

/**
 * Abstract action which performs an operation given by {@link IEditorOperation}
 * in the text of the editor.
 * 
 * @author labramusic
 *
 */
public abstract class TextEditorAction extends LocalizableAction {

	/**
	 * The default serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The notepad.
	 */
	private JNotepadPP notepad;

	/**
	 * Editor operation.
	 */
	private IEditorOperation op;

	/**
	 * Initializes a TextEditorAction.
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
	 * @param op
	 *            editor operation
	 * @param lp
	 *            localization provider
	 */
	public TextEditorAction(String nameKey, String accel, int mnemonic, String descKey, JNotepadPP notepad,
			IEditorOperation op, ILocalizationProvider lp) {
		super(nameKey, accel, mnemonic, descKey, lp);
		this.notepad = notepad;
		this.op = op;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		op.doOperation(notepad.getEditor());
	}

}
