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
import MainApplication.MainApplication;

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
    private User user;
    ExpensesPage ePage;

    ExpensesBox(User user, ExpensesPage ePage) {
        this.ePage = ePage;
        this.user = user;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Vertical box layout
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding
        this.setBackground(Color.LIGHT_GRAY); // Background color for the entire container
        loadExpenses();
    }

    private void loadExpenses() {
        this.removeAll(); // Clear any existing components before loading new ones

        List<String> expensesOwed = user.getExpensesName();

        for (String expenseName : expensesOwed) {
            Expense expense = user.getExpenseByName(expenseName);

            if (expense.isExpensePaid()) {
                continue; // Skip paid expenses
            }

            JPanel expensePanel = createExpensePanel(expense, ePage);
            this.add(expensePanel);
            this.add(Box.createVerticalStrut(5)); // Add small spacing between boxes
        }

        this.revalidate(); // Revalidate the panel after adding components
        this.repaint(); // Repaint to reflect changes
    }

    public JPanel createExpensePanel(Expense expense, ExpensesPage ePage) {
        JPanel expensePanel = new JPanel();
        expensePanel.setBackground(Color.WHITE);
        expensePanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        expensePanel.setLayout(new BoxLayout(expensePanel, BoxLayout.X_AXIS));
        expensePanel.setPreferredSize(new Dimension(460, 40));
        expensePanel.setMaximumSize(new Dimension(460, 40));

        JLabel expenseLabel = new JLabel(expense.getPurpose());
        expenseLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        JLabel amountLabel = new JLabel("$" + expense.getExpenseAmount());
        amountLabel.setFont(new Font("Arial", Font.BOLD, 16));
        amountLabel.setForeground(Color.RED);

        expensePanel.add(Box.createRigidArea(new Dimension(10, 0)));
        expensePanel.add(expenseLabel);
        expensePanel.add(Box.createHorizontalGlue());
        expensePanel.add(amountLabel);
        expensePanel.add(Box.createRigidArea(new Dimension(10, 0)));

        expensePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                eDetailsPage page = new eDetailsPage(expense);
                page.createAndShowWindow();
            }
        });

        return expensePanel;
    }

    // Call this method to refresh the box when an expense is added or paid
    public void refreshExpenses() {
        loadExpenses();
    }
}

public class ExpensesPage extends JPanel implements ExpenseAddedListener{
    private Header header;
    private ExpensesBox expensesBox;
    private User user; // Store the user for accessing their expenses

    int frameWidth = 500;   // Frame width
    int frameHeight = 800;  // Frame height

    public ExpensesPage(User user, MainApplication app) {
        this.user = user; // Initialize the user
        this.setLayout(new BorderLayout()); // Set layout for the main panel

        header = new Header(user, this); // Pass the current ExpensesPage instance
        this.add(header, BorderLayout.NORTH);

        expensesBox = new ExpensesBox(user, this);
        JScrollPane scrollPane = new JScrollPane(expensesBox);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        this.add(scrollPane, BorderLayout.CENTER);

        JButton button = new JButton("Home");
        button.addActionListener(e -> app.showPage("UserHomePage"));
        this.add(button, BorderLayout.SOUTH);

       // this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(frameWidth, frameHeight);
    }
    @Override
    public void onExpenseAdded(Expense expense) {
        System.out.println();
        System.out.println("Expense added: " + expense.getPurpose()); // Debugging
        expensesBox.refreshExpenses();; // Refresh the expense list when a new expense is added
    }

    public void createAndShowWindow() {
        this.setVisible(true);
    }
}

