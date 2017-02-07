package com.threey.packagetool.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.threey.packagetool.pojo.Operation;
import com.threey.packagetool.pojo.PackageContext;

/**
 * operation工具类
 * @author threey
 *
 */
public class OperationUtil {
	
	private static final String SRC_KEY_WORD = "/src/";
	private static final String JAVA_KEY_WORD = ".java";
	
	public static String getRealPath(Operation op,PackageContext pc){
		if (null==op||null==op.getPath()||null==pc) {
			return null;
		}
		return op.getPath().replaceAll(pc.getSvnPath()+pc.getSvnProjectName(), pc.getWorkSpacePath()+pc.getWorkSpaceProjectName());
	}
	public static List<String> getClassesPath(Operation op,PackageContext pc){
		if (null==op||null==op.getPath()||null==pc) {
			return null;
		}
		if (!(op.getPath().indexOf(SRC_KEY_WORD)>0)) {
			List<String> list = new ArrayList<>();
			list.add(getRealPath(op, pc));
			return list;
		}else {
			System.out.println(op.getPath());
			System.out.println(pc.getSvnPath()+pc.getSvnProjectName()+"src/");
			System.out.println(pc.getWorkSpacePath()+pc.getWorkSpaceProjectName()+/*pc.getWebRoot()+*/pc.getClassesPath());
			String path = op.getPath().replaceAll(pc.getSvnPath()+pc.getSvnProjectName()+"src/"
					,pc.getWorkSpacePath()+pc.getWorkSpaceProjectName()/*+pc.getWebRoot()*/+pc.getClassesPath());
			System.out.println(path);
			List<String> list = new ArrayList<>();
			if (op.getPath().endsWith(JAVA_KEY_WORD)) {
				String parent_path = path.substring(0, path.lastIndexOf("/"));
				//System.out.println(parent_path);
				String javaName = path.substring(path.lastIndexOf("/")+1,path.lastIndexOf("."));
				//System.out.println(javaName);
				File file = new File(parent_path);
				File[] files = file.listFiles(f->{
					return (f.getName().startsWith(javaName)||f.getName().startsWith(javaName+"$"));
					});
				for (File f : files) {
					list.add(f.getAbsolutePath().replaceAll("\\\\", "/"));
				}
				return list;
			}else{
				list.add(path);
				return list;
			}
		}
	}

}
