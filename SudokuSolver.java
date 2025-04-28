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
        board = new int[][] {{5, 3, 0, 0, 7, 0, 0, 0, 0},  
            {6, 0, 0, 1, 9, 5, 0, 0, 0},  
            {0, 9, 8, 0, 0, 0, 0, 6, 0},  
            {8, 0, 0, 0, 6, 0, 0, 0, 3},  
            {4, 0, 0, 8, 0, 3, 0, 0, 1},  
            {7, 0, 0, 0, 2, 0, 0, 0, 6},  
            {0, 6, 0, 0, 0, 0, 2, 8, 0},  
            {0, 0, 0, 4, 1, 9, 0, 0, 5},  
            {0, 0, 0, 0, 8, 0, 0, 7, 9}};

        boardGraph = new MyGraphMap();
    }
    
    public void printBoard() {
        for (int row = 0; row < 9; row++) {
            String line = "";
            for (int col = 0; col < 9; col++) {
                if (col == 2 || col == 5) {
                    line += " " + board[row][col] + "  | ";
                } else {
                    line += " " + board[row][col] + " ";
                }
            }
            System.out.println(line);
            if (row == 2 || row == 5) {
                System.out.println("------------------------------------------");
            }
        }
    }
    
    public void createGraph() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                String position = row + ", " + col;
                boardGraph.addVertex(position, String.valueOf(board[row][col]));
                for (int i = 0; i < col; i++) {
                    boardGraph.addEdge(row + ", " + i, position);
                }
            }
        }
        for (int col = 0; col < 9; col++) {
            for (int row = 0; row < 9; row++) {
                String position = row + ", " + col;
                for (int j = 0; j < row; j++) {
                    boardGraph.addEdge(j + ", " + col, position);
                }
            }
        }
    }
    
    public boolean validateBoard() {
        boolean response = false;
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                String position = row + ", " + col;
                if (boardGraph.noAdjDups(position) == true) {
                    response = true;
                }
            }
        }
        return response;
    }
    
    public void solve() {
        solver(0,0);
    }
    
    private boolean solver(int row, int col) {
        int nextRow;
        int nextCol;
        
        if (col == 8) {
            nextCol = 0;
            nextRow = row + 1;
        } else {
            nextCol = col + 1;
            nextRow = row;
        }
        
        if (row == 9) { //this means the previous call worked, and the next row
            //was passed in as 9
            return true;
        }
        
        if (board[row][col] != 0) {
            solver(nextRow, nextCol);
        } else {
            for (int num = 1; num < 10; num++) {
                board[row][col] = num;
                boardGraph.setVertex(row + ", " + col, String.valueOf(num));
                if (boardGraph.noAdjDups(row + ", " + col)) {
                    if (solver(nextRow, nextCol)) {
                        return true; //passes true, if the next one is true
                    }
                }
            }
            board[row][col] = 0; //if it loops through without finishing the 
            //entire board, then that means that this current one is incorrect, 
            //therefore, this one must be reset to 0, and also return false, 
            //so the previous one will not finish its loop
            boardGraph.setVertex(row + ", " + col, String.valueOf(0));
            return false;
        }
        return true;
    }
    
    public void start() {
        printBoard();
        createGraph();
        solve();
        System.out.println();
        printBoard();
        System.out.println();
        System.out.println();
    }
}
