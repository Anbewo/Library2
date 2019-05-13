/*

 */
package Model;

/**
 *
 * @author annic
 */
public class Media {
    
    int mediaID;
    String title;
    String authorDirector;
    int numOfCopies;
    int numOfLoanDays;
    
    private MediaCopy mediaCopy;
    
  

    public String getMedia() {
        return title;
    }

   
    
}
