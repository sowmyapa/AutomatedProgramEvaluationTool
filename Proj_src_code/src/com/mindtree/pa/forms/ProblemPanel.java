package com.mindtree.pa.forms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.mindtree.pa.data.ProblemDAO;
import com.mindtree.pa.data.SolutionDAO;
import com.mindtree.pa.data.TestCaseDAO;
import com.mindtree.pa.entity.Problem;
import com.mindtree.pa.entity.Solution;
import com.mindtree.pa.entity.TestCase;
import com.mindtree.pa.exception.DataAccessException;

import layout.TableLayout;

public class ProblemPanel extends JPanel implements ActionListener {

	private JPanel mainPanel;

	private JPanel buttonPanel;

	private JButton deleteButton;

	private Problem problem;

	private JLabel idLabel;

	private JLabel nameLabel;

	private JLabel semLabel;

	private JLabel descriptionLabel;

	private JLabel timeLabel;

	private JLabel marksLabel;

	private MainForm mainForm;

	private JTextField idTextField;

	private JTextField nameTextField;

	private JTextArea descriptionTextArea;

	private JTextArea semTextArea;

	private JTextField timeTextField;

	private JTextField marksTextField;

	public ProblemPanel() {
		this.mainPanel = new JPanel();
		this.buttonPanel = new JPanel();
		setProperties();
		addComponents();
		addListeners();
	}

	public void setProblem(Problem problem, MainForm mainForm) {
		this.problem = problem;
		this.idTextField.setText(Long.toString(problem.getPid()));
		this.nameTextField.setText(problem.getName());
		this.semTextArea.setText(Long.toString(problem.getSemester()));
		this.descriptionTextArea.setText(problem.getDescription());
		//this.timeTextField.setText(Date.toString(problem.getCreatedOn()));
		this.timeTextField.setText(problem.getCreatedOn());
		this.marksTextField.setText(Integer.toString(problem.getMarks()));
		this.mainForm = mainForm;
	}

	private void setProperties() {
		double gap = 5;
		double[][] mainPanelSize = { { gap, 100, gap, 500, gap },
				{ gap, 30, gap, 30, gap, 30, gap, 300, gap, 30, gap, 30, gap } };
		TableLayout mainPanelLayout = new TableLayout(mainPanelSize);
		this.mainPanel.setLayout(mainPanelLayout);
		double[][] buttonPanelSize = { { gap, 150, gap, 150, gap }, { 30 } };
		TableLayout buttonPanelLayout = new TableLayout(buttonPanelSize);
		this.buttonPanel.setLayout(buttonPanelLayout);

		double[][] thisPanelSize = { { gap, 800, gap },
				{ gap, TableLayout.FILL, gap, 50, gap } };
		this.setLayout(new TableLayout(thisPanelSize));

	}

	private void addComponents() {
		this.idLabel = new JLabel("Id", JLabel.RIGHT);
		this.nameLabel = new JLabel("Name", JLabel.RIGHT);
		this.semLabel = new JLabel("Semester", JLabel.RIGHT);
		this.descriptionLabel = new JLabel("Description", JLabel.RIGHT);
		this.marksLabel = new JLabel("Total Marks", JLabel.RIGHT);
		this.timeLabel = new JLabel("Created On", JLabel.RIGHT);
		this.idTextField = new JTextField(5);
		this.nameTextField = new JTextField(30);
		this.descriptionTextArea = new JTextArea(30, 5);
		this.nameTextField.setEditable(false);
		this.idTextField.setEditable(false);
		this.descriptionTextArea.setEditable(false);
		this.semTextArea = new JTextArea(5, 5);
		this.semTextArea.setEditable(false);
		this.marksTextField = new JTextField(5);
		this.timeTextField = new JTextField(5);
		this.deleteButton = new JButton("Delete");
		this.deleteButton.addActionListener(this);
		this.buttonPanel.add(deleteButton, "1,0");
		this.mainPanel.add(idLabel, "1,1");
		this.mainPanel.add(nameLabel, "1,3");
		this.mainPanel.add(semLabel, "1,5");
		this.mainPanel.add(descriptionLabel, "1,7");
		this.mainPanel.add(marksLabel, "1,9");
		this.mainPanel.add(timeLabel, "1,11");
		this.mainPanel.add(idTextField, "3,1");
		this.mainPanel.add(nameTextField, "3,3");
		this.mainPanel.add(descriptionTextArea, "3,7");
		this.mainPanel.add(semTextArea, "3,5");
		this.mainPanel.add(marksTextField, "3,9");
		this.mainPanel.add(timeTextField, "3,11");
		this.add(mainPanel, "1,1");
		this.add(buttonPanel, "1,3");

	}

	private void addListeners() {

	}

	public void actionPerformed(ActionEvent actionEvent) {
		System.out.println(actionEvent.getSource());
		if ((actionEvent.getSource() == this.deleteButton)
				|| (actionEvent.getActionCommand() == "Delete")) {
			processDeleteAction();
		}
	}

	private void processDeleteAction() {
		int result = JOptionPane.showConfirmDialog(this,
				"You Sure you want to Delete?", "Confirm",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (result == JOptionPane.YES_OPTION) {
			ProblemDAO problemDAO = new ProblemDAO();
			Problem problem = new Problem(this.problem.getPid());

			TestCaseDAO testcaseDAO = new TestCaseDAO();
			TestCase testcase = new TestCase(this.problem.getPid());

			SolutionDAO solutionDAO = new SolutionDAO();
			Solution solution = new Solution(this.problem.getPid());

			try {
				problemDAO.delete(problem);
				solutionDAO.delete(solution);
				testcaseDAO.delete(testcase);
			} catch (DataAccessException e) {
				e.printStackTrace();
			}
			this.mainForm.refreshPanel();
		}
	}
}
