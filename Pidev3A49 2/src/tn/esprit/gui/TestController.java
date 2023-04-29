/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package tn.esprit.gui;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import tn.esprit.entities.Evenements;

/**
 * FXML Controller class
 *
 * @author medfaroukbenbelgacem
 */
public class TestController implements Initializable {

    @FXML
    private TableColumn<Evenements, Integer> xid;
    @FXML
    private TableColumn<Evenements, String> xnom_even;
    @FXML
    private TableColumn<Evenements, String> xdesc_event;
    @FXML
    private TableColumn<Evenements, String> xcategorie_event;
    @FXML
    private TableColumn<Evenements, Date> xdate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
