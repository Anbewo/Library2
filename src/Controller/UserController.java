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
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class UserController {
    
    public static void registerUser(String pnr, String fname, String lname, String password, String email, String phone, String role) {
        
    
       final String DATABASE_URL = "jdbc:mysql://localhost:3306/BiblioteksSystem?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
       final String UPDATE_QUERY = "INSERT INTO User (fName, lName, pnr, email, tel, password, userCat) VALUES (?, ?, ?, ?, ?, ?, ?)";

       Connection connection = null;
      
       try {
          connection = DriverManager.getConnection(DATABASE_URL, "root", "1234");
          final PreparedStatement insertQuery = connection.prepareStatement(UPDATE_QUERY);   
          
          insertQuery.setString(1, fname);
          insertQuery.setString(2, lname);
          insertQuery.setString(3, pnr);
          insertQuery.setString(4, password);
          insertQuery.setString(5, email);
          insertQuery.setString(6, phone);
          insertQuery.setString(7, role);

          System.out.println(insertQuery);
          insertQuery.executeUpdate();
       }
       catch (SQLException sqlException) {
           sqlException.printStackTrace();
       }
       
        
    }
    
    
    public static ArrayList<Integer> loginUser(String pnr, String password, Integer LastPage) {
        
    
       final String DATABASE_URL = "jdbc:mysql://localhost:3306/BiblioteksSystem?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
       final String SELECT_QUERY = "SELECT pnr, password, userCat, userID FROM User WHERE pnr = ?";

       ArrayList<String> content = new ArrayList<String>(); 

       Connection connection = null;
       
       ArrayList<Integer> userInfo = new ArrayList<Integer>(); 

      
       try {
          
          connection = DriverManager.getConnection(DATABASE_URL, "root", "1234");
          final PreparedStatement selectQuery = connection.prepareStatement(SELECT_QUERY);  
          selectQuery.setString(1, pnr);
          ResultSet resultSet = selectQuery.executeQuery();
          ResultSetMetaData metaData = resultSet.getMetaData();
          int numberOfColumns = metaData.getColumnCount();
         
          while(resultSet.next()) {
              for(int i= 1; i <= numberOfColumns; i++) {
                  content.add(resultSet.getObject(i).toString());
              }
          }
          
          System.out.println(pnr);
          System.out.println(password);
          System.out.print(content);
          
          if(pnr.equals(content.get(0))) {
               if(password.equals(content.get(1))) {
                   userInfo.add(Integer.parseInt(content.get(2)));
                   userInfo.add(Integer.parseInt(content.get(3)));
                   userInfo.add(LastPage);
               }
               else {
                   userInfo.add(0);
                   userInfo.add(0);
               }
          }
    
          
       }
       catch (IndexOutOfBoundsException inputException) {
           inputException.printStackTrace();
           userInfo.add(0);
           userInfo.add(0);
           return userInfo;
       }
       catch (SQLException sqlException) {
           //userCategory = 0;
           //return userCategory;
           sqlException.printStackTrace();
       }
       
       return userInfo;
        
    }
    
     public static void deleteUser() {
        
    
       final String DATABASE_URL = "jdbc:mysql://localhost:3306/BiblioteksSystem?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
       final String DELETE_USER = "DELETE FROM User WHERE userID = ?";

       Connection connection = null;
      
       try {
          connection = DriverManager.getConnection(DATABASE_URL, "root", "1234");
          final PreparedStatement deleteUser = connection.prepareStatement(DELETE_USER);   
          
          deleteUser.setInt(1, Login.userID);

          deleteUser.executeUpdate();
       }
       catch (SQLException sqlException) {
           sqlException.printStackTrace();
       }
       
        
    }
     
    public static void deleteUser(String userID) {
        
    
       final String DATABASE_URL = "jdbc:mysql://localhost:3306/BiblioteksSystem?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
       final String DELETE_USER = "DELETE FROM User WHERE userID = ?";

       Connection connection = null;
      
       try {
          connection = DriverManager.getConnection(DATABASE_URL, "root", "1234");
          final PreparedStatement deleteUser = connection.prepareStatement(DELETE_USER);   
          
          deleteUser.setString(1, userID);

          deleteUser.executeUpdate();
       }
       catch (SQLException sqlException) {
           sqlException.printStackTrace();
       }
       
        
    }
     
     public static void updateUser(String pnrVar, String fnameVar, String lnameVar, String emailVar, String phoneVar, String passwordVar) {
        String sqlString = "";
         
        if(!pnrVar.equals("")) {
        String sqlPnr = ",pNr =" + "'" + pnrVar + "'";
        sqlString = sqlString.concat(sqlPnr);
        }
        if(!fnameVar.equals("")) {
        String sqlfname = ",fName =" + "'" + fnameVar + "'";
        sqlString = sqlString.concat(sqlfname);
        }
        if(!lnameVar.equals("")) {
        String sqllname = ",lName =" + "'" + lnameVar + "'";
        sqlString = sqlString.concat(sqllname);
       }
       if(!emailVar.equals("")) {
        String sqlemail = ",email =" + "'" + emailVar + "'";
        sqlString = sqlString.concat(sqlemail);
       }
       if(!phoneVar.equals("")) {
        String sqlphone = ",tel =" + "'" + phoneVar + "'";
        sqlString = sqlString.concat(sqlphone);
       }
       if(!passwordVar.equals("")) {
        String sqlpassword = ",password =" + "'" + passwordVar + "'";
        sqlString = sqlString.concat(sqlpassword);
       }
       
       sqlString = sqlString.substring(1);
       
       final String DATABASE_URL = "jdbc:mysql://localhost:3306/BiblioteksSystem?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
       final String UPDATE_QUERY = "UPDATE User SET " + sqlString + " WHERE userID = ?";
       
       Connection connection = null;
      
       try {
          connection = DriverManager.getConnection(DATABASE_URL, "root", "1234");
          final PreparedStatement insertQuery = connection.prepareStatement(UPDATE_QUERY);   
          insertQuery.setInt(1, Login.userID);

          System.out.println(insertQuery); 
          insertQuery.executeUpdate();
       }
       catch (SQLException sqlException) {
           sqlException.printStackTrace();
       }
       
   
       
   }
     
   public static void updateUser(String userID, String fnameVar, String lnameVar, String pnrVar, String userCat) {
       
       final String DATABASE_URL = "jdbc:mysql://localhost:3306/BiblioteksSystem?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
       final String UPDATE_QUERY = "UPDATE User SET fName = ?, lName = ?, pNr = ?, userCat = ? WHERE userID = ?";
       
       Connection connection = null;
      
       try {
          connection = DriverManager.getConnection(DATABASE_URL, "root", "1234");
          final PreparedStatement insertQuery = connection.prepareStatement(UPDATE_QUERY);   
          insertQuery.setString(1, fnameVar);
          insertQuery.setString(2, lnameVar);
          insertQuery.setString(3, pnrVar);
          insertQuery.setString(4, userCat);
          insertQuery.setString(5, userID);

          System.out.println(insertQuery); 
          insertQuery.executeUpdate();
       }
       catch (SQLException sqlException) {
           sqlException.printStackTrace();
       }
       
   
       
   }
    
}
