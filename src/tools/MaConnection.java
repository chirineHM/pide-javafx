/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fayechi
 */
public class MaConnection {
    public String url="jdbc:mysql://localhost:3306/nyx";
    public String user="root";
    public String pwd="";
    Connection cnx;
        public static MaConnection ct;

    public MaConnection() {
      try {
          cnx = DriverManager.getConnection(url, user, pwd);
           System.out.print("connection etablie");
      } catch (SQLException ex) {
          System.out.print(ex.getMessage());
    }
    
}
    public static MaConnection getInstance(){
        if(ct==null){
            ct = new MaConnection();
        }
        return ct;
    }

    public Connection getCnx() {
        return cnx;
    }
    
}
    
    
   

