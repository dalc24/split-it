package user.addExpensePage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.List;
import java.util.ArrayList;


import user.User;
import user.Expense;
import user.addExpensePage.*;

public class addExpensePage extends JFrame {

    int frameWidth = 500;   // Frame width
    int frameHeight = 600;  // Frame height

    private JTextField purposeField;
    private JTextField amountField;
    private JTextField payerField;

    private JTextField participant1Field;
    private JTextField participant2Field;
    private JTextField participant3Field;

    private JComboBox<String> typeSplitDrop;


    public addExpensePage(User user) {

        setTitle("Expense");
        setSize(frameWidth, frameWidth);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Purpose/Expensename
        JLabel purposeLabel = new JLabel("Purpose: ");
        purposeField = new JTextField(20);
        panel.add(purposeLabel);
        panel.add(purposeField);

       

        // Amount
        JLabel amountLabel = new JLabel("Amount ($): ");
        amountField = new JTextField(20);
        panel.add(amountLabel);
        panel.add(amountField);

        //Payer
        JLabel payerLabel = new JLabel("Paid By: ");
        payerField = new JTextField(20);
        panel.add(payerLabel);
        panel.add(payerField);

        //participants
        JLabel participantLabel = new JLabel("Participants:");
        participant1Field = new JTextField(20);
        participant2Field = new JTextField(20);
        participant3Field = new JTextField(20);
        panel.add(participantLabel);
        panel.add(participant1Field);
        panel.add(participant2Field);
        panel.add(participant3Field);

        JLabel typeSplitLabel = new JLabel("Splitting Method:");
        String[] typeSplits= {"Equal", "Custom"};
        typeSplitDrop = new JComboBox<>(typeSplits);
        panel.add(typeSplitLabel);
        panel.add(typeSplitDrop);

        JButton submitButton = new JButton("Add Expense");
        submitButton.addActionListener(e -> createExpense());
        panel.add(submitButton); 

        add(panel);

    } 

    private void createExpense() {
        String purpose = purposeField.getText();
        float amount = Float.parseFloat(amountField.getText());
        String payerName = payerField.getText();

        // Count filled participant fields and gather participant names
        String[] participantNames = new String[3];
        int partySize = 0;
        if (!participant1Field.getText().isEmpty()) {
            participantNames[partySize++] = participant1Field.getText();
        }
        if (!participant2Field.getText().isEmpty()) {
            participantNames[partySize++] = participant2Field.getText();
        }
        if (!participant3Field.getText().isEmpty()) {
            participantNames[partySize++] = participant3Field.getText();
        }

        String splittingMethod = (String) typeSplitDrop.getSelectedItem();

        // Retrieve or create User objects (this depends on how you manage users in your application)
        User userPaid = getUserByName(payerName);
        User[] usersOwe = new User[partySize];
        for (int i = 0; i < partySize; i++) {
            usersOwe[i] = getUserByName(participantNames[i]);
        }

        // Create the Expense object
        Expense expense = new Expense(userPaid, usersOwe, amount, purpose, splittingMethod, 0, 0);

        JOptionPane.showMessageDialog(this, "Expense Added!"); 

    
    
    
    /*    JPanel buttonPanel = new JPanel();

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
        this.add(buttonPanel, BorderLayout.SOUTH); */
    } 


    private User getUserByName(String name) {
        // Replace with your actual logic to find or create a User object
        return new User(name);
    }

    public void createAndShowWindow() {
        // Make the JFrame visible
        this.setVisible(true);
    }

    public static void main(String[] args) {
        // Create a new JFrame (window)
        JFrame frame = new JFrame("Dropdown Menu Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);

        // Create a panel to hold the dropdown menu
        JPanel panel = new JPanel();

        // Create a list of items for the dropdown menu
        String[] options = {"Option 1", "Option 2", "Option 3", "Option 4"};

        // Create the JComboBox (dropdown menu) with the options
        JComboBox<String> comboBox = new JComboBox<>(options);

        // Add an action listener to handle item selection
        comboBox.addActionListener(e -> {
            String selectedOption = (String) comboBox.getSelectedItem();
            JOptionPane.showMessageDialog(frame, "You selected: " + selectedOption);
        });

        // Add the combo box to the panel
        panel.add(comboBox);

        // Add the panel to the frame
        frame.add(panel);

        // Set the frame to be visible
        frame.setVisible(true);
    }
    
}
