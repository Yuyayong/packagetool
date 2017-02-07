/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.threey.packagetool.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import com.threey.packagetool.pojo.PackageContext;
import com.threey.packagetool.pojo.PackageType;
import com.threey.packagetool.service.PackageService;
import com.threey.packagetool.util.ConfigUtil;
import com.threey.packagetool.util.InfoUtil;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 *
 * @author threey
 */
public class FXMLDocumentController implements Initializable {

	@FXML
	private TextField exportFileTextField;
	@FXML
	private TextField changeLogFileTextFiled;
	@FXML
	private RadioButton radioClass;
	@FXML
	private RadioButton radioSrc;
	@FXML
	private TextArea infoArea;
	@FXML
	private ChoiceBox<String> configFile;
	private File logFile;
	private File exportFile;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		InfoUtil.setInfoArea(infoArea);
		InfoUtil.infoLn("启动成功！");
		String[] configs = ConfigUtil.getConfigs();
		configFile.setItems(FXCollections.observableArrayList(configs));
		configFile.autosize();
	}

	@FXML
	private void doPackage(ActionEvent event) {
		if (check()) {
			ConfigUtil cfu = new ConfigUtil(configFile.getValue());
			PackageContext pc = cfu.getPackageContext();
			pc.setZipFile(exportFile.getAbsolutePath());
			pc.setLogPath(logFile.getAbsolutePath());
			if (radioSrc.isSelected()) {
				pc.setType(PackageType.SRC);
			}else{
				pc.setType(PackageType.CLASS);
			}
			new PackageService().doPakage(pc);
		}
	}

	private boolean check() {
		if (logFile == null || !logFile.exists()) {
			InfoUtil.infoLn("日志文件选择不对!");
			return false;
		}
		if (exportFile == null) {
			InfoUtil.infoLn("请指定要打包文件!");
			return false;
		}
		if (configFile.getValue()==null||"".equals(configFile.getValue())) {
			InfoUtil.infoLn("请指选择配置!");
			return false;
		}
		return true;
	}

	@FXML
	private void doChooseFileLog(ActionEvent e) {
		FileChooser fc = new FileChooser();
		fc.setTitle("请选择");
		logFile = fc.showOpenDialog(null);
		if (null != logFile) {
			changeLogFileTextFiled.setText(logFile.getAbsolutePath());
		}

	}

	@FXML
	private void doChooseFileExport(ActionEvent e) {
		FileChooser fc = new FileChooser();
		fc.setTitle("请选择");
		fc.setSelectedExtensionFilter(new ExtensionFilter("zipfile", "*.zip"));
		exportFile = fc.showSaveDialog(null);
		if (null != exportFile) {
			exportFileTextField.setText(exportFile.getAbsolutePath());
		}
	}

}
