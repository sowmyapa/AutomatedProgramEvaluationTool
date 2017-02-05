package com.mindtree.pa.entity;

public class TestCase {

	private int pid;
	private int tid;

	private String input;

	private String output;

	private String title;

	private String description;

	private boolean status;

	private float weightage;

	public TestCase(int pid,int tid,String input,String output, String title,String description, boolean status,float score) {
		this.pid = pid;
		this.tid=tid;
		this.title = title;
		this.input = input;
		this.output = output;
		this.description = description;
		this.status = status;
		this.weightage = score;
	}
	
	public TestCase(int pid,int tid, boolean status) {
		this.pid = pid;
		this.tid=tid;
		this.status = status;
		}
	
	public TestCase(int pid) {
		this.pid = pid;
		}
	
	
	public TestCase() {
		this.pid = 0;
		this.title = null;
		this.input = "";
		this.output = "";
		this.description = "";
		this.status =false;
		this.weightage = 0;
		this.tid=0;
	}

	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	
	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	

	
	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}


	public float getWeightage() {
		return weightage;
	}


	public void setWeightage(float weightage) {
		this.weightage = weightage;
	}
}