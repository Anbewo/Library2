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
public class Movie extends LoanTip {

    private String ageLimit;
    
    public Movie(String title, String authorDirector, String placement, String ageLimit) {
        super(title, authorDirector, placement);
        this.ageLimit = ageLimit;
    }
    
    public String getExtra() {
        return ageLimit;
    }
    
    public void setExtra(String ageLimit) {
        this.ageLimit = ageLimit;
    }
    
    public String returnTip() {
        
        String message = "Vårt film tips är " + this.getTitle() + System.lineSeparator() + "författaren heter: " + this.getAuthorDirector() + " den finns på hylla " +
        System.lineSeparator() + this.getPlacement() + " och har ISBN " + this.getExtra();
  
        return message;
    }
    
}
