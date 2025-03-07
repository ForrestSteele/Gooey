//345678901234567890123456789012345678901234567890123456789012345678901234567890012345678901234567890
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PopUpWindow5 implements ActionListener
{
    SampleWindow5 parentWindow;
    JFrame frame;
    JPanel mainPanel;
    JButton doneButton;
    
    public PopUpWindow5(SampleWindow5 aParentWindow, int swimmerIndex)
    {
        // Keep track of previous window
        parentWindow = aParentWindow;
        
        // Create top-level container
        frame = new JFrame();
        frame.setSize(300, 200);
        frame.setLocation(300,300);
        frame.setTitle("Swimmer Times");
        frame.setResizable(false);
        
        // Create content panel without a layout manager
        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        frame.setContentPane(mainPanel);
        
        // Setup button
        doneButton = new JButton("Done");
        doneButton.setMnemonic(KeyEvent.VK_D);
        doneButton.addActionListener(this);

        // Setup text fields and labels
        Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 14);
        
        // Modify swimmer name to test the ability to update the scroll pane
        parentWindow.swimmers[swimmerIndex] += " - Viewed";

        JLabel nameLabel = new JLabel(parentWindow.swimmers[swimmerIndex]);
        nameLabel.setFont(font);
        nameLabel.setHorizontalAlignment(JLabel.LEFT);
        
        JLabel timeLabel = 
                new JLabel(Integer.toString(parentWindow.swimmerTime[swimmerIndex]) 
                + " seconds");
        timeLabel.setFont(font);
        timeLabel.setHorizontalAlignment(JLabel.LEFT);
        
        nameLabel.setLocation(10, 10);
        timeLabel.setLocation(10, 40);
        doneButton.setLocation(120, 120);
        
        nameLabel.setSize(150, 30);
        timeLabel.setSize(100, 30);
        doneButton.setSize(100, 30);
        
        mainPanel.add(nameLabel);
        mainPanel.add(timeLabel);
        mainPanel.add(doneButton);
        
        frame.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent event)
    {
        if (event.getSource() == doneButton)
        {
            parentWindow.closePopUp();
            frame.dispose();
        }
    }
}