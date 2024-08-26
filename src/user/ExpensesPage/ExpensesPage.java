package user.ExpensesPage;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.List;
import java.util.ArrayList;


import user.User;
import user.Expense;
import user.addExpensePage.addExpensePage;
import user.eDetailsPage.eDetailsPage;

class Header extends JPanel {
    Color backgroundColor = new Color(255, 255, 255); // Set background color
    private ExpensesPage expensesPage; // Reference to ExpensesPage

    Header(User user, ExpensesPage expensesPage) {
        this.expensesPage = expensesPage;

        this.setPreferredSize(new Dimension(500, 80));
        this.setBackground(backgroundColor);
        this.setLayout(new BorderLayout());

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.setOpaque(false);

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        titlePanel.setOpaque(false);

        JLabel nameText = new JLabel("Expenses");
        nameText.setFont(nameText.getFont().deriveFont(Font.BOLD, 32));
        titlePanel.add(nameText);

        /* button settings */
        JPanel buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(150,10));
        buttonPanel.setOpaque(false);

        JButton addExpenseButton = new JButton("Add Expense");
        buttonPanel.add(addExpenseButton, BorderLayout.SOUTH);

        addExpenseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create and show AddExpensePage
                addExpensePage page = new addExpensePage(user, expensesPage);
                page.createAndShowWindow();
            }
        });

        bottomPanel.add(buttonPanel, BorderLayout.EAST);

        bottomPanel.add(titlePanel, BorderLayout.WEST);
        this.add(bottomPanel, BorderLayout.SOUTH);
    }
}

class ExpensesBox extends JPanel {

    private List<Expense> expenses;
    private User user;

    ExpensesBox(User user) {
        this.user = user;
        // Set layout and properties for this panel
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Vertical box layout
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding
        this.setBackground(Color.LIGHT_GRAY); // Background color for the entire container
        loadExpenses();

    }

    private void loadExpenses() {
        // Get names of users owed money
        List<String> expensesOwed = user.getExpensesName();

        // Iterate over expense name and create a box
        for (String expenseName : expensesOwed) {

            Expense expense = user.getExpenseByName(expenseName);

            // Create a panel for each owed person
            JPanel expensePanel = new JPanel();
            expensePanel.setBackground(Color.WHITE); // Background color for individual entry
            expensePanel.setBorder(BorderFactory.createLineBorder(Color.GRAY)); // Border for individual entry

            // Use BoxLayout to ensure the panel respects the preferred size
            expensePanel.setLayout(new BoxLayout(expensePanel, BoxLayout.X_AXIS));
            expensePanel.setPreferredSize(new Dimension(460, 40)); // Adjust height here
            expensePanel.setMaximumSize(new Dimension(460, 40)); // Ensures height doesn't exceed this

            // Get the amount owed to this person
            
            float amountOwed = expense.getExpenseAmount();

            // Create a label for the name
            JLabel expenseLabel = new JLabel(expenseName);
            expenseLabel.setFont(new Font("Arial", Font.PLAIN, 16)); // Adjust font size if needed

            // Create a label for the amount owed
            JLabel amountLabel = new JLabel("$" + amountOwed);
            amountLabel.setFont(new Font("Arial", Font.BOLD, 16));
            if (expense.isExpensePaid()) {
                amountLabel.setForeground(Color.GREEN); // Make the amount red to stand out
            } else {
                amountLabel.setForeground(Color.RED); // Make the amount red to stand out
            }

            // Add the name and amount labels to the person panel
            expensePanel.add(Box.createRigidArea(new Dimension(10, 0))); // Add padding to the left
            expensePanel.add(expenseLabel);
            expensePanel.add(Box.createHorizontalGlue()); // Pushes the amount label to the right
            expensePanel.add(amountLabel);
            expensePanel.add(Box.createRigidArea(new Dimension(10, 0))); // Add padding to the right

            // Add a MouseListener to make the panel clickable
            expensePanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Show details in a popup when the panel is clicked
                    eDetailsPage page = new eDetailsPage(expense);
                    page.createAndShowWindow();
                }
            });

            // Add the person panel to the main panel
            this.add(expensePanel);
            this.add(Box.createVerticalStrut(5)); // Add small spacing between boxes
        }
    }

    public void addExpense(Expense expense) {
        JPanel expensePanel = new JPanel();
        expensePanel.setBackground(Color.WHITE); // Background color for individual entry
        expensePanel.setBorder(BorderFactory.createLineBorder(Color.GRAY)); // Border for individual entry

        // Use BoxLayout to ensure the panel respects the preferred size
        expensePanel.setLayout(new BoxLayout(expensePanel, BoxLayout.X_AXIS));
        expensePanel.setPreferredSize(new Dimension(460, 40)); // Adjust height here
        expensePanel.setMaximumSize(new Dimension(460, 40)); // Ensures height doesn't exceed this

        // Get the amount owed to this person
        
        float amountOwed = expense.getExpenseAmount();

        // Create a label for the name
        JLabel expenseLabel = new JLabel(expense.getPurpose());
        expenseLabel.setFont(new Font("Arial", Font.PLAIN, 16)); // Adjust font size if needed

        // Create a label for the amount owed
        JLabel amountLabel = new JLabel("$" + amountOwed);
        amountLabel.setFont(new Font("Arial", Font.BOLD, 16));
        if (expense.isExpensePaid()) {
            amountLabel.setForeground(Color.GREEN); // Make the amount red to stand out
        } else {
            amountLabel.setForeground(Color.RED); // Make the amount red to stand out
        }

        // Add the name and amount labels to the person panel
        expensePanel.add(Box.createRigidArea(new Dimension(10, 0))); // Add padding to the left
        expensePanel.add(expenseLabel);
        expensePanel.add(Box.createHorizontalGlue()); // Pushes the amount label to the right
        expensePanel.add(amountLabel);
        expensePanel.add(Box.createRigidArea(new Dimension(10, 0))); // Add padding to the right

        // Add a MouseListener to make the panel clickable
        expensePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Show details in a popup when the panel is clicked
                eDetailsPage page = new eDetailsPage(expense);
                page.createAndShowWindow();
            }
        });

        // Add the person panel to the main panel
        this.add(expensePanel);
        this.add(Box.createVerticalStrut(5)); // Add small spacing between boxes

        this.revalidate();
        this.repaint();
    }

}

public class ExpensesPage extends JFrame implements ExpenseAddedListener{
    private Header header;
    private ExpensesBox expensesBox;
    private User user; // Store the user for accessing their expenses

    int frameWidth = 500;   // Frame width
    int frameHeight = 800;  // Frame height

    public ExpensesPage(User user) {
        this.user = user; // Initialize the user
        this.setLayout(new BorderLayout()); // Set layout for the main panel

        header = new Header(user, this); // Pass the current ExpensesPage instance
        this.add(header, BorderLayout.NORTH);

        expensesBox = new ExpensesBox(user);
        JScrollPane scrollPane = new JScrollPane(expensesBox);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        this.add(scrollPane, BorderLayout.CENTER);
/*
        JPanel buttonPanel = new JPanel();
        JButton button = new JButton("Click Me!");
        button.addActionListener(e -> JOptionPane.showMessageDialog(this, "Button Clicked!"));
        buttonPanel.add(button);
        this.add(buttonPanel, BorderLayout.SOUTH); */

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(frameWidth, frameHeight);
    }
    @Override
    public void onExpenseAdded(Expense newExpense) {
       expensesBox.addExpense(newExpense);

    }

    public void createAndShowWindow() {
        this.setVisible(true);
    }
}

