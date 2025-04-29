
/**
 * Write a description of class SudokuSolverTest here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SudokuSolverTest
{
    public static void start() {
        SudokuSolver sudoku = new SudokuSolver();
        sudoku.start();
    }
    
    public static void testValidation() {
        SudokuSolver sudoku = new SudokuSolver();
        sudoku.createGraph();
        System.out.println(sudoku.validateBoard());
    }
}
