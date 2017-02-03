package hr.fer.zemris.java.hw11.jnotepadpp;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.nio.file.Path;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import hr.fer.zemris.java.hw11.jnotepadpp.actions.AscendingAction;
import hr.fer.zemris.java.hw11.jnotepadpp.actions.CloseDocumentAction;
import hr.fer.zemris.java.hw11.jnotepadpp.actions.CopyTextAction;
import hr.fer.zemris.java.hw11.jnotepadpp.actions.CutTextAction;
import hr.fer.zemris.java.hw11.jnotepadpp.actions.DescendingAction;
import hr.fer.zemris.java.hw11.jnotepadpp.actions.ExitAction;
import hr.fer.zemris.java.hw11.jnotepadpp.actions.InvertCaseAction;
import hr.fer.zemris.java.hw11.jnotepadpp.actions.LowerCaseAction;
import hr.fer.zemris.java.hw11.jnotepadpp.actions.NewDocumentAction;
import hr.fer.zemris.java.hw11.jnotepadpp.actions.OpenDocumentAction;
import hr.fer.zemris.java.hw11.jnotepadpp.actions.PasteTextAction;
import hr.fer.zemris.java.hw11.jnotepadpp.actions.SaveDocumentAction;
import hr.fer.zemris.java.hw11.jnotepadpp.actions.SaveDocumentAsAction;
import hr.fer.zemris.java.hw11.jnotepadpp.actions.SelectCroatianAction;
import hr.fer.zemris.java.hw11.jnotepadpp.actions.SelectEnglishAction;
import hr.fer.zemris.java.hw11.jnotepadpp.actions.SelectGermanAction;
import hr.fer.zemris.java.hw11.jnotepadpp.actions.StatisticsAction;
import hr.fer.zemris.java.hw11.jnotepadpp.actions.UniqueAction;
import hr.fer.zemris.java.hw11.jnotepadpp.actions.UpperCaseAction;
import hr.fer.zemris.java.hw11.jnotepadpp.local.LocalizationProvider;
import hr.fer.zemris.java.hw11.jnotepadpp.local.swing.FormLocalizationProvider;
import hr.fer.zemris.java.hw11.jnotepadpp.local.swing.LJLabel;
import hr.fer.zemris.java.hw11.jnotepadpp.local.swing.LJMenu;
import hr.fer.zemris.java.hw11.jnotepadpp.local.swing.LJToolBar;
import hr.fer.zemris.java.hw11.jnotepadpp.util.FileUtil;

/**
 * <p>
 * Represents a Notepad++ imitation application. The application contains a menu
 * bar, a dockable toolbar, a status bar and supports opening multiple documents
 * in different tabs at the same time. The notepad is available in the following
 * languages: Croatian, English and German; and the language can be dynamically
 * changed.
 * </p>
 * <p>
 * The actions in the menu bar include the standard operations for creating,
 * opening and saving documents, basic text editing, as well as advanced tools,
 * a statistical view of the current document and changing languages. The tools
 * include changing the casing of the selected text, ascending and descending
 * sorting of selected lines, and removing duplicate lines from the selection.
 * The status bar displays the length of the text in the current document,
 * current line and column index and number of selected characters. The current
 * date and time are displayed at the bottom right corner of the status bar.
 * </p>
 */
public class JNotepadPP extends JFrame {

	/**
	 * The default serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The tabbed pane.
	 */
	private JTabbedPane tabbedPane;

	/**
	 * Text editor of currently selected document.
	 */
	private JTextArea editor;

	/**
	 * Opened file path of currently selected document.
	 */
	private Path openedFilePath;

	/**
	 * The localization provider.
	 */
	private FormLocalizationProvider flp = new FormLocalizationProvider(LocalizationProvider.getInstance(), this);

	/**
	 * The tools menu.
	 */
	private JMenu toolsMenu = new LJMenu("toolsMenu", flp);

	/**
	 * Status bar length label.
	 */
	private LJLabel lengthLabel;

	/**
	 * Status bar position label.
	 */
	private LJLabel posLabel;

	/**
	 * Initializes a new JNotepadPP.
	 */
	public JNotepadPP() {
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		setSize(800, 600);
		setLocationRelativeTo(null);

		initGUI();
	}

	/**
	 * Initializes the notepad GUI. A new tab is automatically opened.
	 */
	private void initGUI() {
		tabbedPane = new JTabbedPane();
		tabbedPane.addChangeListener((e) -> {
			int index = tabbedPane.getSelectedIndex();
			TextPanel textPanel = (TextPanel) tabbedPane.getComponentAt(index);
			editor = textPanel.getEditor();
			openedFilePath = textPanel.getOpenedFilePath();

			if (openedFilePath != null) {
				setTitle(openedFilePath.toString() + " - JNotepad++");
			} else {
				setTitle("JNotepad++");
			}

			FileUtil.updateStatus(this, editor);
		});

		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.add(tabbedPane, BorderLayout.CENTER);

		JPanel statusBar = new JPanel(new GridLayout(1, 0));
		statusBar.setBorder(BorderFactory.createEmptyBorder(2, 4, 1, 4));
		lengthLabel = new LJLabel("lengthStatus", 0, flp);
		statusBar.add(lengthLabel);
		posLabel = new LJLabel("lineStatus", 0, "colStatus", 0, "selStatus", 0, flp);
		statusBar.add(posLabel);
		Clock clock = new Clock();
		statusBar.add(clock);
		mainPanel.add(statusBar, BorderLayout.SOUTH);

		setContentPane(mainPanel);
		FileUtil.newTab(this, flp.getString("untitledTabName"), null);

		flp.addLocalizationListener(() -> {
			int tabCount = tabbedPane.getTabCount();
			for (int index = 0; index < tabCount; ++index) {
				TextPanel textPanel = (TextPanel) tabbedPane.getComponentAt(index);
				if (textPanel.getOpenedFilePath() == null) {
					String title = flp.getString("untitledTabName");
					if (textPanel.isModified()) {
						title = "*" + title;
					}
					tabbedPane.setTitleAt(index, title);
				}
			}
		});

		createActions();
		createMenus();
		createToolbars();

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				FileUtil.exitApplication(JNotepadPP.this);
			}

			@Override
			public void windowClosed(WindowEvent e) {
				clock.stop();
			}
		});
	}

	/**
	 * Creates the actions used in the notepad menu and toolbar.
	 */
	private void createActions() {
		newDocumentAction = new NewDocumentAction(this, flp);
		openDocumentAction = new OpenDocumentAction(this, flp);
		saveDocumentAction = new SaveDocumentAction(this, openedFilePath, flp);
		saveDocumentAsAction = new SaveDocumentAsAction(this, flp);
		closeDocumentAction = new CloseDocumentAction(this, flp);
		exitAction = new ExitAction(this, flp);
		cutTextAction = new CutTextAction(this, flp);
		copyTextAction = new CopyTextAction(this, flp);
		pasteTextAction = new PasteTextAction(this, flp);
		statisticsAction = new StatisticsAction(this, flp);
		upperCaseAction = new UpperCaseAction(this, flp);
		lowerCaseAction = new LowerCaseAction(this, flp);
		invertCaseAction = new InvertCaseAction(this, flp);
		ascendingAction = new AscendingAction(this, flp);
		descendingAction = new DescendingAction(this, flp);
		uniqueAction = new UniqueAction(this, flp);
		selectCroatianAction = new SelectCroatianAction(flp);
		selectEnglishAction = new SelectEnglishAction(flp);
		selectGermanAction = new SelectGermanAction(flp);
	}

	/**
	 * Creates the notepad menus.
	 */
	private void createMenus() {
		JMenuBar menuBar = new JMenuBar();

		JMenu fileMenu = new LJMenu("fileMenu", flp);
		menuBar.add(fileMenu);
		fileMenu.add(new JMenuItem(newDocumentAction));
		fileMenu.add(new JMenuItem(openDocumentAction));
		fileMenu.add(new JMenuItem(saveDocumentAction));
		fileMenu.add(new JMenuItem(saveDocumentAsAction));
		fileMenu.add(new JMenuItem(closeDocumentAction));
		fileMenu.addSeparator();
		fileMenu.add(new JMenuItem(exitAction));

		JMenu editMenu = new LJMenu("editMenu", flp);
		menuBar.add(editMenu);
		editMenu.add(new JMenuItem(cutTextAction));
		editMenu.add(new JMenuItem(copyTextAction));
		editMenu.add(new JMenuItem(pasteTextAction));

		JMenu viewMenu = new LJMenu("viewMenu", flp);
		menuBar.add(viewMenu);
		viewMenu.add(new JMenuItem(statisticsAction));

		menuBar.add(toolsMenu);
		JMenu changeCaseMenu = new LJMenu("changeCaseMenu", flp);
		toolsMenu.add(changeCaseMenu);
		changeCaseMenu.add(new JMenuItem(upperCaseAction));
		changeCaseMenu.add(new JMenuItem(lowerCaseAction));
		changeCaseMenu.add(new JMenuItem(invertCaseAction));
		JMenu sortMenu = new LJMenu("sortMenu", flp);
		toolsMenu.add(sortMenu);
		sortMenu.add(new JMenuItem(ascendingAction));
		sortMenu.add(new JMenuItem(descendingAction));
		toolsMenu.add(new JMenuItem(uniqueAction));

		JMenu languagesMenu = new LJMenu("langMenu", flp);
		menuBar.add(languagesMenu);
		languagesMenu.add(new JMenuItem(selectCroatianAction));
		languagesMenu.add(new JMenuItem(selectEnglishAction));
		languagesMenu.add(new JMenuItem(selectGermanAction));

		setJMenuBar(menuBar);
	}

	/**
	 * Creates the notepad toolbars.
	 */
	private void createToolbars() {
		JToolBar toolBar = new LJToolBar("toolBarName", flp);
		toolBar.setFloatable(true);

		toolBar.add(new JButton(newDocumentAction));
		toolBar.add(new JButton(openDocumentAction));
		toolBar.add(new JButton(saveDocumentAction));
		toolBar.add(new JButton(saveDocumentAsAction));
		toolBar.add(new JButton(closeDocumentAction));
		toolBar.addSeparator();
		toolBar.add(new JButton(cutTextAction));
		toolBar.add(new JButton(copyTextAction));
		toolBar.add(new JButton(pasteTextAction));
		toolBar.addSeparator();
		toolBar.add(new JButton(statisticsAction));
		toolBar.addSeparator();
		toolBar.add(new JButton(exitAction));

		this.getContentPane().add(toolBar, BorderLayout.PAGE_START);
	}

	/**
	 * The new document action.
	 */
	private Action newDocumentAction;

	/**
	 * The open document action.
	 */
	private Action openDocumentAction;

	/**
	 * The save document action.
	 */
	private Action saveDocumentAction;

	/**
	 * The save document as action.
	 */
	private Action saveDocumentAsAction;

	/**
	 * The close document action.
	 */
	private Action closeDocumentAction;

	/**
	 * The exit application action.
	 */
	private Action exitAction;

	/**
	 * The copy text action.
	 */
	private Action copyTextAction;

	/**
	 * The cut text action.
	 */
	private Action cutTextAction;

	/**
	 * The paste text action.
	 */
	private Action pasteTextAction;

	/**
	 * The statistics action.
	 */
	private Action statisticsAction;

	/**
	 * The upperCase action.
	 */
	private Action upperCaseAction;

	/**
	 * The lowercase action.
	 */
	private Action lowerCaseAction;

	/**
	 * The invert case action.
	 */
	private Action invertCaseAction;

	/**
	 * The unique lines action.
	 */
	private Action uniqueAction;

	/**
	 * The select Croatian action.
	 */
	private Action selectCroatianAction;

	/**
	 * The select English action.
	 */
	private Action selectEnglishAction;

	/**
	 * The select German action.
	 */
	private Action selectGermanAction;

	/**
	 * The sort ascending action.
	 */
	private Action ascendingAction;

	/**
	 * The sort descending action.
	 */
	private Action descendingAction;

	/**
	 * Gets the tabbed pane.
	 *
	 * @return the tabbed pane
	 */
	public JTabbedPane getTabbedPane() {
		return tabbedPane;
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
	 * Gets the tools menu.
	 *
	 * @return the tools menu
	 */
	public JMenu getToolsMenu() {
		return toolsMenu;
	}

	/**
	 * Gets the length label.
	 *
	 * @return the length label
	 */
	public LJLabel getLengthLabel() {
		return lengthLabel;
	}

	/**
	 * Gets the position label.
	 *
	 * @return the position label
	 */
	public LJLabel getPosLabel() {
		return posLabel;
	}

	/**
	 * The main method.
	 * 
	 * @param args
	 *            command line arguments
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {}
		SwingUtilities.invokeLater(() -> {
			new JNotepadPP().setVisible(true);
		});
	}

}
