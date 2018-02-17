import java.util.*;
import java.io.*;

public class WordSearch {
	public static final int _MAX_MATRIX_SIZE = 15;

	public static final char _FIRST = 'I';
	public static final char _CONNECTOR = '/';
	public static final char _LAST = 'O';

	// Each connector at the ends connect once, and the middle ones connect 3 times (vertically, and both diagonals)
	public static final int _MAX_CONNECTORS_IN_A_ROW = 1 + 1 + (_MAX_MATRIX_SIZE - 2) * 3 ; 


	public static char [][] printMatrix(int occurence){
		// First decide number of rows and columns
		
		//Base case if occurence is 0
		if(occurence == 0) {
			char[][] baseMatrix = { {_FIRST, _LAST, _CONNECTOR} };
			return baseMatrix;
		}

		int connectorRows = 1 + (occurence - 1)/_MAX_CONNECTORS_IN_A_ROW ;
		int maxRows = connectorRows * 2 + 1;
		int maxColumns = 15;
		int occurenceConnectorValue = occurence % _MAX_CONNECTORS_IN_A_ROW;
		if(occurenceConnectorValue == 0) {
			occurenceConnectorValue = _MAX_CONNECTORS_IN_A_ROW;
		}
		int connectorColumns = 2 + (occurenceConnectorValue / 3);

		if(occurence <= _MAX_CONNECTORS_IN_A_ROW) {
			maxColumns = connectorColumns;	
		}	
		
		
		boolean isFirstConnector = (occurenceConnectorValue % 3) > 0;
		boolean isLastConnector = (occurenceConnectorValue % 3) > 1;

		char [][] outputMatrix = new char[maxRows][maxColumns];

		for(int row = 0; row < maxRows; row++) {
			for(int column = 0; column < maxColumns; column++) {
				if(row % 2 == 0) {
					if(row % 4 == 0) {  // Fill with IOIOs
						if(column % 2 == 0) { //Even columns have Is
							outputMatrix[row][column] = _FIRST;
						} else {
							outputMatrix[row][column] = _LAST;
						}
					} else { // Fill with OIOIs
						if(column % 2 == 0) { //Even columns have Is
							outputMatrix[row][column] = _LAST;
						} else {
							outputMatrix[row][column] = _FIRST;
						}
					}
				}
				else {
					//If you are in last row, then fill only upto connectorColumns
					if(row == maxRows - 2) {
						if(column == 0) {
							if(isFirstConnector) {
								outputMatrix[row][column] = _CONNECTOR;
							} else {
								outputMatrix[row][column] = _LAST;
							}
						} else if(column == maxColumns - 1) {
							if(isLastConnector) {
								outputMatrix[row][column] = _CONNECTOR;
							} else {
								outputMatrix[row][column] = _LAST;
							}
						} else if(column <= connectorColumns - 2) { //Number of connector columns not including first column
							outputMatrix[row][column] = _CONNECTOR;	
						} else {
							outputMatrix[row][column] = _LAST;
						}	
					} else {
						outputMatrix[row][column] = _CONNECTOR;	
					}					
				}
			}
		}

		
		return outputMatrix;		
	}
 
	public static void main(String [] args) throws IOException {
		Scanner in = new Scanner(new File(args[0]));
		PrintStream fileOut = new PrintStream(new File(args[1]));

		//Read input from scanner
		int testCaseSize = in.nextInt();
		char [][][] resultArray = new char [testCaseSize + 1][_MAX_MATRIX_SIZE][_MAX_MATRIX_SIZE];

		for(int curTestIndex = 1; curTestIndex <= testCaseSize; curTestIndex++) {

			//Get input for each test case
			int biggestMatrixSize = in.nextInt(); //Since I will be using a 15x15 matrix, I will ignore this input
			int occurences = in.nextInt();

			// Call the function for each test case
			 resultArray[curTestIndex] = printMatrix(occurences);

		}
		in.close();

		System.setOut(fileOut);
		//Print to output stream
		for(int curTestIndex = 1; curTestIndex <= testCaseSize; curTestIndex++) {	
			System.out.println("Case #" + curTestIndex+ ":");
			char [][] matrixToPrint = resultArray[curTestIndex]; // is a 2d matrix
			for(int row = 0; row < matrixToPrint.length; row++) {
				for(int column = 0; column < matrixToPrint[row].length; column++) {
					System.out.print(matrixToPrint[row][column]);	
				}
				System.out.println();
			}
		}
		fileOut.close();
	}
}		