package hr.fer.zemris.java.hw11.jnotepadpp.local;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Abstract localization provider which can add and remove listeners from its
 * internal list and inform them of changes.
 * 
 * @author labramusic
 *
 */
public abstract class AbstractLocalizationProvider implements ILocalizationProvider {

	/**
	 * List of localization listeners.
	 */
	List<ILocalizationListener> listeners;

	/**
	 * Initializes new AbstractLocalizationProvider.
	 */
	public AbstractLocalizationProvider() {
		listeners = new CopyOnWriteArrayList<>();
	}

	@Override
	public void addLocalizationListener(ILocalizationListener listener) {
		listeners.add(listener);

	}

	@Override
	public void removeLocalizationListener(ILocalizationListener listener) {
		listeners.remove(listener);
	}

	/**
	 * Informs listeners of occurred change.
	 */
	public void fire() {
		for (ILocalizationListener listener : listeners) {
			listener.localizationChanged();
		}
	}

}
