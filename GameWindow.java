
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
import javax.swing.SwingConstants;

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
    private ArrayList<JButton> letterButtons;
    private Color yellow;
    private Color green;
    private Color darkGray;
    private Color lightGray;
    private String target;
    
    private String currentWord;
    
    private ArrayList<String> guessWords;
    
    private String letters;
    
    private JLabel invalidGuessLabel;
    private JLabel loseLabel;
    private JLabel winLabel;
    
    private JLabel startButton;
    
    private Font popUpsFont;
    
    private Border typedBorder;
    private Border untypedBorder;
    
    JFrame frame;
    JPanel mainPanel;
    
    private int currentRow;
    private int currentColumn;
    
    public GameWindow() {
        letterLabels = new ArrayList<JLabel>();
        letterButtons = new ArrayList<JButton>();
        
        currentRow = 0;
        currentColumn = 0;
        
        target = "TANGO";
        
        yellow = new Color(219, 195, 78);
        green = new Color(89, 192, 63);
        darkGray = new Color(128, 128, 128);
        lightGray = new Color(232, 232, 232);
        
        letters = "QWERTYUIOPASDFGHJKLZXCVBNM";
        
        popUpsFont = new Font(Font.SANS_SERIF, Font.BOLD, 10);
        
        typedBorder = BorderFactory.createLineBorder(darkGray, 2);
        untypedBorder = BorderFactory.createLineBorder(lightGray, 2);
        
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
        
        int marginVertical = 100;
        
        JLabel specialText = new JLabel();
        specialText.setText("© 2025 The New York Times Company | NYTimes.com | Sitemap | Privacy Policy | Terms of Service | Terms of Sale | California Notices");
        specialText.setLocation(88, 530 + marginVertical);
        specialText.setSize(590, 40);
        Font specialFont = new Font(Font.DIALOG, Font.PLAIN, 8);
        specialText.setFont(specialFont);
        mainPanel.add(specialText);
        
        // PopUps for invalid guess word, win, and loss
        invalidGuessLabel = new JLabel();
        invalidGuessLabel.setLocation(300, 35);
        invalidGuessLabel.setSize(100, 35);
        invalidGuessLabel.setText("Not in word list");
        invalidGuessLabel.setBackground(Color.BLACK);
        invalidGuessLabel.setOpaque(true);
        invalidGuessLabel.setFont(popUpsFont);
        invalidGuessLabel.setForeground(Color.WHITE);
        invalidGuessLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        winLabel = new JLabel();
        winLabel.setLocation(300, 35);
        winLabel.setSize(100, 35);
        winLabel.setText("You win :)");
        winLabel.setBackground(Color.BLACK);
        winLabel.setOpaque(true);
        winLabel.setFont(popUpsFont);
        winLabel.setForeground(Color.WHITE);
        winLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        loseLabel = new JLabel();
        loseLabel.setLocation(300, 35);
        loseLabel.setSize(100, 35);
        loseLabel.setText("You lose :(");
        loseLabel.setBackground(Color.BLACK);
        loseLabel.setOpaque(true);
        loseLabel.setFont(popUpsFont);
        loseLabel.setForeground(Color.WHITE);
        loseLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        // Setup text fields and labels
        
        Border border = BorderFactory.createLineBorder(lightGray, 2);
        Font guessFont = new Font(Font.SANS_SERIF, Font.BOLD, 25);
        
        int boxSize = 50;
        
        int marginBetween = 6;
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
        
        Border keyboardBorder = BorderFactory.createLineBorder(lightGray, 0);
        Font keyboardFont = new Font(Font.SANS_SERIF, Font.BOLD, 20);
        
        //firstRowOfLetters
        for (int letter = 0; letter < 10; letter++) {
            int letterBoxSize = 50;
            int letterMargin = (frameWidth - (letterBoxSize + marginBetween) * 10)/2;
            letterButtons.add(new JButton(letters.substring(letter, letter + 1)));
            JButton letterButton = letterButtons.get(letter);
            letterButton.setLocation(letterMargin + letter * (letterBoxSize + marginBetween), marginVertical + (boxSize + marginBetween) * 6 + 20);
            letterButton.setSize(letterBoxSize, letterBoxSize);
            letterButton.setBackground(lightGray);
            letterButton.setOpaque(true);
            letterButton.setBorder(keyboardBorder);
            letterButton.setFont(keyboardFont);
            letterButton.addActionListener(this);
            mainPanel.add(letterButton);
        }
        
        //secondRowOfLetters
        for (int letter = 0; letter < 9; letter++) {
            int letterBoxSize = 50;
            int letterMargin = (frameWidth - (letterBoxSize + marginBetween) * 9)/2;
            letterButtons.add(new JButton(letters.substring(10 + letter, 10 + letter + 1)));
            JButton letterButton = letterButtons.get(10 + letter);
            letterButton.setLocation(letterMargin + letter * (letterBoxSize + marginBetween), marginVertical + (boxSize + marginBetween) * 6 + 20 + (letterBoxSize + marginBetween));
            letterButton.setSize(letterBoxSize, letterBoxSize);
            letterButton.setBackground(lightGray);
            letterButton.setOpaque(true);
            letterButton.setBorder(keyboardBorder);
            letterButton.setFont(keyboardFont);
            letterButton.addActionListener(this);
            mainPanel.add(letterButton);
        }
        
        //thirdRowOfLetters
        for (int letter = 0; letter < 7; letter++) {
            int letterBoxSize = 50;
            int letterMargin = (frameWidth - (letterBoxSize + marginBetween) * 9)/2;
            letterButtons.add(new JButton(letters.substring(19 + letter, 19 + letter + 1)));
            JButton letterButton = letterButtons.get(19 + letter);
            letterButton.setLocation(letterMargin + letterBoxSize + marginBetween + letter * (letterBoxSize + marginBetween), marginVertical + (boxSize + marginBetween) * 6 + 20 + (letterBoxSize + marginBetween) * 2);
            letterButton.setSize(letterBoxSize, letterBoxSize);
            letterButton.setBackground(lightGray);
            letterButton.setOpaque(true);
            letterButton.setBorder(keyboardBorder);
            letterButton.setFont(keyboardFont);
            letterButton.addActionListener(this);
            mainPanel.add(letterButton);
        }
        
        int commandsKeyWidth = 80; 
        Font commandsFont = new Font(Font.SANS_SERIF, Font.BOLD, 10);

        letterButtons.add(new JButton("ENTER"));
        JButton enterButton = letterButtons.get(26);
        enterButton.setLocation(68, marginVertical + (boxSize + marginBetween) * 6 + 20 + (50 + marginBetween) * 2);
        enterButton.setSize(80, 50);
        enterButton.setBackground(lightGray);
        enterButton.setOpaque(true);
        enterButton.setBorder(keyboardBorder);
        enterButton.setFont(commandsFont);
        enterButton.addActionListener(this);
        mainPanel.add(enterButton);
        
        letterButtons.add(new JButton("DELETE"));
        JButton deleteButton = letterButtons.get(27);
        deleteButton.setLocation(546, marginVertical + (boxSize + marginBetween) * 6 + 20 + (50 + marginBetween) * 2);
        deleteButton.setSize(80, 50);
        deleteButton.setBackground(lightGray);
        deleteButton.setOpaque(true);
        deleteButton.setBorder(keyboardBorder);
        deleteButton.setFont(commandsFont);
        deleteButton.addActionListener(this);
        mainPanel.add(deleteButton);
        
        frame.setVisible(true);
        
        mainPanel.addKeyListener(this);
        mainPanel.setFocusable(true);
        mainPanel.requestFocusInWindow();
        
    }
    
    public void actionPerformed(ActionEvent event) {
        JButton buttonPressed = (JButton) event.getSource();
        if (buttonPressed.getText().equals("DELETE")) {
            if (currentColumn != 0) {
                currentWord = currentWord.substring(0, currentColumn - 1);
                currentColumn--;
                JLabel letterLabel = letterLabels.get(currentRow * 5 + currentColumn);
                letterLabel.setText("");
                letterLabel.setBorder(untypedBorder);
                
                mainPanel.remove(invalidGuessLabel);
                mainPanel.revalidate();
                mainPanel.repaint();
            }
        } else if (buttonPressed.getText().equals("ENTER")) {
            if (currentColumn == 5) {
                System.out.println(currentWord);
                if (guessWords.contains(currentWord)) {
                    colorTime();
                
                    currentRow += 1;
                    currentColumn = 0;
                    
                    currentWord = "";
                } else {
                    mainPanel.add(invalidGuessLabel);
                    mainPanel.revalidate();
                    mainPanel.repaint();
                }
            }
        } else {
            if (currentColumn < 5) {
                JLabel letterLabel = letterLabels.get(currentRow * 5 + currentColumn);
                letterLabel.setText(buttonPressed.getText());
                letterLabel.setBorder(typedBorder);
                currentColumn++;
                
                currentWord += buttonPressed.getText();
            }
        }
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            if (currentColumn != 0) {
                currentWord = currentWord.substring(0, currentColumn - 1);
                currentColumn--;
                JLabel letterLabel = letterLabels.get(currentRow * 5 + currentColumn);
                letterLabel.setText("");
                letterLabel.setBorder(untypedBorder);
                    
                mainPanel.remove(invalidGuessLabel);
                mainPanel.revalidate();
                mainPanel.repaint();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (currentColumn == 5) {
                System.out.println(currentWord);
                if (guessWords.contains(currentWord)) {
                    colorTime();
                    
                    currentRow += 1;
                    currentColumn = 0;
                        
                    currentWord = "";
                } else {
                    mainPanel.add(invalidGuessLabel);
                    mainPanel.revalidate();
                    mainPanel.repaint();
                }
            }
        } else {
            if (currentColumn < 5 && Character.isLetter(e.getKeyCode())) {
                JLabel letterLabel = letterLabels.get(currentRow * 5 + currentColumn);
                letterLabel.setText(String.valueOf(e.getKeyChar()).toUpperCase());
                letterLabel.setBorder(typedBorder);
                currentColumn++;
                
                currentWord += String.valueOf(e.getKeyChar()).toUpperCase();
            }
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
            
            JButton letterButton = letterButtons.get(letters.indexOf(currLetter));
            letterButton.setBackground(color);
            letterButton.setForeground(Color.WHITE);
        }
        
        if (targetWordList.equals(guessWordList)) {
            mainPanel.add(winLabel);
            mainPanel.repaint();
        }
        
        if (currentRow == 5) {
            mainPanel.add(loseLabel);
            mainPanel.repaint();
        }
        
    }
    
    public static void start() {
        SwingUtilities.invokeLater(new GameWindow());
    }
}
