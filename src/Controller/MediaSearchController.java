/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.sql.*;
import java.util.ArrayList;
import Model.Media;
import View.MediaSearch;
import java.util.List;

/**
 *
 * @author Admin
 */

public class MediaSearchController {

    private MediaSearch mediasearch = null;    
    
    
    public static ArrayList listMedia(String searchVar, String choiceVar) {
                
       final String DATABASE_URL = "jdbc:mysql://localhost:3306/BiblioteksSystem?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
       final String SELECT_QUERY = "SELECT * FROM Media WHERE " + choiceVar + " LIKE ".concat(searchVar);
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
    
   public static void updateMedia(String tableData, String mediaID) {
       //
       
       final String DATABASE_URL = "jdbc:mysql://localhost:3306/BiblioteksSystem?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
       //final String UPDATE_QUERY = "INSERT INTO Media (mediaID, mediaItemCat, ISBN, title, authorDirector, actors, keywords, ageLimit, prodCountry, NumOfCopies) VALUES (" + tableData + ") ON NO KEY UPDATE";
       final String UPDATE_QUERY = "REPLACE INTO Media (mediaItemCat, ISBN, title, authorDirector, actors, keywords, ageLimit, prodCountry) VALUES (" + tableData + ") FROM Media WHERE mediaID = " + mediaID;

       Connection connection = null;
       
       
       try {
          connection = DriverManager.getConnection(DATABASE_URL, "root", "1234");
          final PreparedStatement insertQuery = connection.prepareStatement(UPDATE_QUERY);   
          //insertQuery.setString(1, tableData);
          System.out.println(insertQuery);
          insertQuery.executeUpdate();
       }
       catch (SQLException sqlException) {
           sqlException.printStackTrace();
       }
       
       
   }
    
}
