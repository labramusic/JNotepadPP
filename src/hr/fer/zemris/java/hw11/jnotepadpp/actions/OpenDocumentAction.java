package hr.fer.zemris.java.hw11.jnotepadpp.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import hr.fer.zemris.java.hw11.jnotepadpp.JNotepadPP;
import hr.fer.zemris.java.hw11.jnotepadpp.TextPanel;
import hr.fer.zemris.java.hw11.jnotepadpp.local.ILocalizationProvider;
import hr.fer.zemris.java.hw11.jnotepadpp.local.swing.LocalizableAction;
import hr.fer.zemris.java.hw11.jnotepadpp.util.FileUtil;

/**
 * Action which opens a document in a new tab. The user is asked to select the
 * file path of the document to open.
 * 
 * @author labramusic
 *
 */
public class OpenDocumentAction extends LocalizableAction {

	/**
	 * The default serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The notepad.
	 */
	private JNotepadPP notepad;

	/**
	 * Initializes an OpenDocumentAction.
	 * 
	 * @param notepad
	 *            the notepad
	 * @param lp
	 *            localization provider
	 */
	public OpenDocumentAction(JNotepadPP notepad, ILocalizationProvider lp) {
		super("openName", "control O", KeyEvent.VK_O, "openDesc", lp);
		this.notepad = notepad;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("Open file");
		if (fc.showOpenDialog(notepad) != JFileChooser.APPROVE_OPTION) {
			return;
		}
		File fileName = fc.getSelectedFile();
		Path filePath = fileName.toPath();
		if (!Files.isReadable(filePath)) {
			JOptionPane.showMessageDialog(notepad, "File " + fileName.getAbsolutePath() + " doesn't exist!", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		byte[] data;
		try {
			data = Files.readAllBytes(filePath);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(notepad, "Error reading file " + fileName.getAbsolutePath() + ".", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		// open in new tab
		String title = filePath.getFileName().toString();
		FileUtil.newTab(notepad, title, filePath);

		int index = notepad.getTabbedPane().getSelectedIndex();
		TextPanel textPanel = (TextPanel) notepad.getTabbedPane().getComponentAt(index);
		JTextArea editor = textPanel.getEditor();

		// prevent event triggering
		textPanel.setModified(true);
		String text = new String(data, StandardCharsets.UTF_8);
		editor.setText(text);
		notepad.setOpenedFilePath(filePath);

		notepad.setTitle(filePath + " - JNotepad++");
		textPanel.setModified(false);
	}

}
