
/**
 * Write a description of CeasarBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class CeasarBreaker {
    
    public String decrypt(String encrypted){
        CaesarCipher cc = new CaesarCipher();
        int[] freqs = countLetters(encrypted);
        for (int j = 0; j < freqs.length; j++) {
            System.out.println(j +"\t"+freqs[j]);
        }
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4){
            dkey = 26 - (4-maxDex);
        }
        System.out.println("found key: "+dkey);
        return cc.encrypt(encrypted, 26-dkey);
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
    
    public void testDecrypt(){
        int key = 8;
        CaesarCipher cc = new CaesarCipher();
        String message = "Oh man, there are so many eeeeeeeeees in hereeeeeeeee";
        String encrypted = cc.encrypt(message, key);
        System.out.println(encrypted);
        //String decrypted = cc.encrypt(encrypted, 26-key);
        //System.out.println(decrypted);
        //System.out.println("************************************************");
        String decrypted2 = decrypt(encrypted);
        System.out.println(decrypted2);
    }
    
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
    
    public int getKey(String s){
        int[] freqs = countLetters(s);
        for (int k = 0; k < freqs.length; k++){
            System.out.println(k+"\t"+freqs[k]);
        }
        int max = maxIndex(freqs);
        int dkey = max - 4;
        if (max < 4){
            dkey = 26 - (4-max);
        }
        return dkey;
    }
    
    public String decryptTwoKeys(String encrypted){
        CaesarCipher cc = new CaesarCipher();
        String firstHalf = halfOfString(encrypted, 0);
        String otherHalf = halfOfString(encrypted, 1);
        int key1 = getKey(firstHalf);
        int key2 = getKey(otherHalf);
        System.out.println("First key is: "+key1);
        System.out.println("Second key is: "+key2);
        return cc.encryptTwoKeys(encrypted, 26-key1, 26-key2);
    }
    
    public void testDecrypt2k(){
        String message = "Oh man, there are so many eeeeeeeeees in hereeeeeeeee";
        
        
    }
}
