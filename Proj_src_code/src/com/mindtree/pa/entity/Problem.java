package com.mindtree.pa.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Problem {

	private int pid;

	private String name;

	private int semester;

	private String description;

	private int marks;

	private int time;

	private Solution solution;

	private Date createdOn;

	private String createdBy;

	private String pemail;

	private List<TestCase> testCases;

	public Problem() {
		pid = 0;
		semester = 0;
		description = "";
		marks = 0;
		time = 0;
		name = "";
		solution = new Solution();
		createdOn = new Date();
		createdBy = "";
		pemail = "";
		testCases = new ArrayList();

	}

	public Problem(int pid) {
		this.pid = pid;

	}

	public Problem(int pid, String name, int sem, String desciption, int time) {
		this.pid = pid;
		this.name = name;
		this.semester = sem;
		this.description = desciption;
		this.testCases = new ArrayList<TestCase>();
		this.time = time;
	}

	public void addTestCase(TestCase testCase) {
		this.testCases.add(testCase);
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedOn() {
		return createdOn.toString();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public Solution getSolution() {
		return solution;
	}

	public void setSolution(Solution solution) {
		this.solution = solution;
	}

	public List<TestCase> getTestCases() {
		return testCases;
	}

	public void setTestCases(List<TestCase> testCases) {
		this.testCases = testCases;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPemail() {
		return pemail;
	}

	public void setPemail(String pemail) {
		this.pemail = pemail;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
}
