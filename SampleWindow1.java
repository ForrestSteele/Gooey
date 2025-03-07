
//345678901234567890123456789012345678901234567890123456789012345678901234567890012345678901234567890
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;

public class SampleWindow1 implements ActionListener, Runnable
{
    JFrame frame;
    JPanel mainPanel;
    JPanel radioPanel;
    JPanel buttonPanel;
    JRadioButton boldButton;
    JRadioButton italicsButton;
    JRadioButton plainButton;
    JButton smallerButton;
    JButton largerButton;
    JLabel label;
    
    public void run()
    {
        // Create top-level container
        frame = new JFrame();
        frame.setSize(300, 200);
        frame.setLocation(100,100);
        frame.setTitle("Window Title");
        frame.setResizable(true);
        
        // Create content panel
        mainPanel = new JPanel(new BorderLayout(20, 20));
        frame.setContentPane(mainPanel);
        
        // Setup radio buttons
        ButtonGroup bGroup = new ButtonGroup();
        
        boldButton = new JRadioButton("Bold");
        boldButton.setMnemonic(KeyEvent.VK_B);
        boldButton.addActionListener(this);
        bGroup.add(boldButton);
        
        italicsButton = new JRadioButton("Italics");
        italicsButton.setMnemonic(KeyEvent.VK_I);
        italicsButton.addActionListener(this);
        bGroup.add(italicsButton);

        plainButton = new JRadioButton("Plain");
        plainButton.setSelected(true);
        plainButton.setMnemonic(KeyEvent.VK_P);
        plainButton.addActionListener(this);
        bGroup.add(plainButton);
        
        // Place radio buttons on their own panel.
        // Then place on main panel.
        radioPanel = new JPanel(new GridLayout(0,1));
        radioPanel.add(plainButton);
        radioPanel.add(boldButton);
        radioPanel.add(italicsButton);
        mainPanel.add(radioPanel, BorderLayout.WEST);
        
        // Setup buttons
        largerButton = new JButton("Larger");
        largerButton.setMnemonic(KeyEvent.VK_L);
        largerButton.addActionListener(this);

        smallerButton = new JButton("Smaller");
        smallerButton.setMnemonic(KeyEvent.VK_S);
        smallerButton.addActionListener(this);
        
        // Place buttons on their own panel.
        // Then place on main panel.
        buttonPanel = new JPanel(new GridLayout(0, 1, 20, 20));
        buttonPanel.add(largerButton);
        buttonPanel.add(smallerButton);
        mainPanel.add(buttonPanel, BorderLayout.EAST);
        
        // Setup label and place on panel.
        Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 14);
        
        label = new JLabel("This is a label.");
        label.setFont(font);
        label.setHorizontalAlignment(JLabel.CENTER);
        
        mainPanel.add(label, BorderLayout.SOUTH);
        
        frame.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent event)
    {
        if (event.getSource() == boldButton)
        {
            label.setFont(new Font(label.getFont().getFamily(),
                                   Font.BOLD,
                                   label.getFont().getSize()));
        }

        if (event.getSource() == italicsButton)
        {
            label.setFont(new Font(label.getFont().getFamily(),
                                   Font.ITALIC,
                                   label.getFont().getSize()));
        }
        
        if (event.getSource() == plainButton)
        {
            label.setFont(new Font(label.getFont().getFamily(),
                                   Font.PLAIN,
                                   label.getFont().getSize()));
        }

        if (event.getSource() == smallerButton)
        {
            label.setFont(new Font(label.getFont().getFamily(),
                                   label.getFont().getStyle(),
                                   label.getFont().getSize()-1));
        }

        if (event.getSource() == largerButton)
        {
            label.setFont(new Font(label.getFont().getFamily(),
                                   label.getFont().getStyle(),
                                   label.getFont().getSize()+1));
        }
    }
    
    public static void start() {
        SwingUtilities.invokeLater(new SampleWindow1());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new SampleWindow1());
    }
}
