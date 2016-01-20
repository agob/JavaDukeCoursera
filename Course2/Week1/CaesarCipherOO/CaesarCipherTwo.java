
/**
 * Write a description of CaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class CaesarCipherTwo {
    
    private String alphabet;
    private String alphabet_lower;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int mainKey1;
    private int mainKey2;
    
    public CaesarCipherTwo(int key1, int key2){
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        alphabet_lower = alphabet.toLowerCase();
        shiftedAlphabet1 = alphabet.substring(key1)+
        alphabet.substring(0,key1);
        shiftedAlphabet2 = alphabet.substring(key2)+
        alphabet.substring(0,key2);
        mainKey1 = key1;
        mainKey2 = key2;
    }
    
    public String encrypt(String input){
        
        StringBuilder encrypted = new StringBuilder(input);
        
        for(int i = 0; i < encrypted.length(); i++) {
            if (i % 2 == 0) {
                char currChar = encrypted.charAt(i);
                if (Character.isLowerCase(currChar)){
                    int idxl = alphabet_lower.indexOf(currChar);
                    if(idxl != -1){
                        //Get the idxth character of shiftedAlphabet (newChar)
                        char newCharLower = shiftedAlphabet1.toLowerCase().charAt(idxl);
                        //Replace the ith character of encrypted with newChar
                        encrypted.setCharAt(i, newCharLower);
                    }
                }
                else {
                    int idx = alphabet.indexOf(currChar);
                    if(idx != -1){
                        //Get the idxth character of shiftedAlphabet (newChar)
                        char newChar = shiftedAlphabet1.charAt(idx);
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
                        char newCharLowerk2 = shiftedAlphabet2.toLowerCase().charAt(idxlk2);
                        //Replace the ith character of encrypted with newChar
                        encrypted.setCharAt(i, newCharLowerk2);
                    }
                }
                else {
                    int idxk2 = alphabet.indexOf(currChark2);
                    if(idxk2 != -1){
                        //Get the idxth character of shiftedAlphabet (newChar)
                        char newChark2 = shiftedAlphabet2.charAt(idxk2);
                        //Replace the ith character of encrypted with newChar
                        encrypted.setCharAt(i, newChark2);
                    }
                
                }
                
                
            }
            
        }
        
        return encrypted.toString();
    }
    
    public String decrypt(String input){
         CaesarCipherTwo cc = new CaesarCipherTwo(26 - mainKey1, 26 - mainKey2);
         return cc.encrypt(input);
        
    }
    
}
