package hr.fer.zemris.java.hw11.jnotepadpp.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;

import hr.fer.zemris.java.hw11.jnotepadpp.JNotepadPP;
import hr.fer.zemris.java.hw11.jnotepadpp.local.ILocalizationProvider;
import hr.fer.zemris.java.hw11.jnotepadpp.local.swing.LocalizableAction;

/**
 * Action which displays document statistics in a new window. The information
 * displayed includes number of characters, number of non-blank characters and
 * number of lines.
 * 
 * @author labramusic
 *
 */
public class StatisticsAction extends LocalizableAction {

	/**
	 * The default serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The notepad.
	 */
	private JNotepadPP notepad;

	/**
	 * Initializes a StatisticsAction.
	 * 
	 * @param notepad
	 *            the notepad
	 * @param lp
	 *            localization provider
	 */
	public StatisticsAction(JNotepadPP notepad, ILocalizationProvider lp) {
		super("statsName", "control T", KeyEvent.VK_S, "statsDesc", lp);
		this.notepad = notepad;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String text = notepad.getEditor().getText();
		int charCount = text.length();
		int letterCount = text.replaceAll("\\s", "").length();
		int lines = notepad.getEditor().getLineCount();
		String message = "Number of characters: " + charCount + "\nNumber of non-blank characters: " + letterCount
				+ "\nNumber of lines: " + lines;

		JOptionPane.showMessageDialog(notepad, message, "Document statistics", JOptionPane.INFORMATION_MESSAGE);
	}

}
