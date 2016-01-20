
/**
 * Write a description of findAll here.
 * To find the first gene
 * a. Find the start codon ATG.
 * b. Next look immediately past ATG for the first occurrence of each of the three stop
 * codons TAG, TGA, and TAA.
 * c. If the length of the substring between ATG and any of these three stop codons is
 * a multiple of three, then a candidate for a gene is the start codon through the end
 * of the stop codon.
 * d. If there is more than one valid candidate, the smallest such string is the gene.
 * The gene includes the start and stop codon.
 * 2. If no start codon was found, then you are done.
 * 3. If a start codon was found, but no gene was found, then start searching for another gene
 * via the next occurrence of a start codon starting immediately after the start codon that
 * didn't yield a gene.
 * 4. If a gene was found, then start searching for the next gene immediately after this found
 * gene.
 * @author claudio bogazzi
 * @version 26/10/2015
 */
import edu.duke.*;
import java.io.*;

public class findAll {
    public void printAll(String dna){
        String dna1 = dna.toLowerCase();
        int start = 0;
        int stop = 0;

        while (true) {
            int loc = dna1.indexOf("atg",start);
            int len = dna1.length();
   
            if (loc == -1 || stop+3 == len-1) {
                break;
            }

            stop = findStopIndex(dna1, loc+3);
            if (stop+3 < dna.length() ){
    
                String gene = dna.substring(loc, stop+3);
                System.out.println(gene);
                start = stop + 3;               
            }
            else {
        
                start = loc + 3;
            }
            }
            
        }
        
    public void printAll2(String dna) {
        String dna1 = dna.toLowerCase();
        int start = 0;
        while (true){
            int tag = dna1.indexOf("atg", start);
            if (tag == -1) {
                break;
            }
            int end = findStopIndex(dna1, tag+3);
            if (end != dna1.length()){
                System.out.println(dna.substring(tag,end+3));
                start = end+3;
            }
            else {
                start = start + 3;
            }
        }
      
    }
    
    public void printAllStarts(String dna) {
        int start = 0;
        while (true) {
            int loc = dna.indexOf("atg",start);
            if (loc == -1) {
                break;
            }
            System.out.println("starts at "+loc);
            start = loc + 3;
        }
        
    }
    
    public int findStopIndex(String dna, int index) {
        int stop1 = dna.indexOf("tga", index);
        if (stop1 == -1 || (stop1-index) %3 != 0) {
            stop1 = dna.length();
        }
        int stop2 = dna.indexOf("taa", index);
        if (stop2 == -1 || (stop2-index) %3 != 0) {
            stop2 = dna.length();
        }
        int stop3 = dna.indexOf("tag", index);
        if (stop3 == -1 || (stop3-index) %3 != 0) {
            stop3 = dna.length();
        }
        
        return Math.min(stop1, Math.min(stop2,stop3));
    }

        public StorageResource StoreAll(String dna) {
        String dna1 = dna.toLowerCase();
        StorageResource store = new StorageResource();
        int start = 0;
        
        while (true){
            int tag = dna1.indexOf("atg", start);
            if (tag == -1) {
                break;
            }
            int end = findStopIndex(dna1, tag+3);
            if (end != dna1.length()){
                String gene = dna.substring(tag,end+3);
                store.add(gene);
                start = end+3;
            }
            else {
                start = start + 3;
            }
        }

        return store;
    }
    
    
    public void testStorageFinder(){
        //FileResource f = new FileResource("dna/brca1line.fa");
        FileResource f = new FileResource("dna/GRch38dnapart.fa");
        String DNA = f.asString();
        int i = 0;
        int j = 0;
        int longest = 0;
        int c =0;
        StorageResource s1 = StoreAll(DNA);
        //System.out.println(s1.data());
        System.out.println("Number of genese "+s1.size());
        for (String gene : s1.data()){
            if (gene.length()>60){
                i +=1;
            }
            if (cgRatio(gene) > 0.35){
                j +=1;
            }
            if(gene.length() > longest){
                longest = gene.length();
            }
            if (gene.contains("CTG")) {
                c +=1;
            }
       
        }
        
        System.out.println("gene longer than 60 = "+i);
        System.out.println("cg ration > 0.35 in "+ j + " cases");
        System.out.println("The longest gene has "+longest + " genes");
        System.out.println("Number of ctg codons = " +c);
    }
    
    public float cgRatio(String dna){
        dna = dna.toLowerCase();
        float countcg = 0;
        int countc = 0;
        int countg = 0;
        int startc = 0;
        int startg = 0;
        while (true) {
            int posc = dna.indexOf("c", startc);
            if (posc == -1) {
                break;
            }
            countc +=1;
            startc = posc+1;
        }
        while (true) {
            int posg = dna.indexOf("g",startg);
            if (posg == -1) {
                break;
            }
            countg +=1;
            startg = posg+1;
        }
        int total = countc + countg;
        countcg = ((float)total)/dna.length();
        return countcg;
    }
         
         
    }
  
