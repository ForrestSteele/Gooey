//0987654321098765432109876543210987654321098765432109876543210987654321098765432109876543210987654321
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
import javax.swing.Timer;

/**
 * Provides methods to construct and manipulate the main game window. 
 *
 * @author Forrest Steele
 * @version March 2025
 */
public class GameWindow implements ActionListener, Runnable, KeyListener
{
    private ArrayList<JLabel> letterLabels;
    private ArrayList<JButton> letterButtons;
    
    private ArrayList<String> guessWords;
    
    private ArrayList<JButton> correctStorage;
    
    private String targetWord;
    private String guessWord;
    private String currentWord;
    private String letters;
    
    private Color yellow;
    private Color green;
    private Color darkGray;
    private Color lightGray;
    
    private JLabel invalidGuessLabel;
    private JLabel loseLabel;
    private JLabel winLabel;
    
    private JLabel startButton;
    
    private Font popUpsFont;
    private Font commandsFont;
    private Font guessFont;
    private Font letterButtonsFont;

    private Border typedBorder;
    private Border untypedBorder;
    private Border letterLabelsBorder;
    private Border letterButtonsBorder;
    private Border processedBorder;
    
    private JFrame frame;
    private JPanel mainPanel;
    
    private int currentRow;
    private int currentColumn;
    
    public GameWindow() {
        //setting up lists of letter labels and buttons
        letterLabels = new ArrayList<JLabel>();
        letterButtons = new ArrayList<JButton>();
        
        correctStorage = new ArrayList<JButton>();
        
        //initilizing current row and columnn to zero
        currentRow = 0;
        currentColumn = 0;
                
        //colors
        yellow = new Color(200, 182, 83);
        green = new Color(108, 169, 101);
        darkGray = new Color(120, 124, 127);
        lightGray = new Color(232, 232, 232);
        
        //letters complete string
        letters = "QWERTYUIOPASDFGHJKLZXCVBNM";
        
        //fonts
        popUpsFont = new Font(Font.SANS_SERIF, Font.BOLD, 12);
        commandsFont = new Font(Font.SANS_SERIF, Font.BOLD, 10);
        guessFont = new Font(Font.SANS_SERIF, Font.BOLD, 25);
        letterButtonsFont = new Font(Font.SANS_SERIF, Font.BOLD, 20);

        //borders
        typedBorder = BorderFactory.createLineBorder(darkGray, 2);
        untypedBorder = BorderFactory.createLineBorder(lightGray, 2);
        letterLabelsBorder = BorderFactory.createLineBorder(lightGray, 2);
        letterButtonsBorder = BorderFactory.createLineBorder(lightGray, 0);
        processedBorder = BorderFactory.createLineBorder(Color.WHITE, 0);

        //finding target word
        ArrayList<String> targetWords = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("wordle-game-words.txt");
            BufferedReader reader = new BufferedReader(fileReader);  
            String line = reader.readLine();
            
            while ((line = reader.readLine()) != null) {  
                targetWords.add(line.toUpperCase());
            }
        } catch (Exception E) {}
        
        int randomIndex = (int) (Math.random() * targetWords.size()) + 0;
        targetWord = targetWords.get(randomIndex);
        
        System.out.println(targetWord);
                
        //creating list of all possible guess words
        guessWords = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("wordle-guess-words.txt");
            BufferedReader reader = new BufferedReader(fileReader);  
            String line = reader.readLine();
          
            while ((line = reader.readLine()) != null) {  
                guessWords.add(line.toUpperCase());
            }
        } catch (Exception E) {}
        
        currentWord = "";
    }
        
    public void run() {
        
        //setting up frame and panel
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
        
        //margin from top
        int marginVertical = 100;
        
        //adding description text
        JLabel specialText = new JLabel();
        specialText.setText("© 2025 The Mainsheet Newspaper | ChadwickSchool.org | Sitemap | Privacy Policy | Terms of Service | Terms of Sale | California Notices");
        specialText.setLocation(110, 530 + marginVertical + 135);
        specialText.setSize(515, 40);
        Font specialFont = new Font(Font.DIALOG, Font.PLAIN, 8);
        specialText.setFont(specialFont);
        mainPanel.add(specialText);
        
        //pop ups for invalid guess word, win, and loss
        invalidGuessLabel = new JLabel();
        invalidGuessLabel.setLocation(295, 35);
        invalidGuessLabel.setSize(110, 35);
        invalidGuessLabel.setText("Not in word list");
        invalidGuessLabel.setBackground(Color.BLACK);
        invalidGuessLabel.setOpaque(true);
        invalidGuessLabel.setFont(popUpsFont);
        invalidGuessLabel.setForeground(Color.WHITE);
        invalidGuessLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        ArrayList<String> winStatements = new ArrayList<String>();
        winStatements.add("Splendid");
        winStatements.add("Impressive");
        winStatements.add("Phew");
        winStatements.add("Genius");
        winStatements.add("Magnificent");
        winStatements.add("Great");
        
        int randomIndex = (int) (Math.random() * winStatements.size()) + 0;
        String winStatement = winStatements.get(randomIndex);
        int labelWidth = winStatement.length() * 10 + 15;
        winLabel = new JLabel();
        winLabel.setLocation((700 - labelWidth)/2, 35);
        winLabel.setSize(labelWidth, 35);
        winLabel.setText(winStatement);
        winLabel.setBackground(Color.BLACK);
        winLabel.setOpaque(true);
        winLabel.setFont(popUpsFont);
        winLabel.setForeground(Color.WHITE);
        winLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        loseLabel = new JLabel();
        loseLabel.setLocation((700 - 70)/2, 35);
        loseLabel.setSize(70, 35);
        loseLabel.setText(targetWord);
        loseLabel.setBackground(Color.BLACK);
        loseLabel.setOpaque(true);
        loseLabel.setFont(popUpsFont);
        loseLabel.setForeground(Color.WHITE);
        loseLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        //setting up letter labels
        int boxSize = 50;
        
        int marginBetween = 6;
        int marginHorizontal = (frameWidth - (5 * (boxSize + marginBetween)))/2;
        // Set up all text boxes
        for (int row = 0; row < 6; row++) {
            for (int column = 0; column < 5; column++) {
                letterLabels.add(new JLabel());
                JLabel letterLabel = letterLabels.get((row * 5) + column);
                letterLabel.setLocation(marginHorizontal + column*(boxSize + marginBetween), 
                marginVertical + row*(boxSize + marginBetween));
                letterLabel.setSize(boxSize, boxSize);
                letterLabel.setBorder(letterLabelsBorder);
                letterLabel.setHorizontalAlignment(JTextField.CENTER);
                letterLabel.setFont(guessFont);
                letterLabel.setOpaque(true);
                letterLabel.setBackground(Color.WHITE);
                mainPanel.add(letterLabel);
            }
        }
        
        //setting up letter buttons
        //firstRowOfLetters
        for (int letter = 0; letter < 10; letter++) {
            int letterBoxSize = 50;
            int letterMargin = (frameWidth - (letterBoxSize + marginBetween) * 10)/2;
            letterButtons.add(new JButton(letters.substring(letter, letter + 1)));
            JButton letterButton = letterButtons.get(letter);
            letterButton.setLocation(letterMargin + letter * (letterBoxSize + marginBetween), 
            marginVertical + (boxSize + marginBetween) * 6 + 20);
            letterButton.setSize(letterBoxSize, letterBoxSize);
            letterButton.setBackground(lightGray);
            letterButton.setOpaque(true);
            letterButton.setBorder(letterButtonsBorder);
            letterButton.setFont(letterButtonsFont);
            letterButton.addActionListener(this);
            mainPanel.add(letterButton);
        }
        
        //secondRowOfLetters
        for (int letter = 0; letter < 9; letter++) {
            int letterBoxSize = 50;
            int letterMargin = (frameWidth - (letterBoxSize + marginBetween) * 9)/2;
            letterButtons.add(new JButton(letters.substring(10 + letter, 10 + letter + 1)));
            JButton letterButton = letterButtons.get(10 + letter);
            letterButton.setLocation(letterMargin + letter * (letterBoxSize + marginBetween), 
            marginVertical + (boxSize + marginBetween) * 6 + 20 + (letterBoxSize + marginBetween));
            letterButton.setSize(letterBoxSize, letterBoxSize);
            letterButton.setBackground(lightGray);
            letterButton.setOpaque(true);
            letterButton.setBorder(letterButtonsBorder);
            letterButton.setFont(letterButtonsFont);
            letterButton.addActionListener(this);
            mainPanel.add(letterButton);
        }
        
        //thirdRowOfLetters
        for (int letter = 0; letter < 7; letter++) {
            int letterBoxSize = 50;
            int letterMargin = (frameWidth - (letterBoxSize + marginBetween) * 9)/2;
            letterButtons.add(new JButton(letters.substring(19 + letter, 19 + letter + 1)));
            JButton letterButton = letterButtons.get(19 + letter);
            letterButton.setLocation(letterMargin + letterBoxSize + marginBetween + letter * 
            (letterBoxSize + marginBetween), marginVertical + (boxSize + marginBetween) * 6 + 20 + 
            (letterBoxSize + marginBetween) * 2);
            letterButton.setSize(letterBoxSize, letterBoxSize);
            letterButton.setBackground(lightGray);
            letterButton.setOpaque(true);
            letterButton.setBorder(letterButtonsBorder);
            letterButton.setFont(letterButtonsFont);
            letterButton.addActionListener(this);
            mainPanel.add(letterButton);
        }
        
        //setting up commands keys: enter and delete
        int commandsKeyWidth = 80;

        letterButtons.add(new JButton("ENTER"));
        JButton enterButton = letterButtons.get(26);
        enterButton.setLocation(68, marginVertical + (boxSize + marginBetween) * 6 + 20 + 
        (50 + marginBetween) * 2);
        enterButton.setSize(80, 50);
        enterButton.setBackground(lightGray);
        enterButton.setOpaque(true);
        enterButton.setBorder(letterButtonsBorder);
        enterButton.setFont(commandsFont);
        enterButton.addActionListener(this);
        mainPanel.add(enterButton);
        
        letterButtons.add(new JButton("DELETE"));
        JButton deleteButton = letterButtons.get(27);
        deleteButton.setLocation(546, marginVertical + (boxSize + marginBetween) * 6 + 20 + 
        (50 + marginBetween) * 2);
        deleteButton.setSize(80, 50);
        deleteButton.setBackground(lightGray);
        deleteButton.setOpaque(true);
        deleteButton.setBorder(letterButtonsBorder);
        deleteButton.setFont(commandsFont);
        deleteButton.addActionListener(this);
        mainPanel.add(deleteButton);
        
        //finishing frame set up, adding KeyListener, and focusing into mainPanel
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
        mainPanel.requestFocusInWindow();
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
                        
        for (int i = 0; i < 5; i++) {
            JLabel current = letterLabels.get(currentRow * 5 + i);
            guessWordList.add(current.getText());
        }
        
        for (int i = 0; i < 5; i++) {
            targetWordList.add(targetWord.substring(i, i + 1));
            lettersRemaining.add(targetWord.substring(i, i + 1));
        }
        
        //first run through completely and check for correctly placed letters. Running completely 
        //through for this makes it so that there won't be a letter marked yellow that comes before 
        //a duplicate letter that comes later and is correctly placed and marked green. Ex. with 
        //target word "PULSE", the guess word "SALSA" should NOT highlight the first "S" yellow, 
        //instead only the second "S" green. 
        for (int i = 0; i < 5; i++) {
            String currLetter = guessWordList.get(i);
                        
            Color color = darkGray;
            
            if (targetWordList.get(i).equals(guessWordList.get(i))) {
                color = green;
                lettersRemaining.remove(currLetter);
            }
            
            JLabel letterLabel = letterLabels.get(currentRow * 5 + i);
            letterLabel.setBackground(color);
            letterLabel.setForeground(Color.WHITE);
            letterLabel.setBorder(processedBorder);
            
            JButton letterButton = letterButtons.get(letters.indexOf(currLetter));
            if (!correctStorage.contains(letterButton)) { //only change background if 
                //button is not already set to green - want to tell user that they
                //know where this letter is placed if they have made correct guess prior
                letterButton.setBackground(color);
                letterButton.setForeground(Color.WHITE);
                correctStorage.add(letterButton);
            }
        }
        
        //run again to check for letters that do exist in letters remanining, note - won't replace 
        //correctly placed letters because if they are marked as green they are taken out of letters 
        //remaining list. Yet note, this loop only CHANGES letters from grey (set in prev loop) to 
        //yellow. This loop does not have a starting color of grey or else with duplicate letters 
        //it (ex. target "ADEEM", and guess "FEEDS") the second "E" which is marked as green in 
        //first loop would be changed to grey in second loop because by that point both "E"'s would 
        //be taken out of letters remaining list.
        for (int i = 0; i < 5; i++) {
            String currLetter = guessWordList.get(i);
            
            if (lettersRemaining.contains(currLetter) && !targetWordList.get(i).equals(
            guessWordList.get(i))) {
                lettersRemaining.remove(currLetter);
                
                JLabel letterLabel = letterLabels.get(currentRow * 5 + i);
                letterLabel.setBackground(yellow);
                letterLabel.setForeground(Color.WHITE);
                letterLabel.setBorder(processedBorder);
                
                JButton letterButton = letterButtons.get(letters.indexOf(currLetter));

                if (!correctStorage.contains(letterButton)) {
                    letterButton.setBackground(yellow);
                    letterButton.setForeground(Color.WHITE);
                }
            }
        }
        
        if (targetWord.equals(currentWord)) {
            mainPanel.add(winLabel);
            mainPanel.repaint();
            mainPanel.removeKeyListener(this); //prevent further typing
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
