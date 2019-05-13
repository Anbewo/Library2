/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Model.Media;
import View.MediaSearch;

/**
 *
 * @author Admin
 */

public class MediaSearchController {
    
    private MediaSearch mediasearch = null;
    
    public static void listMedia() {
                
       final String DATABASE_URL = "jdbc:mysql://localhost:3306/BiblioteksSystem?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
       final String SELECT_QUERY = "SELECT title, authorDirector FROM Media";
   
       try ( 
          Connection connection = DriverManager.getConnection(DATABASE_URL, "root", "1234");
          Statement statement = connection.createStatement();
          ResultSet resultSet = statement.executeQuery(SELECT_QUERY)) {
          
          ResultSetMetaData metaData = resultSet.getMetaData();
          int numberOfColumns = metaData.getColumnCount();
          
          System.out.printf("Media Search:%n%n");
          
          for(int i = 1; i <= numberOfColumns; i++) {
              System.out.printf("%-8s\t", metaData.getColumnName(i));
          }
          System.out.println();
          
          ArrayList<Object> media = new ArrayList();
                    
          while(resultSet.next()) {
              for(int i= 1; i <= numberOfColumns; i++) {
                  media.add(resultSet.getObject(i));
              }
          }
          
          this.mediasearch.setMessageList(media);

       }
       catch (SQLException sqlException) {
           sqlException.printStackTrace();
       }
       
      
        
        
        /*
        mediaSearchTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        */
       
   }
    
}
