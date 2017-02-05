package com.mindtree.pa.entity;

import javax.swing.Icon;

public class NodeData {

	private int id;
	
	private int tid;

	private int type;

	private String caption;

	private Icon icon;
	
	

	public NodeData(int id, int tid,int type, String caption, Icon icon) {
		this.id = id;
		this.tid=tid;
		this.type = type;
		this.caption = caption;
		this.icon = icon;
		
	}

	


	public String toString() {
		return caption;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Icon getIcon() {
		return icon;
	}

	public void setIcon(Icon icon) {
		this.icon = icon;
	}

	public int getTid()
	{
		return tid;
	}

	public void setTid(int tid)
	{
		this.tid = tid;
	}
}
