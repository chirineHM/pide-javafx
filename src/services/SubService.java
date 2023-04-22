/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import entities.typeSub;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tools.MaConnection;

/**
 *
 * @author Fayechi
 */
public class SubService implements NewInterface<typeSub> {
    Connection cnx;

    public SubService() {
        cnx = MaConnection.getInstance().getCnx();
    }
    /*
    @Override
    public void ajouter(typeSub t) {
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

 String sql = "INSERT INTO type_sub(type_name, desc_t, etat) VALUES (?, ?, ?)";
    try {
        PreparedStatement ste = cnx.prepareStatement(sql);
        ste.setString(1, t.getName());
        ste.setString(2, t.getDescription());
        ste.setString(3, t.getEtat());
        ste.executeUpdate();
        System.out.println("typeSub Ajoutée ");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
        

    }
    */
      @Override
    public void ajouter(typeSub t) {
        
        String sql = "INSERT INTO type_sub(type_name, desc_t, etat) VALUES (?, ?, ?)";
        PreparedStatement ste;
        try {
            ste = cnx.prepareStatement(sql);
            ste.setString(1, t.getName());
            ste.setString(2, t.getDescription());
            ste.setString(3, t.getEtat());
      
            ste.executeUpdate();
            System.out.println("abonnement Ajoutée ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        
         
        }
        }
    

  @Override
public List<typeSub> afficher() {
    List<typeSub> typeSubs = new ArrayList<>();
    String sql = "SELECT * FROM type_sub";
    try (Statement stmt = cnx.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {
        while (rs.next()) {
            typeSub t = new typeSub(
                    rs.getInt("id"),
                    rs.getString("type_name"),
                    rs.getString("desc_t"),
                    rs.getString("etat")
            );
            typeSubs.add(t);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return typeSubs;
}
  public ObservableList<typeSub> getall() {
        ObservableList<typeSub> posts = FXCollections.observableArrayList();
        try {
            String req = "select * from type_sub";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while(rs.next()){
                typeSub p = new typeSub(rs.getInt("id"), rs.getString("type_name"), rs.getString("desc_t"),rs.getString("etat"));
                posts.add(p);
            }
            System.out.print(posts);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return posts;
        }
/*
    @Override
    public void supprimer(typeSub t) {
        String sql ="delete from type_sub where id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, t.getId());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
*/
      @Override
public void supprimer(int id) {
    try {
        PreparedStatement ps = cnx.prepareStatement("DELETE FROM type_sub WHERE id = ?");
        ps.setInt(1, id);
        int rowsDeleted = ps.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("Abonnement supprimé avec succès");
        }
    } catch (SQLException ex) {
        System.out.println("Erreur lors de la suppression de l'abonnement dd: " + ex.getMessage());
    }
}
@Override
public void modifier(typeSub t) {
    String sql = "UPDATE type_sub SET type_name = ?, desc_t = ?, etat = ? WHERE id = ?";
    PreparedStatement ste;
    try {
        ste = cnx.prepareStatement(sql);
        ste.setString(1, t.getName());
        ste.setString(2, t.getDescription());
        ste.setString(3, t.getEtat());
        ste.setInt(4, t.getId()); // assuming "id" is an integer field in the database
      
        int rowsAffected = ste.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("abonnement modifiée");
        } else {
            System.out.println("Aucune abonnement modifiée");
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}
    private Statement st;
    private ResultSet rs;
    /*
@Override
    public typeSub displayById(int id) {
    
       String req="select * from type_sub where id ="+id;
           typeSub p=new typeSub();
        try {
            rs=st.executeQuery(req);
           // while(rs.next()){
            rs.next();
               p.setId(rs.getInt(1));
               p.setName(rs.getString(2));
               p.setDescription(rs.getString(3));
            //}
        } catch (SQLException ex) {
        }
    return p; //To change body of generated methods, choose Tools | Templates.
    }

      @Override
        public typeSub getOneByName(String name) {
            String req = "SELECT * FROM type_sub WHERE name = '" + name + "'";
    typeSub categorie = null;
   try {
            rs=st.executeQuery(req);
           // while(rs.next()){
            rs.next();
               categorie.setId(rs.getInt(1));
               categorie.setName(rs.getString(2));
               categorie.setDescription(rs.getString(3));
            //}
        } catch (SQLException ex) {
        }
    return categorie; //To change body of generated methods, choose Tools | Templates.
    }
        
        
    */
}
