import java.util.*;
import java.io.*;

public class BurgerOptimization {
	
	public static int getMinimumError(int burgerIngredients, int [] optimalDistances) {
		
		Arrays.sort(optimalDistances);
		
		int minimumError = 0;
		int actualDistance = 0;
		int numberOfElements = burgerIngredients;
		int arrayIndex = 0;
		if(burgerIngredients % 2 != 0) {
			numberOfElements = numberOfElements - 1;
		}
		for(arrayIndex = 0; arrayIndex < numberOfElements; arrayIndex = arrayIndex + 2) {
			minimumError = minimumError + (optimalDistances[arrayIndex] - actualDistance)*(optimalDistances[arrayIndex] - actualDistance)	
					            + (optimalDistances[arrayIndex + 1] - actualDistance)*(optimalDistances[arrayIndex + 1] - actualDistance);
			actualDistance++;
		}
		//For odd element, check the last remaining element as well
		if(burgerIngredients % 2 != 0) {
			minimumError = minimumError + (optimalDistances[arrayIndex] - actualDistance)*(optimalDistances[arrayIndex] - actualDistance);
		}
		
		return minimumError;
	}


	public static void main(String [] args) throws IOException {
		Scanner in = new Scanner(new File(args[0]));
		PrintStream fileOut = new PrintStream(new File(args[1]));

		//Read input from scanner
		int testCaseSize = in.nextInt();
		int [] resultArray = new int[testCaseSize + 1];

		for(int curTestIndex = 1; curTestIndex <= testCaseSize; curTestIndex++) {

			//Get input for each test case
			int burgerIngredients = in.nextInt();
			int [] optimalDistances = new int[burgerIngredients];
			
			for(int arrayIndex = 0; arrayIndex  < burgerIngredients; arrayIndex++) {
				optimalDistances[arrayIndex] = in.nextInt();
			}

			// Call the function for each test case
			 resultArray[curTestIndex] = getMinimumError(burgerIngredients, optimalDistances);
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