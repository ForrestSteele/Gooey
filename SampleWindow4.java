//345678901234567890123456789012345678901234567890123456789012345678901234567890012345678901234567890
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class SampleWindow4 implements ActionListener, Runnable
{
    JFrame frame;
    JPanel mainPanel;
    JLabel firstNameLabel, lastNameLabel;
    JTextField firstNameField, lastNameField;
    JCheckBox driverButton;
    JComboBox carColorBox;
    JButton printButton;
    
    public void run()
    {
        // Create top-level container
        frame = new JFrame();
        frame.setSize(300, 300);
        frame.setLocation(100,100);
        frame.setTitle("Window Title");
        frame.setResizable(false);
        
        // Create content panel without a layout manager
        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        frame.setContentPane(mainPanel);
        
        // Setup button
        printButton = new JButton("Print");
        printButton.setMnemonic(KeyEvent.VK_P);
        printButton.addActionListener(this);

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
        driverButton = new JCheckBox("Driver");
        driverButton.setFont(font);
        
        // Setup combobox
        String[] colorStrings = {"select color", "Red", "Blue", "Green", "Yellow"};
        carColorBox = new JComboBox(colorStrings);
        carColorBox.setSelectedIndex(0);

        firstNameLabel.setLocation(10, 10);
        lastNameLabel.setLocation(10, 40);
        firstNameField.setLocation(120, 15);
        lastNameField.setLocation(120, 45);
        driverButton.setLocation(120, 75);
        carColorBox.setLocation(120, 105);
        printButton.setLocation(120, 200);
        
        firstNameLabel.setSize(100, 30);
        lastNameLabel.setSize(100, 30);
        firstNameField.setSize(150, 20);
        lastNameField.setSize(150, 20);
        driverButton.setSize(150, 20);
        carColorBox.setSize(150, 20);
        printButton.setSize(100, 30);
        
        mainPanel.add(firstNameLabel);
        mainPanel.add(lastNameLabel);
        mainPanel.add(firstNameField);
        mainPanel.add(lastNameField);
        mainPanel.add(driverButton);
        mainPanel.add(printButton);
        mainPanel.add(carColorBox);
        
        frame.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent event)
    {
        if (event.getSource() == printButton)
        {
            System.out.print(firstNameField.getText() + " " 
                    + lastNameField.getText() + " ");
            if (driverButton.isSelected())
            {
                System.out.println("is a driver");
            }
            else
            {
                System.out.println("is not a driver");
            }
            System.out.println("Color is " + (String)carColorBox.getSelectedItem());
        }
    }
    
    public static void start() {
        SwingUtilities.invokeLater(new SampleWindow4());
    }
}