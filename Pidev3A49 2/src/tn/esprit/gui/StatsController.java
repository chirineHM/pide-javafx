/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package tn.esprit.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author medfaroukbenbelgacem
 */
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;
import tn.esprit.services.EvenementServices;


public class StatsController implements Initializable {

    @FXML
    private PieChart statistique;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           int total = 0;
EvenementServices es = new EvenementServices();



int encours = es.nbEncours();

total = encours ;



ObservableList<PieChart.Data> list = FXCollections.observableArrayList(
      new PieChart.Data("Vous avez : " +es.nbEncours() + " evenements", encours)
       
);


statistique.setTitle("Nombre des evenements");
statistique.setAnimated(true);
statistique.setData(list);
    }    

    @FXML
    private void retourner(ActionEvent event) throws IOException {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("ListEvent.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
//                stage.setFullScreen(true);
                stage.show();
    }
    
    
}