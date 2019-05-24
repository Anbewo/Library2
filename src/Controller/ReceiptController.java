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
public class ReceiptController {
    
     public static ArrayList getUser() {
        
       final String DATABASE_URL = "jdbc:mysql://localhost:3306/BiblioteksSystem?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
       final String GET_USERINFO = "SELECT fName, lName, pNr, noLoanOccasions FROM User WHERE userID = '" + Login.userID + "';";
       ArrayList<String> userInfo = new ArrayList<String>(); 
       
       try ( 
          Connection connection = DriverManager.getConnection(DATABASE_URL, "root", "1234");
          Statement statement = connection.createStatement();
          ResultSet resultSet = statement.executeQuery(GET_USERINFO)) {
         
          ResultSetMetaData metaData = resultSet.getMetaData();
          int numberOfColumns = metaData.getColumnCount();
 
          
          while(resultSet.next()) {
              for(int i= 1; i <= numberOfColumns; i++) {
                  userInfo.add(resultSet.getObject(i).toString());
              }
          }
          
          
       }
       catch (SQLException sqlException) {
           sqlException.printStackTrace();
       }
     
     
       return userInfo;
       
    }
     
    public static ArrayList getCurrentOccasion(int occasionInt) {
        
       final String DATABASE_URL = "jdbc:mysql://localhost:3306/BiblioteksSystem?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
       final String GET_LOANS = "SELECT l.barcodeID, m.title, l.returnDate FROM Loan l, "
       + "Mediacopy m WHERE l.barcodeID = m.barcodeID AND l.loanOccasion = " + occasionInt + " AND l.userID = " + "'" + Login.userID + "'";
        System.out.println(GET_LOANS);
       ArrayList<String> loanInfo = new ArrayList<String>(); 
  
       try ( 
          Connection connection = DriverManager.getConnection(DATABASE_URL, "root", "1234");
          Statement statement = connection.createStatement();
          ResultSet resultSet = statement.executeQuery(GET_LOANS)) {
         
          ResultSetMetaData metaData = resultSet.getMetaData();
          int numberOfColumns = metaData.getColumnCount();
 
         
          while(resultSet.next()) {
              for(int i= 1; i <= numberOfColumns; i++) {
                  loanInfo.add(resultSet.getObject(i).toString());
              }
          }
          
       }
       catch (SQLException sqlException) {
           sqlException.printStackTrace();
       }
     
     
       return loanInfo;
       
    }
    
    public static void updateCopy(int occasionInt) {
       //
       
       final String DATABASE_URL = "jdbc:mysql://localhost:3306/BiblioteksSystem?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
       final String UPDATE_OCCASION = "UPDATE User SET noLoanOccasions = ? WHERE userID = ?";

       Connection connection = null;
      
       try {
          connection = DriverManager.getConnection(DATABASE_URL, "root", "1234");
          final PreparedStatement insertQuery = connection.prepareStatement(UPDATE_OCCASION);   
          insertQuery.setInt(1, occasionInt);
          insertQuery.setInt(2, Login.userID);

          System.out.println(insertQuery); 
          insertQuery.executeUpdate();
       }
       catch (SQLException sqlException) {
           sqlException.printStackTrace();
       }
       
       
   }
    
    
    
}
