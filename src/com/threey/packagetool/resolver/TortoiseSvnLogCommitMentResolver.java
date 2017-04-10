package com.threey.packagetool.resolver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.threey.packagetool.pojo.CommitMent;
import com.threey.packagetool.pojo.Operation;
import com.threey.packagetool.pojo.OperationType;
import com.threey.packagetool.pojo.PackageContext;
import com.threey.packagetool.pojo.PackageType;
import com.threey.packagetool.util.InfoUtil;
import com.threey.packagetool.util.OperationUtil;

/**
 * TortoiseSvn 日志解析
 * @author threey
 *
 */
public class TortoiseSvnLogCommitMentResolver implements CommitMentResolver{

	private PackageContext context;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss");
	public TortoiseSvnLogCommitMentResolver() {
	}
	public TortoiseSvnLogCommitMentResolver(PackageContext c){
		this.context=c;
	}
	@Override
	public String[] chengeToFiles(List<CommitMent> cms) {
		cms.sort(( a,  b)->{
			 if(a.getTime().before(b.getTime())){
				 return 1;
			 }else{
				 return -1;
			 }
		});
		List<String>  list = new ArrayList<>();
		Set<Operation> resultOps = new HashSet<>();
		cms.forEach(cm->{
			List<Operation> ops = cm.getOperations();
			ops.forEach(op->{
				if (op.getType()==OperationType.DELETE) {
					resultOps.remove(op);
				}else{
					resultOps.add(op);
				}
			});
		});
		resultOps.forEach(op->{
			fillList(op, list);
		});
		list.sort((a,b)->{
			return a.compareTo(b);
		});
		return list.toArray(new String[]{});
	}

	@Override
	public List<CommitMent> getCommitMents() {
		String str="";
		BufferedReader bf = null;
		List<CommitMent> list = new ArrayList<>();
		List<String> strs = new ArrayList<>(); 
		try {
			bf = new BufferedReader(new FileReader(context.getLogPath()));
			
			while ((str=bf.readLine())!=null) {
				str = str.trim();
				if (str.length()>0) {
					strs.add(str);
				}else{
					CommitMent cm = getCommitMentByListOfStrings(strs);
					if (null!=cm) {
						list.add(cm);
					}else{
						InfoUtil.infoLn("解析一条提交信息错误！");
						InfoUtil.infoLn(strs.toString());
						strs.clear();
					}
				}
				
			}
		} catch (Exception e) {
			InfoUtil.infoLn("解析提交记录异常！！");
			e.printStackTrace();
		}finally {
			if (null!=bf) {
				try {
					bf.close();
				} catch (IOException e) {
				}
			}
		}
		
		return list;
	}

	public PackageContext getContext() {
		return context;
	}

	public void setContext(PackageContext context) {
		this.context = context;
	}
	
	private CommitMent getCommitMentByListOfStrings(List<String> strs){
		if (strs.size()<3) {
			strs.clear();
			return null;
		}
		CommitMent cm = new CommitMent();
		try {
			cm.setVersion(strs.get(0).split(":")[1]);
			cm.setAuthor(strs.get(1).split(":")[1]);
			String timeStr = strs.get(1).split(":")[1].trim();
			cm.setTime(sdf.parse(timeStr));
			List<Operation> ops = new ArrayList<>();
			boolean flag = false;
			for (int i = 1; i < strs.size(); i++) {
				
				String str = strs.get(i);
				if (str.equals("----")) {
					flag = true;
				}
				if (flag) {
					if (str.startsWith("A")) {
						Operation op = new Operation();
						op.setType(OperationType.ADD);
						op.setPath(str.substring(str.indexOf("/")));
						ops.add(op);
					}else if (str.startsWith("M")) {
						Operation op = new Operation();
						op.setType(OperationType.ADD);
						op.setPath(str.substring(str.indexOf("/")));
						ops.add(op);
					}else if (str.startsWith("D")) {
						Operation op = new Operation();
						op.setType(OperationType.ADD);
						op.setPath(str.substring(str.indexOf("/")));
						ops.add(op);
					}else {
						cm.setDesc(cm.getDesc()+str+"\n");
					}
				}
				
				
			}
			cm.setOperations(ops);
		} catch (Exception e) {
			//strs.clear();
			return null;
		}
		strs.clear();
		return cm;
	}
	
	private void fillList(Operation op ,List<String> list){
		if (context.getType()==PackageType.SRC) {
			list.add(OperationUtil.getRealPath(op, context));
		}else{
			list.addAll(OperationUtil.getClassesPath(op, context));
		}
	}
}
