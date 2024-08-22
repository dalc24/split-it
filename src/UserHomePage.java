import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList; // Import ArrayList


import user.User;



// Custom Header class that extends JPanel
class Header extends JPanel {
    Color backgroundColor = new Color(255, 255, 255); // Set background color

    Header(User user) {
        // Set the preferred size and background color of the header
        this.setPreferredSize(new Dimension(500, 80));
        this.setBackground(backgroundColor);
        this.setLayout(new BorderLayout());

        // Create a panel to hold the label for name and amount
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout()); // Use BorderLayout for the bottom panel
        bottomPanel.setOpaque(false); // Make the panel transparent to see the header background

        // Create a panel to hold the label for name
        JPanel namePanel = new JPanel();
        namePanel.setLayout(new FlowLayout(FlowLayout.LEFT)); // Align components to the left
        namePanel.setOpaque(false); // Make the panel transparent to see the header background

        // Define the username color (e.g., blue)
        String userNameColor = "blue";

        // Create and add a label to the panel with the username in bold and a different color
        JLabel nameText = new JLabel("<html>Hello, <span style='color:" + userNameColor + "; font-weight:bold;'>" + user.getName() + "</span>!</html>");
        nameText.setFont(new Font("Serif", Font.PLAIN, 32));
        namePanel.add(nameText);

        // Create a panel to hold the label for the amount
        JPanel amountPanel = new JPanel();
        amountPanel.setLayout(new FlowLayout(FlowLayout.RIGHT)); // Align components to the right
        amountPanel.setOpaque(false); // Make the panel transparent to see the header background

        String positiveAmount = "Green";
        String negativeAmount = "Red";

        String amountColor = "";

        float userAmountTotal = user.getUserTotal();

        if (userAmountTotal > 0) {
            amountColor = negativeAmount;
        } else {
            amountColor = positiveAmount;
        }
        userAmountTotal *= -1;

        // Create and add a label to the panel with the amount in color
        JLabel amountText = new JLabel("<html><span style='color:" + amountColor + "; font-weight:bold;'>$   " + userAmountTotal + "</span></html>");
        amountText.setFont(new Font("Serif", Font.PLAIN, 32));
        amountPanel.add(amountText);

        // Add the namePanel to the WEST side of the bottomPanel
        bottomPanel.add(namePanel, BorderLayout.WEST);

        // Add the amountPanel to the EAST side of the bottomPanel
        bottomPanel.add(amountPanel, BorderLayout.EAST);

        // Add the bottomPanel to the SOUTH side of the header
        this.add(bottomPanel, BorderLayout.SOUTH);
    }
    
}

class UsersOwedBox extends JPanel { 

    UsersOwedBox(User user) {
        // Set layout and properties for this panel
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Vertical box layout
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding
        this.setBackground(Color.LIGHT_GRAY); // Background color for the entire container

        // Get names of users owed money
        ArrayList<String> namesOwed = user.getNamesOwed();

        // Iterate over each name and create a box
        for (String name : namesOwed) {
            // Create a panel for each owed person
            JPanel personPanel = new JPanel();
            personPanel.setBackground(Color.WHITE); // Background color for individual entry
            personPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY)); // Border for individual entry

            // Use BoxLayout to ensure the panel respects the preferred size
            personPanel.setLayout(new BoxLayout(personPanel, BoxLayout.X_AXIS));
            personPanel.setPreferredSize(new Dimension(460, 40)); // Adjust height here
            personPanel.setMaximumSize(new Dimension(460, 40)); // Ensures height doesn't exceed this

            // Get the amount owed to this person
            float amountOwed = user.getAmountOwedtoName(name);

            // Create a label for the name
            JLabel nameLabel = new JLabel(name);
            nameLabel.setFont(new Font("Arial", Font.PLAIN, 16)); // Adjust font size if needed

            // Create a label for the amount owed
            JLabel amountLabel = new JLabel("$" + amountOwed);
            amountLabel.setFont(new Font("Arial", Font.BOLD, 16));
            amountLabel.setForeground(Color.RED); // Make the amount red to stand out

            // Add the name and amount labels to the person panel
            personPanel.add(Box.createRigidArea(new Dimension(10, 0))); // Add padding to the left
            personPanel.add(nameLabel);
            personPanel.add(Box.createHorizontalGlue()); // Pushes the amount label to the right
            personPanel.add(amountLabel);
            personPanel.add(Box.createRigidArea(new Dimension(10, 0))); // Add padding to the right

            // Add a MouseListener to make the panel clickable
            personPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Show details in a popup when the panel is clicked
                    JOptionPane.showMessageDialog(
                        personPanel,
                        "Details for " + name + ":\nAmount Owed: $" + amountOwed,
                        "Details for " + name,
                        JOptionPane.INFORMATION_MESSAGE
                    );
                }
            });

            // Add the person panel to the main panel
            this.add(personPanel);
            this.add(Box.createVerticalStrut(5)); // Add small spacing between boxes
        }
    }

}
// UserHomePage class that extends JFrame
public class UserHomePage extends JFrame {
    private Header header;  // Instance variable for Header
    private JPanel userPanel;  // Panel to hold user boxes


    int frameWidth = 500;   // Frame width
    int frameHeight = 800;  // Frame height

    // Constructor for UserHomePage
    public UserHomePage(User user) {
        // Initialize the header with the User object
        header = new Header(user);
        // Add the header to the frame at the top (NORTH)
        this.add(header, BorderLayout.NORTH);

        UsersOwedBox usersOwedBox = new UsersOwedBox(user);
        JScrollPane scrollPane = new JScrollPane(usersOwedBox);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        this.add(scrollPane, BorderLayout.CENTER);










        // Set default close operation and size of the frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(frameWidth, frameHeight);










        /* for button "click me" */
        // Create a panel to hold additional components
        JPanel buttonPanel = new JPanel();

        // Create a button with text "Click Me!"
        JButton button = new JButton("Click Me!");

        // Add an action listener to the button
        button.addActionListener(e -> {
            // Show a message dialog when the button is clicked
            JOptionPane.showMessageDialog(this, "Button Clicked!");
        });

        // Add the button to the panel
        buttonPanel.add(button);

        // Add the button panel to the frame (SOUTH)
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    public void createAndShowWindow() {
        // Make the window visible
        this.setVisible(true);
    }

}
