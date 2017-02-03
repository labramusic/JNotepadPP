package hr.fer.zemris.java.hw11.jnotepadpp.actions;

import java.awt.event.ActionEvent;

import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import hr.fer.zemris.java.hw11.jnotepadpp.JNotepadPP;
import hr.fer.zemris.java.hw11.jnotepadpp.local.ILocalizationProvider;
import hr.fer.zemris.java.hw11.jnotepadpp.local.swing.LocalizableAction;

/**
 * Abstract class which changes casing of selected text based on the action
 * given by {@link ITextOperation}.
 * 
 * @author labramusic
 *
 */
public abstract class CaseAction extends LocalizableAction {

	/**
	 * The default serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The notepad.
	 */
	private JNotepadPP notepad;

	/**
	 * The text operation to perform.
	 */
	private ITextOperation op;

	/**
	 * Initializes a CaseAction.
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
	 *            text operation
	 * @param lp
	 *            localization provider
	 */
	public CaseAction(String nameKey, String accel, int mnemonic, String descKey, JNotepadPP notepad, ITextOperation op,
			ILocalizationProvider lp) {
		super(nameKey, accel, mnemonic, descKey, lp);
		this.notepad = notepad;
		this.op = op;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JTextArea editor = notepad.getEditor();
		Document doc = editor.getDocument();
		int dotPos = editor.getCaret().getDot();
		int markPos = editor.getCaret().getMark();
		int len = Math.abs(dotPos - markPos);
		int offset = 0;
		if (len == 0)
			return;
		offset = Math.min(dotPos, markPos);

		try {
			String text = doc.getText(offset, len);
			text = op.modifyText(text);
			doc.remove(offset, len);
			doc.insertString(offset, text, null);
			editor.getCaret().setDot(markPos);
			editor.getCaret().moveDot(dotPos);
		} catch (BadLocationException ex) {
			ex.printStackTrace();
		}
	}

}
