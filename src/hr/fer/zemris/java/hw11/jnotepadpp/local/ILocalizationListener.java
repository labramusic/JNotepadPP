package hr.fer.zemris.java.hw11.jnotepadpp.local;

/**
 * The observer called by the localization provider(subject) when the selected
 * language changes.
 * 
 * @author labramusic
 *
 */
public interface ILocalizationListener {

	/**
	 * Signals that the localization was changed.
	 */
	void localizationChanged();

}
