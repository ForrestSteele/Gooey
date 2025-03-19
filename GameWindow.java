
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * Provides methods to construct and manipulate the main game window. 
 *
 * @author Forrest Steele, 
 * @version March 2025
 */
public class GameWindow implements ActionListener, Runnable
{
    private String targetWord;
    private String guessWord;
    
    JFrame frame;
    JPanel mainPanel;
    JTextField letterField;
    
    public GameWindow() {
        int frameWidth = 700;
        frame = new JFrame();
        frame.setSize(frameWidth, (int) (frameWidth * 1.5));
        frame.setLocation(100,100);
        frame.setTitle("Window Title");
        frame.setResizable(false);
        
        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        frame.setContentPane(mainPanel);

        // Setup text fields and labels
        Font font = new Font(Font.MONOSPACED, Font.PLAIN, 14);
        
        int margin = 100;
        int boxSize = (frameWidth - margin * 2)/5;
        // Set up all text boxes
        for (int column = 0; column < 5; column++) {
            for (int row = 0; row < 6; row++) {
                letterField = new JTextField();
                letterField.setLocation(margin + column*boxSize, margin + row*boxSize);
                letterField.setSize(boxSize, boxSize);
                mainPanel.add(letterField);
            }
        }
        
        //firstRowOfLetters
        for (int letter = 0; letter < 10; letter++) {
            int letterBoxSize = 50;
            int letterMargin = (frameWidth - letterBoxSize * 10)/2;
            letterField = new JTextField();
            letterField.setLocation(margin + letter*letterBoxSize, margin + boxSize * 6 + 20);
            letterField.setSize(letterBoxSize, letterBoxSize);
            mainPanel.add(letterField);
        }
        
        //secondRowOfLetters
        for (int letter = 0; letter < 9; letter++) {
            int letterBoxSize = 50;
            int letterMargin = (frameWidth - letterBoxSize * 9)/2;
            letterField = new JTextField();
            letterField.setLocation(margin + letter*letterBoxSize, margin + boxSize * 6 + 20 + letterBoxSize);
            letterField.setSize(letterBoxSize, letterBoxSize);
            mainPanel.add(letterField);
        }
        
        //thirdRowOfLetters
        for (int letter = 0; letter < 8; letter++) {
            int letterBoxSize = 50;
            int letterMargin = (frameWidth - letterBoxSize * 8)/2;
            letterField = new JTextField();
            letterField.setLocation(margin + letter*letterBoxSize, margin + boxSize * 6 + 20 + letterBoxSize * 2);
            letterField.setSize(letterBoxSize, letterBoxSize);
            mainPanel.add(letterField);
        }
        
        frame.setVisible(true);
    }
    
    public void run() {
        
    }
    
    public void actionPerformed(ActionEvent event) {
        
    }
    
    public static void start() {
        SwingUtilities.invokeLater(new GameWindow());
    }
}
