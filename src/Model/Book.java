/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Admin
 */
public class Book extends LoanTip {
        
    private String ISBN;
    
    public Book(String title, String authorDirector, String placement, String ISBN) {
        super(title, authorDirector, placement);
        this.ISBN = ISBN;
    }
    
    public String getExtra() {
        return ISBN;
    }
    
    public void setExtra(String ageLimit) {
        this.ISBN = ageLimit;
    }

     public String returnTip() {
        
        String message = "Vårt bok tips är " + this.getTitle() + System.lineSeparator() +"författaren heter: " + this.getAuthorDirector() + 
        System.lineSeparator() + "den finns på hylla " + this.getPlacement() + " och har ISBN " + this.getExtra();
  
        return message;
        
    }
}

