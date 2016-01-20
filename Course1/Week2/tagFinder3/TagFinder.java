
/**
 * Write a description of findProtein here.
 * 
 * @author Claudio Bogazzi
 * @version 25/10/2015
 */

import edu.duke.*;
import java.io.*;

public class TagFinder {
    public String findProtein(String dna) {
        dna = dna.toLowerCase();
        int start = dna.indexOf("atg");
        if (start == -1) {
            return "";
        }
        int stop = dna.indexOf("tag", start+3);
        int stop2 = dna.indexOf("tga",start+3);
        int stop3 = dna.indexOf("taa",start+3);
        if ((stop - start) % 3 == 0) {
            System.out.println("stop codon is tag");
            return dna.substring(start, stop+3);
        }
        else if ((stop2 - start) % 3 == 0) {
            System.out.println("stop codon is tga");
            return dna.substring(start, stop2+3);    
          }
        else if ((stop3 - start) % 3 == 0){
              System.out.println("stop codon is taa");
              return dna.substring(start, stop3+3);
          }
        else {
            return "";
        }
        
    }
    public String stopCodon(String gene){
        gene = gene.toLowerCase();
        int start = gene.indexOf("atg");
        if (start == -1) {
            return "";
        }
        int stop = gene.indexOf("tag", start+3);
        int stop2 = gene.indexOf("tga", start+3);
        int stop3 = gene.indexOf("taa", start+3);
        if ((stop - start) % 3 == 0) {
            return gene.substring(stop, stop+3);   
        }
        else if ((stop2 - start) % 3 == 0) {
            return gene.substring(stop2, stop2+3);
          }
        else if ((stop3 - start) % 3 == 0){
            return gene.substring(stop3, stop3+3);
          }
        else {
            return "";
        }
    }
    
    public String stopCodon2(String dna){
        String answer = findProtein(dna);
        int size = answer.length();
        if ( size > 6 ) {
            return answer.substring(size - 3, size);
        }
        else {
            return "";
        }
    
    }
    
    public void testing() {
        //String a = "cccatggggtttaaataataataggagagagagagagagttt";
        //String ap = "atggggtttaaataataatag";
        String a = "ataaactatgttttaaatgt";
        String ap = "atgttttaa";
        //String a = "ATGCCCTAG";
        //String ap = "ATGCCCTAG";
        String result = findProtein(a);
        if (ap.equals(result)) {
            System.out.println("success for " + ap + " length " + ap.length());
        }
        else {
            System.out.println("mistake for input: " + a);
            System.out.println("got: " + result);
            System.out.println("not: " + ap);
        }
    }

    public void realTesting() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            String s = fr.asString();
            System.out.println("read " + s.length() + " characters");
            String result = findProtein(s);
            System.out.println("found " + result);
        }
    }
}