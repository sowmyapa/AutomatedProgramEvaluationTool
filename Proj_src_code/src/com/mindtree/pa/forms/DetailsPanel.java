package com.mindtree.pa.forms;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class DetailsPanel extends JPanel {

	public DetailsPanel() {
		setProperties();
		addComponents();
		addListeners();
	}

	private void setProperties() {
		this.setLayout(new GridLayout(1, 1));
		this.setSize(600, 600);
		this.setBorder(BorderFactory.createEtchedBorder());
	}

	private void addComponents() {

	}

	private void addListeners() {

	}

}
