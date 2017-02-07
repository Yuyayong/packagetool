package com.threey.packagetool.resolver;

import java.io.File;
import java.util.List;

import com.threey.packagetool.pojo.CommitMent;

/**
 * 转换为commitMent
 * @author threey
 *
 */
public interface CommitMentResolver {

	String[] chengeToFiles(List<CommitMent> cms);
	List<CommitMent> getCommitMents();
}
