package hr.fer.zemris.java.hw11.jnotepadpp.actions;

import javax.swing.JTextArea;

/**
 * Functional interface with a method for performing an operation on the given
 * JTextArea.
 * 
 * @author labramusic
 *
 */
public interface IEditorOperation {

	/**
	 * Does an operation on the given JTextArea.
	 * 
	 * @param editor
	 *            text editor
	 */
	void doOperation(JTextArea editor);

}
