package crud;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Crud extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        try {
            System.out.println(getClass().getResource("Product.fxml").getPath());

            //other solution
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Product.fxml"));
            //Parent root = loader.load();

            //Keep in mind that you are calling a static method
            Parent root = FXMLLoader.load(getClass().getResource("Product.fxml"));

            primaryStage.setTitle("Benutzerverwaltung");
            root.getStylesheets().add(getClass().getResource("product.css").toExternalForm());
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        


    }

    public static void main(String[] args) {
        launch(args);
    }
}