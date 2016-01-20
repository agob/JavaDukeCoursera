
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.ArrayList;

public class CharactersInPlay {
    
    private ArrayList<String> names;
    private ArrayList<Integer> counts;
    
    public CharactersInPlay() {
        names = new ArrayList<String>();
        counts = new ArrayList<Integer>();
    }
    
    public void update(String person){
        
        int index = names.indexOf(person);
        if (index == -1) {
            names.add(person);
            counts.add(1);
        }
        else {
            int freq = counts.get(index);
            counts.set(index,freq+1);
        }
    }
    
    public void findAllCharacters() {
        names.clear();
        counts.clear();
        FileResource resource = new FileResource();
        for(String line : resource.lines()){
            line = line.toLowerCase();
                //System.out.println(line);
            int idx = line.indexOf('.');
            if (idx != -1){
                String c = line.substring(0,idx); 
                    //System.out.println(c);
                update(c);
            }
        }
    }
    
    public void tester(){
        findAllCharacters();       
        for (int k = 0; k < names.size(); k++){
            System.out.println(counts.get(k)+"\t"+names.get(k));
        }
        charactersWithNumParts(10,15);
    }
    
    public void charactersWithNumParts(int num1, int num2) {
        findAllCharacters();       
        for (int k = 0; k < names.size(); k++){
            if (counts.get(k) >= num1 && counts.get(k) <= num2){
                System.out.println(counts.get(k)+"\t"+names.get(k));
            }
        }
        
    }
}
