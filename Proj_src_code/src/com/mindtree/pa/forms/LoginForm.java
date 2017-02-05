package com.mindtree.pa.forms;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.mindtree.pa.data.ProblemDAO;
import com.mindtree.pa.data.SolutionDAO;
import com.mindtree.pa.data.TestCaseDAO;
import com.mindtree.pa.data.UserDAO;
import com.mindtree.pa.entity.Problem;
import com.mindtree.pa.entity.Solution;
import com.mindtree.pa.entity.TestCase;
import com.mindtree.pa.entity.User;
import com.mindtree.pa.exception.DataAccessException;

import layout.TableLayout;

public class LoginForm extends JFrame implements ActionListener {
	private JLabel usernameLbl;

	private JLabel passwordLbl;

	private JTextField usernameTxt;

	private JPasswordField passwordTxt;

	private JButton submitBtn;

	private JButton cancelBtn;

	private ImageIcon icon;

	/* Default Constructor */
	public LoginForm() {
		setProperties();
		addComponents();
		addListeners();
	}

	/* Set Properties */
	private void setProperties() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(screenSize.width / 4 - this.getSize().width,
				screenSize.height / 4 - this.getSize().height);
		this.setResizable(false);
		this.setTitle("Login");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(
				"icons/login.gif"));
		this.setSize(300, 150);

	}

	/*Add components*/
	private void addComponents() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.usernameLbl = new JLabel("Username", JLabel.RIGHT);
		usernameLbl.setToolTipText("enter user name");
		this.passwordLbl = new JLabel("Password", JLabel.RIGHT);
		passwordLbl.setToolTipText("enter password");
		usernameTxt = new JTextField();
		usernameTxt.setToolTipText("username area");
		passwordTxt = new JPasswordField();
		submitBtn = new JButton("Submit");
		cancelBtn = new JButton("Cancel");
		double size[][] = { { 0.3, 0.1, 0.6 }, { 0.3, 0.3, TableLayout.FILL } };
		Container panel = this.getContentPane();
		panel.setLayout(new TableLayout(size));
		panel.add(this.usernameLbl, "0, 0, 0, 0");
		panel.add(this.usernameTxt, "2, 0, 2, 0");
		panel.add(this.passwordLbl, "0, 1, 0, 0");
		panel.add(this.passwordTxt, "2, 1, 2, 0");
		panel.add(this.submitBtn, "0, 2, 1, 0");
		panel.add(this.cancelBtn, "2, 2, 0, 0");
		submitBtn.addActionListener(this);
		cancelBtn.addActionListener(this);

	}

	/*Main Function*/
	public static void main(String[] args) {
		LoginForm form = new LoginForm();
		form.setVisible(true);
	}

	/*Function to add listeners*/
	private void addListeners() {
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				disposeMe();
			}
		});
	}

	/*Function to cleanup after closing application*/
	public void disposeMe() {
		this.dispose();
		System.exit(0);
	}

	/*Function to add actions*/
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == submitBtn) {
			processAuthentication();
		} else if (event.getSource() == cancelBtn) {
			disposeMe();
		}
	}

	/*Function to validate the user*/
	public void processAuthentication() {
		User user = null;
		try {
			user = new UserDAO().retrieve();
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		System.out.println("DAO user" + user.getName() + " pass "
				+ user.getPassword());
		System.out.println("entered user " + usernameTxt.getText() + " pass "
				+ passwordTxt.getText());

		if ((usernameTxt.getText().equals(user.getName()))
				&& (passwordTxt.getText().equals(user.getPassword()))) {
			System.out.println("inside valid");
			this.setVisible(false);

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
						TestCase testCase = new TestCaseDAO().retrieveById(
								problem.getPid(), i);
						problem.addTestCase(testCase);
						System.out.println(testCase.isStatus());
					}
				}

			} catch (DataAccessException e) {
				e.printStackTrace();
			}

			MainForm app = new MainForm(problems);
			app.setVisible(true);
		} else {
			System.out.println("inside INvalid");
			usernameTxt.setText("");
			passwordTxt.setText("");
		}

	}

}
