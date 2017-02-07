package com.threey.packagetool.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import com.threey.packagetool.pojo.PackageContext;

/**
 * 加载配置的类
 * @author threey
 *
 */
public class ConfigUtil {
	
	private String fileName="default.properties";
	private Properties properties;
	
	public ConfigUtil(){
		init();
	}
	public ConfigUtil(String configName){
		this.fileName=configName+".properties";
		init();
	}

	private void init() {
		this.properties = new Properties();
		try {
			/*String path= new Foo().getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
			if (path.endsWith(".jar")) {
				path = path.substring(0, path.lastIndexOf(File.separator));
			}*/
			this.properties.load(new FileInputStream(
					new File(ConfigUtil.class.getResource("/").getPath()+File.separator+"conf"+File.separator+fileName)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public PackageContext getPackageContext(){
		PackageContext pc = new PackageContext();
		//pc.setClassesPath(properties.getProperty("classes_path"));
		pc.setWorkSpacePath(properties.getProperty("work_space_path"));
		pc.setWorkSpaceProjectName(properties.getProperty("work_space_project_name"));
		pc.setSvnPath(properties.getProperty("svn_path"));
		pc.setSvnProjectName(properties.getProperty("svn_project_name"));
		if (null!=properties.getProperty("web_root")) {
			pc.setWebRoot(properties.getProperty("web_root"));
		}
		if (null!=properties.getProperty("classes_path")) {
			pc.setClassesPath(properties.getProperty("classes_path"));
		}
		return pc;
	}
	public static String[] getConfigs(){
		File baseFile =new File(ConfigUtil.class.getResource("/").getPath()+"conf");
		File[] files = baseFile.listFiles(f->f.getName().endsWith(".properties"));
		
		List<String> configs = new ArrayList<>();
		for (File file : files) {
				configs.add(file.getName().replaceAll("\\.properties", ""));
		}
		configs.sort(null);
		return configs.toArray(new String[]{});
	}
	class Foo{
		
	}
}
