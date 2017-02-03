package hr.fer.zemris.java.hw11.jnotepadpp.local.swing;

import javax.swing.JLabel;

import hr.fer.zemris.java.hw11.jnotepadpp.local.ILocalizationProvider;

/**
 * Localizable JLabel which can display a localizable string and a value, or
 * three localizable strings and their respective values.
 * 
 * @author labramusic
 *
 */
public class LJLabel extends JLabel {

	/**
	 * The default serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Localization provider.
	 */
	private ILocalizationProvider lp;

	/**
	 * First string key.
	 */
	private String key1;

	/**
	 * Second string key. Can be null if key3 is null.
	 */
	private String key2;

	/**
	 * Third string key. Can be null if key2 is null.
	 */
	private String key3;

	/**
	 * Initializes a new LJLabel with a single localizable string and its value.
	 * 
	 * @param key
	 *            string key
	 * @param value
	 *            value
	 * @param lp
	 *            localization provider
	 */
	public LJLabel(String key, int value, ILocalizationProvider lp) {
		setText(createString(key, value, lp));

		lp.addLocalizationListener(() -> {
			setText(createString(key, value, lp));
		});
		this.key1 = key;
		this.lp = lp;
	}

	/**
	 * Initializes a new LJLabel with three localizable strings and their
	 * respective values.
	 * 
	 * @param key1
	 *            first key
	 * @param value1
	 *            first value
	 * @param key2
	 *            second key
	 * @param value2
	 *            second value
	 * @param key3
	 *            third key
	 * @param value3
	 *            third value
	 * @param lp
	 *            localization provider
	 */
	public LJLabel(String key1, int value1, String key2, int value2, String key3, int value3,
			ILocalizationProvider lp) {
		setText(createString(key1, value1, lp) + createString(key2, value2, lp) + createString(key3, value3, lp));

		lp.addLocalizationListener(() -> {
			setText(createString(key1, value1, lp) + createString(key2, value2, lp) + createString(key3, value3, lp));
		});
		this.key1 = key1;
		this.key2 = key2;
		this.key3 = key3;
		this.lp = lp;
	}

	/**
	 * Creates a string with the localized name and the given value.
	 * 
	 * @param key
	 *            string key
	 * @param value
	 *            value
	 * @param lp
	 *            localization provider
	 * @return label text
	 */
	private String createString(String key, int value, ILocalizationProvider lp) {
		return lp.getString(key) + ": " + value + "  ";
	}

	/**
	 * Sets the label text with the given value.
	 * 
	 * @param value
	 *            value
	 */
	public void setValue(int value) {
		setText(createString(key1, value, lp));
	}

	/**
	 * Sets the values in the label text. Throws {@link IllegalAccessError} if
	 * label contains only one key.
	 * 
	 * @param value1
	 *            first value
	 * @param value2
	 *            second value
	 * @param value3
	 *            third value
	 */
	public void setValues(int value1, int value2, int value3) {
		if (key2 == null || key3 == null) {
			throw new IllegalAccessError("This label doesn't support multiple keys.");
		}
		setText(createString(key1, value1, lp) + createString(key2, value2, lp) + createString(key3, value3, lp));
	}

}
