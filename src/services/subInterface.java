/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;



import entities.Subscription;
import javafx.stage.Stage;

/**
 *
 * @author uSER
 */

public interface subInterface<S> {
   public void supprimer(int id);

    
    public void addSubscription(Subscription s) ;
        

        public void Qr( Stage primaryStage,Subscription p) ;

}
