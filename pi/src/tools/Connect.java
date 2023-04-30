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
public class Connect {
    private static Connect instance;

	
	 private static final String URL = "jdbc:mysql://localhost:3306/test1";
	 private static final String USER = "root";
	 private static final String PASSWORD = "";

	 private Connection cnx;

	    private Connect() {
	        try {
	            cnx = DriverManager.getConnection(URL, USER, PASSWORD);
	            System.out.println("Connected!");
	        } catch (SQLException ex) {
	            System.err.println(ex.getMessage());
	        }
	    }

	    public static Connect getInstance() {
	        if (instance == null) {
	            instance = new Connect();
	        }
	        return instance;
	    }

	    public Connection getConnection() {
	        return cnx;
	    }


	    public void closeConnection() {
	        try {
	            cnx.close();
	            System.out.println("Connection closed!");
	        } catch (SQLException ex) {
	            System.err.println(ex.getMessage());
	        }
	    }
    
}