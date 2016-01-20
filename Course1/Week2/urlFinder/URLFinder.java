/**
 * Prints out all links within the HTML source of a web page.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import java.io.*;


public class URLFinder {
    public StorageResource findURLs(String url) {
        URLResource page = new URLResource(url);
        String source = page.asString();
        StorageResource store = new StorageResource();
        int start = 0;
        while (true) {
            int index = source.indexOf("href=", start);
            if (index == -1) {
                break;
            }
            int firstQuote = index+6; // after href="
            int endQuote = source.indexOf("\"", firstQuote);
            String sub = source.substring(firstQuote, endQuote);
            if (sub.startsWith("https")) {
                store.add(sub);
            }
            start = endQuote + 1;
        }
        return store;
    }
    
    public StorageResource findAllURLs(String url) {
        URLResource page = new URLResource(url);
        String source = page.asString();
        StorageResource store = new StorageResource();
        int start = 0;
        while (true) {
            int index = source.indexOf("href=", start);
            if (index == -1) {
                break;
            }
            int firstQuote = index+6; // after href="
            int endQuote = source.indexOf("\"", firstQuote);
            String sub = source.substring(firstQuote, endQuote);
            if (sub.startsWith("http")) {
                store.add(sub);
            }
            start = endQuote + 1;
        }
        return store;
    }
    
    public StorageResource findComURLs(String url) {
        URLResource page = new URLResource(url);
        String source = page.asString();
        StorageResource store = new StorageResource();
        int start = 0;
        while (true) {
            int index = source.indexOf("href=", start);
            if (index == -1) {
                break;
            }
            int firstQuote = index+6; // after href="
            int endQuote = source.indexOf("\"", firstQuote);
            String sub = source.substring(firstQuote, endQuote);
            if (sub.contains(".com")) {
                store.add(sub);
            }
            start = endQuote + 1;
        }
        return store;
    }
    
    public StorageResource findComURLsv2(String url) {
        URLResource page = new URLResource(url);
        String source = page.asString();
        StorageResource store = new StorageResource();
        int start = 0;
        while (true) {
            int index = source.indexOf("href=", start);
            if (index == -1) {
                break;
            }
            int firstQuote = index+6; // after href="
            int endQuote = source.indexOf("\"", firstQuote);
            String sub = source.substring(firstQuote, endQuote);
            if (sub.endsWith(".com") || sub.endsWith(".com/")) {
                store.add(sub);
            }
            start = endQuote + 1;
        }
        return store;
    }
    
    public int countdots(StorageResource s){
        //int start = 0;
        int c = 0;
        int count = 0;
        for (String link : s.data())
        {
            int start = 0;
            //System.out.println(link);
            while (true) {
                int pos = link.indexOf(".", start);
                if (pos == -1) {
                    break;
                }
                c +=1;
                start = pos+1;
            }    
        }
        return c;
    }
    
    public void testURL() {
        //StorageResource s1 = findURLs("https://www.whitehouse.gov");
        //StorageResource s2 = findURLs("http://www.doctorswithoutborders.org");
        StorageResource s3 = findURLs("http://www.dukelearntoprogram.com/course2/data/newyorktimes.html");
        StorageResource s4 = findAllURLs("http://www.dukelearntoprogram.com/course2/data/newyorktimes.html");
        StorageResource s5 = findComURLs("http://www.dukelearntoprogram.com/course2/data/newyorktimes.html");
        StorageResource s6 = findComURLsv2("http://www.dukelearntoprogram.com/course2/data/newyorktimes.html");
        
        //for (String link : s3.data()) {

          //  System.out.println(link);
            //System.exit(0);
        //}
                
        System.out.println("number of all urls = " + s4.size());
        System.out.println("number of secure urls = " + s3.size());
        System.out.println("number of urls with .com = " + s5.size());
        System.out.println("number of urls that ends with .com or .com/ = " + s6.size());
        System.out.println("number of dots = " + countdots(s4));
        //System.out.println("size = " + s2.size());
    }
}
