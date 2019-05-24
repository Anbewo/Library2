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
public class MediaCopyController {
    
     public static ArrayList listCopies(String mediaID) {
                
       final String DATABASE_URL = "jdbc:mysql://localhost:3306/BiblioteksSystem?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
       final String SELECT_QUERY = "SELECT * FROM Mediacopy WHERE mediaID = '" + mediaID + "'";
       ArrayList<Object> content = new ArrayList<Object>(); // Create an ArrayList object
   
       try ( 
          Connection connection = DriverManager.getConnection(DATABASE_URL, "root", "1234");
          Statement statement = connection.createStatement();
          ResultSet resultSet = statement.executeQuery(SELECT_QUERY)) {
          
          ResultSetMetaData metaData = resultSet.getMetaData();
          int numberOfColumns = metaData.getColumnCount();
          
          /*
          System.out.printf("Authors Table of Books Database:%n%n");
          
          for(int i = 1; i <= numberOfColumns; i++) {
              System.out.printf("%-8s\t", metaData.getColumnName(i));
          }
          System.out.println();
           */
          
          while(resultSet.next()) {
              for(int i= 1; i <= numberOfColumns; i++) {
                  content.add(resultSet.getObject(i));
              }
              System.out.println();
          }
          

          return content;
         
       }
       catch (SQLException sqlException) {
           sqlException.printStackTrace();
       }
        return null;

        
   }
     
     public static void updateCopy(String[] tableData, String mediaID) {
       //
       
       final String DATABASE_URL = "jdbc:mysql://localhost:3306/BiblioteksSystem?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
       final String UPDATE_QUERY = "UPDATE Mediacopy SET placement = ? WHERE barcodeID = ?";

       Connection connection = null;
      
       try {
          connection = DriverManager.getConnection(DATABASE_URL, "root", "1234");
          final PreparedStatement insertQuery = connection.prepareStatement(UPDATE_QUERY);   
          insertQuery.setString(1, tableData[3].replace("]", "").replaceAll("\\s",""));
          insertQuery.setString(2, mediaID);

          System.out.println(insertQuery); 
          insertQuery.executeUpdate();
       }
       catch (SQLException sqlException) {
           sqlException.printStackTrace();
       }
       
       
   }
   
   public static void insertCopy(String[] tableData, String mediaID) {
       //
       
       String title = getMediaTitle(mediaID);
       
       final String DATABASE_URL = "jdbc:mysql://localhost:3306/BiblioteksSystem?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
       final String UPDATE_QUERY = "INSERT INTO Mediacopy (placement, title, mediaID) VALUES (?, ?, ?)";

       Connection connection = null;
      
       System.out.println(tableData);
       
       for(int i = 0; i < tableData.length; i++) {
           System.out.println(tableData[i]);
       }
       
       try {
          connection = DriverManager.getConnection(DATABASE_URL, "root", "1234");
          final PreparedStatement insertQuery = connection.prepareStatement(UPDATE_QUERY);   
          insertQuery.setString(1, tableData[3].replaceAll("\\s",""));
          insertQuery.setString(2, title);
          insertQuery.setString(3, mediaID);
          
          System.out.println(insertQuery);
          insertQuery.executeUpdate();
       }
       catch (SQLException sqlException) {
           sqlException.printStackTrace();
       }
       
       
   }
   
   
    public static void deleteCopy(String barcodeID) {
        
    
       final String DATABASE_URL = "jdbc:mysql://localhost:3306/BiblioteksSystem?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
       final String DELETE_COPY = "DELETE FROM Mediacopy WHERE barcodeID = ?";

       Connection connection = null;
      
       try {
          connection = DriverManager.getConnection(DATABASE_URL, "root", "1234");
          final PreparedStatement deleteCopy = connection.prepareStatement(DELETE_COPY);   
          
          deleteCopy.setString(1, barcodeID);

          deleteCopy.executeUpdate();
       }
       catch (SQLException sqlException) {
           sqlException.printStackTrace();
       }
       
        
    }
    
    public static String getMediaTitle(String mediaID) {
        
       final String DATABASE_URL = "jdbc:mysql://localhost:3306/BiblioteksSystem?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
       final String GET_TITLE = "SELECT title FROM Media WHERE mediaID = '" + mediaID + "';";
       
       String title = "";
       
       try ( 
          Connection connection = DriverManager.getConnection(DATABASE_URL, "root", "1234");
          Statement statement = connection.createStatement();
          ResultSet resultSet = statement.executeQuery(GET_TITLE)) {
         
          while(resultSet.next()) {
            title = resultSet.getObject(1).toString();
          }
          
       }
       catch (SQLException sqlException) {
           sqlException.printStackTrace();
       }
     
     
       return title;
       
    }
    
    
    
     public static void updateCopies(String mediaID) {
        
 
       final String DATABASE_URL = "jdbc:mysql://localhost:3306/BiblioteksSystem?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
       final String COUNT_COPIES = "SELECT COUNT(*) FROM Mediacopy WHERE mediaID = '" + mediaID + "';";
       final String UPDATE_COPIES = "UPDATE Media SET NumOfCopies = ? WHERE mediaID = ?";
   
       String mediaCount = "";
       
       try ( 
          Connection connection = DriverManager.getConnection(DATABASE_URL, "root", "1234");
          Statement statement = connection.createStatement();
          ResultSet resultSet = statement.executeQuery(COUNT_COPIES)) {
         
          while(resultSet.next()) {
            mediaCount = resultSet.getObject(1).toString();
          }
          
          final PreparedStatement updateCopiesQuery = connection.prepareStatement(UPDATE_COPIES);   
          updateCopiesQuery.setString(1, mediaCount);
          updateCopiesQuery.setString(2, mediaID);

          updateCopiesQuery.executeUpdate();
          
       }
       catch (SQLException sqlException) {
           sqlException.printStackTrace();
       }
     
     
   }
    
   
    
}
