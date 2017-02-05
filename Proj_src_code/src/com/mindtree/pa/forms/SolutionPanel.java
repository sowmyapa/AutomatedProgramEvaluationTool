package com.mindtree.pa.forms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.mindtree.pa.data.SolutionDAO;
import com.mindtree.pa.entity.Solution;
import com.mindtree.pa.exception.DataAccessException;
import com.mindtree.pa.util.CompilerApp;

import layout.TableLayout;

public class SolutionPanel extends JPanel implements ActionListener {

	private JPanel mainPanel;

	private JPanel buttonPanel;

	private JLabel solLabel;

	private JLabel reviewLabel;

	private MainForm mainForm;

	public JTextArea solTextArea;

	private JScrollPane solScrollPane;

	private JTextArea reviewTextArea;

	private JScrollPane reviewScrollPane;

	private JButton executeButton;

	private JButton submitButton;

	private JButton saveButton;

	private Solution solution;

	private String string;

	private int id;

	private Boolean status;

	public SolutionPanel(SolutionPanel solutionPanel) {
		this.mainPanel = new JPanel();
		this.buttonPanel = new JPanel();
		setProperties();
		addComponents();
		addListeners();
	}

	public SolutionPanel() {
		this.mainPanel = new JPanel();
		this.buttonPanel = new JPanel();
		setProperties();
		addComponents();
		addListeners();
	}

	public void setSolution(Solution solution, int id, MainForm mainForm) {
		this.solution = solution;
		this.solTextArea.setText(solution.getSourceCode());
		this.string = solution.getReport();
		this.reviewTextArea.setText(solution.getReport());
		System.out.println("the review repotr from sp" + solution.getReport());
		this.id = id;
		this.mainForm = mainForm;
		return;

	}

	private void setProperties() {
		double gap = 5;
		double[][] mainPanelSize = { { gap, 50, gap, 650, gap },
				{ gap, 400, gap, 200, gap } };
		TableLayout mainPanelLayout = new TableLayout(mainPanelSize);
		this.mainPanel.setLayout(mainPanelLayout);
		double[][] buttonPanelSize = { { gap, 150, gap, 150, gap, 150, gap },
				{ 30 } };
		TableLayout buttonPanelLayout = new TableLayout(buttonPanelSize);
		this.buttonPanel.setLayout(buttonPanelLayout);

		double[][] thisPanelSize = { { gap, 900, gap },
				{ gap, TableLayout.FILL, gap, 50, gap } };
		this.setLayout(new TableLayout(thisPanelSize));

	}

	private void addComponents() {
		this.solLabel = new JLabel("Solution", JLabel.RIGHT);
		this.reviewLabel = new JLabel("Review", JLabel.RIGHT);
		this.solTextArea = new JTextArea(50, 5);
		this.solScrollPane = new JScrollPane(solTextArea);
		this.reviewTextArea = new JTextArea(50, 5);
		this.reviewTextArea.setEditable(false);
		this.reviewScrollPane = new JScrollPane(reviewTextArea);

		this.executeButton = new JButton("Execute");
		this.executeButton.addActionListener(this);
		this.submitButton = new JButton("Submit");
		this.submitButton.addActionListener(this);
		this.saveButton = new JButton("Save");
		this.saveButton.addActionListener(this);
		this.buttonPanel.add(executeButton, "1,0");
		this.buttonPanel.add(submitButton, "3,0");
		this.buttonPanel.add(saveButton, "5,0");

		this.mainPanel.add(solLabel, "1,1");
		this.mainPanel.add(reviewLabel, "1,3");
		this.mainPanel.add(solScrollPane, "3,1");
		this.mainPanel.add(reviewScrollPane, "3,3");

		this.add(mainPanel, "1,1");
		this.add(buttonPanel, "1,3");

	}

	private void addListeners() {

	}

	public void actionPerformed(ActionEvent actionEvent) {
		CompilerApp compilerApp = new CompilerApp();
		if ((actionEvent.getSource() == this.executeButton)
				|| (actionEvent.getActionCommand() == "Execute")) {

			try {

				Boolean check = true;
				Solution data1 = checkingPid(this.id);
				SolutionDAO tc1 = new SolutionDAO();
				check = tc1.checkPid(data1);
				if (check == false) {
					SolutionDAO solutionDAO = new SolutionDAO();
					Solution solution = new Solution(this.id, this.solTextArea
							.getText());

					solutionDAO.insertCode(solution);
				} else {
					SolutionDAO solutionDAO = new SolutionDAO();

					Solution solution = new Solution(this.id, this.solTextArea
							.getText());
					solutionDAO.updateCode(solution);

				}
				String file1 = "/Users/sowmyaparameshwara/Desktop/Assignments/AutomatedProgramEvaluationTool/Proj_src_code/src/com/mindtree/pa/util/solutionFile.java";
				FileWriter fout = new FileWriter(file1);
				BufferedWriter out = new BufferedWriter(fout);

				String contents = this.solTextArea.getText();
				out.write(contents);

				System.out.println("in panel" + contents);
				copy(contents);

				out.close();
				fout.close();
				this.reviewTextArea.setText(null);
				this.string = compilerApp.compile(this.id);

				this.mainForm.refreshPanel();
				processExecuteAction();
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if ((actionEvent.getSource() == this.submitButton)
				|| (actionEvent.getActionCommand() == "Submit")) {
			int result = JOptionPane.showConfirmDialog(this,
					"You Sure you want to Submit?", "Confirm",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (result == JOptionPane.YES_OPTION) {
				System.out.println("in solution submit"
						+ this.solTextArea.getText());
				try {

					Boolean check = true;
					Solution data1 = checkingPid(this.id);
					SolutionDAO tc1 = new SolutionDAO();
					check = tc1.checkPid(data1);
					if (check == false) {
						SolutionDAO solutionDAO = new SolutionDAO();
						Solution solution = new Solution(this.id,
								this.solTextArea.getText());

						solutionDAO.insertCode(solution);
					} else {
						SolutionDAO solutionDAO = new SolutionDAO();
						Solution solution = new Solution(this.id,
								this.solTextArea.getText());
						solutionDAO.updateCode(solution);

					}

					System.out.println("entering  solution panel");
					String file1 = "/Users/sowmyaparameshwara/Desktop/Assignments/AutomatedProgramEvaluationTool/Proj_src_code/src/com/mindtree/pa/util/solutionFile.java";
					FileWriter fout = new FileWriter(file1);
					BufferedWriter out = new BufferedWriter(fout);

					out.write(this.solTextArea.getText());
					out.close();
					fout.close();

					String compilerOutput = compilerApp.compile(this.id);

					this.string = compilerOutput;
					System.out.println("in solution panel" + this.string);
					System.out.println("exiting solution panel");
					this.mainForm.refreshPanel();
					processExecuteAction();
				}

				catch (Exception e) {
					e.printStackTrace();
				}
			}

		} else if ((actionEvent.getSource() == this.saveButton)
				|| (actionEvent.getActionCommand() == "Save")) {

			processSave();

		}

	}

	public void copy(String content) {
		String fileSrc = "/Users/sowmyaparameshwara/Desktop/Assignments/AutomatedProgramEvaluationTool/Proj_src_code/src/com/mindtree/pa/util/solutionFile.java";
		FileWriter foutSrc;
		try {
			foutSrc = new FileWriter(fileSrc);

			BufferedWriter outSrc = new BufferedWriter(foutSrc);
			outSrc.write(content);
			outSrc.close();
			foutSrc.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static Solution checkingPid(int pType) {
		Solution data1 = new Solution();
		data1.setPid(pType);
		return data1;
	}

	private void processSave() {

		try {
			Boolean check = true;
			Solution data1 = checkingPid(this.id);
			SolutionDAO tc1 = new SolutionDAO();
			check = tc1.checkPid(data1);
			if (check == false) {
				SolutionDAO solutionDAO = new SolutionDAO();
				Solution solution = new Solution(this.id, this.solTextArea
						.getText());

				solutionDAO.insertCode(solution);
			} else {
				SolutionDAO solutionDAO = new SolutionDAO();
				Solution solution = new Solution(this.id, this.solTextArea
						.getText());
				solutionDAO.updateCode(solution);

			}
		} catch (DataAccessException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JOptionPane.showMessageDialog(this, "Solution Saved", "Message",
				JOptionPane.INFORMATION_MESSAGE);

	}

	private void processExecuteAction() {

		this.reviewTextArea.setText(this.string);
		JTextArea area = new JTextArea(this.string);
		area.setRows(10);
		area.setColumns(50);
		area.setLineWrap(true);
		JScrollPane pane = new JScrollPane(area);
		JOptionPane.showMessageDialog(this, pane, "Report of the test",
				JOptionPane.INFORMATION_MESSAGE);

}}