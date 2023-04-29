/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package tn.esprit.gui;

import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import tn.esprit.entities.Evenements;
import tn.esprit.services.EvenementServices;

/**
 * FXML Controller class
 *
 * @author medfaroukbenbelgacem
 */
public class Ajouter_eventController implements Initializable {

    @FXML
    private TextField ximage;
    @FXML
    private TextField xdate;
    @FXML
    private TextField xnbr;
    @FXML
    private TextField xcat;
    @FXML
    private TextField xdesc;
    @FXML
    private TextField xevent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    private void ajouter_Event(ActionEvent event) {
 
        
            String title=xevent.getText();
        String description=xdesc.getText();
        String cat=xcat.getText();
        String ima=ximage.getText();
         String dateText = xdate.getText();
            Date t = Date.valueOf(dateText);
            // Convertir le texte en type Date si nécessaire
            Date date = null;
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); // Format de la date à adapter selon votre besoin
                date = (Date) dateFormat.parse(dateText);
            } catch (ParseException e) {
            // Gérer l'exception en cas d'erreur de conversion
            e.printStackTrace();
            }
         Integer nbr=Integer.parseInt(xnbr.getText());
         Evenements e = new Evenements(nbr, title, description, cat,ima , t);
                  EvenementServices crud = new EvenementServices();

            crud.ajouter(e);
                    
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Creation de l'evenement");
            alert.setHeaderText("Creation de l'evenement");
            alert.setContentText("Evenement crée!");
            alert.showAndWait();


            xnbr.setText("");
            xevent.setText("");
            xdesc.setText("");
            xcat.setText("");
             xnbr.setText("");
            xdate.setText("");
            ximage.setText("");

        
        
        
    }
    
    
}
