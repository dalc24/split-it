package user;
import java.util.*;


public class FauxDB {
    // Stores users with their names as keys
    private static final Map<String, User> userDB = new HashMap<>();
    
    // Stores all expenses
    private static final List<Expense> expenseDB = new ArrayList<>();

    // Add a user to the faux database
    public static void addUser(User user) {
        if (user != null && user.getName() != null) {
            userDB.put(user.getName(), user);
        }
    }

    // Retrieve a user from the faux database by name
    public static User getUserByName(String name) {
        return userDB.get(name);
    }

    // Add an expense to the faux database
    public static void addExpense(Expense expense) {
        if (expense != null) {
            expenseDB.add(expense);
        }
    }

    // Static method to check if a user exists in the database
    public static boolean userExists(String name) {
        return userDB.containsKey(name);
    }

    // Retrieve all expenses from the faux database
    public static List<Expense> getAllExpenses() {
        return new ArrayList<>(expenseDB);
    }
}
