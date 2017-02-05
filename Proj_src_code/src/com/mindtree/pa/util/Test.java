package com.mindtree.pa.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Iterator;

import com.mindtree.pa.data.ProblemDAO;
import com.mindtree.pa.data.TestCaseDAO;
import com.mindtree.pa.entity.Problem;
import com.mindtree.pa.entity.TestCase;

public class Test {
	private File file;
    /* function to run the runXml function */    
	public Test(File file) {
		this.file = file;
		runXml();
	}
	
	/*public static void main(String[] args){
		File file = new File("/Users/sowmyaparameshwara/Desktop/Assignments/AutomatedProgramEvaluationTool/Proj_src_code/src/com/mindtree/pa/util/calci.xml");
		Test test = new Test(file);
	}*/

	public void runXml() {

		try {
			// READ FILE INTO XML STRING
			Boolean check=false;
			String xml = new String();
			
			FileReader fr = new FileReader(this.file);
			BufferedReader br = new BufferedReader(fr);
			StringBuffer sb = new StringBuffer();
			String eachLine = br.readLine();
			while (eachLine != null) {
				sb.append(eachLine);
				sb.append("\n");
				eachLine = br.readLine();
			}
			xml = sb.toString();
			System.out.println(xml);

			// GET OBJECT FROM XML STRING
			XmlUtil test = new XmlUtil();
			ProblemType problemType = test.getProblemType(xml);
			System.out.println("pid: " + problemType.getPid());
			System.out.println("name: " + problemType.getName());
			System.out.println("sem: " + problemType.getSemester());
			System.out.println("desc: " + problemType.getDescription());
			System.out.println("marks: " + problemType.getMarks());
			System.out.println("time: " + problemType.getTime());
			System.out.println("on: " + problemType.getCreatedOn());
			System.out.println("by: " + problemType.getCreatedBy());
			System.out.println("pmail: " + problemType.getPemail());
			System.out.println("test cases: ");
			Problem data1 =checkingPid(problemType);
			ProblemDAO tc1 = new ProblemDAO();
		 check=tc1.checkPid(data1);
		 if(check==false)		
		 {
			
			
			Problem dat =getProblem(problemType);
			ProblemDAO tc = new ProblemDAO();
			tc.insert(dat);
			
			for (Iterator iterator = problemType.getTest().getCase().iterator(); iterator
					.hasNext();) {
				CaseType testcase = (CaseType) iterator.next();
				System.out.println("\t pid: " + testcase.getPid());
				System.out.println("\t tid: " + testcase.getTid());
				System.out.println("\t input: " + testcase.getInput());
				System.out.println("\t output: " + testcase.getOutput());
				System.out.println("\t title: " + testcase.getTitle());
				System.out.println("\t desc: " + testcase.getDescription());
				System.out.println("\t weightage: " + testcase.getWeightage());
				TestCase data =getTestCase(testcase);
				TestCaseDAO tcc = new TestCaseDAO();
				tcc.insert(data);
			}
			

		}
		 else
		{
			
		} 
		 }
		catch (Exception e) {
			e.printStackTrace();
			System.out
					.println("An error occurred while performing the operation!");
		}
		
		
	}


private static TestCase getTestCase(CaseType cType)
{
	TestCase data = new TestCase();
	data.setPid(cType.getPid());
	data.setTid(cType.getTid());
	data.setInput(cType.getInput());
	data.setOutput(cType.getOutput());
	data.setTitle(cType.getTitle());
	data.setDescription(cType.getDescription());
	data.setWeightage(cType.getWeightage());
	return data;
}

private static Problem checkingPid(ProblemType pType)
{
	Problem data1 = new Problem();
	data1.setPid(pType.getPid());
	return data1;
	}
	
	
private static Problem getProblem(ProblemType pType)
{
	Problem dat = new Problem();
	dat.setPid(pType.getPid());
	dat.setName(pType.getName());
	dat.setSemester(pType.getSemester());
	dat.setDescription(pType.getDescription());
	dat.setMarks(pType.getMarks());
	dat.setTime(pType.getTime());
	//dat.setCreatedOn(pType.getCreatedOn());
	dat.setCreatedBy(pType.getCreatedBy());
	dat.setPemail(pType.getPemail());
	return dat;
}
}
