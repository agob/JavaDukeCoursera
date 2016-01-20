
/**
 * Write a description of TestCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class TestCaesarCipher {
    
    public int[] countLetters(String message){
        String alph = "abcdefghijklmnopqrstuvwxyz";
    	int[] counts = new int[26];
    	for (int k=0; k < message.length(); k++){
    	    char ch = Character.toLowerCase(message.charAt(k));
    	    int dex = alph.indexOf(ch);
    	       if (dex != -1){
    	           counts[dex] +=1;
    	       }
    	   }
    	return counts;
    	   }
	
    public int maxIndex(int[] value){
        int max = 0;
        for (int i =0 ; i < value.length; i++){
            if (value[i] > value[max]){
                max = i;
            }
        }
        return max;
    }
    
    public void simpleTests(String input){
        int key = 15;
        CaesarCipher cc = new CaesarCipher(key);
        String encrypted = cc.encrypt(input);
        String decrypted = cc.decrypt(encrypted);
        System.out.println("The encrypted message is: "+encrypted);
        System.out.println("The decrypted message is: "+decrypted);
        System.out.println("=========================");
        String broken = breakCaesarCipher(input);
        System.out.println("broken: "+broken );
    }
    
    public String breakCaesarCipher(String input) {
        int[] freqs = countLetters(input);
       // for (int j = 0; j < freqs.length; j++) {
        //    System.out.println(j +"\t"+freqs[j]);
        //}
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4){
            dkey = 26 - (4-maxDex);
        }
        CaesarCipher cc = new CaesarCipher(dkey);
        return cc.decrypt(input);
        
    }

}
