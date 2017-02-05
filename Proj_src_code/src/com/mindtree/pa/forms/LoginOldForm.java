package com.mindtree.pa.forms;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginOldForm extends JFrame implements ActionListener {
	
	private JLabel usernameLbl;
	private JLabel passwordLbl;
	private JTextField usernameTxt;
	private JPasswordField passwordTxt;
	private JButton submitBtn;
	private JButton cancelBtn;

	public LoginOldForm() {
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width/4)-this.getSize().width , (screenSize.height/4)-this.getSize().height );
		this.setResizable(false);
		this.setTitle("Login");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("icons/login.gif"));
		this.setSize(400, 200);
		this.setLayout(new GridLayout(3, 2));

		usernameLbl = new JLabel("Username");
		usernameLbl.setToolTipText("enter user name");
		passwordLbl = new JLabel("Password");
		passwordLbl.setToolTipText("enter password");
		usernameTxt = new JTextField();
		passwordTxt = new JPasswordField();
		submitBtn = new JButton("Submit");
		cancelBtn = new JButton("Cancel");
		
		this.add(usernameLbl);
		this.add(usernameTxt);
		this.add(passwordLbl);
		this.add(passwordTxt);
		this.add(submitBtn);
		this.add(cancelBtn);
		
		submitBtn.addActionListener(this);
		cancelBtn.addActionListener(this);
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				disposeMe();
			}
		});
		this.setVisible(true);
	}

	public void disposeMe() {
		this.dispose();
		System.exit(0);
	}

	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == submitBtn) {
			processAuthentication();
		} else if (event.getSource() == cancelBtn) {
			disposeMe();
		}
	}

	private void processAuthentication() {
		// todo: validate user
	//	sampleJdbc s1= new sampleJdbc();
	//	s1.main(usernameTxt,passwordTxt );
		boolean value=PasswordCheck(usernameTxt.getText(),passwordTxt.getText());
		if(value== true)
		{
		this.setVisible(false);
		
		/*List<Problem> problems = new ArrayList<Problem>();
		Problem problem1 = new Problem(1, "p1", 4 , " ");
		Solution solution1 = new Solution();
		Solution solution4= new Solution();
		problem1.setSolution(solution1);
		problem1.setSolution(solution4);
		TestCase testCase1 = new TestCase(1, "positive flow", true);
		TestCase testCase2 = new TestCase(2, "negative flow", false);
		TestCase testCase3 = new TestCase(3, "negative flow", false);
		problem1.addTestCase(testCase1);
		problem1.addTestCase(testCase2);
		problem1.addTestCase(testCase3);

		Problem problem2 = new Problem(2, "p2",1," ");
		Solution solution2 = new Solution();
		problem2.setSolution(solution2);

		Problem problem3 = new Problem(3, "p3",1," ");
		Solution solution3 = new Solution();
		problem3.setSolution(solution3);*/

		/*problems.add(problem1);
		problems.add(problem2);
		problems.add(problem3);
		*/
		//MainForm app = new MainForm(problems);
		//app.setVisible(true);
		}
		else
		{
			usernameTxt.setText("");
			passwordTxt.setText("");
		}
		
		
	}

	public static void main(String[] args) {
		new LoginOldForm();
	}
	
	//database connectivity
	boolean PasswordCheck(String userid, String pwd )  	
	{
	//	String userid = new String();
		//String pwd = new String();
		Connection con = getConnection();
		if(con!=null)
		{
			try
			{               
 				
						
				
				PreparedStatement st = con.prepareStatement("SELECT * from login WHERE userid=? AND pwd=? ");
				st.setString(1, userid);
                        st.setString(2, pwd);
                        ResultSet rs= st.executeQuery();
				

				
				if(rs.next())
				{
					//System.out.println("login done ");
					return true;
				    
				}    
				
				else
				{	
				//System.out.println("fail");
				//	return 0;
				}
				rs.close();
				con.close();
				return false;
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return false;
	}
	
	
	private static Connection getConnection()
	{
		Connection con = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("MySQL JDBC driver loaded ok.");

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/logs?user=root&password=nikitha");

		} catch (Exception e)
		{
			System.err.println("Exception: " + e.getMessage());
		}
		return con;
	}
}