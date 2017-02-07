package com.threey.packagetool.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * 需要打包的信息
 * @author threey
 *
 */
public class PackageContext implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * svn路径 如：/development_library/shanghai/
	 */
	private String svnPath;
	/**
	 * svn工程名 如：educloud
	 */
	private String svnProjectName;
	/**
	 * workspace D:/lianchuang/workspace_svn/
	 */
	private String workSpacePath;
	/**
	 * workspace 中的工程名   如 ah_educloud
	 */
	private String workSpaceProjectName;
	/**
	 * rootContext 有的是webRoot 有的是WebContent
	 */
	private String webRoot ="WebRoot/";
	
	private String zipFile;
	private String tempPath;
	private String logPath;
	/**
	 * 编译文件路径
	 */
	private String classesPath="WEB-INF/classes/";
	
	private List<CommitMent> commits;
	private PackageType type;

	public String getSvnPath() {
		return svnPath;
	}

	public void setSvnPath(String svnPath) {
		this.svnPath = svnPath;
	}

	public String getSvnProjectName() {
		return svnProjectName;
	}

	public void setSvnProjectName(String svnProjectName) {
		this.svnProjectName = svnProjectName;
	}

	public String getWorkSpacePath() {
		return workSpacePath;
	}

	public void setWorkSpacePath(String workSpacePath) {
		this.workSpacePath = workSpacePath;
	}

	

	public String getWorkSpaceProjectName() {
		return workSpaceProjectName;
	}

	public void setWorkSpaceProjectName(String workSpaceProjectName) {
		this.workSpaceProjectName = workSpaceProjectName;
	}

	public String getClassesPath() {
		return classesPath;
	}

	public void setClassesPath(String classesPath) {
		this.classesPath = classesPath;
	}

	public String getZipFile() {
		return zipFile;
	}

	public void setZipFile(String zipFile) {
		this.zipFile = zipFile;
	}

	public String getTempPath() {
		return tempPath;
	}

	public void setTempPath(String tempPath) {
		this.tempPath = tempPath;
	}

	public List<CommitMent> getCommits() {
		return commits;
	}

	public void setCommits(List<CommitMent> commits) {
		this.commits = commits;
	}

	public PackageType getType() {
		return type;
	}

	public void setType(PackageType type) {
		this.type = type;
	}

	public String getLogPath() {
		return logPath;
	}

	public void setLogPath(String logPath) {
		this.logPath = logPath;
	}

	public String getWebRoot() {
		return webRoot;
	}

	public void setWebRoot(String webRoot) {
		this.webRoot = webRoot;
	}
	
	
}
