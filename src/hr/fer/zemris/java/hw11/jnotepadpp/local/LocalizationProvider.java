package hr.fer.zemris.java.hw11.jnotepadpp.local;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Localization provider which sets the current language and provides the
 * translation from the bundle.
 * 
 * @author labramusic
 *
 */
public class LocalizationProvider extends AbstractLocalizationProvider {

	/**
	 * The instance of the localization provider.
	 */
	private static LocalizationProvider instance;

	/**
	 * The current language.
	 */
	private String language;

	/**
	 * The resource bundle.
	 */
	private ResourceBundle bundle;

	/**
	 * Initializes new LocalizationProvider with English as the default
	 * language.
	 */
	private LocalizationProvider() {
		this.language = "en";
		Locale locale = Locale.forLanguageTag(language);
		bundle = ResourceBundle.getBundle("hr.fer.zemris.java.hw11.jnotepadpp.local.translations", locale);
	}

	/**
	 * Gets the instance of the localization provider.
	 * 
	 * @return localization provider instance
	 */
	public static LocalizationProvider getInstance() {
		if (instance == null) {
			instance = new LocalizationProvider();
		}
		return instance;
	}

	/**
	 * Sets the current language and informs all listeners.
	 * 
	 * @param language
	 *            current language
	 */
	public void setLanguage(String language) {
		this.language = language;
		Locale locale = Locale.forLanguageTag(language);
		bundle = ResourceBundle.getBundle("hr.fer.zemris.java.hw11.jnotepadpp.local.translations", locale);
		fire();
	}

	@Override
	public String getLanguage() {
		return language;
	}

	@Override
	public String getString(String key) {
		return bundle.getString(key);
	}

}
