package com.threey.packagetool.pojo;

import java.io.Serializable;

/**
 * 一次操作
 * @author threey
 *
 */
public class Operation implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OperationType type;
	private String path;
	public OperationType getType() {
		return type;
	}
	public void setType(OperationType type) {
		this.type = type;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	@Override
	public String toString(){
		switch (type) {
		case ADD: return "   add:"+path+"\n";
		case MODIFY: return "   modify:"+path+"\n";
		case DELETE: return "   delete:"+path+"\n";
		default:
			return "\n";
		}
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Operation other = (Operation) obj;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		return true;
	}

}
