/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tn.esprit.services;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import tn.esprit.entities.Evenements;
import tn.esprit.tools.MaConnection;

/**
 *
 * @author medfaroukbenbelgacem
 */
public class EvenementServices {
        Connection cnx;
        Statement ste;

    public EvenementServices() {
        cnx = MaConnection.getInstance().getCnx();
    }
    
        public void ajouter(Evenements t) {
//        String sql="insert into personne(nom,prenom,age) values( '"+
//                t.getNom()+"','"+t.getPrenom()+"','"+t.getAge()
//                +"')";
//        try {
//            Statement ste =cnx.createStatement();
//            ste.executeUpdate(sql);
//            System.out.println("Personne Ajoutée !!");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }

 String sql="insert into evenements(nb_part,nom_even,desc_event,categorie_even,image_even,date_even) values (?,?,?,?,?,?)";
        PreparedStatement ste;
        try {
            ste = cnx.prepareStatement(sql);
            ste.setInt(1, t.getNb_part());
            ste.setString(2, t.getNom_even());
            ste.setString(3, t.getDesc_event());
            ste.setString(4, t.getCategorie_even());
            ste.setString(5, t.getImage_even());
            ste.setDate(6, (java.sql.Date) t.getDate());
            ste.executeUpdate();
            System.out.println("Evenements Ajoutée ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
       
        

    }
        
    
         public void supprimerEvenement(int id) {
        try {
            String req = "DELETE FROM `evenements` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("evenement deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
   }
         
        public void modifierEvenement(int id ,int nb_part, String nom_even, String desc_event, String categorie_even, String image_even, Date date) {
        try {
            String req = "UPDATE `evenements` SET `nb_part` = '" + nb_part + "', `nom_even` = '" + nom_even +  "',`desc_event` = '" + desc_event +  "',`categorie_even` = '" + categorie_even +  "', `image_even` = '" + image_even + "',`date_even` = '" +date + "' WHERE `evenements`.`id` = " +id ;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("event updated !");
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
          public void participer(int id ) {
        try {
            String req = "UPDATE `evenements` SET `nb_part` = `nb_part` - 1 WHERE `evenements`.`id` = " + id;

            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("event updated !");
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
        public List<Evenements> afficher() {
        List<Evenements> Evenements = new ArrayList<>();
        String sql="select * from evenements";
       
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while(rs.next()){
              
                Evenements p = new Evenements(
                        rs.getInt("id"),
                        rs.getInt("nb_part"),
                        rs.getString("nom_even"),
                        rs.getString("desc_event"),
                        rs.getString("categorie_even"),
                        rs.getString("image_even"),
                        rs.getDate("date_even"));
                       
                Evenements.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
      
        return Evenements;
    }
        public int nbEncours(){
     String req="SELECT COUNT(*) FROM evenements  ";
      
      int nb =0;
        
        try {
        	Statement stm = MaConnection.getInstance().getCnx().createStatement();
            ResultSet rs = stm.executeQuery(req);
            while(rs.next()){
             nb= rs.getInt(1);
              System.out.println(nb);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return nb;
    }

         
}
