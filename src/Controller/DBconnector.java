/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.sql.*;

/**
 *
 * @author Admin
 */
public class DBconnector {
   
   public static Connection connectToDB() {
       
       final String DATABASE_URL = "jdbc:mysql://localhost:3306/BiblioteksSystem?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
       
       Connection connection = null;
            
       try {
          connection = DriverManager.getConnection(DATABASE_URL, "root", "1234");
       }
       catch (SQLException sqlException) {
           sqlException.printStackTrace();
       }
       
       return connection;
       
   }
    
    
}
