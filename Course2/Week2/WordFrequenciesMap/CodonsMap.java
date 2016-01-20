
/**
 * Write a description of CodonsHash here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;

public class CodonsMap {
    
    private HashMap<String,Integer> map;
    
    public CodonsMap() {
       map = new HashMap<String,Integer>();
    }
    
    public void buildCodonMap(int start, String dna){
        dna.toUpperCase();
        map.clear();
        for (int i = start; i < dna.length(); i = i+3) {
            int stop = i + 3;
            if (stop > dna.length()){
                break;
            }
            String gene = dna.substring(i, stop);
            //System.out.println(gene);
            if (!map.containsKey(gene))    {
                map.put(gene,1);
                }
            else {
                map.put(gene,map.get(gene)+1); 
            }
        }
    }
    public String getMostCommonCodon(){
        int count = 0;
        String mostCommon = null;
        for(String w : map.keySet()){
            int value = map.get(w);
            if (value >= count) {
                count = value;
                mostCommon = w;
            }
        }
        return mostCommon;
    }
    
    public void printCodonCounts(int start, int stop) {
        buildCodonMap(0,"CAACCTTTAAAAGGAAGAAATCGCAGCAGCCCAGAACCAACTGCATAACATACAACCTTTAAAAGGAAGAAATCGCAGCAGCCCAGAACCAACTGCATAACATACAACCTTTAAAAGGAAGAAATCGCACCAGCCCAGAATCAACTGCATAACATACAAACTTTAAAAGGAAGAAATCTAACATACAACCTTTAAAAGGAAGAAATCGCACCAGCCCAGAATCAACTGCATAACATACAAACTTTAAAAGGAAGAAATCCAACCTTTAAAAGGAAGAAATCGCAGCAGCCCAGAACCAACTGCATAACATACAACCTTTAAAAGGAAGAAATCGCAGCAGCCCAGAACCAACTGCATAACATACAACCTTTAAAAGGAAGAAATCGCACCAGCCCAGAATCAACTGCATAACATACAAACTTTAAAAGGAAGAAATC");
        for(String w : map.keySet()){
            int value = map.get(w);
            if (value >= start && value<= stop ){
                System.out.println(w+"\t"+value);
            }
        }
        
    }
    
    public void tester() {
        //FileResource fr = new FileResource(filename);
        buildCodonMap(1,"CAACCTTTAAAAGGAAGAAATCGCAGCAGCCCAGAACCAACTGCATAACATACAACCTTTAAAAGGAAGAAATCGCAGCAGCCCAGAACCAACTGCATAACATACAACCTTTAAAAGGAAGAAATCGCACCAGCCCAGAATCAACTGCATAACATACAAACTTTAAAAGGAAGAAATCTAACATACAACCTTTAAAAGGAAGAAATCGCACCAGCCCAGAATCAACTGCATAACATACAAACTTTAAAAGGAAGAAATCCAACCTTTAAAAGGAAGAAATCGCAGCAGCCCAGAACCAACTGCATAACATACAACCTTTAAAAGGAAGAAATCGCAGCAGCCCAGAACCAACTGCATAACATACAACCTTTAAAAGGAAGAAATCGCACCAGCCCAGAATCAACTGCATAACATACAAACTTTAAAAGGAAGAAATC");
        for(String w : map.keySet()){
            int value = map.get(w);
            System.out.println(w+"\t"+value);
        }
        System.out.println("===============");
        String mC = getMostCommonCodon();
        System.out.println("The most common string is: "+mC);
        System.out.println("===============");
        //printCodonCounts(2,5);
    }
}
