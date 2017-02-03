package hr.fer.zemris.java.hw11.jnotepadpp.util;

import java.awt.BorderLayout;
import java.awt.Font;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import hr.fer.zemris.java.hw11.jnotepadpp.JNotepadPP;
import hr.fer.zemris.java.hw11.jnotepadpp.TextPanel;

/**
 * Utility class for working with files.
 * 
 * @author labramusic
 *
 */
public class FileUtil {

	/**
	 * Creates a new tab with its text area, and adds a listener to it which
	 * listens to document modifications.
	 * 
	 * @param notepad
	 *            the notepad
	 * @param title
	 *            tab title
	 * @param filePath
	 *            opened file path
	 */
	public static void newTab(JNotepadPP notepad, String title, Path filePath) {
		JTextArea editor = new JTextArea();
		editor.setBorder(BorderFactory.createEmptyBorder(1, 2, 0, 0));
		editor.setFont(new Font("Arial", Font.PLAIN, 12));
		editor.addCaretListener((e) -> {
			updateStatus(notepad, editor);
		});

		TextPanel textPanel = new TextPanel(editor, filePath);
		textPanel.setLayout(new BorderLayout());
		textPanel.add(new JScrollPane(editor), BorderLayout.CENTER);
		textPanel.setModified(false);

		notepad.getTabbedPane().addTab(title, textPanel);
		notepad.getToolsMenu().setEnabled(false);

		int index = notepad.getTabbedPane().getTabCount() - 1;
		notepad.getTabbedPane().setSelectedIndex(index);
		ImageIcon icon = getIconFromPath("icons/saved_disk.png");
		notepad.getTabbedPane().setIconAt(index, icon);
		if (filePath != null) {
			notepad.getTabbedPane().setToolTipTextAt(index, filePath.toString());
		}

		editor.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				update();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				update();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
			}

			private void update() {
				if (!textPanel.isModified()) {
					textPanel.setModified(true);
					int index = notepad.getTabbedPane().getSelectedIndex();
					String title = notepad.getTabbedPane().getTitleAt(index);
					notepad.getTabbedPane().setTitleAt(index, "*" + title);
					ImageIcon icon = getIconFromPath("icons/unsaved_disk.png");
					notepad.getTabbedPane().setIconAt(index, icon);
				}
			}
		});
	}

	/**
	 * Closes the tab at given index and returns true if user didn't abort the
	 * operation.
	 * 
	 * @param notepad
	 *            the notepad
	 * @param index
	 *            tab index
	 * @return true if tab closed successfully
	 */
	public static boolean closeTab(JNotepadPP notepad, int index) {
		JTabbedPane tabbedPane = notepad.getTabbedPane();
		TextPanel textPanel = (TextPanel) tabbedPane.getComponentAt(index);
		// if unsaved changes
		boolean notAborted = true;
		if (textPanel.isModified()) {
			String title = tabbedPane.getTitleAt(index).replaceFirst("\\*", "");
			int decision = JOptionPane.showConfirmDialog(notepad, "Save changes before closing tab \"" + title + "\" ?",
					"System message", JOptionPane.YES_NO_CANCEL_OPTION);
			// save discard or abort
			if (decision != JOptionPane.CANCEL_OPTION) {
				if (decision == JOptionPane.YES_OPTION) {
					Path openedFilePath = textPanel.getOpenedFilePath();
					notAborted = saveFile(notepad, openedFilePath);
				}
			} else {
				notAborted = false;
			}
		}

		if (notAborted) {
			if (tabbedPane.getTabCount() > 1) {
				tabbedPane.removeTabAt(index);
			} else {
				notepad.dispose();
			}
			return true;
		}
		return false;
	}

	/**
	 * Saves the file to the given path and returns true if user didn't abort
	 * the operation.
	 * 
	 * @param notepad
	 *            the notepad
	 * @param openedFilePath
	 *            opened file path
	 * @return true if saved successfully
	 */
	public static boolean saveFile(JNotepadPP notepad, Path openedFilePath) {
		int index = notepad.getTabbedPane().getSelectedIndex();
		TextPanel textPanel = (TextPanel) notepad.getTabbedPane().getComponentAt(index);
		JTextArea editor = textPanel.getEditor();

		if (openedFilePath == null) {
			JFileChooser fc = new JFileChooser();
			fc.setDialogTitle("Save document");
			if (fc.showOpenDialog(notepad) != JFileChooser.APPROVE_OPTION) {
				JOptionPane.showMessageDialog(notepad, "Nothing was saved.", "Warning", JOptionPane.WARNING_MESSAGE);
				return false;
			}
			openedFilePath = fc.getSelectedFile().toPath();
			if (openedFilePath.toFile().exists()) {
				int decision = JOptionPane.showConfirmDialog(notepad, "File exists. Overwrite?", "System message",
						JOptionPane.YES_NO_CANCEL_OPTION);
				if (decision != JOptionPane.YES_OPTION) {
					return false;
				}
			}

			notepad.setOpenedFilePath(openedFilePath);
			textPanel.setOpenedFilePath(openedFilePath);

			String title = openedFilePath.getFileName().toString();
			notepad.getTabbedPane().setTitleAt(index, title);
			notepad.getTabbedPane().setToolTipTextAt(index, openedFilePath.toString());
			notepad.setTitle(openedFilePath + " - JNotepad++");
		}

		byte[] data = editor.getText().getBytes(StandardCharsets.UTF_8);
		try {
			Files.write(openedFilePath, data);
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(notepad, "Error saving file " + openedFilePath + ".", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (textPanel.isModified()) {
			textPanel.setModified(false);
			String title = notepad.getTabbedPane().getTitleAt(index);
			notepad.getTabbedPane().setTitleAt(index, title.replaceFirst("\\*", ""));
			ImageIcon icon = getIconFromPath("icons/saved_disk.png");
			notepad.getTabbedPane().setIconAt(index, icon);
		}

		JOptionPane.showMessageDialog(notepad, "File saved.", "Information", JOptionPane.INFORMATION_MESSAGE);

		return true;
	}

	/**
	 * Exits the application.
	 * 
	 * @param notepad
	 *            the notepad
	 */
	public static void exitApplication(JNotepadPP notepad) {
		int count = notepad.getTabbedPane().getTabCount();
		int index = 0;
		for (int i = 0; i < count; ++i) {
			boolean closed = closeTab(notepad, index);
			if (!closed) {
				++index;
			}
		}
	}

	/**
	 * Loads the icon image from given path. The path is independent from the
	 * physical location.
	 * 
	 * @param pathName
	 *            path name
	 * @return image icon
	 */
	private static ImageIcon getIconFromPath(String pathName) {
		InputStream is = JNotepadPP.class.getResourceAsStream(pathName);
		if (is == null) {
			System.err.println("The file at " + pathName + " doesn't exist.");
		}
		byte[] bytes = readAllBytes(is);
		try {
			is.close();
		} catch (IOException ignorable) {
		}
		return new ImageIcon(bytes);
	}

	/**
	 * Reads all bytes from the given input stream to a byte array.
	 * 
	 * @param is
	 *            input stream
	 * @return byte array from input stream
	 */
	private static byte[] readAllBytes(InputStream is) {
		final int buffer_size = 4096;
		byte[] data = null;
		byte[] buffer = new byte[buffer_size];
		int r;
		try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
			while ((r = is.read(buffer)) >= 1) {
				os.write(buffer, 0, r);
			}
			data = os.toByteArray();
		} catch (IOException e) {
			System.err.println("Error while reading from stream.");
		}
		return data;
	}

	/**
	 * Updates the status bar based on the caret position.
	 * 
	 * @param notepad
	 *            the notepad
	 * @param editor
	 *            the text editor
	 */
	public static void updateStatus(JNotepadPP notepad, JTextArea editor) {
		int dotPos = editor.getCaret().getDot();
		int markPos = editor.getCaret().getMark();
		if (dotPos != markPos) {
			notepad.getToolsMenu().setEnabled(true);
		} else {
			notepad.getToolsMenu().setEnabled(false);
		}

		int len = editor.getText().length();
		notepad.getLengthLabel().setValue(len);
		try {
			int line = editor.getLineOfOffset(editor.getCaretPosition());
			int col = editor.getCaretPosition() - editor.getLineStartOffset(line);
			;
			int sel = Math.abs(dotPos - markPos);
			notepad.getPosLabel().setValues(line, col, sel);
		} catch (Exception ignorable) {
		}
	}

}
