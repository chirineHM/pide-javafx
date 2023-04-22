/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxnyx;

import entities.Subscription;
import static java.time.Clock.system;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.SubsService;


/**
 *
 * @author uSER
 */
public class JavaFxNYX extends Application {
    
    @Override
    public void start(Stage primaryStage) {
      try {
              
            Parent root = FXMLLoader.load(getClass().getResource("/gui/subscription.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();




            } catch (Exception e) {
               e.printStackTrace();
            }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      launch(args);
       /* Date d=new Date(2018-03-27);
    //p.setStart_date(d);
        Subscription s=new Subscription("eee",12,d,d,1);
        SubsService service = new SubsService();
        service.addSubscription(s);
        
        
*/
      
     ObservableList<Subscription> list=FXCollections.observableArrayList();
         SubsService service = new SubsService();
         list=service.getAllSubscriptions();
         System.out.println(list);

    }
    
}
