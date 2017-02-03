package hr.fer.zemris.java.hw11.jnotepadpp.local;

/**
 * Provides translation for given key.
 * 
 * @author labramusic
 *
 */
public interface ILocalizationProvider {

	/**
	 * Adds {@link ILocalizationListener} to the listeners list.
	 * 
	 * @param listener
	 *            localization listener
	 */
	void addLocalizationListener(ILocalizationListener listener);

	/**
	 * Removes {@link ILocalizationListener} from the listeners list.
	 * 
	 * @param listener
	 *            localization listener
	 */
	void removeLocalizationListener(ILocalizationListener listener);

	/**
	 * Returns localization for given key.
	 * 
	 * @param key
	 *            given key
	 * @return localization string
	 */
	String getString(String key);

	/**
	 * Returns the currently selected language.
	 * 
	 * @return current language
	 */
	String getLanguage();

}
