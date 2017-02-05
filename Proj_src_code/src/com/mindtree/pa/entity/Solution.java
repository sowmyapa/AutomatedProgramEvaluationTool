package com.mindtree.pa.entity;

import java.util.Date;

public class Solution {

	private int pid;

	private String sourceCode;

	private boolean status;

	private String report;
	private Date createdOn;
	
	public Solution(int pid,boolean status,String report)
	{
		this.pid=pid;
		this.status=status;
		this.report=report;
	
	}
	
	public Solution(int pid,String sourceCode)
	{
		this.pid=pid;
	   this.sourceCode=sourceCode;
		
	}
	
	public Solution(int pid,String sourceCode,boolean status)
	{
		this.pid=pid;
		this.sourceCode=sourceCode;
		this.status=status;
	
	}
	
	public Solution(int pid)
	{
		this.pid=pid;
	
	}

	public Solution() {
		pid = 0;
		sourceCode = "";
		status = false;
		createdOn = new Date();
		report="";
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getReport() {
		return report;
	}

	public void setReport(String report) {
		this.report = report;
	}
}
