
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
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class SampleWindow2 implements ActionListener, Runnable
{
    JFrame frame;
    JPanel mainPanel;
    JLabel label1, label2;
    JTextField firstNameField, lastNameField;
    JCheckBox driverButton;
    JButton printButton;
    
    public void run()
    {
        // Create top-level container
        frame = new JFrame();
        frame.setSize(300, 200);
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
        Font font1 = new Font(Font.SANS_SERIF, Font.PLAIN, 14);
        
        firstNameField = new JTextField();
        label1 = new JLabel("First Name:");
        label1.setFont(font1);
        label1.setHorizontalAlignment(JLabel.RIGHT);

        lastNameField = new JTextField();
        label2 = new JLabel("Last Name:");
        label2.setFont(font1);
        label2.setHorizontalAlignment(JLabel.RIGHT);
        
        // Setup checkbox and label
        driverButton = new JCheckBox("Driver");
        driverButton.setFont(font1);

        label1.setLocation(10, 10);
        label2.setLocation(10, 40);
        firstNameField.setLocation(120, 15);
        lastNameField.setLocation(120, 45);
        driverButton.setLocation(120, 75);
        printButton.setLocation(120, 120);
        
        label1.setSize(100, 30);
        label2.setSize(100, 30);
        firstNameField.setSize(150, 20);
        lastNameField.setSize(150, 20);
        driverButton.setSize(150, 20);
        printButton.setSize(100, 30);
        
        mainPanel.add(label1);
        mainPanel.add(label2);
        mainPanel.add(firstNameField);
        mainPanel.add(lastNameField);
        mainPanel.add(driverButton);
        mainPanel.add(printButton);
        
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
        }
    }
    
    public static void start() {
        SwingUtilities.invokeLater(new SampleWindow2());
    }
}
