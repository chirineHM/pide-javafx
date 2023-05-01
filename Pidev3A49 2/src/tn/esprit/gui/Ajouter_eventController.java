/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package tn.esprit.gui;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tn.esprit.entities.Evenements;
import tn.esprit.services.EvenementServices;

/**
 * FXML Controller class
 *
 * @author medfaroukbenbelgacem
 */
public class Ajouter_eventController implements Initializable {

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
    @FXML
    private ImageView imageView;
    private String imgpath;
    @FXML
    private Button selectbtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
     public void chooseFile(ActionEvent actionEvent) throws MalformedURLException{
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open File");
        File file = chooser.showOpenDialog(new Stage());
        if(file != null) {
            String imagepath = file.toURI().toURL().toString();
            this.imgpath = imagepath;
            //String imagepath = file.getPath();
            System.out.println("file:"+imagepath);
            Image image = new Image(imagepath);
             imageView.setImage(image);
    }
    else
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Please Select a File");
        /*alert.setContentText("You didn't select a file!");*/
        alert.showAndWait();
    }
}
   

    @FXML
    private void ajouter_Event(ActionEvent event) {
 
        
            String title=xevent.getText();
        String description=xdesc.getText();
        String cat=xcat.getText();
        String ima=this.imgpath;
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

    @FXML
    private void gotoacceuil(MouseEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("Accueil.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
     //           stage.setFullScreen(true);
                stage.show();
    }
    


    @FXML
    private void gotomodifier(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("ListEvent.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
     //           stage.setFullScreen(true);
                stage.show();
    }
    
    
}
