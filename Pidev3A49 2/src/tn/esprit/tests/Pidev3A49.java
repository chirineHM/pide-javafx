/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.tests;

import java.sql.Date;
import tn.esprit.entities.Evenements;
import tn.esprit.services.EvenementServices;
import tn.esprit.tools.MaConnection;

/**
 *
 * @author Fayechi
 */
public class Pidev3A49 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       EvenementServices es = new EvenementServices();
       

        System.out.println( es.afficher());
   Evenements e = new Evenements(9, "cosplay", "best evenet", "1", "iouefhoer", Date.valueOf("2023-06-04"));
es.ajouter(e);
    }
    
}
