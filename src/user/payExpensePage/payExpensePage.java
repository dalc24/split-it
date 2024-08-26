package user.payExpensePage;


import user.*;
import user.eDetailsPage.ExpenseUpdateListener;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.*;  

class ExpenseNamePanel extends JPanel {

    ExpenseNamePanel(Expense expense) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        

        //purpose label
        JLabel purposeLabel = new JLabel("Paying for: ");
        purposeLabel.setFont(purposeLabel.getFont().deriveFont(Font.BOLD, 12)); 
        purposeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        //text box
        JLabel purpose = new JLabel(expense.getPurpose());
        purpose.setFont(purpose.getFont().deriveFont(Font.BOLD, 18)); 
        purpose.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add components to the panel
        add(purposeLabel);
        add(Box.createRigidArea(new Dimension(0, 1)));
        add(purpose);
    }
}

class PayPanel extends JPanel {

    private JTextField amountTextField;
    private JComboBox<String> userMenu;

    PayPanel(Expense expense) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        /* for user that is paying amount */
        JPanel userPanel = new JPanel();
        userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.X_AXIS));
        userPanel.setAlignmentX(Component.LEFT_ALIGNMENT); // Align the panel itself to the left

        //label
        JLabel userLabel = new JLabel("User:          ");
        userLabel.setFont(userLabel.getFont().deriveFont(Font.BOLD, 18)); 
        //dropdown menu
        userMenu = new JComboBox<>(expense.getUsersInvolved().toArray(new String[0]));
        userMenu.setMaximumSize(new Dimension(200,50));

        userPanel.add(userLabel);
        userPanel.add(Box.createRigidArea(new Dimension(0, 10))); 
        userPanel.add(userMenu);

        /*  for amoutn that the person is paying */
        JPanel amountPanel = new JPanel();
        amountPanel.setLayout(new BoxLayout(amountPanel, BoxLayout.X_AXIS));
        amountPanel.setAlignmentX(Component.LEFT_ALIGNMENT); // Align the panel itself to the left

        // label 
        JLabel amountLabel = new JLabel("Amount($): ");
        amountLabel.setFont(userLabel.getFont().deriveFont(Font.BOLD, 18)); 
        //textbox
        amountTextField = new JTextField(20);
        amountTextField.setMaximumSize(new Dimension(200,30));

        amountPanel.add(amountLabel);
        amountPanel.add(Box.createRigidArea(new Dimension(0, 10))); 
        amountPanel.add(amountTextField);
        
        add(userPanel);
        add(amountPanel);
    }

    public float getAmountText() {
        float amount = Float.parseFloat(amountTextField.getText());
        return amount;
    }

    public String getUserText() {
        String userText = (String) userMenu.getSelectedItem();
        return userText;
    }
}


public class payExpensePage extends JFrame {

    int frameWidth = 370;
    int frameHeight = 350;

    private ExpenseNamePanel expenseNamePanel;
    private PayPanel payPanel;
    private ExpenseUpdateListener listener;

    public payExpensePage(Expense expense, ExpenseUpdateListener listener) {
        this.listener = listener;

        setTitle("Pay Expense");
        setSize(frameWidth, frameHeight);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on the screen

        expenseNamePanel = new ExpenseNamePanel(expense);
        payPanel = new PayPanel(expense);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JButton payButton = new JButton("Pay Expense");
        payButton.addActionListener(e -> payExpense(expense));



        mainPanel.add(Box.createVerticalStrut(50)); // Adds space vertically
        mainPanel.add(expenseNamePanel);
        mainPanel.add(Box.createVerticalStrut(30)); // Adds space vertically
        mainPanel.add(payPanel);
        mainPanel.add(Box.createVerticalStrut(40)); // Adds space vertically
        mainPanel.add(payButton);


        expenseNamePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        payPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        payButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(mainPanel);


    }

    private void payExpense(Expense expense) {
        String userName = payPanel.getUserText();
        float amount = payPanel.getAmountText();

        User user = FauxDB.getUserByName(userName);
        expense.payExpense(user, amount);

        JOptionPane.showMessageDialog(this, "Paid Expense!"); 

        // Notify the listener (eDetailsPage) about the update
        if (listener != null) {
            listener.updateExpense(expense);
        }

        dispose();

    }


    public void createAndShowWindow() {
        // Make the JFrame visible
        this.setVisible(true);
    }

    public static void main(String[] args) {
       
        User bob = new User("Bob");
        User alice = new User("Alice");
        User charlie = new User("Charlie");
        User dertha = new User("Dertha");
        User ean = new User("Ean");
        User[] participants = {alice, charlie, dertha, ean};
        Expense expense = new Expense(bob, participants, 150, "Dinner", "Equal", 6, 20);
    }
}
