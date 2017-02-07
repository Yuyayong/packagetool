package com.threey.packagetool.util;

import javafx.scene.control.TextArea;

public class InfoUtil {
	private static TextArea infoArea;
	
	

	public static void info(String info) {
		infoArea.appendText(info);
	}

	public static void infoLn(String info) {
		info(info);
		info("\n");
	}

	public static TextArea getInfoArea() {
		return infoArea;
	}

	public static void setInfoArea(TextArea infoArea) {
		InfoUtil.infoArea = infoArea;
	}
	
	

}
