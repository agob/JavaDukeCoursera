import edu.duke.*;
import java.util.*;
import java.lang.*;

public class CaesarCipher {
    
    public String encrypt(String input, int key) {
        //Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input);
        //Write down the alphabet
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabet_lower = alphabet.toLowerCase();
        //Compute the shifted alphabet
        String shiftedAlphabet = alphabet.substring(key)+
        alphabet.substring(0,key);
        String shiftedAlphabet_lower = alphabet_lower.substring(key)+
        alphabet_lower.substring(0,key);
        //Count from 0 to < length of encrypted, (call it i)
        for(int i = 0; i < encrypted.length(); i++) {
            //Look at the ith character of encrypted (call it currChar)
            char currChar = encrypted.charAt(i);
            if (Character.isLowerCase(currChar)){
                //Find the index of currChar in the alphabet (call it idx)
                int idxl = alphabet_lower.indexOf(currChar);
                //If currChar is in the alphabet
                if(idxl != -1){
                    //Get the idxth character of shiftedAlphabet (newChar)
                    char newCharLower = shiftedAlphabet_lower.charAt(idxl);
                    //Replace the ith character of encrypted with newChar
                    encrypted.setCharAt(i, newCharLower);
                    }
                }
            else {
                int idx = alphabet.indexOf(currChar);
                if(idx != -1){
                //Get the idxth character of shiftedAlphabet (newChar)
                    char newChar = shiftedAlphabet.charAt(idx);
                //Replace the ith character of encrypted with newChar
                    encrypted.setCharAt(i, newChar);
                }
                
            }
            //Otherwise: do nothing
        }
        //Your answer is the String inside of encrypted
        return encrypted.toString();
    }
    public void testCaesar() {
        int key = 15;
        //FileResource fr = new FileResource();
        //String message = fr.asString();
        String message = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        String encrypted = encrypt(message, key);
        //System.out.println(encrypted);
        System.out.println("key is " + key + "\n" + encrypted);
        String decrypted = encrypt(encrypted, 26-key);
        System.out.println(decrypted);
    }
    
    public String encryptTwoKeys(String input, int key1, int key2){
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabet_lower = alphabet.toLowerCase();
        String shiftedAlphabetKey1 = alphabet.substring(key1)+alphabet.substring(0,key1);
        String shiftedAlphabetKey1_lower = alphabet_lower.substring(key1)+alphabet_lower.substring(0,key1);
        String shiftedAlphabetKey2 = alphabet.substring(key2)+alphabet.substring(0,key2);
        String shiftedAlphabetKey2_lower = alphabet_lower.substring(key2)+alphabet_lower.substring(0,key2);
        
        for(int i = 0; i < encrypted.length(); i++) {
            if (i % 2 == 0) {
                char currChar = encrypted.charAt(i);
                if (Character.isLowerCase(currChar)){
                    int idxl = alphabet_lower.indexOf(currChar);
                    if(idxl != -1){
                        //Get the idxth character of shiftedAlphabet (newChar)
                        char newCharLower = shiftedAlphabetKey1_lower.charAt(idxl);
                        //Replace the ith character of encrypted with newChar
                        encrypted.setCharAt(i, newCharLower);
                    }
                }
                else {
                    int idx = alphabet.indexOf(currChar);
                    if(idx != -1){
                        //Get the idxth character of shiftedAlphabet (newChar)
                        char newChar = shiftedAlphabetKey1.charAt(idx);
                        //Replace the ith character of encrypted with newChar
                        encrypted.setCharAt(i, newChar);
                    }
                
                }
            
            }
            
            else {
                char currChark2 = encrypted.charAt(i);
                if (Character.isLowerCase(currChark2)){
                    int idxlk2 = alphabet_lower.indexOf(currChark2);
                    if(idxlk2 != -1){
                        //Get the idxth character of shiftedAlphabet (newChar)
                        char newCharLowerk2 = shiftedAlphabetKey2_lower.charAt(idxlk2);
                        //Replace the ith character of encrypted with newChar
                        encrypted.setCharAt(i, newCharLowerk2);
                    }
                }
                else {
                    int idxk2 = alphabet.indexOf(currChark2);
                    if(idxk2 != -1){
                        //Get the idxth character of shiftedAlphabet (newChar)
                        char newChark2 = shiftedAlphabetKey2.charAt(idxk2);
                        //Replace the ith character of encrypted with newChar
                        encrypted.setCharAt(i, newChark2);
                    }
                
                }
                
                
            }
            
        }
        
        return encrypted.toString();
    }
    
        public void testCaesar2k() {
        int key1 = 7;
        int key2 = 19;
        //FileResource fr = new FileResource();
        //String message = fr.asString();
        String message = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!";
        String encrypted = encryptTwoKeys(message,key1, key2);
        System.out.println(encrypted);
        //String decrypted = encrypt(encrypted, 26-key);
        //System.out.println(decrypted);
    }
}

