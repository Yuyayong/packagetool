package com.threey.packagetool.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import com.threey.packagetool.pojo.PackageContext;
import com.threey.packagetool.pojo.PackageType;

public class FileUtil {

	public static  void deleteFiles(File f){
		if (f.isFile()) {
			deleteFile(f);
		}else{
			File[] files = f.listFiles();
			for (File file : files) {
				deleteFiles(file);
			}
			deleteFile(f);
		}
		
	}
	public static void deleteFile(File f){
		//InfoUtil.infoLn("清除："+f.getAbsolutePath());
		f.delete();
	}
	public static void copyFile(File srcFile,File destFilePath,String root){
		String destPath=destFilePath.getAbsolutePath();
		if (srcFile.isFile()) {
			fileChannelCopy(srcFile, new File(chageDestPath(srcFile.getAbsolutePath(), destPath, root)));
		}else{
			new File(chageDestPath(srcFile.getAbsolutePath(), destPath, root)).mkdirs();
			/*File[] files = srcFile.listFiles();
			if (files.length==0) {
				new File(chageDestPath(srcFile.getAbsolutePath(), destPath, root)).mkdirs();
			}else{
				for (File file : files) {
					fileChannelCopy(srcFile, new File(chageDestPath(file.getAbsolutePath(), destPath, root)));
				}
			}*/
		}
	}
	public static void copyFileToTemp(String[] file,PackageContext pc){
		InfoUtil.infoLn("开始复制文件到临时文件夹");
		File dest = new File(pc.getTempPath()+"/"+pc.getWorkSpaceProjectName());
		for (String str : file) {
			System.out.println(str);
			if (pc.getType()==PackageType.CLASS) {
				copyFile(new File(str) , dest, pc.getWorkSpacePath()+pc.getWorkSpaceProjectName()/*+pc.getWebRoot()*/);
			}else {
				copyFile(new File(str) , dest, pc.getWorkSpacePath()+pc.getWorkSpaceProjectName());
			}
		}
	}
	public static String chageDestPath(String src,String dest,String root){
		src = src.replaceAll("\\\\", "/");
		dest = dest.replaceAll("\\\\", "/");
		String reStr = dest+"/"+src.replaceFirst(root,"");
		System.out.println("复制到："+reStr);
		return reStr;
	}
	public static void fileChannelCopy(File s, File t) {
        FileInputStream fi = null;
        FileOutputStream fo = null;
        FileChannel in = null;
        FileChannel out = null;
        try {
        	if (!t.exists()) {
        		if (!t.getParentFile().exists()) {
					t.getParentFile().mkdirs();
				}
        		t.createNewFile();
        	}
            fi = new FileInputStream(s);
            fo = new FileOutputStream(t);
            in = fi.getChannel();//得到对应的文件通道
            out = fo.getChannel();//得到对应的文件通道
            in.transferTo(0, in.size(), out);//连接两个通道，并且从in通道读取，然后写入out通道
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fi.close();
                in.close();
                fo.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
