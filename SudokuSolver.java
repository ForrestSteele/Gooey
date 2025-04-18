//345678901234567890123456789012345678901234567890123456789012345678901234567890012345678901234567890

/**
 * Provides methods to create a solve a Sudoku board. 
 *
 * @author Forrest Steele
 * @version April 2025
 */
public class SudokuSolver
{
    // instance variables - replace the example below with your own
    private int[][] board;
    private int[][] completeBoard;
    private MyGraphMap boardGraph;

    public SudokuSolver() {
        board = new int[9][9];
        // completeBoard = {
            // {5, 3, 4, 6, 7, 8, 9, 1, 2},  
            // {6, 7, 2, 1, 9, 5, 3, 4, 8},  
            // {1, 9, 8, 3, 4, 2, 5, 6, 7},  
            // {8, 5, 9, 7, 6, 1, 4, 2, 3},  
            // {4, 2, 6, 8, 5, 3, 7, 9, 1},  
            // {7, 1, 3, 9, 2, 4, 8, 5, 6},  
            // {9, 6, 1, 5, 3, 7, 2, 8, 4},  
            // {2, 8, 7, 4, 1, 9, 6, 3, 5},  
            // {3, 4, 5, 2, 8, 6, 1, 7, 9}
        // };
        boardGraph = new MyGraphMap();
    }
    
    public void printBoard() {
        for (int row = 0; row < 10; row++) {
            String line = "";
            for (int col = 0; col < 10; col++) {
                if (col == 2 || col == 5) {
                    line += " " + board[row][col] + " | ";
                }
                line += " " + board[row][col] + " ";
            }
            System.out.println(line);
            if (row == 2 || row == 5) {
                System.out.println("------------------------------------------");
            }
        }
        
    }
    
    public void createGraph() {
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                String position = row + ", " + col;
                boardGraph.addVertex(position, String.valueOf(board[row][col]));
            }
        }
    }
    
    public boolean validateBoard() {
        boolean response = false;
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                String position = row + ", " + col;
                if (boardGraph.noAdjDups(position) == true) {
                    response = true;
                }
            }
        }
        return response;
    }
    
    private void solver() {
        
    }
}
