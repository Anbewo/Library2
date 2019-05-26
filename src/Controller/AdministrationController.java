/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import View.Login;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Admin
 */
public class AdministrationController {
    
    public static Timestamp returnDateCalc() {
           
      Calendar loanDate = Calendar.getInstance();
      Timestamp loanDateSQL = new Timestamp(loanDate.getTimeInMillis()); 
      
        System.out.println(loanDateSQL);
      
      return loanDateSQL;
      
    }
    
    public static ArrayList listCopies() {
                
       Timestamp todayTemp = returnDateCalc();
       String today = todayTemp.toString();
       today = today.substring(0, 10);
        System.out.println(today);
       final String DATABASE_URL = "jdbc:mysql://localhost:3306/BiblioteksSystem?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
       final String LIST_LOANS = "SELECT l.barcodeID, m.title, l.returnDate, l.userID, u.pnr, u.fName, u.lName, u.email FROM Loan l, User u,"
       + "Mediacopy m WHERE u.userID = l.userID AND l.barcodeID = m.barcodeID AND l.returned = 0 AND l.returnDate <= " + "'" + today + "' LIMIT 99;";
       ArrayList<Object> delayInfo = new ArrayList<Object>(); 
   
       try ( 
          Connection connection = DriverManager.getConnection(DATABASE_URL, "root", "1234");
          Statement statement = connection.createStatement();   
          ResultSet resultSet = statement.executeQuery(LIST_LOANS)) {
          ResultSetMetaData metaData = resultSet.getMetaData();
          int numberOfColumns = metaData.getColumnCount();

          
          while(resultSet.next()) {
              for(int i= 1; i <= numberOfColumns; i++) {
                  delayInfo.add(resultSet.getObject(i));
              }
          }
          

          return delayInfo;
         
       }
       catch (SQLException sqlException) {
           sqlException.printStackTrace();
       }
        return null;

        
   }
    
   public static ArrayList listCopies(String searchCat, String searchField) {
                
       Timestamp todayTemp = returnDateCalc();
       String today = todayTemp.toString();
       today = today.substring(0, 10);
        System.out.println(today);
       final String DATABASE_URL = "jdbc:mysql://localhost:3306/BiblioteksSystem?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
       final String LIST_LOANS = "SELECT DISTINCT l.barcodeID, m.title, l.returnDate, l.userID, u.pnr, u.fName, u.lName, u.email FROM User u, Loan l,"
       + "Mediacopy m WHERE u.userID = l.userID AND l.barcodeID = m.barcodeID AND l.returned = 0 AND l.returnDate <= " + "'" + today +
       "' AND " + searchCat + "=" + "'" + searchField + "'" + "LIMIT 99;";
       ArrayList<Object> delayInfo = new ArrayList<Object>(); 
   
       try ( 
          Connection connection = DriverManager.getConnection(DATABASE_URL, "root", "1234");
          Statement statement = connection.createStatement();   
          ResultSet resultSet = statement.executeQuery(LIST_LOANS)) {
          ResultSetMetaData metaData = resultSet.getMetaData();
          int numberOfColumns = metaData.getColumnCount();

          
          while(resultSet.next()) {
              for(int i= 1; i <= numberOfColumns; i++) {
                  delayInfo.add(resultSet.getObject(i));
              }
          }
          

          return delayInfo;
         
       }
       catch (SQLException sqlException) {
           sqlException.printStackTrace();
       }
        return null;

        
   }
    
    public static ArrayList listUsers() {
                
       Timestamp todayTemp = returnDateCalc();
       String today = todayTemp.toString();
       today = today.substring(0, 10);
        System.out.println(today);
       final String DATABASE_URL = "jdbc:mysql://localhost:3306/BiblioteksSystem?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
       final String LIST_USERS = "SELECT userID, fName, lname, pNr, userCat FROM User LIMIT 99";
       ArrayList<Object> userInfo = new ArrayList<Object>(); 
   
       try ( 
          Connection connection = DriverManager.getConnection(DATABASE_URL, "root", "1234");
          Statement statement = connection.createStatement();   
          ResultSet resultSet = statement.executeQuery(LIST_USERS)) {
          ResultSetMetaData metaData = resultSet.getMetaData();
          int numberOfColumns = metaData.getColumnCount();

          
          while(resultSet.next()) {
              for(int i= 1; i <= numberOfColumns; i++) {
                  userInfo.add(resultSet.getObject(i));
              }
          }
          

          return userInfo;
         
       }
       catch (SQLException sqlException) {
           sqlException.printStackTrace();
       }
        return null;

        
   }
    
   public static ArrayList listUsers(String searchCat, String searchField) {
                
       final String DATABASE_URL = "jdbc:mysql://localhost:3306/BiblioteksSystem?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
       final String LIST_USERS = "SELECT DISTINCT u.userID, u.fName, u.lname, u.pNr, u.userCat FROM User u, loan l, mediacopy m WHERE u.userID = l.userID AND l.barcodeID = m.barcodeID AND " + searchCat + "=" + "'" + searchField + "' LIMIT 99";
       ArrayList<Object> userInfo = new ArrayList<Object>(); 
   
       try ( 
          Connection connection = DriverManager.getConnection(DATABASE_URL, "root", "1234");
          Statement statement = connection.createStatement();   
          ResultSet resultSet = statement.executeQuery(LIST_USERS)) {
          ResultSetMetaData metaData = resultSet.getMetaData();
          int numberOfColumns = metaData.getColumnCount();

          
          while(resultSet.next()) {
              for(int i= 1; i <= numberOfColumns; i++) {
                  userInfo.add(resultSet.getObject(i));
              }
          }
          

          return userInfo;
         
       }
       catch (SQLException sqlException) {
           sqlException.printStackTrace();
       }
        return null;

        
   }
    
}
