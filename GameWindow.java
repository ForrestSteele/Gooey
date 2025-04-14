
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
import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Provides methods to construct and manipulate the main game window. 
 *
 * @author Forrest Steele, 
 * @version March 2025
 */
public class GameWindow implements ActionListener, Runnable, KeyListener
{
    private String targetWord;
    private String guessWord;
    private ArrayList<JLabel> letterLabels;
    private ArrayList<JButton> keyboardButtons;
    private Color yellow;
    private Color green;
    private Color darkGray;
    private Color lightGray;
    private String target;
    
    private String currentWord;
    
    private ArrayList<String> guessWords;
    
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
        
        target = "TANGO";
        
        yellow = new Color(219, 195, 78);
        green = new Color(89, 192, 63);
        darkGray = new Color(128, 128, 128);
        lightGray = new Color(232, 232, 232);
        
        ArrayList<String> targetWords = new ArrayList<>();
        
        try {
            FileReader fileReader = new FileReader("wordle-game-words.txt");
        
            BufferedReader reader = new BufferedReader(fileReader);  
        
            String line = reader.readLine();
          
            while ((line = reader.readLine()) != null) {  
                targetWords.add(line.toUpperCase());
            }
        } catch (Exception E) {}
        
        int randomIndex = (int) (Math.random() * targetWords.size()) + 1;
        
        targetWord = targetWords.get(randomIndex);
        
        
        guessWords = new ArrayList<>();
        
        try {
            FileReader fileReader = new FileReader("wordle-game-words.txt");
        
            BufferedReader reader = new BufferedReader(fileReader);  
        
            String line = reader.readLine();
          
            while ((line = reader.readLine()) != null) {  
                guessWords.add(line.toUpperCase());
            }
        } catch (Exception E) {}
        
        currentWord = "";
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
        
        // Setup text fields and labels
        
        Border border = BorderFactory.createLineBorder(lightGray, 2);
        Font guessFont = new Font(Font.SANS_SERIF, Font.BOLD, 25);
        
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
                letterLabel.setOpaque(true);
                letterLabel.setBackground(Color.WHITE);
                mainPanel.add(letterLabel);
            }
        }
        
        String keyboardKeys = "QWERTYUIOPASDFGHJKL@ZXCVBNM&";
        Border keyboardBorder = BorderFactory.createLineBorder(lightGray, 1);
        Font keyboardFont = new Font(Font.SANS_SERIF, Font.BOLD, 20);
        
        //firstRowOfLetters
        for (int letter = 0; letter < 10; letter++) {
            int letterBoxSize = 50;
            int letterMargin = (frameWidth - (letterBoxSize + marginBetween) * 10)/2;
            keyboardButtons.add(new JButton(keyboardKeys.substring(letter, letter + 1)));
            JButton keyboardButton = keyboardButtons.get(letter);
            keyboardButton.setLocation(letterMargin + letter * (letterBoxSize + marginBetween), marginVertical + (boxSize + marginBetween) * 6 + 20);
            keyboardButton.setSize(letterBoxSize, letterBoxSize);
            keyboardButton.setBackground(lightGray);
            keyboardButton.setBorder(keyboardBorder);
            keyboardButton.setFont(keyboardFont);
            keyboardButton.addActionListener(this);
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
            keyboardButton.setBackground(lightGray);
            keyboardButton.setBorder(keyboardBorder);
            keyboardButton.setFont(keyboardFont);
            keyboardButton.addActionListener(this);
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
            keyboardButton.setBackground(lightGray);
            keyboardButton.setBorder(keyboardBorder);
            keyboardButton.setFont(keyboardFont);
            keyboardButton.addActionListener(this);
            mainPanel.add(keyboardButton);
        }
        
        frame.setVisible(true);
        
        mainPanel.addKeyListener(this);
        mainPanel.setFocusable(true);
        mainPanel.requestFocusInWindow();
    }
    
    public void actionPerformed(ActionEvent event) {
        //for displayed buttons
        Border typedBorder = BorderFactory.createLineBorder(darkGray, 2);
        Border untypedBorder = BorderFactory.createLineBorder(lightGray, 2);
        
        JButton buttonPressed = (JButton) event.getSource();
        if (buttonPressed.getText().equals("&")) {
            if (currentColumn != 0) {
                currentWord = currentWord.substring(0, currentColumn - 1);
                currentColumn--;
                JLabel letterLabel = letterLabels.get(currentRow * 5 + currentColumn);
                letterLabel.setText("");
                letterLabel.setBorder(untypedBorder);
                keys.remove(keys.size() - 1); //idk why I wrote this
            }
        } else if (buttonPressed.getText().equals("@")) {
            if (currentColumn == 5) {
                System.out.println(currentWord);
                if (guessWords.contains(currentWord)) {
                    colorTime();
                
                    currentRow += 1;
                    currentColumn = 0;
                    
                    currentWord = "";
                    
                }
            }
        } else {
            if (currentColumn < 5) {
                JLabel letterLabel = letterLabels.get(currentRow * 5 + currentColumn);
                letterLabel.setText(String.valueOf(buttonPressed.getText()));
                letterLabel.setBorder(typedBorder);
                currentColumn++;
                
                currentWord += buttonPressed.getText();
            }
        }
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        JLabel label = new JLabel();
        label.setSize(50, 50);
        label.setSize(50, 200);
        label.setText(String.valueOf(e.getKeyChar()));
        mainPanel.add(label);
        System.out.println("poop");
        
        if (e.getKeyChar() == KeyEvent.CHAR_UNDEFINED) {
            if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                if (currentColumn != 0) {
                    currentColumn--;
                    keys.remove(keys.size() - 1);
                }
            } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                if (currentColumn == 4) {
                    colorTime();
                    
                    currentColumn = 0;
                    currentRow += 1;
                }
            }
        } else {
            JLabel letterLabel = letterLabels.get(currentRow * 5 + currentColumn);
            letterLabel.setText(String.valueOf(e.getKeyChar()));
            currentColumn++;
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }
    
    public void colorTime() {
        ArrayList<String> lettersRemaining = new ArrayList<String>();
        ArrayList<String> targetWordList = new ArrayList<String>();
        ArrayList<String> guessWordList = new ArrayList<String>();
        //lettersRemaining = targetWord;
        
        Border processedBorder = BorderFactory.createLineBorder(Color.WHITE, 0);
                
        for (int i = 0; i < 5; i++) {
            JLabel current = letterLabels.get(currentRow * 5 + i);
            guessWordList.add(current.getText());
        }
        
        for (int i = 0; i < 5; i++) {
            targetWordList.add(targetWord.substring(i, i + 1));
            lettersRemaining.add(targetWord.substring(i, i + 1));
        }
        
        for (int i = 0; i < 5; i++) {
            String currLetter = guessWordList.get(i);
            
            int targetIndex = targetWordList.indexOf(currLetter);
            int guessIndex = guessWordList.indexOf(currLetter);
            
            Color color = darkGray;
            
            if (targetIndex != -1) {
                if (targetIndex == guessIndex) {
                    color = green;
                    lettersRemaining.remove(currLetter);
                } else {
                    if (lettersRemaining.contains(currLetter)) {

                        color = yellow;
                        lettersRemaining.remove(currLetter);
                    }
                }
            }
            
            JLabel letterLabel = letterLabels.get(currentRow * 5 + i);
            letterLabel.setBackground(color);
            letterLabel.setForeground(Color.WHITE);
            letterLabel.setBorder(processedBorder);
            
        }
        
        // int currIndex = 0;
        // while (lettersRemaining.size() > 0) {
            // String currLetter = lettersRemaining.get(currIndex);
            
            // int targetIndex = targetWordList.indexOf(currLetter);
            // int guessIndex = guessWordList.indexOf(currLetter);
            
            // Color color = Color.DARK_GRAY;
            
            // if (targetIndex != -1) {
                // if (targetIndex == guessIndex) {
                    // color = Color.GREEN;
                // } else {
                    // color = Color.YELLOW;
                // }
            // }
            // //fill out more cases
            
            // letterLabels.get(currentRow * 5 + currIndex).setBackground(color);
        // }
    }
    
    public static void start() {
        SwingUtilities.invokeLater(new GameWindow());
    }
}
