
/**
 * Write a description of BabyNames here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;

public class BabyNames {
    public void printNames () {
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn <= 100) {
                System.out.println("Name " + rec.get(0) +
                           " Gender " + rec.get(1) +
                           " Num Born " + rec.get(2));
            }
        }
    }

    public void totalBirths (FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        int uniqueGirls = 0;
        int uniqueBoys = 0;
        int totalNames = 0;

        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            String name = rec.get(0);
            totalBirths += numBorn;
            if (rec.get(1).equals("M")) {
                totalBoys += numBorn;
                uniqueBoys += 1;
            }
            else {
                totalGirls += numBorn;
                uniqueGirls +=1;
            }
            
        }
        totalNames = uniqueBoys + uniqueGirls;
        System.out.println("total births = " + totalBirths);
        System.out.println("female girls = " + totalGirls);
        System.out.println("male boys = " + totalBoys);
        System.out.println("total names = " + totalNames);
        System.out.println("girls names = " + uniqueGirls);
        System.out.println("boys names = " + uniqueBoys);
    }

    public void testTotalBirths () {
        //FileResource fr = new FileResource();
        FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob1905.csv");
        totalBirths(fr);
    }
    
    public int getRank(int year, String name, String gender) {
        
        int rank = 0;
        int count = 0;
        int girls = 0;
        int boys = 0;
        FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob"+year+".csv");
        for (CSVRecord rec : fr.getCSVParser(false)) {
            count +=1;
            if (rec.get(1).equals("M")) {
                boys += 1;
            }
            else {
                girls +=1;
            }
            if (rec.get(1).equals(gender)){
                if (rec.get(0).equals(name)){
                    
                    if (gender.equals("F") && rec.get(1).equals(gender)){
                        rank = count;
                        return rank;
                    }
                    if (gender.equals("M") && rec.get(1).equals(gender)) {
                        rank = count - girls;
                        return rank;
                    }

                }
                else {
                    rank = -1;
                }
                
            }
  
           }
        return rank;
       }
    
    public String getName(int year, int rank, String gender){
        String name = null;
        int count = 0;
        int boys = 0;
        int girls =0;
        FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob"+year+".csv");
        for (CSVRecord rec : fr.getCSVParser(false)){
            count +=1;
            if (rec.get(1).equals("M")) {
                boys += 1;
            }
            else {
                girls +=1;
            }
            if (gender.equals("F") && rec.get(1).equals(gender)){
                if (rank == count){
                    name = rec.get(0);
                    return name;
                }
                else {
                    name = "NO NAME";
                }
            }
            if (gender.equals("M") && rec.get(1).equals(gender)){
                if (rank == count - girls){
                    name = rec.get(0);
                    return name;
                }
                else {
                    name = "NO NAME";
                }
            }
        }
        return name;   
    }
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender) {
        int rank = getRank(year, name, gender);
        String newName = getName(newYear, rank, gender);
        System.out.println(name+" born in "+year+" would be "+newName+" if born in "+newYear);

    }
    
    public int yearOfHighestRank(String name, String gender){
        int rank2012 = getRank(2012, name, gender); 
        int rank2013 = getRank(2013, name, gender); 
        int rank2014 = getRank(2014, name, gender); 
        int year;
        
        if (rank2012 < rank2013 && rank2012 < rank2014){
            year = 2012;
        }
        else if (rank2012 < rank2013 && rank2012 > rank2014){
            year = 2014;
        }
        else {
            year = 2013;
        }
        
        if (rank2012 == -1 && rank2013 == -1 && rank2014 == -1){
            year = -1;
        }
        return year;
    }
    
        public int yearOfHighestRank2(String name, String gender, int year1, int year2){
        int year = 0;
        int girls = countGirls(year1);
        int rank = girls;
        int rankTemp = 0;
        for (int i = year1; i <= year2; i++){
            rankTemp = getRank(i, name, gender); 
            if (rankTemp < rank) {
                rank = rankTemp;
                year = i;
            }
        }
        
        return year;
    }
    
    public double testgetAverageRank(String name, String gender) {
        int rank2012 = getRank(2012, name, gender); 
        int rank2013 = getRank(2013, name, gender); 
        int rank2014 = getRank(2014, name, gender); 
        double avRank;
        if (rank2012 == -1 && rank2013 == -1 && rank2014 == -1){
            avRank = -1;
        }
        avRank = ((double)(rank2012 + rank2013 + rank2014)) / 3;
        return avRank;
    }
    
    public double getAverageRank(String name, String gender) {
        int rank = 0;
        double avRank = 0;
        int count = 0;
        for (int i = 1880; i <= 2014; i++){
            count +=1;
            rank += getRank(i, name, gender); 
            avRank = ((double) rank) / count;
        }
        return avRank;
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender){
        int total = 0;
        int rank = getRank(year, name, gender);
        int girls = countGirls(year);
        int count = 0;
        FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob"+year+".csv");
        for (CSVRecord rec : fr.getCSVParser(false)){
            count +=1;
            int numBorn = Integer.parseInt(rec.get(2));
            if (gender.equals("F") && rec.get(1).equals(gender)){
                if (rank > count) {
                    total += numBorn;
                }
            }           
            if (gender.equals("M") && rec.get(1).equals(gender)) {
                if (rank > (count - girls)){
                    total +=numBorn;
                }
            }
        }
        return total;
    }
    
    public int countBoys(int year){
        int num = 0;
        FileResource fr = new FileResource("us_babynames/us_babynames_by_yeaer/yob"+year+".csv");
        for (CSVRecord rec : fr.getCSVParser(false)){
            if (rec.get(1).equals("M")) {
                num += 1;
            }
        }
        return num;
    }
    
        public int countGirls(int year){
        int num = 0;
        FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob"+year+".csv");
        for (CSVRecord rec : fr.getCSVParser(false)){
            if (rec.get(1).equals("F")) {
                num += 1;
            }
        }
        return num;
    }
}
