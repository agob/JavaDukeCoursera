
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class WordLengths {
    
    public void countWordLengths(FileResource resource, int[] counts){
        for(String word : resource.words()){
            
            word = word.toLowerCase();
            int len = word.length();
            for (int i = 0; i < len; i++){
                char currChar = word.charAt(i);
                if (!Character.isLetter(currChar)) {
                    char newChar = currChar;
                    if (word.startsWith(Character.toString(newChar)) || word.endsWith(Character.toString(newChar))){
                        len = len -1;
                    }
                }
            }
            for (int j = 0; j < counts.length; j++){
                if (j == len){
                    counts[j] +=1;
                    System.out.println(j+" letters:"+word);
                        }
                if (len >= counts.length){
                    counts[counts.length-1] +=1;
                }
            }
        }
            
        
        for (int i = 0; i < counts.length; i++){
            System.out.println(i+"\t"+counts[i]);
        }
        
        int maxIndex = indexOfMax(counts);
        System.out.println("Most commom word length is: "+maxIndex);
    }

    public void testCountWordLengths(){
        FileResource resource = new FileResource("data/manywords.txt");
        int[] counts = new int[31];
        countWordLengths(resource, counts);
        
    }
    
    public int indexOfMax(int[] value){
        int max = 0;
        for (int i =0 ; i < value.length; i++){
            if (value[i] >= max){
                max = value[i];
            }
        }
        return max;
    }
    
        public int indexOf(String[] list, String word) {
        for (int k=0; k<list.length; k++) {
            if (list[k].equals(word)) {
                   return k;
               }
           }
        return -1;
    }
}
