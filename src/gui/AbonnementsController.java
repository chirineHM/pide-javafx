
import entities.Subscription;
import entities.typeSub;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import services.SubService;
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

    // Retrieve the corresponding TypeSub ID from the database
int selectedTypeSubId = service.getTypeSubIdByName(selectedTypeSubName);

    // Create the new Subscription object with the selected TypeSub ID
Subscription newSubscription = new Subscription(description, Integer.parseInt(pri), date_s, date_e, Integer.parseInt(peri), selectedTypeSubId);

    // Add the new subscription
    service.addSubscription(newSubscription);

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



}
