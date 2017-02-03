package hr.fer.zemris.java.hw11.jnotepadpp.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import hr.fer.zemris.java.hw11.jnotepadpp.JNotepadPP;
import hr.fer.zemris.java.hw11.jnotepadpp.local.ILocalizationProvider;
import hr.fer.zemris.java.hw11.jnotepadpp.local.swing.LocalizableAction;

/**
 * Action which removes all duplicate lines from the selection.
 * 
 * @author labramusic
 *
 */
public class UniqueAction extends LocalizableAction {

	/**
	 * The default serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The notepad
	 */
	private JNotepadPP notepad;

	/**
	 * Initializes a UniqueAction.
	 * 
	 * @param notepad
	 *            the notepad
	 * @param lp
	 *            localization provider
	 */
	public UniqueAction(JNotepadPP notepad, ILocalizationProvider lp) {
		super("uniqueName", "control U", KeyEvent.VK_U, "uniqueDesc", lp);
		this.notepad = notepad;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JTextArea editor = notepad.getEditor();
		Document doc = editor.getDocument();
		Set<String> lines = new LinkedHashSet<>();
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

			int startOffset = editor.getLineStartOffset(firstLine);
			for (String line : lines) {
				doc.insertString(startOffset, line + "\n", null);
				int endOffset = startOffset + line.length();
				startOffset = endOffset + 1;
			}

		} catch (BadLocationException ex) {
			ex.printStackTrace();
		}
	}

}
