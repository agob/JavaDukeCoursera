import java.util.*;
import edu.duke.*;
import java.io.*;

public class VigenereBreaker {
    
    private final String alphaDef = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private int maxKeyLength;             
    private int[] lastKey;           
                                         
    private int[] bestKeyDict; 
    private String bestLanguage;
                                          
    private int[] bestKey;  
    private int bestRealWord;
     private int bestRealWordDict;
                                                                                         
    public VigenereBreaker( int maxKeyLength)
    {
        this.maxKeyLength = maxKeyLength;
    }
    
    
    /**
     * Constructor
     */
    
    public VigenereBreaker()
    {
        this( 100);
    }
    
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder slice = new StringBuilder();
        
        for (int i = whichSlice % totalSlices; i < message.length(); i+=totalSlices) {
            slice.append( message.charAt( i));
        }
        return slice.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon, String alpha) {
        int[] key = new int[klength];
        CaesarCracker ccracker = new CaesarCracker( mostCommon, alpha);
        
        for (int i = 0; i < klength; i++)
        {
           String slice = sliceString( encrypted, i, klength); 
           key[i] = ccracker.getKey( slice);
        }
        return key;
    }

    public String breakVigenere (String encrypted, int kLength, char mostCommon, String alpha) {

        lastKey = tryKeyLength( encrypted, kLength, mostCommon, alpha);
        
        VigenereCipher vcipher = new VigenereCipher( lastKey, alpha);
        return vcipher.decrypt( encrypted); 
    }
    
    public void testTryKeyLength( int kLength, char mostCommon)
    {
        FileResource fr = new FileResource();
        
        String message = fr.asString();
        int[] key = tryKeyLength( message, kLength, Character.toLowerCase( mostCommon), alphaDef);
        System.out.println( Arrays.toString( key));
    }
    
    public void testBreakVigenere( int kLength, char mostCommon) 
    {
        FileResource fr = new FileResource();        
        String message = fr.asString();
        
        System.out.println( "*** Encrypted message ***");
        System.out.println( breakVigenere( message, kLength, mostCommon, alphaDef));
    } 
    
    private HashSet<String> readDictionary( FileResource fr)
    {
        HashSet<String> dictionary = new HashSet<String>();

        for (String word : fr.lines())
            dictionary.add( word.toLowerCase());
     
        return dictionary;
    }
    
    private int countWords( String message, HashSet<String> dictionary)
    {
        int num = 0;
        String[] words = message.split( "\\W");
                
        for (String w : words)
        {
            if (dictionary.contains( w.toLowerCase()))
                num++;
        }
        
        return num;
    }
    
    private char mostFreqLet( HashMap<Character,Integer> freqLet)
    {
        int maxFreq = 0;
        char maxLet = 0;
        
        for (char l : freqLet.keySet())
        {       
            int freq = freqLet.get( l);
            if (freq <= maxFreq) {
                continue;
            }

            maxLet = l;
            maxFreq = freq; 
        }
        
        return maxLet;
    }
    
    private char mostCommonCharIn( HashSet<String> dictionary)
    {
        HashMap<Character,Integer> freqLet = countLetterDict( dictionary);
        return mostFreqLet( freqLet);
    }
    
    private HashMap<Character,Integer> countLetterDict( HashSet<String> dictionary)
    {
        HashMap<Character,Integer> freqLet = new HashMap<Character,Integer>();
        
        for (String word : dictionary)
        {
            word = word.trim();
            
            for (char l : word.toCharArray()){
                freqLet.put( l, freqLet.getOrDefault( l, 0) + 1);
            }
            
        }
        
        return freqLet;
    }
    
    public String breakForLanguage( String encrypted, HashSet<String> dictionary, String alpha)
    {        
        char commonLetter = mostCommonCharIn( dictionary);
        String bestDecrypted = null;
        
        bestRealWordDict = 0;
        
        for (int kLen = 1; kLen <= maxKeyLength; kLen++)
        {
            String decrypted = breakVigenere( encrypted, kLen, commonLetter, alpha);

            int realWords = countWords( decrypted, dictionary);
            if (realWords <= bestRealWordDict){
                continue;
            }
            bestRealWordDict = realWords;
            bestKeyDict = lastKey;
            bestDecrypted = decrypted;
        }
        
        return bestDecrypted;
    }
    
    public void testBreakForLanguage() 
    {
        FileResource fr = new FileResource();        
        String txt = fr.asString();                

        FileResource frD = new FileResource();        
        
        System.out.println( breakForLanguage( txt, readDictionary( frD ), alphaDef));
        System.out.println( bestKeyDict.length + " => " + Arrays.toString( bestKeyDict));
        System.out.println( "real words: " + bestRealWordDict);
    } 
    
    public String breakForAllLanguages( String encrypted, HashMap<String,HashSet<String>> languages)
    {
        String bestDecrypted = null;
        bestRealWord = 0;  
        bestLanguage = "";
        
        for (String language : languages.keySet())
        {
 
            String alpha = alphaDef;

            String decrypted = breakForLanguage( encrypted, languages.get( language), alpha);
            if (bestRealWordDict <= bestRealWord) {
                continue;
            }   
            bestRealWord = bestRealWordDict;
            bestLanguage = language;
            bestKey = bestKeyDict;
            bestDecrypted = decrypted;
        }

        return bestDecrypted;
    }
    
    public void testBreakForAllLanguages() 
    {
        HashMap<String,HashSet<String>> languageDict = new HashMap<String,HashSet<String>>();
        DirectoryResource drLanguage = new DirectoryResource();        
        
        for (File f : drLanguage.selectedFiles())
        {
            FileResource fr = new FileResource( f);
            languageDict.put( f.getName(), readDictionary( fr));
        }
        
        FileResource frTxt = new FileResource();        
        String txt = frTxt.asString();                
        
        System.out.println( breakForAllLanguages( txt, languageDict));
        System.out.println( "Best language: " + bestLanguage);
        System.out.println( "Best Key: " + bestKey.length + " => " + Arrays.toString( bestKey));
        System.out.println( "real words: " + bestRealWord);
    } 
}
