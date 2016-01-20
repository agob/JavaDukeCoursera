
/**
 * Write a description of ExportQuiz here.
 * 
 * @author claudio bogazzi
 * @version 2nd Nov, 2015
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class ExportQuiz {
    
    public String countryinfo(CSVParser parser, String country) {
        String answer = new String();
        for (CSVRecord record : parser) {
            String csv_country = record.get("Country");
            if (csv_country.contains(country)) {
                String export = record.get("Exports");
                String value = record.get("Value (dollars)");
                answer = csv_country+": "+export+": "+value;
                break;

            }
            else {
                answer = "NOT FOUND";
            }
        }
        return answer;

    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportitem1, String exportitem2){
        
        for (CSVRecord record : parser){
            String csv_country = record.get("Country");
            String export = record.get("Exports");
            if (export.contains(exportitem1) && export.contains(exportitem2)){
                System.out.println(csv_country);
            }
        }
        
    }
    
    public int numberOfExporters(CSVParser parser, String exportitem){
        int c = 0;
        for (CSVRecord record : parser){
            String export = record.get("Exports");
            if (export.contains(exportitem)){
                c++;
            }
        }
        return c;
    }
    
    public void bigExporters(CSVParser parser, String amount){
        for (CSVRecord record : parser){
            String csv_country = record.get("Country");
            String value = record.get("Value (dollars)");
            int len = value.length();
            if (len > amount.length()) {
                System.out.println(csv_country+value);
            }
        }
        
    }
    
    
    public void tester(){
        FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser();
        //String result = new String();
        //result = countryinfo(parser, "Nauru");
        //System.out.println(result);
        //listExportersTwoProducts(parser,"cotton","flowers");
        //int num = numberOfExporters(parser, "cocoa");
        //System.out.println(num);
        bigExporters(parser, "$999,999,999,999");
    }

}
