/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

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
   
   public static void insertCopy(String[] tableData) {
       //
       
       final String DATABASE_URL = "jdbc:mysql://localhost:3306/BiblioteksSystem?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
       final String UPDATE_QUERY = "INSERT INTO Media (placement) VALUES (?)";

       Connection connection = null;
      
       try {
          connection = DriverManager.getConnection(DATABASE_URL, "root", "1234");
          final PreparedStatement insertQuery = connection.prepareStatement(UPDATE_QUERY);   
          insertQuery.setString(1, tableData[3].replaceAll("\\s",""));

          System.out.println(insertQuery);
          insertQuery.executeUpdate();
       }
       catch (SQLException sqlException) {
           sqlException.printStackTrace();
       }
       
       
   }
    
}
