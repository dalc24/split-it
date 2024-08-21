import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import user.User;



// Custom Header class that extends JPanel
class Header extends JPanel {
    Color backgroundColor = new Color(255, 255, 255); // Set background color

    Header(User user) {
        // Set the preferred size and background color of the header
        this.setPreferredSize(new Dimension(500, 100));
        this.setBackground(backgroundColor);

        // Create and add a label to the header
        // Define the username color (e.g., blue)
        String userNameColor = "blue";

        // Create and add a label to the header with the username in a different color
        JLabel titleText = new JLabel("<html>Hello, <span style='color:" + userNameColor + "; font-weight:bold;'>" + user.getName() + "</span></html>");
        this.add(titleText);
    
    }
}

// UserHomePage class that extends JFrame
public class UserHomePage extends JFrame {
    private Header header;  // Instance variable for Header

    int frameWidth = 500;   // Frame width
    int frameHeight = 800;  // Frame height

    // Constructor for UserHomePage
    public UserHomePage(User user) {
        // Initialize the header
        header = new Header(user);

        // Add the header to the frame at the top (NORTH)
        this.add(header, BorderLayout.NORTH);

        // Set default close operation and size of the frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(frameWidth, frameHeight);

        // Create a panel to hold additional components
        JPanel panel = new JPanel();

        // Create a button with text "Click Me!"
        JButton button = new JButton("Click Me!");

        // Add an action listener to the button
        button.addActionListener(e -> {
            // Show a message dialog when the button is clicked
            JOptionPane.showMessageDialog(this, "Button Clicked!");
        });

        // Add the button to the panel
        panel.add(button);

        // Add the panel to the frame (CENTER by default)
        this.add(panel);

        // Make the window visible
        this.setVisible(true);
    }

    public void createAndShowWindow() {
        // Make the window visible
        this.setVisible(true);
    }

}
