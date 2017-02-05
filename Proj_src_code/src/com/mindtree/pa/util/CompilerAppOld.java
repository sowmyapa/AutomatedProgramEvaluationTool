package com.mindtree.pa.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

public class CompilerAppOld
{
	/**
	 * @param args
	 * @throws Exception
	 */
	public String compile(int pid)
	{
        System.out.println(pid);
		PrintStream console = System.out;
		String outputText = new String();
		int testNumber = 1;

		int correct = 0, incorrect = 0, i = 0;
		int k = 0;
		String className, absolutePath, loadedClass;
		// byte testInputs[]=new byte[50];
		int outExpected[] = new int[10];
		BufferedReader stdin = new BufferedReader(new InputStreamReader(
				System.in));
		String temp[] = new String[10];
/*
		//System.out.println("Enter the problem id");
		try
		{
			probId = stdin.readLine();
		} catch (IOException e1)
		{
			String exception = "Invalid input";
			return exception;
		}*/
		/*System.out.println("Enter the name of the class to be executed");
		try
		{
			className = stdin.readLine();
		} catch (IOException e1)
		{

			String exception = "Invalid class name input";
			return exception;
		}*/
		absolutePath = "F://project MINDTREE//workspace//Integration4//src//com//mindtree//pa//util//solutionFile.java";
		/*absolutePath += className;
		absolutePath += ".java";*/
		loadedClass = "com.mindtree.pa.util.";
		loadedClass += "solutionFile";
		/*loadedClass = "com.mindtree.pa.util.";
		loadedClass += className;*/
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
						.executeQuery("select input,output from testing where pid="
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

					// System.out.println(temp[k]);
					// temp[k]+=String.valueOf(rs.getString(1)+"\n");
					// temp[k]+=String.valueOf(rs.getInt(2)+"\n");
					// temp[k]+=String.valueOf(rs.getInt(3)+"\n");
					outExpected[k] = rs.getInt(2);
					k++;
				}
				rs.close();
				con.close();
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				String exception = "Problem in sql";
				return exception;
			}

			JavaCompiler jc = ToolProvider.getSystemJavaCompiler();
			StandardJavaFileManager sjfm = jc.getStandardFileManager(null,
					null, null);
			File javaFile = new File(absolutePath);
			File[] javaFile1 = new File[1];
			javaFile1[0] = javaFile;
			Iterable fileObjects = sjfm.getJavaFileObjects(javaFile1);
			String[] options = new String[]{"-d",
					"F://project MINDTREE//workspace//integration4//src"};
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
			
			/*
			 * for(int num=0;num<k;num++) {
			 * System.out.println(outExpected[num]); }
			 */
           
			while (k != 0)
			{
				k--;

				URL[] urls;
				try
				{
					urls = new URL[]{new URL(
							"File://F://project MINDTREE//workspace//integration4//src//")};
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
											"F://project MINDTREE//workspace//integration1//src//com//mindtree//pa//util//out.txt")));
				
				
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
					
					String exception = "Compiler error";
					return exception;
				} catch (IllegalAccessException e1)
				{
					String exception = "Compiler error";
					return exception;
				} catch (InvocationTargetException e1)
				{
					String exception = "Compiler error";
					return exception;
				}
				i++;
				BufferedReader in1;
				try
				{
					in1 = new BufferedReader(
							new FileReader(
									"F://project MINDTREE//workspace//integration1//src//com//mindtree//pa//util//out.txt"));
					
				} catch (FileNotFoundException e)
				{
					String exception = "File not found exception";
					return exception;
				}
				int testExecuted;
				try
				{
					testExecuted = Integer.parseInt(in1.readLine());
				} catch (NumberFormatException e)
				{
					String exception = "Number format exception";
					return exception;
				} catch (IOException e)
				{
					String exception = "input output exception";
					return exception;
				}
				int testOut = outExpected[k];

				System.setOut(console);

				if (testExecuted == testOut)
				{
					
					outputText = "the program generated the correct output for test case";
					outputText += testNumber;
					outputText += "\n";
					System.out
							.println("the program generated the correct output for test case"
									+ testNumber);
					correct++;
				} else
				{
					
					outputText = "the program result was incorrect for the test case number";
					outputText += testNumber;
					outputText += "\n";
					System.out
							.println("the program result was incorrect for the test case number"
									+ testNumber);
					incorrect++;
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
			System.out.println("the program output was correct for" + correct
					+ "number of test cases");
			System.out.println("the program output was incorrect for"
					+ incorrect + "number of test cases");
		}
		// new SolutionPanel().setReviewTextField(outputText);
		// solutionPanel.setReviewTextField(outputText);
		/*
		 * SolutionPanel solutionPanel=new SolutionPanel(); Solution
		 * solution=new Solution();
		 * solutionPanel.setSolution(solution,outputText);
		 */
		return outputText;

	}
	private static Connection getConnection()
	{
		Connection con = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			// System.out.println("MySQL JDBC driver loaded ok.");

			con = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/demotest?user=root&password=nikitha");

		} catch (Exception e)
		{

			System.err.println("Exception: " + e.getMessage());
		}
		return con;
	}

}
