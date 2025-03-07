//345678901234567890123456789012345678901234567890123456789012345678901234567890012345678901234567890
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class PopUpWindow3 implements ActionListener
{
    SampleWindow3 parentWindow;
    JFrame frame;
    JPanel mainPanel;
    JLabel firstNameLabel, lastNameLabel;
    JTextField firstNameField, lastNameField;
    JCheckBox driverButton;
    JButton saveButton;
    
    public PopUpWindow3(SampleWindow3 aParentWindow)
    {
        // Keep track of previous window
        parentWindow = aParentWindow;
        
        // Create top-level container
        frame = new JFrame();
        frame.setSize(300, 200);
        frame.setLocation(300,300);
        frame.setTitle("Window Title");
        frame.setResizable(false);
        
        // Create content panel without a layout manager
        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        frame.setContentPane(mainPanel);
        
        // Setup button
        saveButton = new JButton("Save");
        saveButton.setMnemonic(KeyEvent.VK_P);
        saveButton.addActionListener(this);

        // Setup text fields and labels
        Font font1 = new Font(Font.SANS_SERIF, Font.PLAIN, 14);
        
        firstNameField = new JTextField();
        firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setFont(font1);
        firstNameLabel.setHorizontalAlignment(JLabel.RIGHT);

        lastNameField = new JTextField();
        lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setFont(font1);
        lastNameLabel.setHorizontalAlignment(JLabel.RIGHT);
        
        // Setup checkbox and label
        driverButton = new JCheckBox("Driver");
        driverButton.setFont(font1);

        firstNameLabel.setLocation(10, 10);
        lastNameLabel.setLocation(10, 40);
        firstNameField.setLocation(120, 15);
        lastNameField.setLocation(120, 45);
        driverButton.setLocation(120, 75);
        saveButton.setLocation(120, 120);
        
        firstNameLabel.setSize(100, 30);
        lastNameLabel.setSize(100, 30);
        firstNameField.setSize(150, 20);
        lastNameField.setSize(150, 20);
        driverButton.setSize(150, 20);
        saveButton.setSize(100, 30);
        
        mainPanel.add(firstNameLabel);
        mainPanel.add(lastNameLabel);
        mainPanel.add(firstNameField);
        mainPanel.add(lastNameField);
        mainPanel.add(driverButton);
        mainPanel.add(saveButton);
        
        frame.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent event)
    {
        if (event.getSource() == saveButton)
        {
            String toDisplay = firstNameField.getText() + " " 
                    + lastNameField.getText() + " ";
            if (driverButton.isSelected())
            {
                toDisplay = toDisplay + "is a driver";
            }
            else
            {
                toDisplay = toDisplay + "is not a driver";
            }
            
            JOptionPane.showMessageDialog(frame, "Data saved.");
            parentWindow.closePopUp(toDisplay);
            frame.dispose();
        }
    }
}