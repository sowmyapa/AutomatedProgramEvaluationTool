package com.mindtree.pa.forms;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class StatusPanel extends JPanel {

	private JLabel applabel;

	private JLabel usernamelabel;

	private JLabel timelabel;

	private SimpleDateFormat dateFormatter;

	private Timer timer;

	public StatusPanel() {
		setProperties();
		addComponents();
		addListeners();
	}

	private void setProperties() {
		this.setLayout(new GridLayout(1, 1));
		this.setSize(900, 100);
		this.setBorder(BorderFactory.createEtchedBorder());
	}

	private void addComponents() {

		this.setLayout(new FlowLayout());

		dateFormatter = new SimpleDateFormat();
		dateFormatter.applyPattern("dd-MMM-yyyy");

		Font fontLbl = new Font("Times", Font.PLAIN, 11);

		applabel = new JLabel("Programming Application");
		applabel.setFont(fontLbl);
		applabel.setBorder(BorderFactory.createEtchedBorder());
		this.add(applabel);

		usernamelabel = new JLabel("bmsce");
		usernamelabel.setFont(fontLbl);
		usernamelabel.setBorder(BorderFactory.createEtchedBorder());
		this.add(usernamelabel);

		timelabel = new JLabel(dateFormatter.format(new Date()));
		timelabel.setFont(fontLbl);
		timelabel.setBorder(BorderFactory.createEtchedBorder());
		this.add(timelabel);

		ActionListener timerListener = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				timelabel.setText(dateFormatter.format(new Date()));
				timelabel.paintAll(timelabel.getGraphics());
			}
		};
		timer = new Timer(2000, timerListener);
		timer.start();

	}

	private void addListeners() {

	}

}