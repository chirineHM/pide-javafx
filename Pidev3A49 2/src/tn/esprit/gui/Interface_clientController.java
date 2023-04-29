/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package tn.esprit.gui;


import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


import tn.esprit.entities.Evenements;
import tn.esprit.entities.SMS;
import tn.esprit.services.EvenementServices;

/**
 * FXML Controller class
 *
 * @author medfaroukbenbelgacem
 */
public class Interface_clientController implements Initializable {
    

   @FXML
    private TableView<Evenements> xtable;
   
   @FXML
    private TableColumn<Evenements, Integer> xnb_part; 
    @FXML
    private TableColumn <Evenements, String>xnom_even;
    @FXML
    private TableColumn<Evenements, String> xdesc_event;
    @FXML
    private TableColumn <Evenements, String> xcategorie_even;
    @FXML
    private TableColumn<Evenements, Date> xdate;
    @FXML
    private TableColumn <Evenements, String> ximage_even;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        table();
        
    } 

    public void table(){
     xnom_even.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNom_even()));
        xdesc_event.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDesc_event()));
        xcategorie_even.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategorie_even()));
//        xdate.setCellValueFactory(cellData -> {
//    Date date = cellData.getValue().getDate();
//    String formattedDate = dateFormat.format(date);
//    return new SimpleStringProperty(formattedDate);
//    });
        xdate.setCellFactory(tc -> new TableCell<Evenements, Date>() {
              private SimpleDateFormat dateFormat = new SimpleDateFormat ("dd-MM-yyyy") ;
         
              @Override
           protected void updateItem (Date item,boolean empty) {
              super.updateItem(item,empty);
                if (empty) {
                setText(null);
                }else {
              setText(dateFormat.format(item));
            }
          }
       });
       xdate.setCellValueFactory((new Callback<TableColumn.CellDataFeatures<Evenements, Date>, ObservableValue<Date>>() {
           @Override  
           public ObservableValue<Date> call(TableColumn.CellDataFeatures<Evenements, Date> p) {
               return new SimpleObjectProperty<>(p.getValue().getDate());
           }
       }));     

        ximage_even.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getImage_even()));
        xnb_part.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getNb_part()).asObject());
        
        
        EvenementServices crud = new EvenementServices();
        // Populate the table with data
        List<Evenements> data;

            data = crud.afficher();
            xtable.getItems().setAll(data);
            xtable.setRowFactory(tv -> {
		     TableRow<Evenements> myRow = new TableRow<>();
		     myRow.setOnMouseClicked ((event) -> 
		     {
		        if (event.getClickCount() == 1 && (!myRow.isEmpty()))
		        {
		            int myIndex =  xtable.getSelectionModel().getSelectedIndex();

		           int id = Integer.parseInt(String.valueOf(xtable.getItems().get(myIndex).getId()));
		            
                           
                         
                           
		        }
		     });
		        return myRow;
                   });
    }
    @FXML
    private void Participer(ActionEvent event) {
              EvenementServices crud = new EvenementServices();

        Evenements Evenement = xtable.getSelectionModel().getSelectedItem();

    if (Evenement != null) {
        int id = Evenement.getId();
        crud.participer(id);
       
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Participer à l'evenement");
            alert.setHeaderText("Participer à l'evenement");
            alert.setContentText("Votre demande de participation est bien enregistrer !");
            alert.showAndWait();
       table();
       
      SMS ss=new SMS();
      ss.sms();
       
       
    }
  
    }
   
    @FXML
    private void gotoacceuil(ActionEvent event) throws IOException {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("Accueil.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
     //           stage.setFullScreen(true);
                stage.show();
    }
    
}
