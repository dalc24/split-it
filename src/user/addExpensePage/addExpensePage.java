package user.addExpensePage;

import java.awt.*;
import javax.swing.*;
import java.util.*;


import user.User;
import user.Expense;
import user.FauxDB;




class purposePane extends JPanel {
    private JTextField purposeTextField;

    public purposePane() {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(380, 70)); 
        setMinimumSize(new Dimension(380, 70)); 
        setMaximumSize(new Dimension(380, 70));

        //purpose label
        JLabel purposeLabel = new JLabel("Expense Name: ");
        purposeLabel.setFont(purposeLabel.getFont().deriveFont(Font.BOLD, 12)); 
        purposeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        //text box
        purposeTextField = new JTextField(20);
        purposeTextField.setPreferredSize(new Dimension(300, 30)); 
        purposeTextField.setMinimumSize(new Dimension(300, 30)); 
        purposeTextField.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Add components to the panel
        add(purposeLabel);
        add(Box.createRigidArea(new Dimension(0, 3)));
        add(purposeTextField);
    }

    public String getPurposeText() {
        return purposeTextField.getText();
    }
}

class payPanel extends JPanel {
    private JTextField amountTextField;
    private JComboBox<String> typeSplitMenu;

    public payPanel() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS)); // Arrange components horizontally
        setPreferredSize(new Dimension(380, 50)); 
        setMinimumSize(new Dimension(380, 50)); 
        setMaximumSize(new Dimension(380, 50));

        /* amount panel */
        JPanel amountPanel = new JPanel();
        amountPanel.setLayout(new BoxLayout(amountPanel, BoxLayout.Y_AXIS));

        // amount label
        JLabel amountLabel = new JLabel("Total Amount($): ");
        amountLabel.setAlignmentX(Component.LEFT_ALIGNMENT); 
        amountLabel.setFont(amountLabel.getFont().deriveFont(Font.BOLD, 12)); 

        //amount textfield
        amountTextField = new JTextField(15); 
        amountTextField.setMaximumSize(new Dimension(Integer.MAX_VALUE, amountTextField.getPreferredSize().height));
        amountTextField.setAlignmentX(Component.LEFT_ALIGNMENT);

        amountPanel.add(amountLabel);
        amountPanel.add(Box.createRigidArea(new Dimension(0, 3))); // Reduce vertical space between label and text field
        amountPanel.add(amountTextField);

        /* typeSplit panel */
        JPanel typePanel = new JPanel();
        typePanel.setLayout(new BoxLayout(typePanel, BoxLayout.Y_AXIS));

        JLabel typeLabel = new JLabel("Split Type:");
        typeLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        typeLabel.setFont(amountLabel.getFont().deriveFont(Font.BOLD, 12)); 


        typeSplitMenu = new JComboBox<>(new String[]{"Equal", "Custom"});
        typeSplitMenu.setMaximumSize(new Dimension(Integer.MAX_VALUE, typeSplitMenu.getPreferredSize().height));

        typePanel.add(typeLabel);
        typePanel.add(Box.createRigidArea(new Dimension(0, 3))); 
        typePanel.add(typeSplitMenu);

        // Add both panels to the main panel
        add(amountPanel);
        add(Box.createHorizontalStrut(10)); // Add some space between the two panels
        add(typePanel);

    }

    public float getAmountText() {
        float amount = Float.parseFloat(amountTextField.getText());
        return amount;
    }

    public String getSplitType() {
        String splittingMethod = (String) typeSplitMenu.getSelectedItem();
        return splittingMethod;
    }

}

class paidByPanel extends JPanel {
    private JTextField paidByField;

    public paidByPanel() {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(380, 60)); 
        setMinimumSize(new Dimension(380, 60)); 
        setMaximumSize(new Dimension(380, 60));

        //purpose label
        JLabel paidByLabel = new JLabel("Paid By: ");
        paidByLabel.setFont(paidByLabel.getFont().deriveFont(Font.BOLD, 12)); 
        paidByLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        //text box
        paidByField = new JTextField(20);
        paidByField.setPreferredSize(new Dimension(300, 30)); 
        paidByField.setMinimumSize(new Dimension(300, 30)); 
        paidByField.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Add components to the panel
        add(paidByLabel);
        add(Box.createRigidArea(new Dimension(0, 3)));
        add(paidByField);

        
    }

    public String getPaidByText() {
        return paidByField.getText();
    }
}

class participantsPanel extends JPanel {


    private JTextField participant1;
    private JTextField participant2;
    private JTextField participant3;
    private JTextField participant4;

    public participantsPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(380, 220)); // Adjusted size to fit all participants
        setMinimumSize(new Dimension(380, 220)); 
        setMaximumSize(new Dimension(380, 220));

        // Panel for participant 1
        add(createParticipantPanel("Participant 1: ", participant1 = new JTextField()));
        // Panel for participant 2
        add(createParticipantPanel("Participant 2: ", participant2 = new JTextField()));
        // Panel for participant 3 (if needed)
        add(createParticipantPanel("Participant 3: ", participant3 = new JTextField()));
        // Panel for participant 4 (if needed)
        add(createParticipantPanel("Participant 4: ", participant4 = new JTextField()));
    }

    private JPanel createParticipantPanel(String labelText, JTextField textField) {
        JPanel participantPanel = new JPanel();
        participantPanel.setLayout(new BoxLayout(participantPanel, BoxLayout.Y_AXIS));

        // Create and configure label
        JLabel participantLabel = new JLabel(labelText);
        participantLabel.setFont(participantLabel.getFont().deriveFont(Font.BOLD, 12)); 
        participantLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Configure text field
        textField.setPreferredSize(new Dimension(300, 30)); 
        textField.setMinimumSize(new Dimension(300, 30)); 
        textField.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Add label and text field to the panel
        participantPanel.add(participantLabel);
        participantPanel.add(Box.createRigidArea(new Dimension(0, 3))); // Space between label and text field
        participantPanel.add(textField);

        return participantPanel;
    }
    
    public String getParticipant1() {
        return participant1.getText();
    }

    public String getParticipant2() {
        return participant2.getText();
    }

    public String getParticipant3() {
        return participant3.getText();
    }

    public String getParticipant4() {
        return participant4.getText();
    }

}

public class addExpensePage extends JFrame {

    int frameWidth = 500;   // Frame width
    int frameHeight = 600;  // Frame height

    private purposePane purposePanel;
    private payPanel payPanel;
    private paidByPanel paidByPanel;
    private participantsPanel participantsPanel;


    public addExpensePage(User user) {

        setTitle("Expense");
        setSize(frameWidth, frameHeight);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on the screen

        // Initialize panels
        purposePanel = new purposePane();
        payPanel = new payPanel();
        paidByPanel = new paidByPanel();
        participantsPanel = new participantsPanel();


        JButton submitButton = new JButton("Add Expense");
        submitButton.addActionListener(e -> createExpense());

        // main panel for frame
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // add panels to the main panel
        mainPanel.add(Box.createVerticalStrut(20)); // adds space vertically
        mainPanel.add(purposePanel);    
        mainPanel.add(Box.createVerticalStrut(10)); // adds space vertically
        mainPanel.add(payPanel);
        mainPanel.add(Box.createVerticalStrut(10)); // adds space vertically
        mainPanel.add(paidByPanel);
        mainPanel.add(Box.createVerticalStrut(25)); // adds space vertically
        mainPanel.add(participantsPanel);
        mainPanel.add(Box.createVerticalStrut(25)); //adds space vertically
        mainPanel.add(submitButton); 

        //center the main panel within the JFrame
        mainPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        purposePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        payPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        paidByPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        participantsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        setVisible(true);
        add(mainPanel);

    } 

    private void createExpense() {
        String purpose = purposePanel.getPurposeText();
        float amount = payPanel.getAmountText();
        String splittingMethod = payPanel.getSplitType();

        String payerName = paidByPanel.getPaidByText();

        // Count filled participant fields and gather participant names
        String[] participantNames = new String[4];
        int partySize = 0;
        if (!participantsPanel.getParticipant1().isEmpty()) {
            participantNames[partySize++] = participantsPanel.getParticipant1();
        }
        if (!participantsPanel.getParticipant2().isEmpty()) {
            participantNames[partySize++] = participantsPanel.getParticipant2();
        }
        if (!participantsPanel.getParticipant3().isEmpty()) {
            participantNames[partySize++] = participantsPanel.getParticipant3();
        }
        if (!participantsPanel.getParticipant4().isEmpty()) {
            participantNames[partySize++] = participantsPanel.getParticipant4();
        }

        // Retrieve or create User objects (depends )
        User userPaid = getUserByName(payerName);
        User[] usersOwe = new User[partySize];
        for (int i = 0; i < partySize; i++) {
            usersOwe[i] = getUserByName(participantNames[i]);
        }

        // create expense object
        Expense expense = new Expense(userPaid, usersOwe, amount, purpose, splittingMethod, 0, 0);
        FauxDB.addExpense(expense);

        System.out.println();
        System.out.println("Purpose: " + expense.getPurpose());
        System.out.println("User Paid: " + expense.getUserPaid());
        System.out.println("Party Size: " + expense.getPartySize());
        System.out.println("Expense Amount: " + expense.getExpenseAmount());
        System.out.println("Users Involved: " + expense.getUsersInvolved());
        // Print all expenses in the FauxDB
        System.out.println("All Expenses in FauxDB:");
        for (Expense e : FauxDB.getAllExpenses()) {
            System.out.println("Purpose: " + e.getPurpose());
            System.out.println("User Paid: " + e.getUserPaid());
            System.out.println("Amount: " + e.getExpenseAmount());
            System.out.println("Split Type: " + e.getSplitType());
            System.out.println("Participants: " + (e.getUsersInvolved())); // Adjust according to your Expense class
        }

        JOptionPane.showMessageDialog(this, "Expense Added!"); 


        // close frame
        dispose(); 
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
