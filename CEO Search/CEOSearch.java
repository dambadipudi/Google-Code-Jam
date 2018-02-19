import java.util.*;
import java.io.*;

public class CEOSearch {
	
	public static long getMinimumCEOExpLevel(HashMap<Long, Long> experienceEmployeeMap) {
		
		long countOfEmployeesLeft = 0;
		long maxExpLevel = 0;
		
		//Have to sort the hashmap because it does not guarantee that it iterates in the order that the keys were added
		List<Long> sortedExperienceLevels = new ArrayList<Long>();
		
		for(Long currentExperience : experienceEmployeeMap.keySet()) {
			sortedExperienceLevels.add(currentExperience);
		}
		Collections.sort(sortedExperienceLevels);
		
		for(Long currentExperience : sortedExperienceLevels) {
			long currentEmployees = experienceEmployeeMap.get(currentExperience);
			countOfEmployeesLeft = currentEmployees + Math.max(0, countOfEmployeesLeft - currentExperience*currentEmployees);
			if(currentExperience > maxExpLevel) {
				maxExpLevel = currentExperience;
			}
		}
				
		return Math.max(countOfEmployeesLeft, maxExpLevel+1);
	}
 
	public static void main(String [] args) throws IOException {
		Scanner in = new Scanner(new File(args[0]));
		PrintStream fileOut = new PrintStream(new File(args[1]));

		//Read input from scanner
		int testCaseSize = in.nextInt();
		long [] resultArray = new long[testCaseSize + 1];

		for(int curTestIndex = 1; curTestIndex <= testCaseSize; curTestIndex++) {

			//Get input for each test case
			int totalExperiences = in.nextInt();
			HashMap<Long, Long> experienceEmployeeMap = new HashMap<Long, Long>(); 

			for(long arrayIndex = 0; arrayIndex < totalExperiences; arrayIndex++) {
				long employeeCount = in.nextLong();
				long experienceLevel = in.nextInt();
				experienceEmployeeMap.put(experienceLevel, employeeCount);
			}		
	
			// Call the function for each test case
			 resultArray[curTestIndex] = getMinimumCEOExpLevel(experienceEmployeeMap);
		}
		in.close();

		System.setOut(fileOut);
		//Print to output stream
		for(int curTestIndex = 1; curTestIndex <= testCaseSize; curTestIndex++) {	
			System.out.println("Case #" + curTestIndex+ ": "+resultArray[curTestIndex]);
		}
		fileOut.close();
	}
}		
