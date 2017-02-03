package hr.fer.zemris.java.hw11.jnotepadpp.actions;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import hr.fer.zemris.java.hw11.jnotepadpp.JNotepadPP;
import hr.fer.zemris.java.hw11.jnotepadpp.local.ILocalizationProvider;
import hr.fer.zemris.java.hw11.jnotepadpp.local.swing.LocalizableAction;

/**
 * Abstract action which sorts the selected lines with the given comparator.
 * 
 * @author labramusic
 *
 */
public abstract class SortAction extends LocalizableAction {

	/**
	 * The default serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The notepad.
	 */
	private JNotepadPP notepad;

	/**
	 * Comparator for sorting.
	 */
	private Comparator<String> comparator;

	/**
	 * Initializes a SortAction.
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
	 * @param comparator
	 *            comparator
	 * @param lp
	 *            localization provider
	 */
	public SortAction(String nameKey, String accel, int mnemonic, String descKey, JNotepadPP notepad,
			Comparator<String> comparator, ILocalizationProvider lp) {
		super(nameKey, accel, mnemonic, descKey, lp);
		this.notepad = notepad;
		this.comparator = comparator;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JTextArea editor = notepad.getEditor();
		Document doc = editor.getDocument();
		List<String> lines = new ArrayList<>();
		int dotPos = editor.getCaret().getDot();
		int markPos = editor.getCaret().getMark();
		try {
			int firstLine = editor.getLineOfOffset(dotPos);
			int lastLine = editor.getLineOfOffset(markPos);
			for (int lineIndex = firstLine; lineIndex <= lastLine; ++lineIndex) {
				int startOffset = editor.getLineStartOffset(firstLine);
				int endOffset = editor.getLineEndOffset(firstLine);
				int len = endOffset - startOffset;
				String line = doc.getText(startOffset, len);
				line = line.replace("\n", "");
				lines.add(line);
				doc.remove(startOffset, len);
			}

			Collections.sort(lines, comparator);

			int startOffset = editor.getLineStartOffset(firstLine); // ?
			for (String line : lines) {
				doc.insertString(startOffset, line + "\n", null);
				int endOffset = startOffset + line.length(); // ?
				startOffset = endOffset + 1;
			}

		} catch (BadLocationException ex) {
			ex.printStackTrace();
		}

	}

}
