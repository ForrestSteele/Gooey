//345678901234567890123456789012345678901234567890123456789012345678901234567890012345678901234567890
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

public class SampleWindow6 implements ActionListener, Runnable
{
    JFrame frame;
    JPanel mainPanel;
    JTable table;
    JScrollPane scrollablePane;
    JButton saveButton;
    
    public void run()
    {
        // Setup data
        String[] columnNames = {"<html><center>Event<br>Stuff</html>", "Date", "Time"};
        
        Object[][] data = {
            {"100 Free", "01/01/2018", "1:14.34"},
            {"100 Free", "02/01/2018", "1:13.34"},
            {"100 Free", "03/01/2018", "1:12.34"},
            {"100 Free", "04/01/2018", "1:11.34"},
            {"100 Free", "05/01/2018", "1:11.34"},
            {"100 Free", "06/01/2018", "1:11.34"},
            {"100 Yard Butterfly - Boys", "01/01/2018", "1:24.34"},
            {"100 Fly", "02/01/2018", "1:23.34"},
            {"100 Fly", "03/01/2018", "1:22.34"},
            {"100 Fly", "04/01/2018", "1:21.34"},
            {"100 Fly", "05/01/2018", "1:21.34"},
            {"100 Fly", "06/01/2018", "1:21.34"}
        };
        
        // Create top-level container
        frame = new JFrame();
        frame.setSize(400, 260);
        frame.setLocation(100, 100);
        frame.setTitle("Window Title");
        frame.setResizable(false);
        
        // Create content panel without a layout manager
        mainPanel = new JPanel();
        mainPanel.setSize(400, 200);
        mainPanel.setLayout(null);
        frame.setContentPane(mainPanel);
        
        // Create table
        table = new JTable(data, columnNames);
        table.setShowHorizontalLines(true);
        table.setShowVerticalLines(false);
        table.setEnabled(true);
        table.getColumnModel().getColumn(0).setPreferredWidth(150);
        table.getColumnModel().getColumn(1).setPreferredWidth(50);
        table.getColumnModel().getColumn(2).setPreferredWidth(50);
        
        // Create scrollable pane
        scrollablePane = new JScrollPane(table,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        // Create button
        saveButton = new JButton("Save");
        saveButton.addActionListener(this);

        // Set sizes
        scrollablePane.setSize(360, 140);
        saveButton.setSize(90, 50);

        // Set locations
        scrollablePane.setLocation(10, 10);
        saveButton.setLocation(135, 160);

        mainPanel.add(scrollablePane);
        mainPanel.add(saveButton);
        
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent event)
    {
        if (event.getSource() == saveButton)
        {
            for (int i=0; i<table.getRowCount(); i++)
            {
                for (int j=0; j<table.getColumnCount() ; j++)
                {
                    System.out.print(table.getValueAt(i, j) + " ");
                }
                System.out.println();
            }
        }
    }
    
    public static void start()
    {
        SwingUtilities.invokeLater(new SampleWindow6());
    }
}