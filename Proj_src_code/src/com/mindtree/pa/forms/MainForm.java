package com.mindtree.pa.forms;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import com.mindtree.pa.data.ProblemDAO;
import com.mindtree.pa.data.SolutionDAO;
import com.mindtree.pa.data.TestCaseDAO;
import com.mindtree.pa.entity.Problem;
import com.mindtree.pa.entity.Solution;
import com.mindtree.pa.entity.TestCase;
import com.mindtree.pa.exception.DataAccessException;
import com.mindtree.pa.util.Test;

import layout.TableLayout;

public class MainForm extends JFrame implements ActionListener {

	private ExplorerPanel explorerPanel;

	private DetailsPanel detailsPanel;

	private StatusPanel statusPanel;

	private JMenuBar mainMenuBar;

	private JMenu fileMenu;

	private JMenuItem importMenuItem;

	private JMenuItem exitMenuItem;

	public MainForm() {

	}

	public MainForm(List<Problem> problems) {
		setProperties();
		this.detailsPanel = new DetailsPanel();
		this.explorerPanel = new ExplorerPanel(problems, this.detailsPanel,
				this);
		this.statusPanel = new StatusPanel();
		addComponents();
		addListeners();

	}

	private void setProperties() {
		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			this.setLayout(new BorderLayout());
			this.setExtendedState(MAXIMIZED_BOTH);
			this.setTitle("Programming Assesment");
			this.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent windowEvent) {
					disposeMe();
				}
			});
		} catch (Exception exception) {
			JOptionPane.showMessageDialog(this,
					"Data processing error, please try again.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void disposeMe() {
		this.dispose();
		System.exit(0);
	}

	private void addComponents() {

		this.mainMenuBar = new JMenuBar();
		this.fileMenu = new JMenu("File");
		this.fileMenu.setToolTipText("File menu");
		this.importMenuItem = createMenuItem(fileMenu,
				"Import Problem Statement");
		this.importMenuItem.setToolTipText("import xml file for problem");
		this.exitMenuItem = createMenuItem(fileMenu, "Exit");
		this.exitMenuItem.setToolTipText("exit the application");

		this.mainMenuBar.add(fileMenu);
		this.fileMenu.add(importMenuItem);
		this.fileMenu.add(exitMenuItem);
		this.setJMenuBar(mainMenuBar);

		double size[][] = { { 0.4, 0.6 }, { TableLayout.FILL, 2, 30 } };
		Container panel = this.getContentPane();
		panel.setLayout(new TableLayout(size));
		panel.add(this.explorerPanel, "0, 0, 0, 0");
		panel.add(this.detailsPanel, "1, 0, 1, 0");
		panel.add(this.statusPanel, "0,2,1,0");
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				cleanUp();
			}
		});
	}

	private void addListeners() {

	}

	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == exitMenuItem) {
			cleanUp();
		} else if (event.getSource() == importMenuItem)
			openFile();
	}

	private JMenuItem createMenuItem(JMenu menu, String caption) {
		JMenuItem menuItem = new JMenuItem(caption);
		menu.add(menuItem);
		menuItem.addActionListener(this);
		return menuItem;
	}

	private void cleanUp() {
		int result = JOptionPane.showConfirmDialog(this,
				"You sure you want to Exit?", "Confirm",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

		if (result == JOptionPane.YES_OPTION) {
			dispose();
			System.exit(0);
		}
	}

	void refreshPanel() {
		this.dispose();
		this.redrawMainFrom();
	}

	private void openFile() {
		JFileChooser jfc = new JFileChooser();
		int result = jfc.showOpenDialog(this);
		if (result == JFileChooser.CANCEL_OPTION)
			return;
		try {
			File file = jfc.getSelectedFile();
			Test test = new Test(file);
			redrawMainFrom();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "File error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void redrawMainFrom() {
		List<Problem> problems = null;
		try {
			problems = new ProblemDAO().retrieve();
			for (Iterator iter = problems.iterator(); iter.hasNext();) {
				Problem problem = (Problem) iter.next();
				Solution solution = new SolutionDAO().retrieveById(problem
						.getPid());
				int testCount = new TestCaseDAO().count(problem.getPid());
				for (int i = 1; i <= testCount; i++) {
					TestCase testCase = new TestCaseDAO().retrieveById(problem
							.getPid(), i);
					problem.addTestCase(testCase);
				}
			}

		} catch (DataAccessException e) {
			e.printStackTrace();
		}

		this.dispose();
		new MainForm(problems).setVisible(true);
	}

	public static void main(String[] args) {
		List<Problem> problems = null;
		try {
			problems = new ProblemDAO().retrieve();
			for (Iterator iter = problems.iterator(); iter.hasNext();) {
				Problem problem = (Problem) iter.next();
				Solution solution = new SolutionDAO().retrieveById(problem
						.getPid());
				System.out.println("in main" + solution.getSourceCode());
				int testCount = new TestCaseDAO().count(problem.getPid());
				for (int i = 1; i <= testCount; i++) {
					TestCase testCase = new TestCaseDAO().retrieveById(problem
							.getPid(), i);
					problem.addTestCase(testCase);
					System.out.println(testCase.isStatus());
				}
			}

		} catch (DataAccessException e) {
			e.printStackTrace();
		}

		new MainForm(problems).setVisible(true);
	}

}
