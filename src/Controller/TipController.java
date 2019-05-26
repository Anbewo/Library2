/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Book;
import Model.LoanTip;
import Model.Movie;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class TipController {
   
    ArrayList<LoanTip> tipList; 

    
    public String getBookTip() {
       
        Connection connection = DBconnector.connectToDB();
        final String TIP_QUERY = "SELECT m.title, m.authorDirector, m.ISBN, mc.placement, mc.barcodeID FROM Media m, Mediacopy mc WHERE m.mediaID = mc.mediaID AND m.mediaItemCat = 'bok' ORDER BY RAND() LIMIT 1";
        Book tip = null;
        
         try ( 
          
          Statement statement = connection.createStatement();
          ResultSet resultSet = statement.executeQuery(TIP_QUERY)) {
         
          while(resultSet.next()) {
            tip = new Book(resultSet.getString("title"),resultSet.getString("authorDirector"), resultSet.getString("placement"), resultSet.getString("ISBN"));
          }
          
          String tipMessage = tip.returnTip();

          return tipMessage;
         
       }
       catch (SQLException sqlException) {
           sqlException.printStackTrace();
       }
        
       return null;
   
    }
    
     public String getMovieTip() {
       
        Connection connection = DBconnector.connectToDB();
        final String TIP_QUERY = "SELECT m.title, m.authorDirector, m.ageLimit, mc.placement, mc.barcodeID FROM Media m, Mediacopy mc WHERE m.mediaID = mc.mediaID AND m.mediaItemCat = 'movie' ORDER BY RAND() LIMIT 1";
        Movie tip = null;
        
         try ( 
          
          Statement statement = connection.createStatement();
          ResultSet resultSet = statement.executeQuery(TIP_QUERY)) {
         
          while(resultSet.next()) {
            tip = new Movie(resultSet.getString("title"),resultSet.getString("authorDirector"), resultSet.getString("placement"), resultSet.getString("ageLimit"));
          }
          
          String tipMessage = tip.returnTip();

          return tipMessage;
         
       }
       catch (SQLException sqlException) {
           sqlException.printStackTrace();
       }
        
       return null;
   
    }
    
}
