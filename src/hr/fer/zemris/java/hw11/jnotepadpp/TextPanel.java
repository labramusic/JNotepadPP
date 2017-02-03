package hr.fer.zemris.java.hw11.jnotepadpp;

import java.nio.file.Path;

import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * Panel which resides in a tab. Contains its text editor and the path of the
 * opened file.
 * 
 * @author labramusic
 *
 */
public class TextPanel extends JPanel {

	/**
	 * The default serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Text editor.
	 */
	private JTextArea editor;

	/**
	 * Opened file path.
	 */
	private Path openedFilePath;

	/** True if document was modified. */
	private boolean modified;

	/**
	 * Instantiates a new text panel.
	 *
	 * @param editor
	 *            the editor
	 * @param openedFilePath
	 *            the opened file path
	 */
	public TextPanel(JTextArea editor, Path openedFilePath) {
		this.editor = editor;
		this.openedFilePath = openedFilePath;
		modified = true;
	}

	/**
	 * Gets the text editor.
	 *
	 * @return the text editor
	 */
	public JTextArea getEditor() {
		return editor;
	}

	/**
	 * Gets the opened file path.
	 *
	 * @return the opened file path
	 */
	public Path getOpenedFilePath() {
		return openedFilePath;
	}

	/**
	 * Sets the opened file path.
	 *
	 * @param filePath
	 *            the new opened file path
	 */
	public void setOpenedFilePath(Path filePath) {
		this.openedFilePath = filePath;
	}

	/**
	 * Checks if document is modified.
	 *
	 * @return true if document is modified
	 */
	public boolean isModified() {
		return modified;
	}

	/**
	 * Sets the document modified state.
	 *
	 * @param modified
	 *            the new modified state
	 */
	public void setModified(boolean modified) {
		this.modified = modified;
	}

}
