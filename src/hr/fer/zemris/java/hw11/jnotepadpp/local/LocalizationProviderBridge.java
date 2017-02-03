package hr.fer.zemris.java.hw11.jnotepadpp.local;

/**
 * Decorates a localization provider and allows its connection.
 * 
 * @author labramusic
 *
 */
public class LocalizationProviderBridge extends AbstractLocalizationProvider {

	/**
	 * The parent localization provider.
	 */
	private ILocalizationProvider parent;

	/**
	 * The parent localization listener.
	 */
	private ILocalizationListener listener;

	/**
	 * Bridge connection state.
	 */
	private boolean connected;

	/**
	 * Initializes a LocalizationProviderBridge.
	 * 
	 * @param parent
	 *            localization provider
	 */
	public LocalizationProviderBridge(ILocalizationProvider parent) {
		this.parent = parent;
	}

	/**
	 * Disconnects the parent from the bridge.
	 */
	public void disconnect() {
		parent.removeLocalizationListener(listener);
		connected = false;
	}

	/**
	 * Connects the parent to the bridge.
	 */
	public void connect() {
		if (!connected) {
			if (listener == null) {
				// notify all listeners that are registered as its listeners
				listener = () -> fire();
			}
			parent.addLocalizationListener(listener);
			connected = true;
		}
	}

	@Override
	public String getString(String key) {
		return parent.getString(key);
	}

	@Override
	public String getLanguage() {
		return parent.getLanguage();
	}

}
