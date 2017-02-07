package com.threey.packagetool.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 一次提交
 * @author threey
 *
 */
public class CommitMent implements Serializable{


	private static final long serialVersionUID = 1L;
	
	private String version;
	private String author;
	private Date time;
	private List<Operation> operations;
	private String desc="";
	
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public List<Operation> getOperations() {
		return operations;
	}
	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString(){
		return new StringBuilder(version).append(",").append(author).append(",").append(time)
				.append(",").append(desc).append("\n").append(operations).append("\n").toString();
	}
	
}
