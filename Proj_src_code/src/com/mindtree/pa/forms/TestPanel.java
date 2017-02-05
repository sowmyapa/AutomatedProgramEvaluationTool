package com.mindtree.pa.forms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.mindtree.pa.entity.TestCase;

import layout.TableLayout;

public class TestPanel extends JPanel implements ActionListener {

	private JPanel mainPanel;

	private JPanel buttonPanel;

	private TestCase testCase;

	private JLabel inputLabel;

	private JLabel outputLabel;

	private JLabel descriptionLabel;

	private JLabel passedLabel;

	private JLabel weightageLabel;

	private JTextField inputTextField;

	private JTextField outputTextField;

	private JTextField weightageTextField;

	private JTextArea descriptionTextArea;

	private JTextArea passedTextArea;

	public TestPanel() {
		this.mainPanel = new JPanel();
		this.buttonPanel = new JPanel();
		setProperties();
		addComponents();
		addListeners();
	}

	public void setTestCase(TestCase testCase) {
		this.testCase = testCase;
		this.inputTextField.setText(testCase.getInput());
		this.outputTextField.setText(testCase.getOutput());
		this.passedTextArea.setText(Boolean.toString(testCase.isStatus()));
		this.descriptionTextArea.setText(testCase.getDescription());
		this.weightageTextField
				.setText(Float.toString(testCase.getWeightage()));
	}

	private void setProperties() {
		double gap = 5;
		double[][] mainPanelSize = { { gap, 100, gap, 180, gap },
				{ gap, 30, gap, 30, gap, 300, gap, 30, gap, 30, gap } };
		TableLayout mainPanelLayout = new TableLayout(mainPanelSize);
		this.mainPanel.setLayout(mainPanelLayout);
		double[][] thisPanelSize = { { gap, 500, gap },
				{ gap, TableLayout.FILL, gap, 50, gap } };
		this.setLayout(new TableLayout(thisPanelSize));

	}

	private void addComponents() {
		this.inputLabel = new JLabel("Input", JLabel.RIGHT);
		this.outputLabel = new JLabel("Output", JLabel.RIGHT);
		this.descriptionLabel = new JLabel("Description", JLabel.RIGHT);
		this.passedLabel = new JLabel("Status", JLabel.RIGHT);
		this.weightageLabel = new JLabel("Weightage", JLabel.RIGHT);
		this.inputTextField = new JTextField(5);
		this.inputTextField.setEditable(false);
		this.outputTextField = new JTextField(30);
		this.outputTextField.setEditable(false);
		this.descriptionTextArea = new JTextArea(30, 5);
		this.descriptionTextArea.setEditable(false);
		this.passedTextArea = new JTextArea(5, 5);
		this.passedTextArea.setEditable(false);
		this.weightageTextField = new JTextField(5);
		this.mainPanel.add(inputLabel, "1,1");
		this.mainPanel.add(outputLabel, "1,3");
		this.mainPanel.add(descriptionLabel, "1,5");
		this.mainPanel.add(passedLabel, "1,7");
		this.mainPanel.add(weightageLabel, "1,9");
		this.mainPanel.add(inputTextField, "3,1");
		this.mainPanel.add(outputTextField, "3,3");
		this.mainPanel.add(descriptionTextArea, "3,5");
		this.mainPanel.add(passedTextArea, "3,7");
		this.mainPanel.add(weightageTextField, "3,9");
		this.add(mainPanel, "1,1");
		this.add(buttonPanel, "1,3");

	}

	private void addListeners() {

	}

	public void actionPerformed(ActionEvent actionEvent) {
	}

	private void processOkAction() {
		JOptionPane.showMessageDialog(this, "Processing Ok", "Ok",
				JOptionPane.INFORMATION_MESSAGE);
	}

	private void processCancelAction() {
		JOptionPane.showMessageDialog(this, "Processing Cancel", "Cancel",
				JOptionPane.INFORMATION_MESSAGE);
	}

}
