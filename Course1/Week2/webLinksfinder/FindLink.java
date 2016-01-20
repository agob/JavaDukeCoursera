
/**
 * Write a description of FindLink here.
 * 
 * @author claudio bogazzi 
 * @version 26/10/2015
 */
import edu.duke.*;
import java.io.*;

public class FindLink {
    public void findLink() {
        String y = "youtube.com";
        URLResource url = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");        
        for (String w : url.words()){  
            String v = w.toLowerCase();
            if (v.indexOf(y) != -1 ) {  
                int start = v.indexOf(y);
                int stop = v.lastIndexOf(y);
                int quote1 = v.lastIndexOf("\"",start-1);
                int quote2 = v.indexOf("\"", stop);
                String mylink = w.substring(quote1+1, quote2);
                System.out.println(mylink);

            }

        }
    }
    
    public void linkfinder(){
        URLResource file = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        for ( String item : file.words() ) {
            String itemLower = item.toLowerCase();
            int pos = itemLower.indexOf("youtube.com");
            if ( pos != -1 ) {
                int beg = item.lastIndexOf("\"", pos);
                int end = item.indexOf("\"", pos + 1);
                System.out.println(item.substring(beg + 1, end));
            }
        }
    }
}