import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import user.Expense;
import user.User;

public class UserHomePage {

    public void createAndShowWindow() {
        // Create a new JFrame (window)
        JFrame frame = new JFrame("Simple Swing Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        // Create a panel to hold components
        JPanel panel = new JPanel();

        // Create a button
        JButton button = new JButton("Click Me!");
        // Add an action listener to the button
        button.addActionListener(e -> {
            // Show a message dialog when the button is clicked
            JOptionPane.showMessageDialog(frame, "Button Clicked!");
        });

        // Add the button to the panel
        panel.add(button);

        // Add the panel to the frame
        frame.add(panel);

        // Make the window visible
        frame.setVisible(true);
    }
}
