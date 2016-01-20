
/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;

public class WordsInFiles {
    
    HashMap<String,ArrayList<String>> map;
    
    public WordsInFiles(){
        map = new HashMap<String,ArrayList<String>>();
    }
    
    public void addWordsFromFile(File f){
        ArrayList<String> list = new ArrayList<String>();
        
        for(String w : f.words()) {
            w = w.toLowerCase();
            if (!map.containsKey(w)){
                list.add(w);
				map.put(w,list);
			}
			else {
				map.put(w,list);
			}
        }
    }


}
