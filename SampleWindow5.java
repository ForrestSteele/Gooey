//345678901234567890123456789012345678901234567890123456789012345678901234567890012345678901234567890
// import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

public class SampleWindow5 implements ActionListener, Runnable
{
    private JList swimmerList;
    private JButton detailsButton;
    
    String[] swimmers;
    int[] swimmerTime;
    JScrollPane swimmerListScrollPane;
    
    public void run()
    {
        // Setup data
        swimmers = new String[6];
        swimmers[0] = "Swimmer #1";
        swimmers[1] = "Swimmer #2";
        swimmers[2] = "Swimmer #3";
        swimmers[3] = "Swimmer #4";
        swimmers[4] = "Swimmer #5";
        swimmers[5] = "Swimmer #6";
        
        swimmerTime = new int[6];
        swimmerTime[0] = 30;
        swimmerTime[1] = 31;
        swimmerTime[2] = 32;
        swimmerTime[3] = 33;
        swimmerTime[4] = 34;
        swimmerTime[5] = 35;
        
        // Create top-level container
        JFrame frame = new JFrame();
        frame.setSize(300, 200);
        frame.setLocation(100,100);
        frame.setTitle("Swimmer List");
        frame.setResizable(true);
        
        // Create content panel without a layout manager
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        frame.setContentPane(mainPanel);

        // Create list
        swimmerList = new JList(swimmers);
        swimmerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        swimmerList.setLayoutOrientation(JList.VERTICAL);
        swimmerList.setSelectedIndex(0);

        // Create scroll pane for list
        swimmerListScrollPane = new JScrollPane(swimmerList);
        
        // Create button and pane
        detailsButton = new JButton("Details");
        detailsButton.setMnemonic(KeyEvent.VK_D);
        detailsButton.addActionListener(this);

        swimmerListScrollPane.setSize(275, 100);
        detailsButton.setSize(100, 30);

        swimmerListScrollPane.setLocation(5, 10);
        detailsButton.setLocation(5, 120);
        
        mainPanel.add(swimmerListScrollPane);
        mainPanel.add(detailsButton);
        
        frame.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent event)
    {
        if (event.getSource() == detailsButton)
        {
            // Open another window
            new PopUpWindow5(this, swimmerList.getSelectedIndex());
            detailsButton.setEnabled(false);
            swimmerList.setEnabled(false);
        }
    }
    
    public void closePopUp()
    {
        // Create new list for scrollpane
        swimmers = new String[2];
        swimmers[0] = "Swimmer #1";
        swimmers[1] = "Swimmer #2";
        swimmerList = new JList(swimmers);
        swimmerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        swimmerList.setLayoutOrientation(JList.VERTICAL);
        swimmerList.setSelectedIndex(0);

        // Update scrollpane with new list
        swimmerListScrollPane.setViewportView(swimmerList);
        detailsButton.setEnabled(true);
        swimmerList.setEnabled(true);
    }
    
    public static void start() {
        SwingUtilities.invokeLater(new SampleWindow5());
    }
}