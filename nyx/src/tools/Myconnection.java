/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hmida
 */
public class Myconnection {
    public String url="jdbc:mysql://localhost:3306/nyxx";
    public String user="root";
    public String pwd="";
    Connection cnx;
        public static Myconnection ct;

    public Myconnection() {
      try {
          cnx = DriverManager.getConnection(url, user, pwd);
           System.out.print("connection etablie");
      } catch (SQLException ex) {
          System.out.print(ex.getMessage());
    }
    
}
    public static Myconnection getInstance(){
        if(ct==null){
            ct = new Myconnection();
        }
        return ct;
    }

    public Connection getCnx() {
        return cnx;
    }
    
}