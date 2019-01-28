public class Sudoku{
	
	public static void main(String[] args){

		int[][] sudoku1 = {{4, 1, 0, 0, 6, 5, 0, 0, 7},
				  		   {0, 0, 6, 0, 0, 7, 4, 8, 0},
				  		   {2, 0, 7, 4, 9, 0, 0, 0, 6},
				  		   {0, 6, 0, 0, 7, 0, 1, 0, 0},
				  		   {3, 0, 1, 5, 0, 0, 0, 7, 2},
				  		   {0, 9, 0, 0, 4, 2, 3, 0, 8},
				  		   {1, 0, 8, 6, 0, 0, 0, 2, 9},
				  		   {0, 2, 0, 0, 1, 8, 6, 4, 0},
				  		   {6, 0, 0, 3, 0, 0, 0, 1, 0}};

		int[][] sudoku2 = {{0, 2, 0, 0, 0, 0, 0, 4, 3},
				  		   {0, 5, 0, 3, 0, 7, 6, 0, 0},
				  		   {0, 0, 6, 0, 2, 0, 0, 0, 0},
				  		   {0, 0, 3, 0, 4, 8, 0, 9, 0},
				  		   {0, 0, 0, 0, 6, 0, 0, 0, 0},
				  		   {0, 9, 0, 1, 5, 0, 2, 0, 0},
				  		   {0, 0, 0, 0, 1, 0, 3, 0, 0},
				  		   {0, 0, 8, 5, 0, 6, 0, 1, 0},
				  		   {7, 1, 0, 0, 0, 0, 0, 5, 0}};

		int[][] sudoku3 = {{0, 5, 0, 4, 0, 8, 0, 0, 0},
				  		   {7, 0, 0, 0, 1, 2, 0, 9, 0},
				  		   {0, 9, 0, 0, 3, 0, 0, 0, 4},
				  		   {0, 6, 2, 0, 0, 9, 1, 0, 0},
				  		   {1, 8, 0, 0, 0, 3, 0, 0, 0},
				  		   {9, 0, 7, 0, 6, 4, 0, 0, 0},
				  		   {0, 0, 0, 0, 0, 0, 2, 0, 0},
				  		   {0, 0, 6, 0, 0, 0, 0, 0, 0},
				  		   {4, 0, 0, 6, 0, 0, 0, 5, 7}};

		viewSudoku(solveSudoku(sudoku1));
		viewSudoku(solveSudoku(sudoku2));
		viewSudoku(solveSudoku(sudoku3));
		System.out.println();
	}

	public static int[][] solveSudoku(int[][] sudoku){

		if (solveSudokuStep(sudoku)) {
			return sudoku;
		}else{
			throw new IllegalArgumentException("Das Sudoku ist nicht lösbar!");
		}
	}

	private static boolean solveSudokuStep(int[][] sudoku){

		/*
		if (isSolved(sudoku)) {
			return true;
		}
		*/

		for (int i = 0; i < sudoku.length; i++) {
			for (int j = 0; j < sudoku[i].length; j++) {
				
				if (sudoku[i][j] == 0) {
					for (int number = 1; number <= 9; number++) {
						if (isValidMove(i, j, number, sudoku)) {
							int buffer = sudoku[i][j];
							sudoku[i][j] = number;

							/* 
							// wird eigentlich nicht benoetigt
							if (isSolved(sudoku)) {
								return true;
							}
							*/

							if(solveSudokuStep(sudoku)){
								return true;
							}
							sudoku[i][j] = buffer;
						}	//sudoku[i][j] = 0;
					}
					// gibt false zurueck, wenn keine passende Zahl gefunden wird
					return false;
				}
			}
		}
		// gibt true zurueck, wenn alle Zeilen und Spalten durchlaufen wurden, und keine 0 mehr gefunden wurde -> Raetsel geloest
		return true;
	}

	private static boolean isValidMove(int row, int col, int number, int[][] sudoku){

		// Test der Zeilen und Spalten
		for (int i = 0; i < sudoku.length; i++) {
			if (sudoku[row][i] == number) {
				return false;
			}
			if (sudoku[i][col] == number) {
				return false;
			}
		}

		// Test der Kaestchen
		for (int i = row - (row % 3), k = 0; k < 3; i++, k++ ) {
			for (int j = col - (col % 3), l = 0; l < 3; j++, l++ ) {
				if (sudoku[i][j] == number) {
					return false;
				}
			}
		}
		return true;

	}
	
	private static boolean isSolved(int[][]sudoku){
		// wird eigentlich nicht benoetigt

		for (int i = 0; i < sudoku.length; i++) {
			for (int j = 0; j < sudoku[0].length; j++) {
				if (sudoku[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
	}

	public static void viewSudoku(int[][] sudoku){

		System.out.println();
		for (int row = 0; row < sudoku.length; row++) {

			String actualRow = "";
			for (int col = 0; col < sudoku[row].length; col++) {
				actualRow += " " + sudoku[row][col];
			}
			System.out.println(actualRow);
		}
		
	}

}