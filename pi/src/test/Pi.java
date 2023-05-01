/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.IOException;

 import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author amine
 */
public class Pi extends Application{
	public static Stage stg;
    public void start(Stage primaryStage) throws IOException  
    {
    	Pi.stg = primaryStage;
     
        // FXMLLoader loader= new FXMLLoader(getClass().getResource("../gui/Interface_client.fxml"));
         FXMLLoader loader= new FXMLLoader(getClass().getResource("../gui/AffichageCommande.fxml"));
         //FXMLLoader loader= new FXMLLoader(getClass().getResource("../gui/AjoutProduit.fxml"));

	            //FXMLLoader loader= new FXMLLoader(getClass().getResource("../gui/ProduitBack.fxml"));
                   //FXMLLoader loader= new FXMLLoader(getClass().getResource("../gui/categorieBack.fxml"));
	            // FXMLLoader loader= new FXMLLoader(getClass().getResource("../gui/ajoutCat.fxml"));
                 
                //FXMLLoader loader= new FXMLLoader(getClass().getResource("../gui/AffichageProduitFront.fxml"));
                
	            Parent root= loader.load();
	            Scene scene= new Scene(root);
	            primaryStage.setTitle("Bievenue");
	            primaryStage.setScene(scene);
	            primaryStage.show();
	        
	        
	    }
	    

	 
	    
	   public static void main(String[] args) {
	           launch(args);
	         
	    }}
    
 