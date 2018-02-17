import java.util.*;
import java.io.*;

public class TicketTrouble {
	public static int countMaxFriendsInRow(int [][] ticketArray, int dimension) {
		//Create a 2 D matrix of size dimension. 
		int [][] seatMatrix = new int[dimension][dimension];

		//Fill in the seatMatrix matrix with possible values
		for(int ticketIndex = 0; ticketIndex < ticketArray.length; ticketArray++) {
			//For each ticket, get row and column value and insert a 1 in the seatMatrix for both possible values 
			seatMatrix[ticketArray[ticketIndex][0]][ticketArray[ticketIndex][1]] = 1;
			seatMatrix[ticketArray[ticketIndex][1]][ticketArray[ticketIndex][0]] = 1;
		}

		int maxRowSum = 0;
		// Now get max sum of all rows
		for(int rowIndex = 0; rowIndex < dimension; rowIndex++) {
			int curRowSum = 0;
			for(int columnIndex = 0; columnIndex < dimension; columnIndex++) {
				curRowSum += seatMatrix[rowIndex][columnIndex];	
			}
			if(curRowSum > maxRowSum) {
				maxRowSum = curRowSum;
			}
		}
		return maxRowSum;
	} 
 
	public static void main(String [] args) throws IOException {
		Scanner in = new Scanner(new File(args[0]));
		PrintStream fileOut = new PrintStream(new File(args[1]));

		int testCaseSize = in.nextInt();
		int [] resultArray = new int[testCaseSize + 1];

		for(int curTestIndex = 1; curTestIndex <= testCaseSize; curTestIndex++) {

			int friendSize = in.nextInt();
			int dimension = in.nextInt();
			int [][] ticketArray = new ticketArray[friendSize][2];

			for(int friendIndex = 0; friendIndex < friendSize; friendIndex++) {
				int row = in.nextInt() - 1;
				int column = in.nextInt() - 1;
				ticketArray[friendIndex][0] = row;
				ticketArray[friendIndex][1] = column;
			}

			// Call the function to count max possible friends in row
			resultArray[curTestIndex] = countMaxFriendsInRow(ticketArray, dimension);
		}
		in.close();

		System.setOut(fileOut);
		for(int curTestIndex = 1; curTestIndex <= testCaseSize; curTestIndex++) {	
			System.out.println("Case #" + curTestIndex+ ": "+resultArray[curTestIndex]);
		}
		fileOut.close();
	}
}		