/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package tn.esprit.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import tn.esprit.entities.Evenements;
import tn.esprit.entities.SMS;
import tn.esprit.services.EvenementServices;

/**
 * FXML Controller class
 *
 * @author medfaroukbenbelgacem
 * 
 */


public class Afficher_evenement_sliderController implements Initializable {

    /**
     * Initializes the controller class.
     */

  @FXML
    private TextField xnb_part; 
    @FXML
    private TextField xnom_even;
    @FXML
    private TextField xdesc_event;
    @FXML
    private TextField xdate;
  @FXML
    private Label xlabel;
    @FXML
    private ImageView ximage_even;
    
    
    private int current_slider;
    private String imgpath;
    private  List<Evenements> data;
    @FXML
    private Button acceuil;
    
    
    private void get_data_events() {
        EvenementServices crud = new EvenementServices();
        this.data = crud.afficher();
                
    }
    
    private void update_current_event() {
        Evenements evt = this.data.get(current_slider);
        this.imgpath = evt.getImage_even();
        this.xdate.setText(evt.getDate().toString());
        this.xdesc_event.setText(evt.getDesc_event());
        this.xnom_even.setText(evt.getNom_even());
        this.xnb_part.setText(Integer.toString(evt.getNb_part()));
        System.out.println("file:"+this.imgpath);
        Image image = new Image(this.imgpath);
        this.ximage_even.setImage(image);
        this.xlabel.setText(Integer.toString(this.current_slider+1)+" / "+Integer.toString(this.data.size()));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.current_slider=0;
        get_data_events();
        update_current_event();
        
    } 
    
    @FXML
     public void Next(ActionEvent actionEvent) {
         this.current_slider +=1;
         if (this.data.size() == this.current_slider )
         {
             this.current_slider =0;
     
         } 
       
         update_current_event();
         
         
     }
    @FXML
     public void Previous(ActionEvent actionEvent) {
         this.current_slider -=1;
         if (this.current_slider == -1 )
         {
             this.current_slider = this.data.size()-1;
     
         } 
         update_current_event();
         
     }

    @FXML
    private void Participer(ActionEvent event) {
              EvenementServices crud = new EvenementServices();

        Evenements Evenement = this.data.get(this.current_slider);

    if (Evenement != null) {
        int id = Evenement.getId();
        crud.participer(id);
        get_data_events();
      update_current_event(); 
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Participer à l'evenement");
            alert.setHeaderText("Participer à l'evenement");
            alert.setContentText("Votre demande de participation est bien enregistrer !");
            alert.showAndWait();
      
       
      SMS ss=new SMS();
      ss.sms();
      
      
       
       
    }
  
    }

   

    @FXML
    private void participer(ActionEvent event) {
    }

    @FXML
    private void gotoaccueil(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("Accueil.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
     //           stage.setFullScreen(true);
                stage.show();
    }

   
    
}
