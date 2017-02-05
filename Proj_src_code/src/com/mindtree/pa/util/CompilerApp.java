package com.mindtree.pa.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.StringTokenizer;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import com.mindtree.pa.data.SolutionDAO;
import com.mindtree.pa.data.TestCaseDAO;
import com.mindtree.pa.entity.Solution;
import com.mindtree.pa.entity.TestCase;
import com.mindtree.pa.exception.DataAccessException;

public class CompilerApp
{
	
	/*public String submit(int pid)
	{  

		Boolean statusProg=false;
		//this.pid=pid;
        //System.out.println(pid);
        int numberTest;
		PrintStream console = System.out;
		String outputText = new String();
		int testNumber = 1; 
		Float[] weightage=new Float[20];
		Float finalWeightage=new Float(0.0);
        int[] tidData=new int[20];
		int correct = 0, incorrect = 0, i = 0;
		int k = 0;
		Boolean testStatus=new Boolean(false);
		String className, absolutePath, loadedClass;
		// byte testInputs[]=new byte[50];
		String outExpected[] = new String[10];
		
		BufferedReader stdin = new BufferedReader(new InputStreamReader(
				System.in));
		String temp[] = new String[10];

		//System.out.println("Enter the problem id");
		try
		{
			probId = stdin.readLine();
		} catch (IOException e1)
		{
			String exception = "Invalid input";
			return exception;
		}
		System.out.println("Enter the name of the class to be executed");
		try
		{
			className = stdin.readLine();
		} catch (IOException e1)
		{

			String exception = "Invalid class name input";
			return exception;
		}
		absolutePath = "E://project MT//workspace//BugsI//src//com//mindtree//pa//util//solutionFile.java";
		absolutePath += className;
		absolutePath += ".java";
		loadedClass = "com.mindtree.pa.util.";
		loadedClass += "solutionFile";
		loadedClass = "com.mindtree.pa.util.";
		loadedClass += className;
		Connection con = getConnection();
		if (con != null)
		{
			try
			{

				Statement st = con.createStatement();

				// ResultSet rsOut=st.executeQuery("select output from test
				// where pid="+probId+" ");
				//		   
				// while(rsOut.next())
				// {
				// outExpected[j++]=rsOut.getInt(1);
				// }
				// rsOut.close();
				ResultSet rs = st
						.executeQuery("select input,output,weightage,tid from testcase where pid="
								+ pid +"");
				while (rs.next())
				{
					
					temp[k] = "";
					StringTokenizer tokenizer = new StringTokenizer(rs
							.getString(1), " ");
					while (tokenizer.hasMoreTokens())
					{
						temp[k] += String.valueOf(tokenizer.nextToken() + "\n");
					}

					 System.out.println(temp[k]);
					// temp[k]+=String.valueOf(rs.getString(1)+"\n");
					// temp[k]+=String.valueOf(rs.getInt(2)+"\n");
					// temp[k]+=String.valueOf(rs.getInt(3)+"\n");
					outExpected[k] = rs.getString(2);
					weightage[k]=rs.getFloat(3);
					tidData[k]=rs.getInt(4);
					 //System.out.println(outExpected[k]); System.out.println(weightage[k]);
					k++;
				}
				rs.close();
				con.close();
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				//e.printStackTrace();
				String exception = "Problem in sql";
				return exception;
			}
			numberTest=k;

			JavaCompiler jc = ToolProvider.getSystemJavaCompiler();
			StandardJavaFileManager sjfm = jc.getStandardFileManager(null,
					null, null);
			File javaFile = new File(absolutePath);
			File[] javaFile1 = new File[1];
			javaFile1[0] = javaFile;
			Iterable fileObjects = sjfm.getJavaFileObjects(javaFile1);
			String[] options = new String[]{"-d",
					"E://project MT//workspace//BugsI//src"};
			try
			{
				jc.getTask(null, null, null, Arrays.asList(options), null,
						fileObjects).call();
				System.out.println("Class has been successfully compiled");
			} catch (Exception e)
			{
				String exception = "compiling error";
				return exception;

			}
			try
			{
				sjfm.close();
			} catch (IOException e)
			{
				String exception = "error closing file manager";
				return exception;
			}
			
			
			 * for(int num=0;num<k;num++) {
			 * System.out.println(outExpected[num]); }
			 
           
			while (k!=0)
			{
				k--;

				URL[] urls;
				try
				{
					urls = new URL[]{new URL(
							"File://E://project MT//workspace//BugsI//src//")};
				} catch (MalformedURLException e)
				{

					String exception = "url malformed";
					return exception;
				}
				URLClassLoader ucl = new URLClassLoader(urls);
				
				Class clazz;
				try
				{
					clazz = ucl.loadClass(loadedClass);
					//System.out.println(clazz);
				} catch (ClassNotFoundException e)
				{

					String exception = "Class not found exception";

					return exception;
				}
				Method method = null;
				try
				{
					method = clazz.getDeclaredMethod("main", null);
				} catch (SecurityException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e)
				{

					String exception = "method not found";
					return exception;
				}
				Object object;
				try
				{
					object = clazz.newInstance();
				} catch (InstantiationException e)
				{
					String exception = "could not instantiate exception";
					return exception;
				} catch (IllegalAccessException e)
				{
					String exception = "illegal access exception";
					return exception;
				}
				// InputStream in = new
				// ByteArrayInputStream(testInputs,(iteration*2),2);
				InputStream in = new ByteArrayInputStream(temp[k].getBytes());
				System.setIn(in);
				// iteration++;

				try
				{
					System
							.setOut(new PrintStream(
									new FileOutputStream(
											"E://project MT//workspace//BugsI//src//com//mindtree//pa//util//out.txt")));
				
				
				} catch (FileNotFoundException e)
				{
					String exception = "file not found";
					return exception;
				}
				try
				{
					method.invoke(object, null);
				
				} catch (IllegalArgumentException e1)
				{
					
					e1.printStackTrace();
					String exception = "Compiler error illegal argument";
					return exception;
				} catch (IllegalAccessException e1)
				{
					e1.printStackTrace();
					String exception = "Compiler error illegal access";
					return exception;
				} catch (InvocationTargetException e1)
				{
					e1.printStackTrace();
					String exception = "Compiler error invocatiuon target";
					return exception;
				}
				i++;
				BufferedReader in1;
				try
				{
					in1 = new BufferedReader(
							new FileReader( "E://project MT//workspace//BugsI//src//com//mindtree//pa//util//out.txt"));
							
									
					
				} catch (FileNotFoundException e)
				{
					String exception = "File not found exception";
					return exception;
				}
				String testExecuted;
				try
				{
					testExecuted = in1.readLine();
				} catch (NumberFormatException e)
				{
					String exception = "Number format exception";
					return exception;
				} catch (IOException e)
				{
					String exception = "input output exception";
					return exception;
				}
				String testOut = outExpected[k];
				System.setOut(console);
				System.out.println("expected:" +testOut);
				outputText +="tid:";
				outputText +=tidData[k];
				outputText +="\n";
				outputText +="inputs:";
				outputText +="\n";
				outputText +=temp[k];
				
				outputText +="expected output:";
				//outputText +="\n";
				outputText +=outExpected[k];
				outputText +="\n";
				
				outputText +="test result";
				outputText +=testExecuted;
				outputText +="\n";
				if (testOut.equals(testExecuted))
				{
					
					outputText += "the program generated the correct output for test case";
					outputText += tidData[k];
					outputText += "\n";
					System.out
							.println("the program generated the correct output for test case"
									+ testNumber);
					correct++;
					finalWeightage+=weightage[k];
					testStatus = true;
					TestCaseDAO testcaseDAO=new TestCaseDAO();
					System.out.println("com"+pid+""+tidData[k]+""+testStatus);
					TestCase testcase=new TestCase(pid,tidData[k],testStatus);
					try {
						testcaseDAO.updateStatus(testcase);
					} catch (DataAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				} else
				{
					
					outputText += "the program result was incorrect for the test case number";
					outputText += tidData[k];
					outputText += "\n";
					System.out
							.println("the program result was incorrect for the test case number"
									+ testNumber);
					incorrect++;
					testStatus = false;
					System.out.println("com"+pid+""+tidData[k]+""+testStatus);
					TestCaseDAO testcaseDAO=new TestCaseDAO();
					TestCase testcase=new TestCase(pid,tidData[k],testStatus);
					try {
						testcaseDAO.updateStatus(testcase);
					} catch (DataAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				testNumber++;
			}
			outputText += "the program output was correct for";
			outputText += correct;
			outputText += "number of test cases";
			outputText += "\n";
			outputText += "the program output was incorrect for";
			outputText += incorrect;
			outputText += "number of test cases";
			outputText += "\n";
			outputText+="the total score is";
            outputText+=finalWeightage;
			outputText+="\n";
			System.out.println("number test"+ numberTest);
			System.out.println("number correct"+ correct);
			
			if(numberTest==correct){
				statusProg=true;
				if (actionEvent.getSource() == this.submitButton)
				{
				
					SolutionDAO solutionDAO=new SolutionDAO();
					System.out.println("in compiler submit"+program);
					System.out.println(""+progId);
					System.out.println(""+statusProg);
					Solution solution=new Solution(progId,program,statusProg);
					try {
						solutionDAO.insert(solution);
					} catch (DataAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				
				}
			}
				System.out.println("the program output was correct for" + correct
					+ "number of test cases");
			System.out.println("the program output was incorrect for"
					+ incorrect + "number of test cases");
			outputText+="the program id is";
			outputText+=pid;
			outputText+="\n";
			outputText+="the status of the program execution is";
			outputText+=statusProg;
			
			outputText+="\n";
		}
		outputText+="the generated report is saved for future use";
		outputText+="\n";
		// new SolutionPanel().setReviewTextField(outputText);
		// solutionPanel.setReviewTextField(outputText);
		
		 SolutionPanel solutionPanel=new SolutionPanel(); 
		 Solution solution=new Solution();
		 solutionPanel.setSolution(solution,outputText);
		 
		
		System.out.println("returning from compiler app"+outputText);
		
		SolutionDAO solutionDAO=new SolutionDAO();
		Solution solution=new Solution(pid,statusProg,outputText);
		try {
			solutionDAO.updateStatus(solution);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return outputText;
		
	}
	*/
	
	public String compile(int pid)
	{  
		//function to compile user's program and to execute it against built in text cases
		Boolean statusProgram=false;
        int numberTest;
		PrintStream console = System.out;
		String outputText= new String();
		int testNumber=1; 
		Float[] weightage=new Float[20];
		Float finalWeightage=new Float(0.0);
        int[] tidData=new int[20];
		int correct = 0, incorrect = 0, i = 0;
		int k = 0;
		Boolean testStatus=new Boolean(false);
		String absolutePath, loadedClass;
		String outExpected[] = new String[10];
		String tempInput[] = new String[10];
		//absolute pathname for the file which contains the program to be compiled
		absolutePath = "/Users/sowmyaparameshwara/Desktop/Assignments/AutomatedProgramEvaluationTool/Proj_src_code/src/com/mindtree/pa/util/solutionFile.java";
		//pathname for the class to be loaded
		loadedClass = "com.mindtree.pa.util.";
		loadedClass += "solutionFile";
		//Establishing connection with the database to retrieve test case values
		Connection con = getConnection();
		if (con != null)
		{
			try
			{
				Statement statement = con.createStatement();
				ResultSet result = statement.executeQuery("select input,output,weightage,tid from testcase where pid="
								+ pid +"");
				while (result.next())
				{					
					tempInput[k]= "";
					StringTokenizer tokenizer = new StringTokenizer(result.getString(1), " ");
					while (tokenizer.hasMoreTokens())
					{
						tempInput[k]+= String.valueOf(tokenizer.nextToken() + "\n");
					}
					outExpected[k] = result.getString(2);
					weightage[k]=result.getFloat(3);
					tidData[k]=result.getInt(4);
					 //System.out.println(outExpected[k]); System.out.println(weightage[k]);
					k++;
				}
				result.close();
				con.close();
			} catch (SQLException e)
			{
				String exception = "Problem in sql";
				return exception;
			}
			numberTest=k;
             //the code for compiling the user program
			
			JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
			StandardJavaFileManager sjfm = javaCompiler.getStandardFileManager(null,
					null, null);
			File javaFile = new File(absolutePath);
			File[] javaFileArray = new File[1];
			javaFileArray[0] = javaFile;
			Iterable fileObjects = sjfm.getJavaFileObjects(javaFileArray);
			//options contains the various options to be supplied while compiling
			String[] options = new String[]{"-d","/Users/sowmyaparameshwara/Desktop/Assignments/AutomatedProgramEvaluationTool/Proj_src_code/src"};
			try
			{
				javaCompiler.getTask(null, null, null, Arrays.asList(options), null,
						fileObjects).call();
				System.out.println("Class has been successfully compiled");
			} catch (Exception e)
			{
				e.printStackTrace();
				String exception = "compiling error";
				return exception;

			}
			try
			{
				sjfm.close();
				//end of compilation process
			} catch (IOException e)
			{
				String exception = "error closing file manager";
				return exception;
			}
			           
			while (k!=0)
			{
				k--;
                //the following lines contain the code for loading the class
				URL[] urlLoadedFile;
				try
				{
					urlLoadedFile = new URL[]{new URL("File:/Users/sowmyaparameshwara/Desktop/Assignments/AutomatedProgramEvaluationTool/Proj_src_code/src/c")};
				} 
				catch (MalformedURLException e)
				{					
					String exception = "url malformed";
					return exception;
				}
				URLClassLoader ucl = new URLClassLoader(urlLoadedFile);
				Class classLoaded;
				try
				{
					classLoaded = ucl.loadClass(loadedClass);
				} 
				catch (ClassNotFoundException e)
				{
				    String exception = "Class not found exception";
				    return exception;
				}
				Method method = null;
				try
				{
					method = classLoaded.getDeclaredMethod("main", null);
				} catch (SecurityException e)
				{
					e.printStackTrace();
				}
				catch (NoSuchMethodException e)
				{
					String exception = "method not found";
					return exception;
				}
				// loading the class completed
				Object object;
				try
				{
					//getting a new instance of the loaded class to execute the code via reflection
					object = classLoaded.newInstance();
				}
				catch (InstantiationException e)
				{
				    String exception = "could not instantiate exception";
					return exception;
				} 
				catch (IllegalAccessException e)
				{
					String exception = "illegal access exception";
					return exception;
				}
				InputStream inputFile = new ByteArrayInputStream(tempInput[k].getBytes());
				//redirecting the standard input,so that the user's program obtains it's console input from database
				System.setIn(inputFile);
				try
				{
					System.setOut(new PrintStream(new FileOutputStream("/Users/sowmyaparameshwara/Desktop/Assignments/AutomatedProgramEvaluationTool/Proj_src_code/src/com/mindtree/pa/util/out.txt")));
				} 
				catch (FileNotFoundException e)
				{
					String exception = "file not found";
					return exception;
				}
				try
				{
					//user's program is invoked and executed
					method.invoke(object, null);
				
				}
				catch (IllegalArgumentException e1)
				{
					String exception = "Compiler error illegal argument";
					return exception;
				}
				catch (IllegalAccessException e1)
				{
					String exception = "Compiler error illegal access";
					return exception;
				}
				catch (InvocationTargetException e1)
				{
					String exception = "Compiler error invocatiuon target";
					return exception;
				}
				i++;
				BufferedReader programOutput;
				try
				{
					//redirecting user's program output into a file to save it for further comparisions with the test case ouput
					programOutput = new BufferedReader(new FileReader("/Users/sowmyaparameshwara/Desktop/Assignments/AutomatedProgramEvaluationTool/Proj_src_code/src/com/mindtree/pa/util/out.txt"));
					
				}
				catch (FileNotFoundException e)
				{
					String exception = "File not found exception";
					return exception;
				}
				
				String testExecuted;
				try
				{
					testExecuted = programOutput.readLine();
				} 
				catch (NumberFormatException e)
				{
					String exception = "Number format exception";
					return exception;
				} catch (IOException e)
				{
					String exception = "input output exception";
					return exception;
				}
				String testOut=outExpected[k];
				System.setOut(console);
				outputText +="tid:";
				outputText +=tidData[k];
				outputText +="\n";
				outputText +="inputs:";
				outputText +="\n";
				outputText +=tempInput[k];				
				outputText +="expected output:";
				outputText +=outExpected[k];
				outputText +="\n";				
				outputText +="test result";
				outputText +=testExecuted;
				outputText +="\n";
				//comparing test case output with the user's program output
				if (testOut.equals(testExecuted))
				{					
					outputText += "the program generated the correct output for test case";
					outputText += tidData[k];
					outputText += "\n";
					correct++;
					finalWeightage+=weightage[k];
					testStatus = true;
					TestCaseDAO testcaseDAO=new TestCaseDAO();
					TestCase testcase=new TestCase(pid,tidData[k],testStatus);
					try {
						testcaseDAO.updateStatus(testcase);
					} catch (DataAccessException e) {
					outputText+="sql error";
					}
				} 
				else
				{
					outputText += "the program result was incorrect for the test case number";
					outputText += tidData[k];
					outputText += "\n";
					incorrect++;
					testStatus = false;
					TestCaseDAO testcaseDAO=new TestCaseDAO();
					TestCase testcase=new TestCase(pid,tidData[k],testStatus);
					try 
					{
						testcaseDAO.updateStatus(testcase);
					}  
					catch (DataAccessException e) {
					     outputText+="sql error";
					}
				}
				testNumber++;
			}
			// detail program execution report
			outputText += "the program output was correct for";
			outputText += correct;
			outputText += "number of test cases";
			outputText += "\n";
			outputText += "the program output was incorrect for";
			outputText += incorrect;
			outputText += "number of test cases";
			outputText += "\n";
			outputText+="the total score is";
            outputText+=finalWeightage;
			outputText+="\n";
					
			if(numberTest==correct)
			{
				statusProgram=true;
			}
		        outputText+="the program id is";
			    outputText+=pid;
			    outputText+="\n";
			    outputText+="the status of the program execution is";
			    outputText+=statusProgram;
			    outputText+="\n";
		}
			
		//code to update test status in the tree
		SolutionDAO solutionDAO=new SolutionDAO();
		Solution solution=new Solution(pid,statusProgram,outputText);
		try
		{
			solutionDAO.updateStatus(solution);
		} 
		catch (DataAccessException e)
		{
			outputText+="data access error";
		}
		//returning the report to be printed in the details panel
		return outputText;
		
		
	} 
	
	
	private static Connection getConnection()
	{
		Connection con = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			// System.out.println("MySQL JDBC driver loaded ok.");

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/apet?user=root1&password=password&autoReconnect=true&useSSL=false");


		} catch (Exception e)
		{

		}
		return con;
	}

}
