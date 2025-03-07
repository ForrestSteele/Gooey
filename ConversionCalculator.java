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

/**
 * Write a description of class ConversionCalculator here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ConversionCalculator implements ActionListener, Runnable
{
    JFrame frame;
    JPanel mainPanel;
    JLabel fromUnitLabel, toUnitLabel;
    JTextField fromUnitField, toUnitField;
    JComboBox fromUnitBox;
    JComboBox toUnitBox;
    JButton convertButton;
    
    public void run()
    {
        frame = new JFrame();
        frame.setSize(600, 300);
        frame.setLocation(100,100);
        frame.setTitle("Window Title");
        frame.setResizable(false);
        
        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        frame.setContentPane(mainPanel);
        
        // Setup button
        convertButton = new JButton("Convert");
        convertButton.setMnemonic(KeyEvent.VK_P);
        convertButton.addActionListener(this);

        // Setup text fields and labels
        Font font = new Font(Font.MONOSPACED, Font.PLAIN, 14);
        
        fromUnitField = new JTextField();
        fromUnitLabel = new JLabel("From:");
        fromUnitLabel.setFont(font);
        fromUnitLabel.setHorizontalAlignment(JLabel.RIGHT);

        toUnitField = new JTextField();
        toUnitLabel = new JLabel("To:");
        toUnitLabel.setFont(font);
        toUnitLabel.setHorizontalAlignment(JLabel.RIGHT);
                
        // Setup combobox
        String[] unitStrings = {"Kilometer", "Meter", "Centimeter", "Millimeter", "Mile", "Yard", "Foot", "Inch", "Light Year"};
        fromUnitBox = new JComboBox(unitStrings);
        fromUnitBox.setSelectedIndex(1);
        toUnitBox = new JComboBox(unitStrings);
        toUnitBox.setSelectedIndex(5);

        fromUnitLabel.setLocation(120, 15);
        toUnitLabel.setLocation(420, 15);
        fromUnitField.setLocation(120, 45);
        toUnitField.setLocation(420, 45);
        fromUnitBox.setLocation(120, 105);
        toUnitBox.setLocation(420, 105);
        convertButton.setLocation(300, 45);
        
        fromUnitLabel.setSize(100, 30);
        toUnitLabel.setSize(100, 30);
        fromUnitField.setSize(150, 20);
        toUnitField.setSize(150, 20);
        fromUnitBox.setSize(150, 20);
        toUnitBox.setSize(150, 20);
        convertButton.setSize(100, 30);
        
        mainPanel.add(fromUnitLabel);
        mainPanel.add(toUnitLabel);
        mainPanel.add(fromUnitField);
        mainPanel.add(toUnitField);
        mainPanel.add(convertButton);
        mainPanel.add(fromUnitBox);
        mainPanel.add(toUnitBox);
        
        frame.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent event)
    {
        if (event.getSource() == convertButton)
        {
            System.out.print(fromUnitField.getText() + " " 
                    + toUnitField.getText() + " ");
            
            System.out.println("Color is " + (String) fromUnitBox.getSelectedItem());
        }
    }
    
    public static void start() {
        SwingUtilities.invokeLater(new ConversionCalculator());
    }
}
