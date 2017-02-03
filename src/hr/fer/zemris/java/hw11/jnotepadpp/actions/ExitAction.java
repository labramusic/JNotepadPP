package hr.fer.zemris.java.hw11.jnotepadpp.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import hr.fer.zemris.java.hw11.jnotepadpp.JNotepadPP;
import hr.fer.zemris.java.hw11.jnotepadpp.local.ILocalizationProvider;
import hr.fer.zemris.java.hw11.jnotepadpp.local.swing.LocalizableAction;
import hr.fer.zemris.java.hw11.jnotepadpp.util.FileUtil;

/**
 * Action which exits the application.
 * 
 * @author labramusic
 *
 */
public class ExitAction extends LocalizableAction {

	/**
	 * The default serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The notepad.
	 */
	private JNotepadPP notepad;

	/**
	 * Initializes an ExitAction.
	 * 
	 * @param notepad
	 *            the notepad
	 * @param lp
	 *            localization provider
	 */
	public ExitAction(JNotepadPP notepad, ILocalizationProvider lp) {
		super("exitName", "ESCAPE", KeyEvent.VK_E, "exitDesc", lp);
		this.notepad = notepad;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		FileUtil.exitApplication(notepad);
	}

}
