
/**
 * Write a description of TestCaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class TestCaesarCipherTwo {
    public String halfOfString(String message, int start){
        String output = "";
        message = message.substring(start);
        for (int i = 0; i < message.length(); i ++){
            char ch = message.charAt(i);
            int idx = message.indexOf(ch);
            if (idx % 2 == 0 ) {
                output = output+Character.toString(ch);
            }
            

        }
        return output;
    }
    
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
        int key1 = 20;
        int key2 = 8;
        CaesarCipherTwo cc = new CaesarCipherTwo(key1, key2);
        String encrypted = cc.encrypt(input);
        String decrypted = cc.decrypt(encrypted);
        System.out.println("The encrypted message is: "+encrypted);
        System.out.println("The decrypted message is: "+decrypted);
        System.out.println("=========================");
        String broken = breakCaesarCipher(encrypted);
        System.out.println("broken: "+broken );
    }
    
    public int getKey(String s){
        int[] freqs = countLetters(s);
        //for (int k = 0; k < freqs.length; k++){
        //    System.out.println(k+"\t"+freqs[k]);
        //}
        int max = maxIndex(freqs);
        int dkey = max - 4;
        if (max < 4){
            dkey = 26 - (4-max);
        }
        return dkey;
    }
    
    public String breakCaesarCipher(String input) {
        String firstHalf = halfOfString(input, 0);
        String otherHalf = halfOfString(input, 1);
        int key1 = getKey(firstHalf);
        int key2 = getKey(otherHalf);
        System.out.println("First key is: "+key1);
        System.out.println("Second key is: "+key2);
        CaesarCipherTwo cc = new CaesarCipherTwo(key1, key2);
        return cc.decrypt(input);
        
    }
}
