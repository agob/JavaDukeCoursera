import edu.duke.*;
import java.util.*;

public class GladLib {
    private ArrayList<String> adjectiveList;
    private ArrayList<String> nounList;
    private ArrayList<String> colorList;
    private ArrayList<String> countryList;
    private ArrayList<String> nameList;
    private ArrayList<String> animalList;
    private ArrayList<String> timeList;
    private ArrayList<String> fruitList;
    private ArrayList<String> verbList;
    private ArrayList<String> checker;
    private int wordCount = 0;
    
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "datalong";
    
    public GladLib(){
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }
    
    public GladLib(String source){
        initializeFromSource(source);
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
        adjectiveList= readIt(source+"/adjective.txt"); 
        nounList = readIt(source+"/noun.txt");
        colorList = readIt(source+"/color.txt");
        countryList = readIt(source+"/country.txt");
        nameList = readIt(source+"/name.txt");      
        animalList = readIt(source+"/animal.txt");
        timeList = readIt(source+"/timeframe.txt");
        verbList = readIt(source+"/verb.txt");
        fruitList = readIt(source+"/fruit.txt");
        checker = new ArrayList<String>();
    }
    
    private String randomFrom(ArrayList<String> source){
        //int index = myRandom.nextInt(source.size());
        //return source.get(index);
        String randWord;
        
        while (true) {
            int index = myRandom.nextInt(source.size());
            randWord = source.get(index);
            int usedIndex = checker.indexOf(randWord);
            if (usedIndex == -1) break;
            else continue;
        }        
        checker.add(randWord);
        wordCount++;
        return randWord;
    }
    
    private String getSubstitute(String label) {
        if (label.equals("country")) {
            return randomFrom(countryList);
            //checker.add(countryR);
            //return countryR;
        }
        if (label.equals("color")){
            return randomFrom(colorList);
            //checker.add(colorR);
            //return colorR;
        }
        if (label.equals("noun")){
            return randomFrom(nounList);
            //checker.add(nounR);
            //return nounR;
        }
        if (label.equals("name")){
            return randomFrom(nameList);
            //checker.add(nameR);
            //return nameR;
        }
        if (label.equals("adjective")){
            return randomFrom(adjectiveList);
            //checker.add(adjR);
            //return adjR;
        }
        if (label.equals("animal")){
            return randomFrom(animalList);
            //checker.add(animalR);
            //return animalR;
        }
        if (label.equals("timeframe")){
            return randomFrom(timeList);
            //checker.add(timeR);
            //return timeR;
        }
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        if (label.equals("verb")){
            return randomFrom(verbList);
            //checker.add(verbR);
            //return verbR;
        }
        if (label.equals("fruit")){
            return randomFrom(fruitList);
            //checker.add(fruitR);
            //return fruitR;
        }
        
        return "**UNKNOWN**";
    }
    
    private String check(String s) {
        String sub = getSubstitute(s);
        if (checker.contains(sub)){
            return check(s);
           } 
        return sub;
       }
       
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String s = w.substring(first+1,last);
        String sub = getSubstitute(s);
        //String sub = check(s);
        return prefix+sub+suffix;
        

    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        checker.clear();
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    public void makeStory(){
        //checker.clear();
        System.out.println("\n");
        String story = fromTemplate("datalong/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("\nReplaced words: "+wordCount);
        wordCount = 0;
    }
    


}
