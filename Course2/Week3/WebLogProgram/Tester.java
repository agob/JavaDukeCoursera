
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    LogAnalyzer la;
    
    public Tester(String fname) {
        la = new LogAnalyzer();
        la.readFile(fname);
    }
    
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        // complete method
        la.printAll();
    }
    
    public void testUniqueIP(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        int uniqueIPs = la.countUniqueIPs();
        System.out.println("there are "+uniqueIPs+" IPs");
    }
    
    public void testHigherThanNum(){
        System.out.println("Testing statuses:");
        int num = 400;
        la.printAllHigherThanNum(num);
    }
    public void testUniqueIPVisitsOnDay() {
    	System.out.println("Ips on: Sep 24");
    	System.out.println(la.uniqueIPVisitsOnDay("Sep 24").size());
    	//System.out.println("Ips on: Sep 30");
    	//System.out.println(la.uniqueIPVisitsOnDay("Sep 30"));
    }
    
    public void testCountUniqueIPsInRange() {
    	System.out.println("Testing ips in range 200-299");
    	System.out.println(la.countUniqueIPsInRange(200, 299));
    	//System.out.println("Testing ips in range 300-399");
    	//System.out.println(la.countUniqueIPsInRange(300, 399));
    }
    
    public void testCounts() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String,Integer> map = la.countVisitsPerIP();
        for(String w : map.keySet()){
            int value = map.get(w);
            System.out.println(w+"\t"+value);
            }

    }
    
     public void testMostNumberVisitsByIP() {
    	int max = la.mostNumberVisitsByIP(la.countVisitsPerIP());
    	System.out.println("Max number of visits from ip: "+max);
    }
        public void testIPsForDays() {
    	HashMap<String, ArrayList<String>> map = la.iPsForDays();
    	System.out.println("Mapping day-visit");
    	System.out.println(map);
    }
    
    public void testDayWithMostIPVisits() {
    	HashMap<String, ArrayList<String>> map = la.iPsForDays();
    	System.out.println("Day with most visits:");
    	System.out.println(la.dayWithMostIPVisits(map));
    }
    
    public void testIPsWithMostVisitsOnDay() {
    	ArrayList<String> list = la.iPsWithMostVisitsOnDay("Sep 30");
    	System.out.println("IPs with most visits on Sep 30:");
    	System.out.println(list);
    }
    
        public static void main(String[] args) {
		Tester t = new Tester("weblog2_log");
		//t.testLogAnalyzer();
		t.testUniqueIP();
		t.testHigherThanNum();
		t.testUniqueIPVisitsOnDay();
		t.testCountUniqueIPsInRange();
		t.testCounts();
		t.testMostNumberVisitsByIP();
		t.testIPsForDays();
		t.testDayWithMostIPVisits();
		t.testIPsWithMostVisitsOnDay();
	}
}
