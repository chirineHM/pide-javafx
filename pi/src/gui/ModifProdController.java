/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Produit;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import services.ProduitService;

/**
 * FXML Controller class
 *
 * @author k
 */
public class ModifProdController implements Initializable {

    @FXML
    private TextField tfmodifqte;
    @FXML
    private TextField tfmodifprix;
    @FXML
    private TextField tfmodifnom;
    @FXML
    private TextArea tfmodifdesc;
    
    public Produit exemplaire;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    
    public void recevoir(Produit p)
    {
        this.exemplaire=p;
        tfmodifqte.setText(""+p.getQuantite());
        tfmodifnom.setText(""+p.getNom());
        tfmodifdesc.setText(p.getDescription());
        tfmodifprix.setText(""+p.getPrix());
    }
    
    @FXML
        public void modif()
    {
        //if( tfmodifnom.getText().isEmpty() || tfmodifdesc.getText().isEmpty() || tfmodifprix.getText().isEmpty() || tfmodifqte.getText().isEmpty()  )
        //{   
        this.exemplaire.setNom( tfmodifnom.getText() );
        this.exemplaire.setDescription( tfmodifdesc.getText() );
        
    double prix = Double.parseDouble(tfmodifprix.getText());
    exemplaire.setPrix(prix);
        
                int q = Integer.parseInt( tfmodifqte.getText() ) ;
                this.exemplaire.setQuantite(q);
        
        
        ProduitService snek = new ProduitService();
        
            try {                snek.updateProd(exemplaire);
            } catch (SQLException ex) {
                Logger.getLogger(ModifProdController.class.getName()).log(Level.SEVERE, null, ex);
            }

        
        
        System.out.println("updated ");
        
    //}
    }
    
    
    
    
    
    
    
    
}
