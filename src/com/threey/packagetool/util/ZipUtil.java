package com.threey.packagetool.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class ZipUtil {
	
static final int BUFFER = 8192;     
    
     private File zipFile;     
       
     public ZipUtil(String pathName) {     
         zipFile = new File(pathName);     
     }     
     public void compress(String... pathName) {   
         ZipOutputStream out = null;     
         try {    
             FileOutputStream fileOutputStream = new FileOutputStream(zipFile);     
             CheckedOutputStream cos = new CheckedOutputStream(fileOutputStream,     
                     new CRC32());     
             out = new ZipOutputStream(cos);     
             String basedir = "";   
             for (int i=0;i<pathName.length;i++){  
                 compress(new File(pathName[i]), out, basedir);     
             }  
             out.close();    
         } catch (Exception e) {     
             throw new RuntimeException(e);     
         }   
     }     
     public void compress(String srcPathName) {     
         File file = new File(srcPathName);     
         if (!file.exists())     
             throw new RuntimeException(srcPathName + "不存在！");     
         try {     
             FileOutputStream fileOutputStream = new FileOutputStream(zipFile);     
             CheckedOutputStream cos = new CheckedOutputStream(fileOutputStream,     
                     new CRC32());     
             ZipOutputStream out = new ZipOutputStream(cos);     
             String basedir = "";     
             compress(file, out, basedir);     
             out.close();     
         } catch (Exception e) {     
             throw new RuntimeException(e);     
         }     
     }     
     
     private void compress(File file, ZipOutputStream out, String basedir) {     
         /* 判断是目录还是文件 */    
         if (file.isDirectory()) {     
             //System.out.println("压缩：" + basedir + file.getName());     
             this.compressDirectory(file, out, basedir);     
         } else {     
             //System.out.println("压缩：" + basedir + file.getName());     
             this.compressFile(file, out, basedir);     
         }     
     }     
     
     /** 压缩一个目录 */    
     private void compressDirectory(File dir, ZipOutputStream out, String basedir) {     
         if (!dir.exists())     
             return;     
     
         File[] files = dir.listFiles();   
         if (files.length==0) {
        	 compressEmptyDictory(dir,out,basedir);
		}
         for (int i = 0; i < files.length; i++) {     
             /* 递归 */    
             compress(files[i], out, basedir + dir.getName() + "/");     
         }     
     } 
     private void compressEmptyDictory(File dir, ZipOutputStream out, String basedir){
    	 try {
			out.putNextEntry(new ZipEntry(basedir+dir.getName()+ "/"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		} 
     }
     
     /** 压缩一个文件 */    
     private void compressFile(File file, ZipOutputStream out, String basedir) {     
         if (!file.exists()) {     
             return;     
         }     
         try {     
             BufferedInputStream bis = new BufferedInputStream(     
                     new FileInputStream(file));     
             ZipEntry entry = new ZipEntry(basedir + file.getName());     
             out.putNextEntry(entry);     
             int count;     
             byte data[] = new byte[BUFFER];     
             while ((count = bis.read(data, 0, BUFFER)) != -1) {     
                 out.write(data, 0, count);     
             }     
             bis.close();     
         } catch (Exception e) {     
             throw new RuntimeException(e);     
         }     
     }     
    public static void main(String[] args) {     
    	 ZipUtil zc = new ZipUtil("E:/test.zip");     
         zc.compress("D:/AppServer");    
     }  
    public static void zipFile(String folder ,String zipFileName){
    	ZipUtil zc=new ZipUtil(zipFileName);
    	zc.compress(folder);
    }

}
