
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
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

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
    private ArrayList<JLabel> letterLabels;
    private ArrayList<JButton> keyboardButtons;
    
    private ArrayList<Integer> keys;

    JFrame frame;
    JPanel mainPanel;
    
    private int currentRow;
    private int currentColumn;
    
    public GameWindow() {
        letterLabels = new ArrayList<JLabel>();
        keyboardButtons = new ArrayList<JButton>();
        
        keys = new ArrayList<Integer>();
        
        currentRow = 0;
        currentColumn = 0;
        
        targetWord = "tango";
    }
    
    public void run() {
        int frameWidth = 700;
        frame = new JFrame();
        frame.setSize(frameWidth, (int) (frameWidth * 1.2));
        frame.setLocation(100, 100);
        frame.setTitle("Window Title");
        frame.setResizable(false);
        
        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        frame.setContentPane(mainPanel);
        mainPanel.setBackground(Color.WHITE);
        
        JLabel specialText = new JLabel();
        specialText.setText("© 2025 The New York Times Company | NYTimes.com | Sitemap | Privacy Policy | Terms of Service | Terms of Sale | California Notices");
        specialText.setLocation(110, 570);
        specialText.setSize(500, 40);
        Font specialFont = new Font(Font.SANS_SERIF, Font.BOLD, 8);
        specialText.setFont(specialFont);
        mainPanel.add(specialText);
        
        Color custom = new Color(232, 232, 232);

        // Setup text fields and labels
        
        Border border = BorderFactory.createLineBorder(custom, 2);
        Font guessFont = new Font(Font.SANS_SERIF, Font.PLAIN, 20);
        
        int boxSize = 50;
        
        int marginBetween = 6;
        int marginVertical = 40;
        int marginHorizontal = (frameWidth - (5 * (boxSize + marginBetween)))/2;
        // Set up all text boxes
        for (int row = 0; row < 6; row++) {
            for (int column = 0; column < 5; column++) {
                letterLabels.add(new JLabel());
                JLabel letterLabel = letterLabels.get((row * 5) + column);
                letterLabel.setLocation(marginHorizontal + column*(boxSize + marginBetween), marginVertical + row*(boxSize + marginBetween));
                letterLabel.setSize(boxSize, boxSize);
                letterLabel.setBorder(border);
                letterLabel.setHorizontalAlignment(JTextField.CENTER);
                letterLabel.setFont(guessFont);
                letterLabel.setBackground(Color.WHITE);
                mainPanel.add(letterLabel);
            }
        }
        
        String keyboardKeys = "QWERTYUIOPASDFGHJKL@ZXCVBNM&";
        Border keyboardBorder = BorderFactory.createLineBorder(custom, 1);
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
    
    public void actionPerformed(ActionEvent event) {
        //for displayed buttons
        JButton buttonPressed = (JButton) event.getSource();
        if (buttonPressed.getText() == "@") {
            if (currentColumn != 0) {
                currentColumn--;
                keys.remove(keys.size() - 1);
            }
        } else if (buttonPressed.getText() == "&") {
            if (currentColumn == 4) {
                currentRow += 1;
                currentColumn = 0;
                colorTime();
            }
        } else {
            JLabel letterLabel = letterLabels.get(currentRow * 5 + currentColumn);
            letterLabel.setText(String.valueOf(buttonPressed.getText()));
            currentColumn++;
        }
    }
    
    public void keyPressed(KeyEvent e) {
        JLabel label = new JLabel();
        label.setSize(50, 50);
        label.setSize(50, 200);
        label.setText(String.valueOf(e.getKeyChar()));
        mainPanel.add(label);
        
        if (e.getKeyChar() == KeyEvent.CHAR_UNDEFINED) {
            if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                if (currentColumn != 0) {
                    currentColumn--;
                    keys.remove(keys.size() - 1);
                }
            } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                if (currentColumn == 4) {
                    currentRow += 1;
                    currentColumn = 0;
                    colorTime();
                }
            }
        } else {
            JLabel letterLabel = letterLabels.get(currentRow * 5 + currentColumn);
            letterLabel.setText(String.valueOf(e.getKeyChar()));
            currentColumn++;
        }
    }
    
    public void colorTime() {
        ArrayList<String> lettersRemaining = new ArrayList<String>();
        ArrayList<String> targetWordList = new ArrayList<String>();
        ArrayList<String> guessWordList = new ArrayList<String>();
        //lettersRemaining = targetWord;
        
        for (int i = 0; i < 4; i++) {
            JLabel current = letterLabels.get(currentRow * 5 + i);
            lettersRemaining.add(current.getText());
            guessWordList.add(current.getText());
        }
        
        for (int i = 0; i < 4; i++) {
            targetWordList.add(targetWord.substring(i, i + 1));
        }
        
        int currIndex = 0;
        while (lettersRemaining.size() > 0) {
            String currLetter = lettersRemaining.get(currIndex);
            
            int targetIndex = targetWordList.indexOf(currLetter);
            int guessIndex = guessWordList.indexOf(currLetter);
            
            Color color = Color.DARK_GRAY;
            
            if (targetIndex != -1) {
                if (targetIndex == guessIndex) {
                    color = Color.GREEN;
                } else {
                    color = Color.YELLOW;
                }
            }
            //fill out more cases
            
            letterLabels.get(currentRow * 5 + currIndex).setBackground(color);
        }
    }
    
    public static void start() {
        SwingUtilities.invokeLater(new GameWindow());
    }
}
