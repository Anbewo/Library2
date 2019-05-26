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
public class LoanTip {

    private String title;
    private String authorDirector;
    private String placement;
        
      public LoanTip(String title, String authorDirector, String placement){
        setTitle(title);
        setAuthorDirector(authorDirector);
        setPlacement(placement);
      }
      
     public String getTitle() {
        return title;
     }

     public void setTitle(String title) {
        this.title = title;
     }

     public String getAuthorDirector() {
        return authorDirector;
     }

     public void setAuthorDirector(String authorDirector) {
        this.authorDirector = authorDirector;
     }
     
     public String getPlacement() {
        return placement;
     }

     public void setPlacement(String placement) {
        this.placement = placement;
     }
  
}
