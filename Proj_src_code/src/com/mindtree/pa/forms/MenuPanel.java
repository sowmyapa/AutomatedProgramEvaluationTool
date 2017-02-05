package com.mindtree.pa.forms;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MenuPanel extends JFrame implements ActionListener {
	private JMenuBar mainMenuBar;

	private JMenu fileMenu;

	private JMenu toolsMenu;

	private JMenuItem importMenuItem;

	private JMenuItem exitMenuItem;

	private JMenuItem compileMenuItem;

	private JMenuItem executeMenuItem;

	private JMenuItem submitMenuItem;

	private JPanel mainPanel;

	MenuPanel() {
		this.mainPanel = new JPanel();
		setProperties();
		addComponents();
		addListeners();
	}

	private void setProperties() {
		this.setLayout(new GridLayout(1, 1));
		this.setSize(50, 600);

	}

	private void addComponents() {
		this.mainMenuBar = new JMenuBar();
		this.fileMenu = new JMenu("File");
		this.toolsMenu = new JMenu("Tools");
		this.importMenuItem = createMenuItem(fileMenu,
				"Import Problem Statement");
		this.exitMenuItem = createMenuItem(fileMenu, "Exit");
		this.compileMenuItem = createMenuItem(toolsMenu, "Compile");
		this.executeMenuItem = createMenuItem(toolsMenu, "Execute");
		this.submitMenuItem = createMenuItem(toolsMenu, "Submit");

		this.mainMenuBar.add(fileMenu);
		this.mainMenuBar.add(toolsMenu);
		this.fileMenu.add(importMenuItem);
		this.fileMenu.add(exitMenuItem);
		this.toolsMenu.add(compileMenuItem);
		this.toolsMenu.addSeparator();
		this.toolsMenu.add(executeMenuItem);
		this.toolsMenu.add(submitMenuItem);
		this.setJMenuBar(mainMenuBar);
	}

	private void addListeners() {

	}

	private JMenuItem createMenuItem(JMenu menu, String caption) {
		JMenuItem menuItem = new JMenuItem(caption);
		menu.add(menuItem);
		menuItem.addActionListener(this);
		return menuItem;
	}

	private void cleanUp() {
		dispose();
		System.exit(0);
	}

	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == exitMenuItem) {
			cleanUp();
		} else if (event.getSource() == importMenuItem)
			openFile();
	}

	private void openFile() {
		JFileChooser jfc = new JFileChooser();
		int result = jfc.showOpenDialog(this);
		if (result == JFileChooser.CANCEL_OPTION)
			return;
		try {
			File file = jfc.getSelectedFile();
			BufferedReader br = new BufferedReader(new FileReader(file));
			String s = "";
			int c = 0;
			while ((c = br.read()) != -1)
				s += (char) c;
			br.close();
			System.out.println(s);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "File error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

}
