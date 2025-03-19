
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
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.Box;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

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
        mainPanel.setBackground(Color.WHITE);
        
        Color custom = new Color(232, 232, 232);

        // Setup text fields and labels
        
        ArrayList<JTextField> letterFields = new ArrayList<JTextField>();
        Border border = BorderFactory.createLineBorder(custom, 2);
        Font guessFont = new Font(Font.SANS_SERIF, Font.PLAIN, 20);
        
        int boxSize = 70;
        
        int marginBetween = 8;
        int marginVertical = 20;
        int marginHorizontal = (frameWidth - (5 * (boxSize + marginBetween)))/2;
        // Set up all text boxes
        for (int row = 0; row < 6; row++) {
            for (int column = 0; column < 5; column++) {
                letterFields.add(new JTextField());
                JTextField letterField = letterFields.get((row * 5) + column);
                letterField.setLocation(marginHorizontal + column*(boxSize + marginBetween), marginVertical + row*(boxSize + marginBetween));
                letterField.setSize(boxSize, boxSize);
                letterField.setBorder(border);
                letterField.setHorizontalAlignment(JTextField.CENTER);
                letterField.setFont(guessFont);
                letterField.setEditable(false);
                letterField.setText("A");
                letterField.setBackground(Color.WHITE);
                mainPanel.add(letterField);
            }
        }
        
        ArrayList<JButton> keyboardButtons = new ArrayList<JButton>();
        String keyboardKeys = "QWERTYUIOPASDFGHJKL@ZXCVBNM&";
        Border keyboardBorder = BorderFactory.createLineBorder(custom, 0);
        Font keyboardFont = new Font(Font.SANS_SERIF, Font.BOLD, 20);
        
        //firstRowOfLetters
        for (int letter = 0; letter < 10; letter++) {
            int letterBoxSize = 50;
            int letterMargin = (frameWidth - (letterBoxSize + marginBetween) * 10)/2;
            keyboardButtons.add(new JButton(keyboardKeys.substring(letter, letter + 1)));
            JButton keyboardButton = keyboardButtons.get(letter);
            keyboardButton.setLocation(letterMargin + letter * (letterBoxSize + marginBetween), marginVertical + (boxSize + marginBetween) * 6 + 20);
            keyboardButton.setSize(letterBoxSize, letterBoxSize);
            keyboardButton.setBackground(custom);
            keyboardButton.setBorder(keyboardBorder);
            keyboardButton.setFont(keyboardFont);
            mainPanel.add(keyboardButton);
        }
        
        //secondRowOfLetters
        for (int letter = 0; letter < 9; letter++) {
            int letterBoxSize = 50;
            int letterMargin = (frameWidth - (letterBoxSize + marginBetween) * 9)/2;
            keyboardButtons.add(new JButton(keyboardKeys.substring(10 + letter, 10 + letter + 1)));
            JButton keyboardButton = keyboardButtons.get(10 + letter);
            keyboardButton.setLocation(letterMargin + letter * (letterBoxSize + marginBetween), marginVertical + (boxSize + marginBetween) * 6 + 20 + (letterBoxSize + marginBetween));
            keyboardButton.setSize(letterBoxSize, letterBoxSize);
            keyboardButton.setBackground(custom);
            keyboardButton.setBorder(keyboardBorder);
            keyboardButton.setFont(keyboardFont);
            mainPanel.add(keyboardButton);
        }
        
        //thirdRowOfLetters
        for (int letter = 0; letter < 9; letter++) {
            int letterBoxSize = 50;
            int letterMargin = (frameWidth - (letterBoxSize + marginBetween) * 9)/2;
            keyboardButtons.add(new JButton(keyboardKeys.substring(19 + letter, 19 + letter + 1)));
            JButton keyboardButton = keyboardButtons.get(19 + letter);
            keyboardButton.setLocation(letterMargin + letter * (letterBoxSize + marginBetween), marginVertical + (boxSize + marginBetween) * 6 + 20 + (letterBoxSize + marginBetween) * 2);
            keyboardButton.setSize(letterBoxSize, letterBoxSize);
            keyboardButton.setBackground(custom);
            keyboardButton.setBorder(keyboardBorder);
            keyboardButton.setFont(keyboardFont);
            mainPanel.add(keyboardButton);
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
