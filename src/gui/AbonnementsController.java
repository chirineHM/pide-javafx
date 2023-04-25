

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import entities.Subscription;
import entities.typeSub;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.SubsService;




public class AbonnementsController implements Initializable {

    @FXML
    private TableView<Subscription> subscriptionsTable;
    @FXML
    private TableColumn<Subscription, Integer> idColumn;
    @FXML
    private TableColumn<Subscription, String> descriptionColumn;
    @FXML
    private TableColumn<Subscription, Integer> priceColumn;
    @FXML
    private TableColumn<Subscription, Date> startDateColumn;
    @FXML
    private TableColumn<Subscription, Date> endDateColumn;
    @FXML
    private TableColumn<Subscription, Integer> periodColumn;

    private SubsService subsService;
    @FXML
    private Button tfcreate;
    @FXML
    private DatePicker start;
    @FXML
    private Button removetf;
    @FXML
    private DatePicker end;
    @FXML
    private TextArea des;
    @FXML
    private TextField price;
    @FXML
    private TextField period;
    @FXML
    private TableColumn<Subscription, String> typeN;
    @FXML
    private ComboBox<String> combo;
    @FXML
    private Button mod;
    @FXML
    private TextField searchField;
    @FXML
    private Button searchButton;
    @FXML
    private ComboBox<String> tri;



    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    
      
        
        
        subsService = new SubsService();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("start_date"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("end_date"));
        periodColumn.setCellValueFactory(new PropertyValueFactory<>("period"));
     


typeN.setCellValueFactory(cellData -> {
    Subscription subscription = cellData.getValue();
    typeSub type = subscription.getTypeSubs();
    String typeName = (type != null) ? type.getName() : "";
    return new SimpleStringProperty(typeName);
});






        refresh();

        // Debugging messages
        System.out.println("Initialized subscription table");
        System.out.println("Subscription table items: " + subscriptionsTable.getItems());
        System.out.println("Subscription table columns: " + subscriptionsTable.getColumns());
        
        
        
            Alert alertee= new Alert(Alert.AlertType.CONFIRMATION);
    alertee.setTitle("ALETRE ");
    alertee.setHeaderText(null);
        alertee.setContentText("Vous voulez supprimer les Abonnements qui ont été termine ? ");
    alertee.showAndWait();
    if (alertee.getResult() == ButtonType.OK) {

     subsService.SUPPRIME();
    refresh();
    }
        
        
      ///////////////////////////////////////////////
        
        
        ObservableList<String> options = FXCollections.observableArrayList("Par prix","Par date");
       tri.setItems(options);
      tri.valueProperty().addListener((obs, oldVal, newVal) -> {
    // effectuer la requête de tri correspondante ici
    String selectedValue = tri.getValue();
   // PromotionDao pd = new PromotionDao();

     switch(selectedValue) {
        case "Par prix":
       ObservableList<Subscription> pourcentageliste = (ObservableList<Subscription>) subsService.ordredbyPrice();
      subscriptionsTable.setItems(pourcentageliste);
            break;
        case "Par date":
            ObservableList<Subscription> dateliste = (ObservableList<Subscription>) subsService.ordredbyDate();
      subscriptionsTable.setItems(dateliste);

            break;
            case "Par date de fin":
            ObservableList<Subscription> finliste = (ObservableList<Subscription>) subsService.ordredbyPrice();
      subscriptionsTable.setItems(finliste);

            break;
       
     
            
    }

      });

        ///////////////////////////////////////////////
        
    
      
    
        
              }
private void refresh() {
    if (subsService != null) {
        // Get the list of subscriptions from the service
        List<Subscription> subscriptions = subsService.getAllSubscriptions();

        // Clear the table and add the new data
        subscriptionsTable.getItems().clear();
        subscriptionsTable.getItems().addAll(subscriptions);
        
        // Get all the type sub names
        List<String> typeSubNames = subsService.getAllTypeSubNames();

        // Clear the ComboBox and add the new names
        combo.getItems().clear();
        combo.getItems().addAll(typeSubNames);
    }
}

@FXML
private void Add(ActionEvent event) {
    SubsService service = new SubsService();

    String description = des.getText();
String pri = price.getText();
java.sql.Date date_s = java.sql.Date.valueOf(start.getValue());
java.sql.Date date_e = java.sql.Date.valueOf(end.getValue());
String peri = period.getText();
String selectedTypeSubName = combo.getSelectionModel().getSelectedItem();

// Check if any of the fields are empty
if (description == null || description.isEmpty() || pri == null || pri.isEmpty() ||
        peri == null || peri.isEmpty() || selectedTypeSubName == null) {
    Alert alert = new Alert(AlertType.WARNING);
    alert.setTitle("Empty fields");
    alert.setHeaderText(null);
    alert.setContentText("Please fill in all fields.");
    alert.showAndWait();
    return;
}

// Check if the description contains any numbers
if (description.matches(".*\\d.*")) {
    Alert alert = new Alert(AlertType.WARNING);
    alert.setTitle("Invalid description");
    alert.setHeaderText(null);
    alert.setContentText("Description cannot contain numbers.");
    alert.showAndWait();
    return;
}

// Retrieve the corresponding TypeSub ID from the database
int selectedTypeSubId = service.getTypeSubIdByName(selectedTypeSubName);

// Validate the dates
LocalDate currentDate = LocalDate.now();
LocalDate startDate = start.getValue();
LocalDate endDate = end.getValue();

if (startDate == null || startDate.isBefore(currentDate)) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Invalid start date");
    alert.setHeaderText(null);
    alert.setContentText("Please select a start date that is greater than or equal to the current date.");
    alert.showAndWait();
    return;
}

if (endDate == null || endDate.isBefore(startDate)) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Invalid end date");
    alert.setHeaderText(null);
    alert.setContentText("Please select an end date that is greater than the start date.");
    alert.showAndWait();
    return;
}

// Create the new Subscription object with the selected TypeSub ID
Subscription newSubscription = new Subscription(description, Integer.parseInt(pri), date_s, date_e, Integer.parseInt(peri), selectedTypeSubId);

// Add the new subscription
service.addSubscription(newSubscription);

// Refresh the table view
refresh();

}



@FXML
private void Update(ActionEvent event) {
    SubsService service = new SubsService();

   // Get the selected subscription from the table view
Subscription selectedSubscription = subscriptionsTable.getSelectionModel().getSelectedItem();

// Retrieve the corresponding TypeSub object from the database
String selectedTypeSubName = combo.getSelectionModel().getSelectedItem();
int selectedTypeSub = service.getTypeSubIdByName(selectedTypeSubName);

// Update the fields of the selected subscription
selectedSubscription.setDescription(des.getText());
selectedSubscription.setPrice(Integer.parseInt(price.getText()));
selectedSubscription.setStart_date(Date.valueOf(start.getValue()));
selectedSubscription.setEnd_date(Date.valueOf(end.getValue()));
selectedSubscription.setPeriod(Integer.parseInt(period.getText()));
selectedSubscription.setType_sub_id(selectedTypeSub);

// Update the subscription in the database
service.modifier(selectedSubscription);

// Update the value of the combo box
combo.setValue(selectedTypeSubName);

// Refresh the table view
refresh();

}



    @FXML
    private void remove(ActionEvent event) {
        Subscription p = subscriptionsTable.getSelectionModel().getSelectedItem();
        SubsService service = new SubsService();
        service.supprimer(p.getId());
        refresh();
    }
    
    @FXML
private void handleRowSelect(MouseEvent event) {
    if (event.getClickCount() == 1) { // only handle single click events
        Subscription selectedSubscription = subscriptionsTable.getSelectionModel().getSelectedItem();
        if (selectedSubscription != null) {
            // populate the text fields with the values from the selected row
            des.setText(selectedSubscription.getDescription());
            price.setText(String.valueOf(selectedSubscription.getPrice()));
            start.setValue(selectedSubscription.getStart_date().toLocalDate());
            end.setValue(selectedSubscription.getEnd_date().toLocalDate());
            period.setText(String.valueOf(selectedSubscription.getPeriod()));
            String typeName = selectedSubscription.getTypeSubs().getName();
            combo.getSelectionModel().select(typeName);
        }
    }
}
/*
@FXML
private void update(ActionEvent event) {
    // Get the selected row
    Subscription selectedSubscription = subscriptionsTable.getSelectionModel().getSelectedItem();
    if (selectedSubscription != null) {
        // Update the selected row with the new data
        selectedSubscription.setDescription(des.getText());
        selectedSubscription.setPrice(Integer.parseInt(price.getText()));
        selectedSubscription.setStart_date(Date.valueOf(start.getValue()));
        selectedSubscription.setEnd_date(Date.valueOf(end.getValue()));
        selectedSubscription.setPeriod(Integer.parseInt(period.getText()));
        String selectedTypeName = combo.getSelectionModel().getSelectedItem();

        // Update the subscription's type
      typeSub selectedType = new typeSub(selectedTypeName);
selectedSubscription.setTypeSubs(selectedType);


        // Update the data in the database using SubsService
        SubsService subsService = new SubsService();
        subsService.modifier(selectedSubscription);

        // Refresh the table to show the updated data
        refresh();
    } else {
        // Show an alert if no row is selected
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Aucune sélection");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner une ligne à modifier.");
        alert.showAndWait();
    }
}


*/

@FXML
private void search(ActionEvent event) {
    String searchText = searchField.getText().trim().toLowerCase();

    if (searchText.isEmpty()) {
        refresh();
        return;
    }

    List<Subscription> searchResults = subsService.search(searchText);

    subscriptionsTable.getItems().clear();
    subscriptionsTable.getItems().addAll(searchResults);
}

    @FXML
    private void QR(MouseEvent event) {
         Stage qrStage = new Stage();
        Subscription p;
        
        p=subscriptionsTable.getSelectionModel().getSelectedItem();
        subsService.Qr(qrStage, p);
      
          
    }
    
    
  public static final String ACCOUNT_SID = "ACb63a0b4fd4a6de530177199e0a543bab";
  public static final String AUTH_TOKEN = "4e01b5c984e864c3bf424d7a76f2e2bf";

  
  
    @FXML
    private void sms(MouseEvent event) {
        
        SubsService pd = new SubsService();

        subscriptionsTable.refresh();
        pd.getAllSubscriptions();
        Subscription p;
        p=subscriptionsTable.getSelectionModel().getSelectedItem();
        System.out.println(p);
     
        String promo="L'Abonnement "+p.getPrice()+"% commence le "+p.getStart_date()+" et se termine le "+p.getEnd_date();
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
                    Message message = Message.creator(new PhoneNumber("+21653500513"),new PhoneNumber("+16813203620"),promo).create();
    }


}
