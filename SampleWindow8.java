//345678901234567890123456789012345678901234567890123456789012345678901234567890012345678901234567890
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
 * Provides examples of basic GUI components.
 * 
 * @author      Mr. Murphy
 * @version     May 2024
*/
public class SampleWindow8 implements ActionListener, Runnable {
    JFrame frame;
    JPanel mainPanel;
    JLabel firstNameLabel;
    JLabel lastNameLabel;
    JTextField firstNameField;
    JTextField lastNameField;
    JCheckBox driverBox;
    JComboBox carColorBox;
    JRadioButton yesRadioButton;
    JRadioButton noRadioButton;
    JButton printButton;
    
    public void run() {
        // Create top-level container
        frame = new JFrame();
        frame.setSize(300, 300);
        frame.setLocation(100,100);
        frame.setTitle("My Window Title");
        frame.setResizable(false);
        
        // Create content panel without a layout manager
        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        frame.setContentPane(mainPanel);
        
        // Setup text fields and labels
        Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 14);
        
        firstNameField = new JTextField();
        firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setFont(font);
        firstNameLabel.setHorizontalAlignment(JLabel.RIGHT);

        lastNameField = new JTextField();
        lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setFont(font);
        lastNameLabel.setHorizontalAlignment(JLabel.RIGHT);
        
        // Setup checkbox and label
        driverBox = new JCheckBox("Driver");
        driverBox.setFont(font);
        
        // Setup combobox
        String[] colorStrings = {"select color", "Red", "Blue", "Green", "Yellow"};
        carColorBox = new JComboBox(colorStrings);
        carColorBox.setSelectedIndex(0);

        // Setup radio button
        ButtonGroup bGroup = new ButtonGroup();
        
        yesRadioButton = new JRadioButton("Yes");
        noRadioButton = new JRadioButton("No");
        yesRadioButton.addActionListener(this);
        noRadioButton.addActionListener(this);
        
        bGroup.add(yesRadioButton);
        bGroup.add(noRadioButton);
        
        // Setup button
        printButton = new JButton("Print");
        printButton.addActionListener(this);
        
        firstNameLabel.setLocation(10, 10);
        lastNameLabel.setLocation(10, 40);
        firstNameField.setLocation(120, 15);
        lastNameField.setLocation(120, 45);
        driverBox.setLocation(120, 75);
        carColorBox.setLocation(120, 105);
        yesRadioButton.setLocation(120, 135);
        noRadioButton.setLocation(120, 165);
        printButton.setLocation(120, 200);
        
        firstNameLabel.setSize(100, 30);
        lastNameLabel.setSize(100, 30);
        firstNameField.setSize(150, 20);
        lastNameField.setSize(150, 20);
        driverBox.setSize(150, 20);
        carColorBox.setSize(150, 20);
        yesRadioButton.setSize(50, 20);
        noRadioButton.setSize(50, 20);
        printButton.setSize(100, 30);
        
        mainPanel.add(firstNameLabel);
        mainPanel.add(lastNameLabel);
        mainPanel.add(firstNameField);
        mainPanel.add(lastNameField);
        mainPanel.add(driverBox);
        mainPanel.add(carColorBox);
        mainPanel.add(yesRadioButton);
        mainPanel.add(noRadioButton);
        mainPanel.add(printButton);
        
        frame.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == printButton) {
            System.out.print(firstNameField.getText() + " " + lastNameField.getText() + " ");

            if (driverBox.isSelected()) {
                System.out.println("is a driver");
            } else {
                System.out.println("is not a driver");
            }

            System.out.println("Color is " + (String)carColorBox.getSelectedItem());

            if (yesRadioButton.isSelected()) {
                System.out.println("Yes");
            } else if (noRadioButton.isSelected()) {
                System.out.println("No");
            }
        } else if (event.getSource() == yesRadioButton) {
            JOptionPane.showMessageDialog(frame, "Yes.");

        }
    }
    
    public static void start() {
        SwingUtilities.invokeLater(new SampleWindow8());
    }
}
