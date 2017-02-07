/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.threey.packagetool.application;






import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


/**
 *
 * @author threey
 */
public class JavaFXApplication extends Application {
	
    
    @Override
    public void start(Stage stage) throws Exception {
        
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("com/threey/packagetool/fxml/FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        stage.setTitle("打包工具0.5");
        stage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("res/packageIcon.jpg")));
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    /*public static void initLog(){
    	Properties prop = new Properties(); 
    	try {
			prop.load(JavaFXApplication.class.getClassLoader().getResourceAsStream("conf/log4j.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	String path=JavaFXApplication.class.getResource("").getPath();
    	prop.setProperty("log4j.appender.D.File", path+prop.getProperty("log4j.appender.D.File"));
    	prop.setProperty("log4j.appender.E.File", path+prop.getProperty("log4j.appender.E.File"));
    	PropertyConfigurator.configure(prop);
    	log= Logger.getLogger(JavaFXApplication.class);
    }*/
}
