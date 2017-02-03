package hr.fer.zemris.java.hw11.jnotepadpp.actions;

/**
 * Functional interface with a method for modifying text.
 * 
 * @author labramusic
 *
 */
public interface ITextOperation {

	/**
	 * Modifies the given text and returns the result.
	 * 
	 * @param text
	 *            text
	 * @return modified text
	 */
	String modifyText(String text);

}
