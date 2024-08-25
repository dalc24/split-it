package user.eDetailsPage;
import user.User;
import user.Expense;
import user.ExpensesPage.ExpensesPage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.*;  



class ExpenseNamePanel extends JPanel {

    ExpenseNamePanel(Expense expense) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        

        //purpose label
        JLabel purposeLabel = new JLabel("Name: ");
        purposeLabel.setFont(purposeLabel.getFont().deriveFont(Font.BOLD, 12)); 
        purposeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        //text box
        JLabel purpose = new JLabel(expense.getPurpose());
        purpose.setFont(purpose.getFont().deriveFont(Font.BOLD, 30)); 
        purpose.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add components to the panel
        add(purposeLabel);
        add(Box.createRigidArea(new Dimension(0, 1)));
        add(purpose);
    }

}

class PayPanel extends JPanel {

    public PayPanel(Expense expense) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Arrange components vertically

        /* amount panel */
        JPanel amountPanel = new JPanel();
        amountPanel.setLayout(new BoxLayout(amountPanel, BoxLayout.X_AXIS));
        amountPanel.setAlignmentX(Component.LEFT_ALIGNMENT); // Align the panel itself to the left
        // amount label
        JLabel amountLabel = new JLabel("Total Amount:   ");
        amountLabel.setFont(amountLabel.getFont().deriveFont(Font.BOLD, 14)); 
        // amount value
        JLabel amount = new JLabel(String.format("$%.2f", expense.getExpenseAmount()));
        amount.setFont(amount.getFont().deriveFont(Font.PLAIN, 18)); 
        // Add components to the panel
        amountPanel.add(amountLabel);
        amountPanel.add(amount);

        /* split type panel */
        JPanel typePanel = new JPanel();
        typePanel.setLayout(new BoxLayout(typePanel, BoxLayout.X_AXIS));
        typePanel.setAlignmentX(Component.LEFT_ALIGNMENT); // Align the panel itself to the left
        // type label
        JLabel typeLabel = new JLabel("Split Type:         ");
        typeLabel.setFont(typeLabel.getFont().deriveFont(Font.BOLD, 14)); 
        // type value
        JLabel type = new JLabel(expense.getSplitType());
        type.setFont(type.getFont().deriveFont(Font.PLAIN, 18)); 
        // Add components to the panel
        typePanel.add(typeLabel);
        typePanel.add(type);

        /* paid by label */
        JPanel paidByPanel = new JPanel();
        paidByPanel.setLayout(new BoxLayout(paidByPanel, BoxLayout.X_AXIS));
        paidByPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        //paidby Label
        JLabel paidByLabel = new JLabel("Paid By:             ");
        paidByLabel.setFont(paidByLabel.getFont().deriveFont(Font.BOLD, 14)); 
        //paidby name
        JLabel paidbyName = new JLabel(expense.getUserPaid());
        paidbyName.setFont(type.getFont().deriveFont(Font.PLAIN, 18)); 
        // Add components to the panel
        paidByPanel.add(paidByLabel);
        paidByPanel.add(paidbyName);

        amountPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        typePanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        paidByPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));



        // Add both panels to the main panel
        this.add(amountPanel);
        this.add(typePanel);
        this.add(paidByPanel);
    }
}

class ParticipantPanel extends JPanel {

    public ParticipantPanel(Expense expense) {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // map for user and what they owe
        Map<String, Float> userOwedAmounts = expense.getUserOwedAmounts();
        Color darkGreen = new Color(0, 100, 0); // Dark Green

        // panel for the 
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 20, 15, 30); // Padding between cells

        /* payer info */
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        //label
        JLabel payerLabel = new JLabel(expense.getUserPaid());
        payerLabel.setFont(payerLabel.getFont().deriveFont(Font.BOLD, 20));
        detailsPanel.add(payerLabel, gbc);
        //amount
        JLabel payerAmountLabel = new JLabel(String.format("$%.2f", 0.00));
        payerAmountLabel.setFont(payerAmountLabel.getFont().deriveFont(Font.PLAIN, 20));
        payerAmountLabel.setForeground(darkGreen); // Payer owes $0
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.EAST;
        detailsPanel.add(payerAmountLabel, gbc);

        int row = 1;
        /*for the number of people that owe */
        for (Map.Entry<String, Float> entry : userOwedAmounts.entrySet()) {
            String userName = entry.getKey();
            Float amountOwed = entry.getValue();

            // label
            JLabel userLabel = new JLabel(userName);
            userLabel.setFont(userLabel.getFont().deriveFont(Font.BOLD, 20));
            gbc.gridx = 0;
            gbc.gridy = row;
            gbc.anchor = GridBagConstraints.WEST;
            detailsPanel.add(userLabel, gbc);

            // amount
            amountOwed *= -1; // negative to show that they owe
            JLabel amountLabel = new JLabel(String.format("$%.2f", (amountOwed)));
            amountLabel.setFont(amountLabel.getFont().deriveFont(Font.PLAIN, 20));
            gbc.gridx = 1;
            gbc.gridy = row;
            gbc.anchor = GridBagConstraints.EAST;

            // color 
            if (amountOwed >= 0) {
                amountLabel.setForeground(darkGreen); 
             } else {
                amountLabel.setForeground(Color.RED); // negative amount actual
            }

            detailsPanel.add(amountLabel, gbc);
            row++;
        }

        // Add detailsPanel to this panel
        this.add(detailsPanel);
    }
}

public class eDetailsPage extends JFrame{

    int frameWidth = 500;   // Frame width
    int frameHeight = 650;  // Frame height

    private ExpenseNamePanel expenseNamePanel;
    private PayPanel payPanel;
    private ParticipantPanel participantPanel;

    public eDetailsPage(Expense expense) {

        setTitle("Expense");
        setSize(frameWidth, frameHeight);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on the screen

        expenseNamePanel = new ExpenseNamePanel(expense);
        payPanel = new PayPanel(expense);
        participantPanel = new ParticipantPanel(expense);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JButton payButton = new JButton("Pay Expense");
       // submitButton.addActionListener(e -> createExpense());

        mainPanel.add(Box.createVerticalStrut(70)); // Adds space vertically
        mainPanel.add(expenseNamePanel);
        mainPanel.add(Box.createVerticalStrut(30)); // Adds space vertically
        mainPanel.add(payPanel);
        mainPanel.add(participantPanel);
        mainPanel.add(payButton); 



        // Ensure panels are centered in the main frame
        expenseNamePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        payPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        participantPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        payButton.setAlignmentX(Component.CENTER_ALIGNMENT);


        add(mainPanel);
        setVisible(true);

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

       eDetailsPage dp = new eDetailsPage(expense);
       dp.createAndShowWindow();;

    }
    
}
