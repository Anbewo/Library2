/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import View.UpdateCopy;
import View.Login;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 *
 * @author Admin
 */
public class LoanController {
    
    
    public static String checkStatus(String barcodeID) {
        
       final String DATABASE_URL = "jdbc:mysql://localhost:3306/BiblioteksSystem?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
       final String GET_LOAN_QUERY = "SELECT returned FROM Loan WHERE barcodeID = ?;";
       
       Connection connection = null;
      
       String returnedStatus = "";
       
       try {
          connection = DriverManager.getConnection(DATABASE_URL, "root", "1234");
          final PreparedStatement getStatus = connection.prepareStatement(GET_LOAN_QUERY);   
          getStatus.setString(1, barcodeID);
          ResultSet resultSet = getStatus.executeQuery();
          
          while(resultSet.next()) {
              returnedStatus = resultSet.getObject(1).toString();
          }
          
       }
       catch (SQLException sqlException) {
           sqlException.printStackTrace();
       }
       
       return returnedStatus;
        
    }
    
    
    public static void addToBasket(String barcodeID, int userID, String mediaID) {
        
      //int currentOccasion = 0;
      
      boolean allowed = checkLoanCount();
      int nrLoanDays = checkLoanDays(mediaID);
      
      if(allowed == true) {
          
           if(nrLoanDays > 0) {
            addLoan(nrLoanDays, barcodeID);
            System.out.println("FRAMGÅNG");
           } 
      
           else {
            System.out.println("KAN INTE LÅNAS");
           }
          
      }
      
      else {
          System.out.println("FEL FÖR MÅNGA LÅN");     
      }
      
     
        
       //Tabeller: MediaCopy, Media, User
      
      //Kontrollera att exemplaret är tillgänligt-MediaCopy(DONE)
      
      //Kontrollera att användaren inte har lånat för många böcker-User(DONE) 
      
      //Kontrollera att exemplaret får lånas-Media(DONE)
      
      //Sätt in ny rad med userID, barcodeID, return_date, loan_occasion+1
      
      //Bekräfta lånet
       
        
    }
    
    //Sätt in ny rad med userID, barcodeID, return_date, loan_occasion+1
    
    
    public static void returnLoan(String barcodeID) {
        
        final String DATABASE_URL = "jdbc:mysql://localhost:3306/BiblioteksSystem?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        final String RETURN_LOAN = "UPDATE Loan SET returned = 1 WHERE barcodeID = ?";

        Connection connection = null;
      
       try {
          connection = DriverManager.getConnection(DATABASE_URL, "root", "1234");
          final PreparedStatement returnLoan = connection.prepareStatement(RETURN_LOAN);   
          returnLoan.setString(1, barcodeID);
          
          returnLoan.executeUpdate();
       }
       catch (SQLException sqlException) {
           sqlException.printStackTrace();
       }
        
        
    }
    
    
    public static void addLoan(int nrLoanDays, String barcodeID) {
        
        
        Timestamp returnDate = returnDateCalc(nrLoanDays);
        int loanOccasion = getLoanOccasion();
        
        final String DATABASE_URL = "jdbc:mysql://localhost:3306/BiblioteksSystem?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        final String ADD_LOAN = "INSERT INTO Loan (userID, barcodeID, returnDate, returned, loanOccasion) VALUES (?, ?, ?, ?, ?)";

        Connection connection = null;
      
       try {
          connection = DriverManager.getConnection(DATABASE_URL, "root", "1234");
          final PreparedStatement addLoan = connection.prepareStatement(ADD_LOAN);   
          addLoan.setInt(1, Login.userID);
          addLoan.setString(2, barcodeID);
          addLoan.setTimestamp(3 ,returnDate);
          addLoan.setInt(4 ,0);
          addLoan.setInt(5, loanOccasion+1); //Lägger till ett för ett nytt lånetillfälle
          
          addLoan.executeUpdate();
           System.out.println("ARTIKEL LÅNAD");
       }
       catch (SQLException sqlException) {
           sqlException.printStackTrace();
           System.out.println("LÅNET FAILADE");
       }
        
        
    }
    
     
    public static Timestamp returnDateCalc(int loanDays) {
        
         
      Calendar loanDate = Calendar.getInstance();
      Calendar tempReturnDate = Calendar.getInstance();
      tempReturnDate.add(Calendar.DAY_OF_MONTH, loanDays);
      Timestamp returnDate = new Timestamp(tempReturnDate.getTimeInMillis()); 
      
      return returnDate;
      
    }
    
    public static int getLoanOccasion() {
        
        int loanOcc = 0;
        
        final String DATABASE_URL = "jdbc:mysql://localhost:3306/BiblioteksSystem?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        final String GET_LOAN_OCC = "SELECT noLoanOccasions FROM User WHERE userID = ?";

        Connection connection = null;
      
       try {
          connection = DriverManager.getConnection(DATABASE_URL, "root", "1234");
          final PreparedStatement loanOccasion = connection.prepareStatement(GET_LOAN_OCC);   
          loanOccasion.setInt(1, Login.userID);
          ResultSet resultSet = loanOccasion.executeQuery();
          
          
          while(resultSet.next()) {
              String tempNr = resultSet.getObject(1).toString();
              loanOcc = Integer.parseInt(tempNr);
              System.out.println(loanOcc);
          }
          
          
       }
       catch (SQLException sqlException) {
           sqlException.printStackTrace();
       }
       
       return loanOcc;
       
    }
    
    
    public static boolean checkLoanCount() {
        
        int maxloans = 0;
        int count = 0;
        boolean allowed = false;
        
        final String DATABASE_URL = "jdbc:mysql://localhost:3306/BiblioteksSystem?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        final String GET_LOANS_QUERY = "SELECT COUNT(*) FROM Loan WHERE userID = ? AND returned = 0";

        Connection connection = null;
      
       try {
          connection = DriverManager.getConnection(DATABASE_URL, "root", "1234");
          final PreparedStatement countLoans = connection.prepareStatement(GET_LOANS_QUERY);   
          countLoans.setInt(1, Login.userID);
          ResultSet resultSet = countLoans.executeQuery();
          
           while(resultSet.next()) {
                String tempNr = resultSet.getObject(1).toString();
                count = Integer.parseInt(tempNr);
           }
        
       }
       catch (SQLException sqlException) {
           sqlException.printStackTrace();
       }
       
       
       switch(Login.userCategory) {
           
           case 0: 
               maxloans = 0;
           break;
           case 1: 
               maxloans = 1;
           break;
           case 2: 
               maxloans = 3;
           break;
           case 3: 
               maxloans = 5;
           break;
           case 4: 
               maxloans = 7;
           break; 
           case 5: 
               maxloans = 100;
           break;
       
       }
       
       if(count < maxloans) {
           allowed = true;
           System.out.println(allowed);
           return allowed;    
       }
       else {
          allowed = false;
           System.out.println(allowed);
          return allowed;
       }
       
    }
    
    
    public static int checkLoanDays(String MediaID) {
     
        int loanDays = 0;
        String mediaCat = "";
        
        final String DATABASE_URL = "jdbc:mysql://localhost:3306/BiblioteksSystem?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        final String GET_MEDIACAT_QUERY = "SELECT mediaItemCat FROM Media WHERE mediaID = ?";

        Connection connection = null;
      
       try {
          connection = DriverManager.getConnection(DATABASE_URL, "root", "1234");
          final PreparedStatement getMediaCat = connection.prepareStatement(GET_MEDIACAT_QUERY);   
          getMediaCat.setString(1, MediaID);
          ResultSet resultSet = getMediaCat.executeQuery();
          
           while(resultSet.next()) {
                mediaCat = resultSet.getObject(1).toString();
           }
 
       }
       catch (SQLException sqlException) {
           sqlException.printStackTrace();
       }
       
       
       switch(mediaCat) {
           
           case "Kurslitteratur": 
               loanDays = 14;
           break;
           case "Bok": 
               loanDays = 30;
           break;
           case "Tidsskrift": 
               loanDays = 0;
           break;
           case "Referenslitteratur": 
               loanDays = 0;
           break;
           case "Film": 
               loanDays = 7;
           break;
       
       }
       
       return loanDays;
       
    }
    
}
