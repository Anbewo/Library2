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
   
    
   
   public static void main(String args[]) {
       
       //DBconnector conn = DBconnector();
       MediaSearchController.listMedia("'%The%'");
       
       /*
       final String DATABASE_URL = "jdbc:mysql://localhost:3306/BiblioteksSystem?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
       final String SELECT_QUERY = "SELECT userID, fName, lName FROM User";
   
       try ( 
          Connection connection = DriverManager.getConnection(DATABASE_URL, "root", "1234");
          Statement statement = connection.createStatement();
          ResultSet resultSet = statement.executeQuery(SELECT_QUERY)) {
          
          ResultSetMetaData metaData = resultSet.getMetaData();
          int numberOfColumns = metaData.getColumnCount();
          
          System.out.printf("Authors Table of Books Database:%n%n");
          
          for(int i = 1; i <= numberOfColumns; i++) {
              System.out.printf("%-8s\t", metaData.getColumnName(i));
          }
          System.out.println();
          
          while(resultSet.next()) {
              for(int i= 1; i <= numberOfColumns; i++) {
                  System.out.printf("%-8s\t", resultSet.getObject(i));
              }
              System.out.println();
          }
       }
       catch (SQLException sqlException) {
           sqlException.printStackTrace();
       }
       */
   
   } 
   
   
   public static void connectToDB() {
        final String DATABASE_URL = "jdbc:mysql://localhost:3306/BiblioteksSystem?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
   
       try ( 
          Connection connection = DriverManager.getConnection(DATABASE_URL, "root", "1234");
        ) {}
       catch (SQLException sqlException) {
           sqlException.printStackTrace();
       }
   }
    
    
}
