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
    
}
