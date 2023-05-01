/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import java.util.Date;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class Interface_clientController implements Initializable {

    @FXML
    private Button blog;
    @FXML
    private Button Event;
    @FXML
    private Button Produit;
    @FXML
    private Button abonnement;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void blog(ActionEvent event) {
    }

    @FXML
    private void produit(ActionEvent event) {
             try {

            Parent page1= FXMLLoader.load(getClass().getResource("../gui/AffichageProduitFront.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
          
        } catch (IOException ex) {
            System.out.println("Erreur\n");
            Logger.getLogger(AffichageProduitFrontController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }


    @FXML
    private void gotoacceuil(ActionEvent event) {
    }

    @FXML
    private void Event(ActionEvent event) {
    }

    @FXML
    private void abonnement(ActionEvent event) {
    }
    
}
