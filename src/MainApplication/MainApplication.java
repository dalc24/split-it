package MainApplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import user.*;
import user.ExpensesPage.ExpensesPage;
import user.UserHomePage.UserHomePage;

public class MainApplication extends JFrame{
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private User user;

    int frameWidth = 500;   // Frame width
    int frameHeight = 800;  // Frame height

    public MainApplication(User user) {
        this.user = user;

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Create instances of your pages and pass 'this' to them
        ExpensesPage expensesPage = new ExpensesPage(user,this);
        UserHomePage userHomePage = new UserHomePage(user, this);

        // Add the pages to the main panel with unique names
        mainPanel.add(userHomePage, "UserHomePage");
        mainPanel.add(expensesPage, "ExpensesPage");
        //mainPanel.add(userHomePage, "UserHomePage");

        // Set the main panel as the content pane
        add(mainPanel);

        // Configure the main frame
        setTitle("Main Application");
        setSize(frameWidth, frameHeight);  // Adjust the size as needed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Center the frame on the screen

    }

    // Method to switch between pages
    public void showPage(String pageName) {
        cardLayout.show(mainPanel, pageName);
    }

    public void showPage() {
        this.setVisible(true);
    }

    
}
