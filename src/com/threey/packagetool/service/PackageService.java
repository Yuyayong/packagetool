package com.threey.packagetool.service;

import java.io.File;

import com.threey.packagetool.pojo.PackageContext;
import com.threey.packagetool.resolver.CommitMentResolver;
import com.threey.packagetool.resolver.SvnLogCommitMentResolver;
import com.threey.packagetool.resolver.TortoiseSvnLogCommitMentResolver;
import com.threey.packagetool.util.ConfigUtil;
import com.threey.packagetool.util.FileUtil;
import com.threey.packagetool.util.InfoUtil;
import com.threey.packagetool.util.ZipUtil;

public class PackageService {
	
	private  CommitMentResolver commitMentResolver ;
	public void doPakage(PackageContext context){
		//commitMentResolver = new SvnLogCommitMentResolver(context);
		commitMentResolver = new TortoiseSvnLogCommitMentResolver(context);
		clearTemp(context);
		copyFiles(commitMentResolver.chengeToFiles(commitMentResolver.getCommitMents()),context);
		InfoUtil.infoLn("压缩文件.....");
		zipFiles(context);
		InfoUtil.infoLn("压缩文件完成，开始请理临时目录.....");
		clearTemp(context);
		InfoUtil.infoLn("清理完成.....");
		InfoUtil.infoLn("打包成功！！！！！输出文件为：");
		InfoUtil.infoLn(context.getZipFile());
	}
	/*public void analysisLog(PackageContext context){
		commitMentResolver = new SvnLogCommitMentResolver(context);
		commitMentResolver.getCommitMents();
	}*/
	public void copyFiles(String[] files,PackageContext context){
		FileUtil.copyFileToTemp(files,context );
	}
	public void zipFiles(PackageContext context){
		ZipUtil.zipFile(context.getTempPath()+"/"+context.getWorkSpaceProjectName(), context.getZipFile());
	}
	
	
	public void clearTemp(PackageContext context){
		File file = new File(PackageService.class.getResource("/").getPath()+"temp");
		InfoUtil.infoLn("清理临时文件夹....");
		InfoUtil.infoLn(file.getAbsolutePath());
		if (!file.isDirectory()) {
			file.delete();
		}
		if (file.exists()) {
			FileUtil.deleteFiles(file);
		}
		file.mkdir();
		context.setTempPath(file.getAbsolutePath());
	}
	
}
