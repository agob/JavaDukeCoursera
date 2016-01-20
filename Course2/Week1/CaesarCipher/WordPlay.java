
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class WordPlay {

    public boolean isVowel(char ch) {
        if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u'){
            return true;
        }
        if (ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U'){
            return true;
        }
        else {
            return false;
        }
        
    }
    


    public void testisVowel(){
        char input = 'F';
        boolean output = isVowel(input);
        System.out.println(output);
    }
    
    public String replaceVowels(String phrase, char ch){
        StringBuilder input = new StringBuilder(phrase);
        for (int i = 0; i < input.length(); i++){
            char currChar = phrase.charAt(i);
            if (isVowel(currChar)){
                input.setCharAt(i, ch);
            }
        }
        String output = input.toString();
        return output;
    }
    
    public void testreplaceVowels(){
        String input = "Hello World";
        char ch = '*';
        String answer = replaceVowels(input, ch);
        System.out.println(answer);
        
    }
    
    public String emphasize(String phrase, char ch) {
        StringBuilder input = new StringBuilder(phrase);
        int idx = 0;
        for (int i = 0; i < input.length(); i++){
            if (input.charAt(i) == ch){
                idx = phrase.indexOf(ch, i);
                if (idx % 2 != 0 ) {
                    input.setCharAt(i, '*');
                }
                else {
                    input.setCharAt(i,'+');
                }
        }
        }
        String output = input.toString();
        return output;
    }
    
    public void testEmphasize(){
    
        String input = "Mary Bella Abracadabra";
        char ch = 'a';
        String output = emphasize(input, ch);
        System.out.println(output);
    }
    
}