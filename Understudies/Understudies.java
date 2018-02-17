import java.util.*;
import java.io.*;

public class Understudies{
	
	private static float getMaxProbability(int numberOfRoles, float [] probabilityOfLeaving) {
		// Sort the array in O(nlogn) time
		Arrays.sort(probabilityOfLeaving);	
	
		// Create a running product of the largest and smallest elements, then subtract by 1 to get the prob of staying
		int firstElement = 0;
		int lastElement = probabilityOfLeaving.length - 1;
		float runningProduct = 1;
		for(int arrayIndex = 0; arrayIndex < numberOfRoles; arrayIndex++) {
			runningProduct = runningProduct * (1 - (probabilityOfLeaving[firstElement] * probabilityOfLeaving[lastElement]));
			firstElement++;
			lastElement--;
		}

		return runningProduct;
			
	}
 
	public static void main(String [] args) throws IOException {
		Scanner in = new Scanner(new File(args[0]));
		PrintStream fileOut = new PrintStream(new File(args[1]));

		//Read input from scanner
		int testCaseSize = in.nextInt();
		float [] resultArray = new float[testCaseSize + 1];

		for(int curTestIndex = 1; curTestIndex <= testCaseSize; curTestIndex++) {

			//Get input for each test case
			int numberOfRoles = in.nextInt();
			float [] probabilityOfLeaving = new float[2 * numberOfRoles];

			for(int arrayIndex = 0; arrayIndex < probabilityOfLeaving.length; arrayIndex++) {
				probabilityOfLeaving[arrayIndex] = in.nextFloat();
			}
			// Call the function for each test case
			 resultArray[curTestIndex] = getMaxProbability(numberOfRoles, probabilityOfLeaving);
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