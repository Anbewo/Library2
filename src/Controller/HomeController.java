/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import View.Login;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class HomeController {
    
     public static ArrayList listCopies() {
                
       final String DATABASE_URL = "jdbc:mysql://localhost:3306/BiblioteksSystem?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
       final String LIST_LOANS = "SELECT l.barcodeID, m.title, l.startDate, l.returnDate, l.returned, l.userID, m.barcodeID FROM Loan l, "
       + "Mediacopy m WHERE l.barcodeID = m.barcodeID AND l.returned = 0 AND l.userID = " + "'" + Login.userID + "'";
       ArrayList<Object> content = new ArrayList<Object>(); // Create an ArrayList object
   
       try ( 
          Connection connection = DriverManager.getConnection(DATABASE_URL, "root", "1234");
          Statement statement = connection.createStatement();   
          ResultSet resultSet = statement.executeQuery(LIST_LOANS)) {
        
          int COLUMNS = 4;
          
          while(resultSet.next()) {
              for(int i= 1; i <= COLUMNS; i++) {
                  content.add(resultSet.getObject(i));
              }
          }
          

          return content;
         
       }
       catch (SQLException sqlException) {
           sqlException.printStackTrace();
       }
        return null;

        
   }
     
     public static ArrayList<String> fillUser() {
        
    
       final String DATABASE_URL = "jdbc:mysql://localhost:3306/BiblioteksSystem?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
       final String USER_QUERY = "SELECT userCat, pNr, fName, lName, email, tel FROM User WHERE userID = ?";
       
       Connection connection = null;
       
       ArrayList<String> userInfo = new ArrayList<String>(); 

      
       try {
          
          connection = DriverManager.getConnection(DATABASE_URL, "root", "1234");
          final PreparedStatement userQuery = connection.prepareStatement(USER_QUERY);  
          userQuery.setInt(1, Login.userID);
          ResultSet resultSet = userQuery.executeQuery();
          ResultSetMetaData metaData = resultSet.getMetaData();
          int numberOfColumns = metaData.getColumnCount();
         
          while(resultSet.next()) {
              for(int i= 1; i <= numberOfColumns; i++) {
                  userInfo.add(resultSet.getObject(i).toString());
              }
          }
          
    
          
       }

       catch (SQLException sqlException) {
           //userCategory = 0;
           //return userCategory;
           sqlException.printStackTrace();
       }
       
       return userInfo;
        
    }
    
}
