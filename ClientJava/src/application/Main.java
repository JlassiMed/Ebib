package application;
	
import java.util.ArrayList;
import java.util.Properties;

import javax.naming.InitialContext;

import entity.Adherent;
import entity.Auteur;
import entity.Enseignant;
import entity.Etudiant;
import javafx.application.Application;
import javafx.stage.Stage;
import sessions.IRemote;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	public static IRemote bean;
	@Override
	public void start(Stage primaryStage) {
		try {
			 
				
			    Properties props = new Properties();
		        props.put("java.naming.factory.url.pkgs","org.jboss.ejb.client.naming");
		        InitialContext context = new InitialContext(props);
	 
		        String appName = "";        	 
		        String moduleName = "GestionBiblio";
		        String distinctName = "";        	 
		        String beanName = "EJBImpl";        	 
		        String interfaceName = IRemote.class.getName();
		        String name = "ejb:" + appName + "/" + moduleName + "/" +  distinctName    + "/" + beanName + "!" + interfaceName;
		        System.out.println(name);
		        bean = (IRemote)context.lookup(name);
		      
			
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("Acceuil.fxml"));
			Scene scene = new Scene(root,1230,661);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
