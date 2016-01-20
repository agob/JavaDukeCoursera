
/**
 * Write a description of CSVMin here.
 * 
 * @author claudio bogazzi
 * @version 4th Nov, 2015
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;


public class CSVMin {
    
    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord lowestSoFar = null;
        //For each row (currentRow) in the CSV File
        for (CSVRecord currentRow : parser) {
            // use method to compare two records
            lowestSoFar = getLowestOfTwo(currentRow, lowestSoFar);
        }
        //The lowestSoFar is the answer
        return lowestSoFar;
        
        
    }

        public void testColdestInDay () {
        FileResource fr = new FileResource("data/2015/weather-2015-05-01.csv");
        CSVRecord lowest = coldestHourInFile(fr.getCSVParser());
        System.out.println("coldest temperature was " + lowest.get("TemperatureF") +
                   " at " + lowest.get("TimeEDT"));
    }
    
        public CSVRecord coldestInManyDays() {
        CSVRecord lowestSoFar = null;
        
        DirectoryResource dr = new DirectoryResource();
        // iterate over files
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            // use method to get largest in file.
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            // use method to compare two records
            lowestSoFar = getLowestOfTwo(currentRow, lowestSoFar);
        }
        //The lowestSoFar is the answer
        return lowestSoFar;
    }
    
        public CSVRecord getLowestOfTwo (CSVRecord currentRow, CSVRecord lowestSoFar) {
        //If largestSoFar is nothing
            if (lowestSoFar == null) {
                lowestSoFar = currentRow;
            }
            //Otherwise
            else {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                if (currentTemp == -9999) {
                    currentTemp = 100;
                }
                double lowestTemp = Double.parseDouble(lowestSoFar.get("TemperatureF"));
            //Check if currentRow’s temperature > largestSoFar’s
                if (currentTemp < lowestTemp) {
                    //If so update largestSoFar to currentRow
                    lowestSoFar = currentRow;
                }
            }
        return lowestSoFar;
    }
    
        public CSVRecord getLowestHumidityOfTwo (CSVRecord currentRow, CSVRecord lowestSoFar) {
            int currentHum = 0;

            if (lowestSoFar == null) {
                lowestSoFar = currentRow;
            }
        //Otherwise
            else {
                if (Double.parseDouble(currentRow.get("TemperatureF") )== -9999){
                    currentHum = 1000;
                    System.out.println("diocaneeee");
                } 
                else {
                    currentHum = Integer.parseInt(currentRow.get("Humidity"));
                }
                
                int lowestHum = Integer.parseInt(lowestSoFar.get("Humidity"));
                //Check if currentRow’s temperature > largestSoFar’s
                if (currentHum < lowestHum) {
                    //If so update largestSoFar to currentRow
                    lowestSoFar = currentRow;
                }
            
        }
        return lowestSoFar;
    }
    
    public void testColdestInManyDays () {
        CSVRecord lowest = coldestInManyDays();
        System.out.println("coldest temperature was " + lowest.get("TemperatureF") +
        " at " + lowest.get("DateUTC"));
    }
    
    
    public String fileWithColdestTemperature () {
        String coldestfile = null;
        CSVRecord lowestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) { 
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            if (lowestSoFar == null) {
                lowestSoFar = currentRow;
            }
            else {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double lowestTemp = Double.parseDouble(lowestSoFar.get("TemperatureF"));
                if (currentTemp < lowestTemp) {
                    lowestSoFar = currentRow;
                    coldestfile = f.getName();
                    
            }
        }
        
        }

        return coldestfile;
        
       }
       
    public void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was " + csv.get("Humidity") +
                   " at " + csv.get("DateUTC"));
           
           
           
        }
       
    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord lowestSoFar = null;
           //For each row (currentRow) in the CSV File
        for (CSVRecord currentRow : parser) {
               // use method to compare two records
               lowestSoFar = getLowestHumidityOfTwo(currentRow, lowestSoFar);
            }
            //The lowestSoFar is the answer
        return lowestSoFar;
           
        }
       
       
    public void testFileWithColdestTemperature() {
        String coldestFile = fileWithColdestTemperature();
        String f = "data/2014/"+coldestFile;
        FileResource fr = new FileResource(f);
        CSVRecord lowest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest day was in file " + coldestFile );
        System.out.println("Coldest temperature on that day was " + lowest.get("TemperatureF") +
           " at " + lowest.get("DateUTC"));
           
        }

        
    public CSVRecord lowestHumidityInManyFiles() {
         CSVRecord lowestSoFar = null;
         DirectoryResource dr = new DirectoryResource();
            // iterate over files
         for (File f : dr.selectedFiles()) {
             FileResource fr = new FileResource(f);
                // use method to get largest in file.
             CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
                // use method to compare two records
             lowestSoFar = getLowestHumidityOfTwo(currentRow, lowestSoFar);
            }
            //The largestSoFar is the answer
         return lowestSoFar;
    }
    
    public void testLowestHumidityInManyFiles(){
         CSVRecord lowest = lowestHumidityInManyFiles();
         System.out.println("Lowest humidity was " + lowest.get("Humidity") +
           " at " + lowest.get("DateUTC"));
        
       }
       
       
    public double averageTemperatureInFile(CSVParser parser) {
         double sum = 0;
         double average = 0;
         int count = 0;
            //For each row (currentRow) in the CSV File
         for (CSVRecord currentRow : parser) {
             double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
             sum = currentTemp + sum;
             count +=1;

            }
         average = sum/count;
         System.out.println(sum);
         System.out.println(count);
         System.out.println(average);
         return average;
            }
            
    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double average = averageTemperatureInFile(parser);
        System.out.println("Average Temperature in file is "+average);
        
    }

    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
        double sum = 0;
        double average = 0;
        int count = 0;
       for (CSVRecord currentRow : parser) {
           if (currentRow.get("Humidity") == "N/A"){
            int currentHum = 0; 
        }
           else {
               int currentHum = Integer.parseInt(currentRow.get("Humidity"));
               if (currentHum >= value) {
                   double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                   sum = currentTemp + sum;
                   count +=1;

                }
            }
            
        }
       if (sum == 0) {
           average = 0;
        }
       else { average = sum / count;}
       return average;
       
     }
   
    
    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double average = averageTemperatureWithHighHumidityInFile( parser, 80);
        if (average == 0) {
            System.out.println("No temperatures with that humidity");
        }
        else {
            System.out.println("Average temp when high humidity is " + average);
        }
        
        }      
           
       }
